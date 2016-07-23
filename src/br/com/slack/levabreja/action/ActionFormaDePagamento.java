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

import br.com.slack.levabreja.model.FormaDePagamento;
import br.com.slack.levabreja.model.UsuarioEmpresa;
import br.com.slack.levabreja.service.FormaDePagamentoService;
import br.com.slack.levabreja.util.LoginUsuarioEmpresa;

@ManagedBean
@Controller
@Scope("view")
public class ActionFormaDePagamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private FormaDePagamentoService formaDePagamentoService; 
	
	public UsuarioEmpresa getUsuarioEmpresaLogado() {
		return usuarioEmpresaLogado;
	}

	public void setUsuarioEmpresaLogado(UsuarioEmpresa usuarioEmpresaLogado) {
		this.usuarioEmpresaLogado = usuarioEmpresaLogado;
	}

	private LoginUsuarioEmpresa login = new LoginUsuarioEmpresa();
	private UsuarioEmpresa usuarioEmpresaLogado = new UsuarioEmpresa();
	private List<FormaDePagamento> listaDeFormasDePagamentos;
	private FormaDePagamento novaFormaDePagamento;
	private FormaDePagamento formaDePagamentoSelecionada;
	
	@PostConstruct
	public void inicio(){
		usuarioEmpresaLogado = login.usuarioLogadoNoSistema();
		if (usuarioEmpresaLogado != null){
			listaDeFormasDePagamentos = listarFormasDePagamentos();
			novaFormaDePagamento = new FormaDePagamento();
		}else{
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<FormaDePagamento> listarFormasDePagamentos(){
		return formaDePagamentoService.listarFormasDePagamentos(usuarioEmpresaLogado.getEmpresa().getIdEmpresa());
	}
		
	public void salvar(){
		try{
			if(!novaFormaDePagamento.getDescricao().trim().equals("")){
				novaFormaDePagamento.setEmpresa(usuarioEmpresaLogado.getEmpresa());
				formaDePagamentoService.salvar(novaFormaDePagamento);
				listaDeFormasDePagamentos.add(novaFormaDePagamento);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						("Cadastro realizado com sucesso!"));
				novaFormaDePagamento = new FormaDePagamento();
			}
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"Não foi possível realizar o cadastro!",null));
			novaFormaDePagamento = new FormaDePagamento();
		}
	}
	
	public void atualizar(){
		try{
			if(formaDePagamentoService.atualizar(formaDePagamentoSelecionada)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						("Cadastro alterado com sucesso!"));
				formaDePagamentoSelecionada = new FormaDePagamento();
			}
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"Não foi possível alterar o cadastro!",null));
			formaDePagamentoSelecionada = new FormaDePagamento();
		}
	}


	public void apagar(){
		try{
			if(formaDePagamentoService.apagar(formaDePagamentoSelecionada.getIdFormaDePagamento())){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						("Cadastro excluído com sucesso!"));
				listaDeFormasDePagamentos.remove(formaDePagamentoSelecionada);
				formaDePagamentoSelecionada = new FormaDePagamento();
			}
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"Não foi possível excluir o cadastro!",null));
			formaDePagamentoSelecionada = new FormaDePagamento();
			}
	}

	public LoginUsuarioEmpresa getLogin() {
		return login;
	}

	public void setLogin(LoginUsuarioEmpresa login) {
		this.login = login;
	}

	public List<FormaDePagamento> getListaDeFormasDePagamentos() {
		return listaDeFormasDePagamentos;
	}

	public void setListaDeFormasDePagamentos(List<FormaDePagamento> listaDeFormasDePagamentos) {
		this.listaDeFormasDePagamentos = listaDeFormasDePagamentos;
	}

	public FormaDePagamento getNovaFormaDePagamento() {
		return novaFormaDePagamento;
	}

	public void setNovaFormaDePagamento(FormaDePagamento novaFormaDePagamento) {
		this.novaFormaDePagamento = novaFormaDePagamento;
	}

	public FormaDePagamento getFormaDePagamentoSelecionada() {
		return formaDePagamentoSelecionada;
	}

	public void setFormaDePagamentoSelecionada(FormaDePagamento formaDePagamentoSelecionada) {
		this.formaDePagamentoSelecionada = formaDePagamentoSelecionada;
	}
	
	
}
