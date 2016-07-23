package br.com.slack.levabreja.repository;

import java.util.List;

import br.com.slack.levabreja.model.Pedido;

public interface PedidoRepository {

	public List<Pedido> listarPedidosPorStatusEmpresa(int idEmpresa, String status);
	
	public List<Pedido> listarPedidosPorStatusCliente(int idCliente, String status);
	
	public List<Pedido> listarPedidosSaiuEntregador(String nomeEntregador, String status);
	
	public List<Pedido> listarTodosPedidosDaEmpresa(int idEmpresa);

	public List<Pedido> listarTodosPedidosDoCliente(int idCliente);

	boolean salvar(Pedido novoPedido);

	boolean atualizar(Pedido pedidoSelecionado);
	
}
