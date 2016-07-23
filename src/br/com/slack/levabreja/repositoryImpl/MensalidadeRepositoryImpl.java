package br.com.slack.levabreja.repositoryImpl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.slack.levabreja.model.Mensalidade;
import br.com.slack.levabreja.repository.MensalidadeRepository;

@Repository
public class MensalidadeRepositoryImpl implements Serializable, MensalidadeRepository {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	protected EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Mensalidade> listar() {
		List<Mensalidade> mensalidades = null;
		try{
			Query query = em.createQuery("SELECT m FROM Mensalidade m");
			mensalidades = query.getResultList();
		}catch(NoResultException e){
		}catch(Exception e){
			e.printStackTrace();
		}
		return mensalidades;
	}

	@Transactional
	@Override
	public boolean salvar(Mensalidade novaMensalidade) {
		try{
			em.persist(novaMensalidade);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}

	@Transactional
	@Override
	public boolean atualizar(Mensalidade mensalidadeSelecionada) {
		try{
			em.merge(mensalidadeSelecionada);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	@Override
	public boolean apagar(int idMensalidade) {
		try{
			em.remove(em.getReference(Mensalidade.class, idMensalidade));
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}

}
