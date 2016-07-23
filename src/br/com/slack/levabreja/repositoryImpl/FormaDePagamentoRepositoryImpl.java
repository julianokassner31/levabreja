package br.com.slack.levabreja.repositoryImpl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.slack.levabreja.model.FormaDePagamento;
import br.com.slack.levabreja.repository.FormaDePagamentoRepository;

@Repository
public class FormaDePagamentoRepositoryImpl implements Serializable, FormaDePagamentoRepository {

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	protected EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FormaDePagamento> listarFormasDePagamentos(int idEmpresa) {
		List <FormaDePagamento> list = null;
		try{
			Query query = em.createQuery("SELECT f FROM FormaDePagamento f WHERE f.empresa.idEmpresa = :idEmpresa");
			query.setParameter("idEmpresa", idEmpresa);
			list = query.getResultList();
		}catch(NoResultException e){
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}

	@Transactional
	@Override
	public boolean salvar(FormaDePagamento formaDePagamento) {
		try{
			em.persist(formaDePagamento);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	@Override
	public boolean atualizar(FormaDePagamento formaDePagamentoSelecionada) {
		try{
			em.merge(formaDePagamentoSelecionada);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	@Override
	public boolean apagar(int idFormaDePagamento) {
		try{
			em.remove(em.getReference(FormaDePagamento.class, idFormaDePagamento));;
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
