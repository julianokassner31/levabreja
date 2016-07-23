package br.com.slack.levabreja.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.slack.levabreja.model.Entregador;
import br.com.slack.levabreja.model.Pedido;
import br.com.slack.levabreja.model.PedidoProduto;
import br.com.slack.levabreja.model.UsuarioEmpresa;
import br.com.slack.levabreja.service.EntregadorService;
import br.com.slack.levabreja.service.PedidoProdutoService;
import br.com.slack.levabreja.service.PedidoService;
import br.com.slack.levabreja.util.LoginUsuarioEmpresa;
import br.com.slack.levabreja.util.StatusEntrega;

@ManagedBean
@Controller
@Scope("view")
public class ActionListaDePedidos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private EntregadorService entregadorService;
	
	@Autowired
	private PedidoProdutoService pedidoProdutoService;
	
	private List<Pedido> listaDePedidos = new ArrayList<Pedido>();
	private List<PedidoProduto> listaDeProdutos = new ArrayList<PedidoProduto>();
	private List<Entregador> listaDeEntregadores = new ArrayList<Entregador>();
	
	//variaveis referente ao login do cliente
	private LoginUsuarioEmpresa login = new LoginUsuarioEmpresa();
	private UsuarioEmpresa usuarioEmpresaLogado = new UsuarioEmpresa();
	
	//variaveis referente ao status do pedido
	private String status;
	private StatusEntrega [] listaDeStatus;
	
	private long quantidadePedidos = 0l;
	private Pedido pedidoSelecionado;
	private Pedido filtrarPedidoPorNome;
	
	@PostConstruct
	public void inicio(){
		usuarioEmpresaLogado = login.usuarioLogadoNoSistema();
		if(usuarioEmpresaLogado != null){
			listaDeEntregadores = listarEntregadores();
			listaDeStatus = StatusEntrega.values();
			status = "todos";
			listaDePedidos = listarTodosPedidos();
			pedidoSelecionado = new Pedido();
		}else{
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void verificarStatus(){
		this.status = status.toLowerCase();
		
		if(!this.status.equals("todos")){
			
			listarPedidosPorStatus();
			
		}else{
			
			listarTodosPedidos();
			
		}
	}
	
	public List<Pedido> listarPedidosPorStatus(){
		listaDePedidos = pedidoService.listarPedidosPorStatusEmpresa(usuarioEmpresaLogado.getEmpresa().getIdEmpresa(), status);
		setQuantidadePedidos(listaDePedidos.size());
		return listaDePedidos;
	}
	
	public List<Pedido> listarTodosPedidos(){
		listaDePedidos = pedidoService.listarTodosPedidosDaEmpresa(usuarioEmpresaLogado.getEmpresa().getIdEmpresa());
		setQuantidadePedidos(listaDePedidos.size());
		return listaDePedidos;
	}
	
	public List<PedidoProduto> buscarProdutos(){
		listaDeProdutos = pedidoProdutoService.listar(pedidoSelecionado.getIdPedido());
		
		
		return listaDeProdutos;
	}
	
	public List<Entregador> listarEntregadores(){
		return entregadorService.listarEntregadorAtivo(usuarioEmpresaLogado.getEmpresa().getIdEmpresa());
	}
	
	public void atualizar(){
		System.out.println(pedidoSelecionado.getStatus());
		
		switch (pedidoSelecionado.getStatus()){
		
		case "saiu para entrega":
			if(pedidoSelecionado.getDataSaiu() == null && pedidoSelecionado.getDataFim() == null){
				pedidoSelecionado.setDataSaiu(new Date());
				pedidoService.atualizar(pedidoSelecionado);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Pedido alterado com sucesso!",null));
			}else{
				
				pedidoSelecionado = new Pedido();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Pedido já saiu para entrega!",null));
			}
			break;
			
		case "entregue":
			if(pedidoSelecionado.getDataSaiu() != null && pedidoSelecionado.getDataFim() == null){
				pedidoSelecionado.setDataFim(new Date());
				pedidoService.atualizar(pedidoSelecionado);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Pedido entregue com sucesso!",null));
			}else{
				
				pedidoSelecionado = new Pedido();
				listaDePedidos = listarTodosPedidos();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Pedido já foi entregue!",null));
			}
			break;
			
		default:
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_INFO,"Opção inválida para o status do pedido!",null));
		}
	}

	public List<Pedido> getListaDePedidos() {
		return listaDePedidos;
	}

	public void setListaDePedidos(List<Pedido> listaDePedidos) {
		this.listaDePedidos = listaDePedidos;
	}

	public List<PedidoProduto> getListaDeProdutos() {
		return listaDeProdutos;
	}

	public void setListaDeProdutos(List<PedidoProduto> listaDeProdutos) {
		this.listaDeProdutos = listaDeProdutos;
	}

	public List<Entregador> getListaDeEntregadores() {
		return listaDeEntregadores;
	}

	public void setListaDeEntregadores(List<Entregador> listaDeEntregadores) {
		this.listaDeEntregadores = listaDeEntregadores;
	}

	public LoginUsuarioEmpresa getLogin() {
		return login;
	}

	public void setLogin(LoginUsuarioEmpresa login) {
		this.login = login;
	}

	public UsuarioEmpresa getUsuarioEmpresaLogado() {
		return usuarioEmpresaLogado;
	}

	public void setUsuarioEmpresaLogado(UsuarioEmpresa usuarioEmpresaLogado) {
		this.usuarioEmpresaLogado = usuarioEmpresaLogado;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public StatusEntrega[] getListaDeStatus() {
		return listaDeStatus;
	}

	public void setListaDeStatus(StatusEntrega[] listaDeStatus) {
		this.listaDeStatus = listaDeStatus;
	}

	public long getQuantidadePedidos() {
		return quantidadePedidos;
	}

	public void setQuantidadePedidos(long quantidadePedidos) {
		this.quantidadePedidos = quantidadePedidos;
	}

	public Pedido getPedidoSelecionado() {
		return pedidoSelecionado;
	}

	public void setPedidoSelecionado(Pedido pedidoSelecionado) {
		this.pedidoSelecionado = pedidoSelecionado;
	}

	public Pedido getFiltrarPedidoPorNome() {
		return filtrarPedidoPorNome;
	}

	public void setFiltrarPedidoPorNome(Pedido filtrarPedidoPorNome) {
		this.filtrarPedidoPorNome = filtrarPedidoPorNome;
	}
	
	
}
