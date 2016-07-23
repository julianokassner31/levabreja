package br.com.slack.levabreja.repositoryImpl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.slack.levabreja.model.Cliente;
import br.com.slack.levabreja.repository.ClienteRepository;

@Repository
public class ClienteRepositoryImpl implements Serializable, ClienteRepository {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;
	
	@Override
	public Cliente procurarCliente(Cliente cliente){
		Cliente login = null;

		try{
			Query query = em.createQuery("SELECT u FROM Cliente u WHERE u.login = ?1 AND u.senha = ?2");
			query.setParameter(1, cliente.getLogin());
			query.setParameter(2, cliente.getSenha());
			login = (Cliente) query.getSingleResult();
					
		}catch(NoResultException e){
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return login;
	}
	
	@Override
	public Cliente verificarSeLoginExiste(String login) {
		Cliente loginDigitado = null;

			Query query = em.createQuery("SELECT u FROM Cliente u WHERE u.login  = ?1");
			query.setParameter(1, login);
			loginDigitado = (Cliente) query.getSingleResult();
			return loginDigitado;
	}
	
	@Override
	public Cliente verificarSeCpfExiste(String cpf) {
		Cliente cpfDigitado = null;

		Query query = em.createQuery("SELECT u FROM Cliente u WHERE u.cpf  = ?1");
		query.setParameter(1, cpf);
		cpfDigitado = (Cliente) query.getSingleResult();
		return cpfDigitado;

	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Cliente> listar() {
		List <Cliente> clientes = null;
		try{
			Query query = em.createQuery("SELECT c FROM Cliente c");
			clientes = query.getResultList();
		}catch(NoResultException e){
		}catch(Exception e){
			e.printStackTrace();
		}
		return clientes;
	}

	@Transactional
	@Override
	public boolean salvar(Cliente novoCliente) {
		try{
			em.persist(novoCliente);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	@Override
	public boolean atualizar(Cliente clienteSelecionado) {
		try{
			em.merge(clienteSelecionado);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	@Override
	public boolean apagar(int idCliente) {
		try{
			em.remove(em.getReference(Cliente.class, idCliente));
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
