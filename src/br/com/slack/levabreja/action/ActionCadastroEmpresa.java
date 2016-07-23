package br.com.slack.levabreja.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.slack.levabreja.model.Empresa;
import br.com.slack.levabreja.model.Mensalidade;
import br.com.slack.levabreja.model.UsuarioEmpresa;
import br.com.slack.levabreja.service.EmpresaService;
import br.com.slack.levabreja.service.MensalidadeService;
import br.com.slack.levabreja.service.UsuarioEmpresaService;
import br.com.slack.levabreja.util.DiasDoMes;


@ManagedBean
@Controller
@Scope("view")
public class ActionCadastroEmpresa implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private EmpresaService empresaService;
		
	@Autowired
	private UsuarioEmpresaService usuarioService;
	
	@Autowired
	private MensalidadeService mensalidadeService;
	
	private UsuarioEmpresa novoUsuarioEmpresa;
	private Empresa novaEmpresa;
	private Empresa empresaSelecionada;
	private List<Empresa> listaDeEmpresas = new ArrayList<>();
	private List<Mensalidade> listaDeMensalidades;
	private DiasDoMes[] diasDoMes = DiasDoMes.values();
	private String loginDigitado;
	private String login;
	
		
	@PostConstruct
	public void iniciar(){
		novaEmpresa = new Empresa();
		empresaSelecionada = new Empresa();
		novoUsuarioEmpresa = new UsuarioEmpresa();
		login = null;
		loginDigitado = null;
		listaDeEmpresas = listarEmpresas();
		listaDeMensalidades = listarMensalidades();		
	}
	
	public List<Mensalidade> listarMensalidades(){
		return mensalidadeService.listar();
	}
	public List<Empresa> listarEmpresas() {
		return empresaService.listar();
	}

	public void salvar(){
		novaEmpresa.setDataCadastro(new Date());
		novaEmpresa.setTaxaEntrega(0.0);
		if(login != null){
			try{
				empresaService.salvar(novaEmpresa);

				//Passa a nova empresa para o usuario
				salvarUsuarioEmpresa(novaEmpresa);

				listaDeEmpresas.add(novaEmpresa);

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Cadastro realizado com sucesso!",null ));

				//zera o form da empresa
				novaEmpresa = new Empresa();
				login = null;
				loginDigitado = null;
			}catch(Exception e){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_ERROR,"Não foi possível realizar o cadastro!",null ));
			}
	}else{
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
				(FacesMessage.SEVERITY_ERROR,"Login indísponivel!",null ));
		}
	}
	
	public void atualizar(){
		try{
			if(empresaService.atualizar(empresaSelecionada)){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Cadastro alterado com sucesso!",null));
				empresaSelecionada = new Empresa();
			}
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"Não foi possível alterar o cadastro!",null));
			empresaSelecionada = new Empresa();
		}
	}
	
	public void apagar(){
		try{
			if(empresaService.apagar(empresaSelecionada.getIdEmpresa())){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Cadastro excluído com sucesso!",null));
				listaDeEmpresas.remove(empresaSelecionada);
				empresaSelecionada = new Empresa();
			}
		}catch(Exception e){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"Não foi possível excluir o cadastro!",null));
					empresaSelecionada = new Empresa();	
		}
	}
	
	public void salvarUsuarioEmpresa(Empresa novaEmpresa){
		//pega nova empresa que ja foi salva no banco e add para o usuario
		novoUsuarioEmpresa.setEmpresa(novaEmpresa);
		novoUsuarioEmpresa.setLogin(login.toLowerCase());
		usuarioService.salvar(novoUsuarioEmpresa);
		novoUsuarioEmpresa = new UsuarioEmpresa();
	}
	
	public void verificarSeLoginExiste(){
		try{
			UsuarioEmpresa usuarioEmpresa = usuarioService.verificarSeLoginExiste(loginDigitado); 
				if (usuarioEmpresa != null){
					System.out.println("Login indisponivel "+loginDigitado);
					FacesContext.getCurrentInstance().addMessage
						(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Login indisponível!",null));
				}
		}catch(Exception e){		
			FacesContext.getCurrentInstance().addMessage
				(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Login disponível!",null));
			login = loginDigitado;
		}
	}
	
	public UsuarioEmpresa getNovoUsuarioEmpresa() {
		return novoUsuarioEmpresa;
	}

	public void setNovoUsuarioEmpresa(UsuarioEmpresa novoUsuarioEmpresa) {
		this.novoUsuarioEmpresa = novoUsuarioEmpresa;
	}

	public Empresa getNovaEmpresa() {
		return novaEmpresa;
	}

	public void setNovaEmpresa(Empresa novaEmpresa) {
		this.novaEmpresa = novaEmpresa;
	}

	public Empresa getEmpresaSelecionada() {
		return empresaSelecionada;
	}

	public void setEmpresaSelecionada(Empresa empresaSelecionada) {
		this.empresaSelecionada = empresaSelecionada;
	}

	public List<Empresa> getListaDeEmpresas() {
		return listaDeEmpresas;
	}

	public void setListaDeEmpresas(List<Empresa> listaDeEmpresas) {
		this.listaDeEmpresas = listaDeEmpresas;
	}

	public List<Mensalidade> getListaDeMensalidades() {
		return listaDeMensalidades;
	}

	public void setListaDeMensalidades(List<Mensalidade> listaDeMensalidades) {
		this.listaDeMensalidades = listaDeMensalidades;
	}

	public String getLoginDigitado() {
		return loginDigitado;
	}

	public void setLoginDigitado(String loginDigitado) {
		this.loginDigitado = loginDigitado;
	}

	public DiasDoMes[] getDiasDoMes() {
		return diasDoMes;
	}

	public void setDiasDoMes(DiasDoMes[] diasDoMes) {
		this.diasDoMes = diasDoMes;
	}

	
}
