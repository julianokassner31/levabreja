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

import br.com.slack.levabreja.model.Cliente;
import br.com.slack.levabreja.model.Empresa;
import br.com.slack.levabreja.model.FormaDePagamento;
import br.com.slack.levabreja.model.Pedido;
import br.com.slack.levabreja.model.PedidoProduto;
import br.com.slack.levabreja.model.Produto;
import br.com.slack.levabreja.model.Setor;
import br.com.slack.levabreja.service.EmpresaService;
import br.com.slack.levabreja.service.FormaDePagamentoService;
import br.com.slack.levabreja.service.PedidoProdutoService;
import br.com.slack.levabreja.service.PedidoService;
import br.com.slack.levabreja.service.ProdutoService;
import br.com.slack.levabreja.service.SetorService;
import br.com.slack.levabreja.util.LoginUsuarioCliente;
import br.com.slack.levabreja.util.StatusEntrega;

@ManagedBean
@Controller
@Scope("view")
public class ActionFazerPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private SetorService setorService;
			
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private FormaDePagamentoService formaDePagamentoService;
	
	@Autowired
	private PedidoProdutoService pedidoProdutoService;
	
	//Variaveis referentes ao login do usuario
	private LoginUsuarioCliente login = new LoginUsuarioCliente();
	private Cliente clienteLogado = new Cliente();
	
	//variaveis referentes a empresa e setor
	private Empresa empresaEscolhida = new Empresa();
	private Setor setorEscolhido = new Setor();
	
	//variaveis referentes as Listas de empresas e setores
	private boolean botaoEmpresaAtivada;
	private List<Empresa> listaDeEmpresas;
	private List<Setor> listaDeSetores;
	
	//variaveis referentes a produtos
	private Produto produtoEscolhido;
	private List<Produto> listaDeProdutos;
	private List<Produto> listaDeProdutosEscolhidos;
	private int codProduto = 0;
	double valorTotal = 0;
	double subTotal = 0;
	private boolean botaoAdicionarProduto;
	private int quantidade;
	private int totalDeItens = 0;
	
	//variaveis referentes a Pedidos
	private Pedido novoPedido;
	private List<FormaDePagamento> listaDeFormasDePagamento = new ArrayList<FormaDePagamento>();
	
	//variaveis referentes a Pedido Produto
	private PedidoProduto pedidoProduto;
	
	@PostConstruct
	public void inicio(){
		this.clienteLogado = login.usuarioLogadoNoSistema();
		if(clienteLogado != null){
			this.valorTotal = 0;
			this.subTotal = 0;
			this.quantidade = 1;
			this.totalDeItens = 0;
			this.novoPedido = new Pedido();
			this.pedidoProduto = new PedidoProduto();
			this.produtoEscolhido = new Produto();
			this.botaoAdicionarProduto = true;
			this.botaoEmpresaAtivada = false;
			this.listaDeProdutos = new ArrayList<Produto>();
			this.listaDeProdutosEscolhidos = new ArrayList<Produto>();
			this.listaDeSetores = new ArrayList<Setor>();
			this.listaDeEmpresas = listarEmpresas();
			Date datahj = new Date();
			System.out.print(datahj);
			
			
		}else{
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void listarProdutos(){
		listaDeProdutos = produtoService.listarProdutosPorSetor(
				this.setorEscolhido.getIdSetor(), this.empresaEscolhida.getIdEmpresa());
	}
	
	public void listarProdutosPorEmpresa(){
		System.out.println(empresaEscolhida.getNomeFantasia());
		listaDeProdutos = produtoService.listarProdutosPorEmpresa(this.empresaEscolhida.getIdEmpresa());
	}
	
	public void listarSetores(){
		listaDeSetores = setorService.listar(this.empresaEscolhida.getIdEmpresa());
	}
	
	public void listarFormasDePagamentos(){
		listaDeFormasDePagamento = formaDePagamentoService.listarFormasDePagamentos(this.empresaEscolhida.getIdEmpresa());
	}
	
	public List<Empresa> listarEmpresas(){
		 return empresaService.listarEmpresasAtivadas();	
		}
	
	public void adicionarProdutoNaLista(){
		produtoEscolhido = produtoService.buscarProdutoPorId(this.codProduto); 
		produtoEscolhido.setEmpresa(empresaService.buscar(this.empresaEscolhida.getIdEmpresa()));
		produtoEscolhido.setQuantidade(this.quantidade);
		totalDeItens += produtoEscolhido.getQuantidade();
		produtoEscolhido.setSubtotal(produtoEscolhido.getValor() * produtoEscolhido.getQuantidade() );
		valorTotal += produtoEscolhido.getValor() * produtoEscolhido.getQuantidade();
		listaDeProdutosEscolhidos.add(produtoEscolhido);
		produtoEscolhido = new Produto();
	}
	
	public void salvar(){
		novoPedido.setCliente(clienteLogado);
		novoPedido.setDataPedido(new Date());
		novoPedido.setEmpresa(empresaEscolhida);
		novoPedido.setStatus(StatusEntrega.EM_ESPERA.getStatus());
		novoPedido.setValorTotal(valorTotal);
		pedidoService.salvar(novoPedido);
		
		for (Produto produto : listaDeProdutosEscolhidos){
			pedidoProduto.setPedido(novoPedido);
			pedidoProduto.setNomeProduto(produto.getNome());
			pedidoProduto.setQuantidade(produto.getQuantidade());
			pedidoProduto.setValorProduto(produto.getValor());
			pedidoProduto.setSubTotal(produto.getSubtotal());
			
			try{
				pedidoProdutoService.salvar(pedidoProduto);
				pedidoProduto = new PedidoProduto();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Pedido realizado com sucesso!",null));
			}catch(Exception e){
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage
						(FacesMessage.SEVERITY_INFO,"Não foi possível realizar o pedido!",null));
			}
		}
		inicio();
	}
	
	public void atualizarProdutoNaLista(){
		//zera e refaz a contagem
		totalDeItens = 0;
		valorTotal = 0;
		
		for (Produto produto : listaDeProdutosEscolhidos){
			totalDeItens += produto.getQuantidade();
			valorTotal += produto.getValor() * produto.getQuantidade();
			produto.setSubtotal(0);
			produto.setSubtotal(produto.getValor() * produto.getQuantidade() );
		}
		produtoEscolhido = new Produto();
	}
	
	public void excluirProdutoDaLista(){
		//zera e refaz a contagem
		totalDeItens = 0;
		valorTotal = 0;
		
		listaDeProdutosEscolhidos.remove(produtoEscolhido);
			
		for (Produto produto : listaDeProdutosEscolhidos){
			totalDeItens += produto.getQuantidade();
			valorTotal += produto.getValor() * produto.getQuantidade();
		}
		produtoEscolhido = new Produto();
	}
	
	public Cliente getClienteLogado() {
		return clienteLogado;
	}

	public void setClienteLogado(Cliente clienteLogado) {
		this.clienteLogado = clienteLogado;
	}

	public LoginUsuarioCliente getLogin() {
		return login;
	}

	public void setLogin(LoginUsuarioCliente login) {
		this.login = login;
	}

	public Empresa getEmpresaEscolhida() {
		return empresaEscolhida;
	}

	public void setEmpresaEscolhida(Empresa empresaEscolhida) {
		this.empresaEscolhida = empresaEscolhida;
	}

	public List<Empresa> getListaDeEmpresas() {
		return listaDeEmpresas;
	}
	
	public void setListaDeEmpresas(List<Empresa> listaDeEmpresas) {
		this.listaDeEmpresas = listaDeEmpresas;
	}
	
	public Setor getSetorEscolhido() {
		return setorEscolhido;
	}

	public void setSetorEscolhido(Setor setorEscolhido) {
		this.setorEscolhido = setorEscolhido;
	}

	public List<Setor> getListaDeSetores() {
		return listaDeSetores;
	}
	
	public void setListaDeSetores(List<Setor> listaDeSetores) {
		this.listaDeSetores = listaDeSetores;
	}
	
	public List<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}
	
	public void setListaDeProdutos(List<Produto> listaDeProdutos) {
		this.listaDeProdutos = listaDeProdutos;
	}

	public void setProdutoEscolhido(Produto produtoEscolhido) {
		this.produtoEscolhido = produtoEscolhido;
	}
	
	public Produto getProdutoEscolhido() {
		return produtoEscolhido;
	}

	public List<Produto> getListaDeProdutosEscolhidos() {
		return listaDeProdutosEscolhidos;
	}

	public void setListaDeProdutosEscolhidos(List<Produto> listaDeProdutosEscolhidos) {
		this.listaDeProdutosEscolhidos = listaDeProdutosEscolhidos;
	}

	public int getCodProduto() {
		return codProduto;
	}

	public void setCodProduto(int codProduto) {
		this.codProduto = codProduto;
	}
	
	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Pedido getNovoPedido() {
		return novoPedido;
	}

	public void setNovoPedido(Pedido novoPedido) {
		this.novoPedido = novoPedido;
	}

	public List<FormaDePagamento> getListaDeFormasDePagamento() {
		return listaDeFormasDePagamento;
	}

	public void setListaDeFormasDePagamento(List<FormaDePagamento> listaDeFormasDePagamento) {
		this.listaDeFormasDePagamento = listaDeFormasDePagamento;
	}

	public void desativaBotaoEmpresa(){
		this.botaoEmpresaAtivada = true;
	}

	public boolean isBotaoEmpresaAtivada() {
		return botaoEmpresaAtivada;
	}

	public void setBotaoEmpresaAtivada(boolean botaoEmpresaAtivada) {
		this.botaoEmpresaAtivada = botaoEmpresaAtivada;
	}

	public void ativarBotaoAdicionarProduto(){
		this.botaoAdicionarProduto = false;
	}

	public boolean isBotaoAdicionarProduto() {
		return botaoAdicionarProduto;
	}

	public void setBotaoAdicionarProduto(boolean botaoAdicionarProduto) {
		this.botaoAdicionarProduto = botaoAdicionarProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public int getTotalDeItens() {
		return totalDeItens;
	}

	public void setTotalDeItens(int totalDeItens) {
		this.totalDeItens = totalDeItens;
	}
	
	
}
