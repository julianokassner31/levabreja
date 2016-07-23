
package br.com.slack.levabreja.action;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.slack.levabreja.model.Cliente;
import br.com.slack.levabreja.model.UsuarioEmpresa;
import br.com.slack.levabreja.service.ClienteService;
import br.com.slack.levabreja.service.UsuarioEmpresaService;
import br.com.slack.levabreja.util.LoginUsuarioCliente;
import br.com.slack.levabreja.util.LoginUsuarioEmpresa;


@ManagedBean
@Controller
@Scope("view")
public class ActionLogin implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioEmpresaService usuarioService;

	@Autowired
	private ClienteService clienteService;

	private String tipoDoAcesso;
	private String login;
	private String senha;
	private Cliente cliente;
	private LoginUsuarioCliente loginCliente = null;
	private UsuarioEmpresa usuarioEmpresa;
	private LoginUsuarioEmpresa loginUsuarioEmpresa = null;
	
	@PostConstruct
	public void inicio(){
		usuarioEmpresa = new UsuarioEmpresa();
		cliente = new Cliente();
		loginUsuarioEmpresa = new LoginUsuarioEmpresa();
		loginCliente = new LoginUsuarioCliente();
		pegarIPCliente();
	}
	
	public String pegarIPCliente() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        System.out.println(request.getRemoteAddr());
        return request.getRemoteAddr();
    }
	
	public void autenticar(){
		if(!tipoDoAcesso.trim().equals("") || !login.trim().equals("") || !senha.trim().equals("")){

			try{

				switch (tipoDoAcesso){

				case "Empresa":
					usuarioEmpresa.setLogin(login);
					usuarioEmpresa.setSenha(senha);
					autenticarUsuarioEmpresa();
					break;
				case "Cliente":
					cliente.setLogin(login);
					cliente.setSenha(senha);
					autenticarCliente();
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	private void autenticarUsuarioEmpresa() throws IOException{
		UsuarioEmpresa usuarioEmpresa = usuarioService.buscarUsuarioEmpresa(this.usuarioEmpresa);
		if(usuarioEmpresa != null && usuarioEmpresa.getEmpresa().isAtivada() == true){
			loginUsuarioEmpresa.criaSession(usuarioEmpresa);
			
		}else if(usuarioEmpresa != null && usuarioEmpresa.getEmpresa().isAtivada() == false){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR, "Empresa encontra-se desativada!", null));
		}else{
			erroLoginSenha();
		}
	}

	private void autenticarCliente() throws IOException{
		Cliente cliente = clienteService.procurarCliente(this.cliente);
		if(cliente != null){
			loginCliente.criaSession(cliente);
		}else{
			erroLoginSenha();
		}
	}

	public UsuarioEmpresa getUsuarioEmpresa() {
		return usuarioEmpresa;
	}

	public void setUsuarioEmpresa(UsuarioEmpresa usuarioEmpresa) {
		this.usuarioEmpresa = usuarioEmpresa;
	}

	public String getTipoDoAcesso() {
		return tipoDoAcesso;
	}

	public void setTipoDoAcesso(String tipoDoAcesso) {
		this.tipoDoAcesso = tipoDoAcesso;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LoginUsuarioCliente getLoginCliente() {
		return loginCliente;
	}

	public void setLoginCliente(LoginUsuarioCliente loginCliente) {
		this.loginCliente = loginCliente;
	}

	public LoginUsuarioEmpresa getLoginUsuarioEmpresa() {
		return loginUsuarioEmpresa;
	}

	public void setLoginEmpresa(LoginUsuarioEmpresa loginUsuarioEmpresa) {
		this.loginUsuarioEmpresa = loginUsuarioEmpresa;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public void erroLoginSenha(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario ou senha inválido"));
	}

}
