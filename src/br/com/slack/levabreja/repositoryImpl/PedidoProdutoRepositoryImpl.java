package br.com.slack.levabreja.repositoryImpl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.slack.levabreja.model.PedidoProduto;
import br.com.slack.levabreja.repository.PedidoProdutoRepository;

@Repository
public class PedidoProdutoRepositoryImpl implements Serializable, PedidoProdutoRepository {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<PedidoProduto> listar(int idPedido) {
		List<PedidoProduto> list = null;
		try{
			Query query = em.createQuery("SELECT p FROM PedidoProduto p WHERE p.pedido.idPedido = ?1");
			query.setParameter(1, idPedido);
			list = query.getResultList();
		}catch(NoResultException e){
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Transactional
	@Override
	public boolean salvar(PedidoProduto novoPedidoProduto) {
		try{
			em.persist(novoPedidoProduto);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	

}
