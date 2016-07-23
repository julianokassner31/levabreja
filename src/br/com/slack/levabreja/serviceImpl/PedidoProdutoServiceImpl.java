package br.com.slack.levabreja.serviceImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.slack.levabreja.model.PedidoProduto;
import br.com.slack.levabreja.repository.PedidoProdutoRepository;
import br.com.slack.levabreja.service.PedidoProdutoService;

@Service
public class PedidoProdutoServiceImpl implements Serializable, PedidoProdutoService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PedidoProdutoRepository pedidoProdutoRepository;
	
	@Override
	public List<PedidoProduto> listar(int idPedido) {
		return pedidoProdutoRepository.listar(idPedido);
	}

	@Override
	public boolean salvar(PedidoProduto novoPedidoProduto) {
		return pedidoProdutoRepository.salvar(novoPedidoProduto);
	}

}
