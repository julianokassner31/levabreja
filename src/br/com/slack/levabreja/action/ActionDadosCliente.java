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
import br.com.slack.levabreja.service.ClienteService;
import br.com.slack.levabreja.service.PedidoService;
import br.com.slack.levabreja.util.LoginUsuarioCliente;

@ManagedBean
@Controller
@Scope("view")
public class ActionDadosCliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PedidoService pedidoService;
	
	private List<Pedido> listaDePedidosEmEspera = new ArrayList<Pedido>();
	private LoginUsuarioCliente login = new LoginUsuarioCliente();
	private Cliente clienteLogado = new Cliente();
	private Cliente dadosCliente = new Cliente();

	@PostConstruct
	public void inicio(){
		
		clienteLogado = login.usuarioLogadoNoSistema();
		
		if (clienteLogado != null){
			
			dadosCliente = buscarDadosCliente();
			listaDePedidosEmEspera = listarPedidosEmEspera();
			
		}else{
			
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public void atualizar(){
		if(listaDePedidosEmEspera.isEmpty()){
			
				clienteService.atualizar(dadosCliente);
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Cadastro alterado com sucesso!",null));
			
		}else{
			
			dadosCliente = buscarDadosCliente();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"Não foi possível alterar o cadastro! Existe entrega(s) em espera.",null));
		}
	}
	
	public void alterarSenha(){
		
		try{
			if(clienteService.atualizar(dadosCliente)){
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Senha alterada com sucesso! Faça logout e entre novamente.",null));
			}
		}catch(Exception e){
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"Não foi possível alterar a senha!",null));
		}
	}
	
	public Cliente buscarDadosCliente(){
		
		dadosCliente = clienteService.procurarCliente(clienteLogado);
		return dadosCliente;
		
	}
	
	public List<Pedido> listarPedidosEmEspera(){
		
		return pedidoService.listarPedidosPorStatusCliente(clienteLogado.getIdCliente(), "em espera");
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

	public List<Pedido> getListaDePedidosEmEspera() {
		return listaDePedidosEmEspera;
	}

	public void setListaDePedidosEmEspera(List<Pedido> listaDePedidosEmEspera) {
		this.listaDePedidosEmEspera = listaDePedidosEmEspera;
	}

	public Cliente getDadosCliente() {
		return dadosCliente;
	}

	public void setDadosCliente(Cliente dadosCliente) {
		this.dadosCliente = dadosCliente;
	}
}
