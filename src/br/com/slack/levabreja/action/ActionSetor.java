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

import br.com.slack.levabreja.model.Setor;
import br.com.slack.levabreja.model.UsuarioEmpresa;
import br.com.slack.levabreja.service.SetorService;
import br.com.slack.levabreja.util.LoginUsuarioEmpresa;

@ManagedBean
@Controller
@Scope("view")
public class ActionSetor implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SetorService setorService;
	
	private LoginUsuarioEmpresa login = new LoginUsuarioEmpresa();
	private UsuarioEmpresa usuarioEmpresaLogado = new UsuarioEmpresa();
	private Setor novoSetor;
	private Setor setorSelecionado;
	private List<Setor> listaDeSetores;
		
	@PostConstruct
	public void inicio(){
		usuarioEmpresaLogado = login.usuarioLogadoNoSistema();
		
		if (usuarioEmpresaLogado != null){
			
			novoSetor = new Setor();
			setorSelecionado = new Setor();
			listaDeSetores = listarSetores();
		}else{
			
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Setor> listarSetores(){
		return setorService.listar(usuarioEmpresaLogado.getEmpresa().getIdEmpresa());
	}
	
	public void salvar(){
		try{
			if(!novoSetor.getNome().trim().equals("")){
			
				novoSetor.setEmpresa(usuarioEmpresaLogado.getEmpresa());
				setorService.salvar(novoSetor);
				listaDeSetores.add(novoSetor);
				novoSetor = new Setor();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Cadastro realizado com sucesso!", null));
				
			}
			
		}catch(Exception e){
			novoSetor = new Setor();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"Não foi possível realizar o cadastro!",null));
		}
	}

	public void atualizar(){
		
			if(!setorSelecionado.getNome().trim().equals("") && !setorSelecionado.getNome().isEmpty()){
				setorService.atualizar(setorSelecionado);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Cadastro alterado com sucesso!",null));
			}else{
				this.setorSelecionado = new Setor();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"Não foi possível alterar o cadastro!",null));
			}
	}
	
	public void apagar(){
		try{
			if(setorService.apagar(setorSelecionado.getIdSetor())){
				listaDeSetores.remove(setorSelecionado);
				setorSelecionado = new Setor();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Cadastro excluído com sucesso!",null));
			}
		}catch(Exception e){
			setorSelecionado = new Setor();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"Não foi possível excluir o cadastro! Está em uso.",null));
		}
	}

	public Setor getNovoSetor() {
		return novoSetor;
	}

	public void setNovoSetor(Setor novoSetor) {
		this.novoSetor = novoSetor;
	}

	public Setor getSetorSelecionado() {
		return setorSelecionado;
	}

	public void setSetorSelecionado(Setor setorSelecionado) {
		this.setorSelecionado = setorSelecionado;
	}

	public List<Setor> getListaDeSetores() {
		return listaDeSetores;
	}

	public void setListaDeSetores(List<Setor> listaDeSetores) {
		this.listaDeSetores = listaDeSetores;
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

	
}
