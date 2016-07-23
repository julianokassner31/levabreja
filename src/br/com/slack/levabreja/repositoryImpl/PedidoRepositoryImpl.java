package br.com.slack.levabreja.repositoryImpl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.slack.levabreja.model.Pedido;
import br.com.slack.levabreja.repository.PedidoRepository;

@Repository
public class PedidoRepositoryImpl implements Serializable, PedidoRepository {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> listarPedidosPorStatusEmpresa(int idEmpresa, String status) {
		List<Pedido> list = null;
		try{
			Query query = em.createQuery("SELECT p FROM Pedido p WHERE p.empresa.idEmpresa = ?1 AND p.status = ?2 ORDER BY p.dataPedido");
			query.setParameter(1, idEmpresa);
			query.setParameter(2, status);
			list = query.getResultList();
		}catch(NoResultException e){
		}catch(Exception e){
			e.printStackTrace();
		};
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> listarPedidosPorStatusCliente(int idCliente, String status) {
		List<Pedido> list = null;
		try{
			Query query = em.createQuery("SELECT p FROM Pedido p WHERE p.cliente.idCliente = ?1 AND p.status = ?2");
			query.setParameter(1, idCliente);
			query.setParameter(2, status);
			list = query.getResultList();
		}catch(NoResultException e){
		}catch(Exception e){
			e.printStackTrace();
		};
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> listarPedidosSaiuEntregador(String nomeEntregador, String status) {
		List<Pedido> list = null;
		try{
			Query query = em.createQuery("SELECT p FROM Pedido p WHERE p.entregador = ?1 AND p.status = ?2");
			query.setParameter(1, nomeEntregador);
			query.setParameter(2, status);
			list = query.getResultList();
		}catch(NoResultException e){
		}catch(Exception e){
			e.printStackTrace();
		};
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> listarTodosPedidosDaEmpresa(int idEmpresa) {
		List<Pedido> list = null;
		try{
			Query query = em.createQuery("SELECT p FROM Pedido p WHERE p.empresa.idEmpresa = ?1");
			query.setParameter(1, idEmpresa);
			list = query.getResultList();
		}catch(NoResultException e){
		}catch(Exception e){
			e.printStackTrace();
		};
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pedido> listarTodosPedidosDoCliente(int idCliente) {
		List<Pedido> pedidos = null;
		try{
			Query query = em.createQuery("SELECT p FROM Pedido p WHERE p.cliente.idCliente = ?1");
			query.setParameter(1, idCliente);
			pedidos = query.getResultList();
		}catch(NoResultException e){
		}catch(Exception e){
			e.printStackTrace();
		}
		return pedidos;
	}
	
	@Transactional
	@Override
	public boolean salvar(Pedido novoPedido) {
		try{
			em.persist(novoPedido);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	@Override
	public boolean atualizar(Pedido pedidoSelecionado) {
		try{
			em.merge(pedidoSelecionado);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
