package br.com.slack.levabreja.repository;

import java.util.List;

import br.com.slack.levabreja.model.Produto;

public interface ProdutoRepository{
	
	public List<Produto> listarProdutosPorEmpresa(int idEmpresa);
	
	public Produto buscarProdutoPorId(int idProduto);
	
	public List<Produto> listarProdutosPorSetor(int idSetor, int idEmpresa);
	
	public boolean salvar(Produto novoProduto);
	
	public boolean atualizar(Produto produtoSelecionado);
	
	public boolean apagar(int idProdutoSelecionado);

	

}
