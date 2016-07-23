package br.com.slack.levabreja.serviceImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.slack.levabreja.model.Pedido;
import br.com.slack.levabreja.repository.PedidoRepository;
import br.com.slack.levabreja.service.PedidoService;

@Service
public class PedidoServiceImpl implements Serializable, PedidoService{

	private static final long serialVersionUID = 1L;

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Override
	public List<Pedido> listarPedidosPorStatusEmpresa(int idEmpresa, String status) {
		return pedidoRepository.listarPedidosPorStatusEmpresa(idEmpresa, status);
	}
	
	@Override
	public List<Pedido> listarPedidosPorStatusCliente(int idCliente, String status) {
		return pedidoRepository.listarPedidosPorStatusCliente(idCliente, status);
	}
	
	@Override
	public List<Pedido> listarPedidosSaiuEntregador(String nomeEntregador, String status) {
		return pedidoRepository.listarPedidosSaiuEntregador(nomeEntregador, status);
	}
	
	@Override
	public List<Pedido> listarTodosPedidosDaEmpresa(int idEmpresa) {
		return pedidoRepository.listarTodosPedidosDaEmpresa(idEmpresa);
	}
	
	@Override
	public List<Pedido> listarTodosPedidosDoCliente(int idCliente) {
		return pedidoRepository.listarTodosPedidosDoCliente(idCliente);
	}
	
	public boolean salvar(Pedido novoPedido){
		return pedidoRepository.salvar(novoPedido);
	}
	
	public boolean atualizar(Pedido pedidoSelecionado){
		return pedidoRepository.atualizar(pedidoSelecionado);
	}

}
