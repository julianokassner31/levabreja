package br.com.slack.levabreja.serviceImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.slack.levabreja.model.Produto;
import br.com.slack.levabreja.repository.ProdutoRepository;
import br.com.slack.levabreja.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService, Serializable {

	private static final long serialVersionUID = 1L;

	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override
	public List<Produto> listarProdutosPorEmpresa(int idEmpresa) {
		return produtoRepository.listarProdutosPorEmpresa(idEmpresa);
	}
	
	@Override
	public List<Produto> listarProdutosPorSetor(int idSetor, int idEmpresa){
		return produtoRepository.listarProdutosPorSetor(idSetor, idEmpresa);
	}

	@Override
	public boolean salvar(Produto novoProduto) {
		return produtoRepository.salvar(novoProduto);
	}

	@Override
	public boolean atualizar(Produto produtoSelecionado) {
		return produtoRepository.atualizar(produtoSelecionado);
	}

	@Override
	public boolean apagar(int idProdutoSelecionado) {
		return produtoRepository.apagar(idProdutoSelecionado);
	}

	

	@Override
	public Produto buscarProdutoPorId(int idProduto) {
		return produtoRepository.buscarProdutoPorId(idProduto);
	}

}
