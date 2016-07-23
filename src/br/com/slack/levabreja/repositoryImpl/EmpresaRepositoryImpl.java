package br.com.slack.levabreja.repositoryImpl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.slack.levabreja.model.Empresa;
import br.com.slack.levabreja.repository.EmpresaRepository;

@Repository
public class EmpresaRepositoryImpl implements Serializable, EmpresaRepository {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Empresa> listar() {
		List<Empresa> listaDeEmpresas = null;
		try{
			Query query = em.createQuery("SELECT e FROM Empresa e");
			listaDeEmpresas = query.getResultList();
		}catch(NoResultException e){
		}catch(Exception e){
				e.printStackTrace();
			}
		return listaDeEmpresas;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Empresa> listarEmpresasAtivadas() {
		List<Empresa> listaDeEmpresas = null;
		try{
			Query query = em.createQuery("SELECT e FROM Empresa e WHERE e.ativada = true");
			listaDeEmpresas = query.getResultList();
		}catch(NoResultException e){
		}catch(Exception e){
				e.printStackTrace();
			}
		return listaDeEmpresas;
	}
		
	@Override
	public Empresa buscar(int idEmpresa) {
		return em.find(Empresa.class, idEmpresa);
	}

	@Transactional
	@Override
	public boolean salvar(Empresa novaEmpresa) {
		try{
			em.persist(novaEmpresa);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	@Override
	public boolean atualizar(Empresa empresaSelecionada) {
		try{
			em.merge(empresaSelecionada);
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
			em.remove(em.getReference(Empresa.class,idEmpresa));
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
