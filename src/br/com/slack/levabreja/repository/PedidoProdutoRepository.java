package br.com.slack.levabreja.repository;

import java.util.List;

import br.com.slack.levabreja.model.PedidoProduto;

public interface PedidoProdutoRepository {
	
	public List<PedidoProduto> listar(int idPedido);
	
	public boolean salvar(PedidoProduto novoPedidoProduto);
	
}
