package br.com.slack.levabreja.repositoryImpl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.slack.levabreja.model.UsuarioEmpresa;
import br.com.slack.levabreja.repository.UsuarioEmpresaRepository;

@Repository
public class UsuarioEmpresaRepositoryImpl implements Serializable, UsuarioEmpresaRepository{

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<UsuarioEmpresa> listarTodosUsuariosEmpresa(int idEmpresa) {
		List <UsuarioEmpresa> usuarios = null;
		try{
			Query query = em.createQuery("SELECT u FROM UsuarioEmpresa u WHERE u.empresa.idEmpresa = :idEmpresa");
			query.setParameter("idEmpresa", idEmpresa);
			usuarios = query.getResultList();
		}catch(NoResultException e){
		}catch(Exception e){
			e.printStackTrace();
		}
		return usuarios;
	}
	
	@Override
	public UsuarioEmpresa buscarUsuarioEmpresa(UsuarioEmpresa usuarioEmpresa){
		UsuarioEmpresa login = null;

		try{
			Query query = em.createQuery("SELECT u FROM UsuarioEmpresa u WHERE u.login = :login AND u.senha = :senha");
			query.setParameter("login", usuarioEmpresa.getLogin());
			query.setParameter("senha", usuarioEmpresa.getSenha());
			login = (UsuarioEmpresa) query.getSingleResult();
					
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return login;
	}
	
	@Override
	public UsuarioEmpresa verificarSeLoginExiste(String loginDigitado) {
		UsuarioEmpresa login = null;
		Query query = em.createQuery("SELECT l FROM UsuarioEmpresa l WHERE l.login = ?1 ");
		query.setParameter(1, loginDigitado);
		login = (UsuarioEmpresa) query.getSingleResult();
		return login;
	}

	@Transactional
	@Override
	public boolean salvar(UsuarioEmpresa novoUsuarioEmpresa) {
		try{
			em.persist(novoUsuarioEmpresa);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}

	@Transactional
	@Override
	public boolean atualizar(UsuarioEmpresa usuarioSelecionado) {
		try{
			em.merge(usuarioSelecionado);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	@Override
	public boolean apagar(int idUsuarioEmpresa) {
		try{
			em.remove(em.getReference(UsuarioEmpresa.class, idUsuarioEmpresa));
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}

