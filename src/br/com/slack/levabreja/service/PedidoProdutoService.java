package br.com.slack.levabreja.service;

import java.util.List;

import br.com.slack.levabreja.model.PedidoProduto;

public interface PedidoProdutoService {
	
	public List<PedidoProduto> listar(int idPedido);
	
	public boolean salvar(PedidoProduto novoPedidoProduto);

}
