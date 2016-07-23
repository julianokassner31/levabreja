package br.com.slack.levabreja.repositoryImpl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.slack.levabreja.model.Entregador;
import br.com.slack.levabreja.repository.EntregadorRepository;

@Repository
public class EntregadorRepositoryImpl implements Serializable, EntregadorRepository{

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Entregador> listar(int idEmpresa) {
		List<Entregador> entregadores = null;
		try{
			Query query = em.createQuery("SELECT e FROM Entregador e WHERE e.empresa.idEmpresa = ?1");
			query.setParameter(1, idEmpresa);
			entregadores = query.getResultList();
		}catch(NoResultException e){
		}catch(Exception e){
			e.printStackTrace();
		}
		return entregadores;
	}
	
	@SuppressWarnings("unchecked")
	public List<Entregador> listarEntregadorAtivo(int idEmpresa) {
		List<Entregador> entregadores = null;
		try{
			Query query = em.createQuery("SELECT e FROM Entregador e WHERE e.empresa.idEmpresa = ?1 AND e.ativado = ?2");
			query.setParameter(1, idEmpresa);
			query.setParameter(2, true);
			entregadores = query.getResultList();
		}catch(NoResultException e){
		}catch(Exception e){
			e.printStackTrace();
		}
		return entregadores;
	}

	@Transactional
	@Override
	public boolean salvar(Entregador novoEntregador) {
		try{
			em.persist(novoEntregador);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	@Override
	public boolean atualizar(Entregador entregadorSelecionado) {
		try{
			em.merge(entregadorSelecionado);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	@Override
	public boolean apagar(int idEmpresa) {
		try{
			em.remove(em.getReference(Entregador.class, idEmpresa));
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
