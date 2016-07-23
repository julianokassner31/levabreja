package br.com.slack.levabreja.action;

import java.io.IOException;
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

import br.com.slack.levabreja.model.Produto;
import br.com.slack.levabreja.model.Setor;
import br.com.slack.levabreja.model.UsuarioEmpresa;
import br.com.slack.levabreja.service.ProdutoService;
import br.com.slack.levabreja.service.SetorService;
import br.com.slack.levabreja.util.LoginUsuarioEmpresa;

@ManagedBean
@Controller
@Scope("view")
public class ActionProduto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private SetorService setorService;
	
	private LoginUsuarioEmpresa login = new LoginUsuarioEmpresa();
	private UsuarioEmpresa usuarioEmpresaLogado = new UsuarioEmpresa();
	private Produto novoProduto;
	private Produto produtoSelecionado;
	private List<Produto> listaDeProdutos = new ArrayList<Produto>();
	private List<Setor> listaDeSetores = new ArrayList<Setor>();
	private int idSetor;
	private int quantidadeProdutos = 0;	
	
	@PostConstruct
	public void inicio(){
		
		usuarioEmpresaLogado = login.usuarioLogadoNoSistema();
		
		if (usuarioEmpresaLogado != null){
			
			produtoSelecionado = new Produto();
			novoProduto = new Produto();
			idSetor = 0;
			listaDeSetores = listarSetores();
			listaDeProdutos = listarTodosProdutos();
		
		}else{
		
			try {
			
				FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
			
			} catch (IOException e) {
			
				e.printStackTrace();
			}
		}
	}
	//faz a busca do setores por determinado id
	public void buscarProdutos(){
		
		if(idSetor != 0){
			
			listarProdutosPorSetor();
			
		}else{
			
			listarTodosProdutos();
			
		}
		
	}
	
	public List<Produto> listarProdutosPorSetor(){
		
		listaDeProdutos =  produtoService.listarProdutosPorSetor(idSetor, usuarioEmpresaLogado.getEmpresa().getIdEmpresa());
		
		//numero de produtos que ha nessa lista, pega a quantidade e mostra na view
		quantidadeProdutos = listaDeProdutos.size();
		
		return listaDeProdutos;
	}
	
	//lista todos os produtos cadastrado da empresa conforme o usuario logado
	public List<Produto> listarTodosProdutos(){
		
		listaDeProdutos =  produtoService.listarProdutosPorEmpresa(usuarioEmpresaLogado.getEmpresa().getIdEmpresa());
		
		quantidadeProdutos = listaDeProdutos.size();
		
		return listaDeProdutos;
	}
	
	//lista todos os setores cadastrados da empresa conforme o usuario logado
	public List<Setor> listarSetores(){
		return setorService.listar(usuarioEmpresaLogado.getEmpresa().getIdEmpresa());
	}
	
	public void salvar(){
		
		if(!novoProduto.getNome().trim().equals("") && !novoProduto.getSetor().equals("")
				&& novoProduto.getValor() != 0.0 ){
			
			novoProduto.setDataCadastro(new Date());
			
			novoProduto.setEmpresa(usuarioEmpresaLogado.getEmpresa());
			
			produtoService.salvar(novoProduto);
			
			//adiciona o novo produto na lista
			listaDeProdutos.add(novoProduto);
			
			quantidadeProdutos = listaDeProdutos.size();
			
			novoProduto = new Produto();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_INFO,"Cadastro realizado com sucesso!",null));
	
		}else{
			
			//instancia um novo objeto
			novoProduto = new Produto();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"Não foi possível realizar o cadastro!",null));
		}
	}
	
	public void atualizar(){
		
		if(!produtoSelecionado.getNome().trim().equals("") && produtoSelecionado.getValor() != 0.0){
			
			//atualiza o produto selecionado
			produtoService.atualizar(produtoSelecionado);
					
			//instacia um novo obejto, limpando o outro com erro
			produtoSelecionado = new Produto();
		
		}else{
			
			//instacia um novo obejto, limpando o outro com erro
			produtoSelecionado = new Produto();
			
			//traz a lista novamete para nao deixa-la modificada caso ocorra um erro no if
			listaDeProdutos = listarTodosProdutos();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"Não foi possível realizar o cadastro!",null));
			
		}
	}
	
	public void apagar(){
		
		if(produtoService.apagar(produtoSelecionado.getIdProduto())){
			
			listaDeProdutos.remove(produtoSelecionado);
			
			quantidadeProdutos = listaDeProdutos.size();
			
			produtoSelecionado = new Produto();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_INFO,"Cadastro excluído com sucesso!",null));
		
		}else{
			
			//instacia um novo obejto, limpando o outro com erro
			produtoSelecionado = new Produto();
			
			//traz a lista novamete para nao deixa-la modificada caso ocorra um erro no if
			listaDeProdutos = listarTodosProdutos();
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
					(FacesMessage.SEVERITY_ERROR,"Não foi possível excluir o cadastro!",null));
		}
	}
	
	
	//geters e seters
	public Produto getNovoProduto() {
		return novoProduto;
	}

	public void setNovoProduto(Produto novoProduto) {
		this.novoProduto = novoProduto;
	}

	public List<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}

	public void setListaDeProdutos(List<Produto> listaDeProdutos) {
		this.listaDeProdutos = listaDeProdutos;
	}

	public Produto getProdutoSelecionado() {
		return produtoSelecionado;
	}

	public void setProdutoSelecionado(Produto produtoSelecionado) {
		this.produtoSelecionado = produtoSelecionado;
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

	public List<Setor> getListaDeSetores() {
		return listaDeSetores;
	}

	public void setListaDeSetores(List<Setor> listaDeSetores) {
		this.listaDeSetores = listaDeSetores;
	}

	public int getIdSetor() {
		return idSetor;
	}

	public void setIdSetor(int idSetor) {
		this.idSetor = idSetor;
	}

	public long getQuantidadeProdutos() {
		return quantidadeProdutos;
	}

	public void setQuantidadeProdutos(int quantidadeProdutos) {
		this.quantidadeProdutos = quantidadeProdutos;
	}

}
