package br.com.slack.levabreja.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.slack.levabreja.model.Cliente;
import br.com.slack.levabreja.model.Pedido;
import br.com.slack.levabreja.model.PedidoProduto;
import br.com.slack.levabreja.service.PedidoProdutoService;
import br.com.slack.levabreja.service.PedidoService;
import br.com.slack.levabreja.util.LoginUsuarioCliente;

@ManagedBean
@Controller
@Scope("view")
public class ActionPedidosRealizados implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired 
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoProdutoService pedidoProdutoService;
	
	private LoginUsuarioCliente login = new LoginUsuarioCliente();
	private Cliente clienteLogado = new Cliente();
	
	private List<Pedido> listaDePedidos = new ArrayList<Pedido>(); 
	private List<PedidoProduto> listaDeProdutos = new ArrayList<PedidoProduto>();
			
	private Pedido pedidoSelecionado;
	
	private int quantidadePedido;
	
	
	
	@PostConstruct
	public void inicio(){
		clienteLogado = login.usuarioLogadoNoSistema();
		if (clienteLogado != null){
			pedidoSelecionado = new Pedido();
			listaDePedidos = buscarPedidos();
			
			
		}else{
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Pedido> buscarPedidos(){
		listaDePedidos = pedidoService.listarTodosPedidosDoCliente(clienteLogado.getIdCliente());
		quantidadePedido = listaDePedidos.size();
		return listaDePedidos;
	}
	
	public void buscarProdutos(){
		System.out.print(pedidoSelecionado.getIdPedido());
		listaDeProdutos = pedidoProdutoService.listar(pedidoSelecionado.getIdPedido());
	}
	
	public void atualizar(){
		System.out.println(pedidoSelecionado.getStatus());
		
		switch (pedidoSelecionado.getStatus()){
		
		case "cancelado":
			if(pedidoSelecionado.getDataSaiu() == null && pedidoSelecionado.getDataFim() == null){
				pedidoService.atualizar(pedidoSelecionado);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Pedido cancelado!",null));
			}else{
				
				pedidoSelecionado = new Pedido();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Pedido já saiu para entrega!",null));
			}
			break;
			
			default:
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_INFO,"Opção inválida para o status do pedido!",null));
		}
	}
	
	
	public LoginUsuarioCliente getLogin() {
		return login;
	}

	public void setLogin(LoginUsuarioCliente login) {
		this.login = login;
	}

	public Cliente getClienteLogado() {
		return clienteLogado;
	}

	public void setClienteLogado(Cliente clienteLogado) {
		this.clienteLogado = clienteLogado;
	}

	
	public Pedido getPedidoSelecionado() {
		return pedidoSelecionado;
	}

	public void setPedidoSelecionado(Pedido pedidoSelecionado) {
		this.pedidoSelecionado = pedidoSelecionado;
	}

	public int getQuantidadePedido() {
		return quantidadePedido;
	}

	public void setQuantidadePedido(int quantidadePedido) {
		this.quantidadePedido = quantidadePedido;
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

	
}
