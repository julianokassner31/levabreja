package br.com.slack.levabreja.service;

import java.util.List;

import br.com.slack.levabreja.model.Produto;

public interface ProdutoService {
	
	public List<Produto> listarProdutosPorEmpresa(int idEmpresa);
	
	public Produto buscarProdutoPorId(int idProduto);
	
	public List<Produto> listarProdutosPorSetor(int idSetor, int idEmpresas);
	
	public boolean salvar(Produto novoProduto);
	
	public boolean atualizar(Produto produtoSelecionado);
	
	public boolean apagar(int idProdutoSelecionado);

	

	

}
