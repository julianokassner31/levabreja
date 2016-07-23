package br.com.slack.levabreja.action;


import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.slack.levabreja.model.Empresa;
import br.com.slack.levabreja.model.UsuarioEmpresa;
import br.com.slack.levabreja.service.EmpresaService;
import br.com.slack.levabreja.util.LoginUsuarioEmpresa;

@ManagedBean
@Controller
@Scope("view")
public class ActionDadosEmpresa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EmpresaService empresaService;

	private LoginUsuarioEmpresa login = new LoginUsuarioEmpresa();
	private UsuarioEmpresa usuarioEmpresaLogado = new UsuarioEmpresa();

	private Empresa empresa = new Empresa();

	@PostConstruct
	public void inicio(){
		usuarioEmpresaLogado = login.usuarioLogadoNoSistema();
		if (usuarioEmpresaLogado != null){
			empresa = buscarEmpresa();
		}else{
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public  Empresa buscarEmpresa(){
		return empresaService.buscar(usuarioEmpresaLogado.getEmpresa().getIdEmpresa());
	}
	
	public void atualizar(){
		try{
			if(empresaService.atualizar(empresa)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Cadastro alterado com sucesso!",null));
			}
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_INFO,"Cadastro alterado com sucesso!",null));
		}
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

	public Empresa getEmpresa() {
		
		System.out.println(empresa.getIdEmpresa());                           
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
}
