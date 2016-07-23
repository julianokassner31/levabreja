package br.com.slack.levabreja.repositoryImpl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.slack.levabreja.model.Setor;
import br.com.slack.levabreja.repository.SetorRepository;

@Repository
public class SetorRepositoryImpl implements Serializable, SetorRepository{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	protected EntityManager em;


	@SuppressWarnings("unchecked")
	@Override
	public List<Setor> listar(int idEmpresa) {
		List<Setor> listaDeSetores = null;
		try{
			Query query = em.createQuery("SELECT s FROM Setor s WHERE s.empresa.idEmpresa= :idEmpresa");
			query.setParameter("idEmpresa", idEmpresa);
			listaDeSetores = query.getResultList();
		}catch(NoResultException e){
		}catch(Exception e){
			e.printStackTrace();
		}
		return listaDeSetores;
	}
	
	@Override
	public Setor listarSetorPorId(int idEmpresa, int idSetor) {
		Setor setor = null;
		try{
			Query query = em.createQuery("SELECT s FROM Setor s WHERE s.empresa.idEmpresa= ?1 AND s.idSetor = ?2");
			query.setParameter(1, idEmpresa);
			query.setParameter(2, idSetor);
			setor = (Setor)query.getSingleResult();
		}catch(NoResultException e){
		}catch(Exception e){
			e.printStackTrace();
		}
		return setor;
	}


	@Transactional
	@Override
	public boolean salvar(Setor novoSetor) {
		try{
			em.persist(novoSetor);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}


	@Transactional
	@Override
	public boolean atualizar(Setor setorSelecionado) {
		try{
			em.merge(setorSelecionado);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	@Override
	public boolean apagar(int idSetor) {
		try{
			em.remove(em.getReference(Setor.class, idSetor));
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}


