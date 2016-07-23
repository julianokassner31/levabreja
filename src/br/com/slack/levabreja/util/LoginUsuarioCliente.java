package br.com.slack.levabreja.util;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.slack.levabreja.model.Cliente;

public class LoginUsuarioCliente implements Serializable{

	private static final long serialVersionUID = 1L;

	public void criaSession(Cliente login) throws IOException {
			FacesContext sessaoId = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) sessaoId.getExternalContext().getSession(false);
			session.setAttribute("login", login);
			System.out.println("Usúario "+usuarioLogadoNoSistema().getNome()+" entrou do sistema!");
			FacesContext.getCurrentInstance().getExternalContext().redirect("cliente/fazped.xhtml");
	}

	public Cliente usuarioLogadoNoSistema() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false); 
		return (Cliente) session.getAttribute("login"); 
	}  

	public void logout() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();  
		HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
		System.out.println("Usúario "+usuarioLogadoNoSistema().getNome()+" saiu do sistema!");
		session.invalidate();
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
	}
}
