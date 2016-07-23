package br.com.slack.levabreja.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.slack.levabreja.model.Cliente;
import br.com.slack.levabreja.service.ClienteService;

@ManagedBean
@Controller
@Scope("view")
public class ActionCadastroCliente implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private ClienteService clienteService;
	
	private Cliente novoCliente;
	private Cliente clienteSelecionado;
	private String login;
	private String loginDigitado;
	private String cpf;
	private String cpfDigitado;
	
	@PostConstruct
	public void inicio(){
		novoCliente = new Cliente();
		clienteSelecionado = new Cliente();
		login = null;
		loginDigitado = null;
		cpf = null;
		cpfDigitado = null;
	}
	
	public void salvar(){
		try{
			if(!login.trim().equals("") && !cpf.trim().equals("")){
				novoCliente.setLogin(login.toLowerCase());
				novoCliente.setCpf(cpf);
				novoCliente.setDataCadastro(new Date());
				clienteService.salvar(novoCliente);
				novoCliente = new Cliente();
				redirecionar(); 
			}
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"Não foi possível realizar o cadastro", null));
		}	
		
	}
	
	public void atualizar(){
		try{
			if(clienteService.atualizar(clienteSelecionado)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Cadastro alterado com sucesso!",null));
				clienteSelecionado = new Cliente();
			}
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"Não foi possível alterar o cadastro",null));
			clienteSelecionado = new Cliente();
			
		}
	}
	
	public void apagar(){
		try{
			if(clienteService.apagar(clienteSelecionado.getIdCliente())){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO, "Cadastro excluído com sucesso!",null));
			}
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR, "Não foi possível excluir o cadastro!",null));
		}
	}

	public void redirecionar(){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void verificarSeLoginExiste(){
		try{
			Cliente cliente = clienteService.verificarSeLoginExiste(loginDigitado); 
				if (cliente != null){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
							(FacesMessage.SEVERITY_INFO,"Login indisponível!",null));
				}
		}catch(Exception e){		
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_INFO,"Login disponível!",null));
			login = loginDigitado;
			System.out.println(loginDigitado);
			}
		}
	
	public void verificarSeCpfExiste(){
		try{
			Cliente cliente = clienteService.verificarSeCpfExiste(cpfDigitado); 
				if (cliente != null){
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
							(FacesMessage.SEVERITY_INFO,"CPF já cadastrado!",null));
				}
		}catch(Exception e){		
			cpf = cpfDigitado;
			System.out.println(cpfDigitado);
			}
		}
	
	public Cliente getNovoCliente() {
		return novoCliente;
	}

	public void setNovoCliente(Cliente novoCliente) {
		this.novoCliente = novoCliente;
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	public String getLoginDigitado() {
		return login;
	}

	public void setLoginDigitado(String loginDigitado) {
		this.loginDigitado = loginDigitado;
	}

	public String getCpfDigitado() {
		return cpfDigitado;
	}

	public void setCpfDigitado(String cpfDigitado) {
		this.cpfDigitado = cpfDigitado;
	}
	
}
