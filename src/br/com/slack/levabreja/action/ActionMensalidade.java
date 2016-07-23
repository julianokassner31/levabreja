package br.com.slack.levabreja.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.slack.levabreja.model.Mensalidade;
import br.com.slack.levabreja.service.MensalidadeService;

@ManagedBean
@Controller
@Scope("view")
public class ActionMensalidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private MensalidadeService mensalidadeService;
	
	private Mensalidade novaMensalidade;
	private Mensalidade mensalidadeSelecionada;
	private List<Mensalidade> listaDeMensalidades;
	
	@PostConstruct
	public void inicio(){
		novaMensalidade = new Mensalidade();
		listaDeMensalidades = listarMensalidades();
	}
	
	public List<Mensalidade> listarMensalidades(){
		try{
			return mensalidadeService.listar();
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_INFO,"Não há mensalidades cadastradas!",null));
			return null;
		}
	}
	
	public void salvar(){
		try{
			if(!novaMensalidade.getNome().trim().equals("") && novaMensalidade.getValor() > 0 && novaMensalidade.getLevel() > 0){
				mensalidadeService.salvar(novaMensalidade);
				listaDeMensalidades.add(novaMensalidade);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Cadastro realizado com sucesso!",null));
				novaMensalidade = new Mensalidade();
			}
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"Não foi possível realizar o cadastro!",null));
			novaMensalidade = new Mensalidade();
		}
	}
	
	public void atualizar(){
		try{
			if(mensalidadeService.atualizar(mensalidadeSelecionada)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Cadastro alterado com sucesso!",null));
				mensalidadeSelecionada = new Mensalidade();
			}
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"Não foi possível alterar o cadastro!",null));
			mensalidadeSelecionada = new Mensalidade();
		}
	}
	
	public void apagar(){
		try{
			if(mensalidadeService.apagar(mensalidadeSelecionada.getIdMensalidade())){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Cadastro excluído com sucesso!",null));
				listaDeMensalidades.remove(mensalidadeSelecionada);
				mensalidadeSelecionada = new Mensalidade();
			}
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"Não foi possível excluir o cadastro!",null));
					mensalidadeSelecionada = new Mensalidade();
		}
	}
	
	public Mensalidade getNovaMensalidade() {
		return novaMensalidade;
	}

	public void setNovaMensalidade(Mensalidade novaMensalidade) {
		this.novaMensalidade = novaMensalidade;
	}

	public Mensalidade getMensalidadeSelecionada() {
		return mensalidadeSelecionada;
	}

	public void setMensalidadeSelecionada(Mensalidade mensalidadeSelecionada) {
		this.mensalidadeSelecionada = mensalidadeSelecionada;
	}

	public List<Mensalidade> getListaDeMensalidades() {
		return listaDeMensalidades;
	}

	public void setListaDeMensalidades(List<Mensalidade> listaDeMensalidades) {
		this.listaDeMensalidades = listaDeMensalidades;
	}
	
}
