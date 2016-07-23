package br.com.slack.levabreja.action;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.slack.levabreja.model.UsuarioEmpresa;
import br.com.slack.levabreja.service.UsuarioEmpresaService;
import br.com.slack.levabreja.util.LoginUsuarioEmpresa;

@ManagedBean
@Controller
@Scope("view")
public class ActionUsuarioEmpresa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UsuarioEmpresaService usuarioService;
	
	private LoginUsuarioEmpresa login = new LoginUsuarioEmpresa();
	private UsuarioEmpresa usuarioEmpresaLogado = new UsuarioEmpresa();
	private List<UsuarioEmpresa> listaDeUsuarios;
	private UsuarioEmpresa novoUsuario;
	private UsuarioEmpresa usuarioSelecionado;
		
	@PostConstruct
	public void inicio(){
		usuarioEmpresaLogado = login.usuarioLogadoNoSistema();
		
		if(usuarioEmpresaLogado != null){
			
			novoUsuario = new UsuarioEmpresa();
			usuarioSelecionado = new UsuarioEmpresa();
			listaDeUsuarios = listarUsuarios();
		
		}else{
			
			try {
				
				FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
			
			}catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
	
	public List<UsuarioEmpresa> listarUsuarios(){
		return usuarioService.listarTodosUsuariosEmpresa(usuarioEmpresaLogado.getEmpresa().getIdEmpresa());
	}
	
	public void salvar(){
		
			if(!novoUsuario.getNome().trim().equals("") && !novoUsuario.getLogin().trim().equals("")
					&& !novoUsuario.getSenha().trim().equals("")){
				
				novoUsuario.setEmpresa(usuarioEmpresaLogado.getEmpresa());
				
				usuarioService.salvar(novoUsuario);
				
				listaDeUsuarios.add(novoUsuario);
				
				novoUsuario = new UsuarioEmpresa();
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Cadastro realizado com sucesso!",null));
				
			}else{
			
			novoUsuario = new UsuarioEmpresa();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_INFO,"Não foi possível realizar o cadastro!",null));
			
		}
	}
	
	public void atualizar() throws IOException, InterruptedException{
		
			if(!usuarioSelecionado.getNome().trim().equals("") && !usuarioSelecionado.getLogin().trim().equals("")
					&& !usuarioSelecionado.getSenha().trim().equals("")){
				
				usuarioService.atualizar(usuarioSelecionado);
										
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Cadastro alterado com sucesso!",null));
				
				if(usuarioEmpresaLogado.getLogin().equals(usuarioSelecionado.getLogin())){
					
					login.logout();
					
				}
				
				usuarioSelecionado = new UsuarioEmpresa();
			
			}else{
				
				listaDeUsuarios = listarUsuarios();
				usuarioSelecionado = new UsuarioEmpresa();
							
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"Não foi possível alterar o cadastro!",null));
		}
		
	}
	
	public void apagar(){
		
		if(!usuarioEmpresaLogado.getLogin().equals(usuarioSelecionado.getLogin())){
			
			usuarioService.apagar(usuarioSelecionado.getIdUsuarioEmpresa());
					
			listaDeUsuarios.remove(usuarioSelecionado);
					
			usuarioSelecionado = new UsuarioEmpresa();
					
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_INFO,"Cadastro excluído com sucesso!",null));
		}else{
			
			listaDeUsuarios = listarUsuarios();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"Usuário está em uso no momento!",null));
		}
	}

	public List<UsuarioEmpresa> getListaDeUsuarios() {
		return listaDeUsuarios;
	}

	public void setListaDeUsuarios(List<UsuarioEmpresa> listaDeUsuarios) {
		this.listaDeUsuarios = listaDeUsuarios;
	}

	public UsuarioEmpresa getNovoUsuario() {
		return novoUsuario;
	}

	public void setNovoUsuario(UsuarioEmpresa novoUsuario) {
		this.novoUsuario = novoUsuario;
	}

	public UsuarioEmpresa getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(UsuarioEmpresa usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public UsuarioEmpresa getUsuarioEmpresaLogado() {
		return usuarioEmpresaLogado;
	}

	public void setUsuarioEmpresaLogado(UsuarioEmpresa usuarioEmpresaLogado) {
		this.usuarioEmpresaLogado = usuarioEmpresaLogado;
	}

	public LoginUsuarioEmpresa getLogin() {
		return login;
	}

	public void setLogin(LoginUsuarioEmpresa login) {
		this.login = login;
	}

}
