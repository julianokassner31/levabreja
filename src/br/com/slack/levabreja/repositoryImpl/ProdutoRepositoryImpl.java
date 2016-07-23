package br.com.slack.levabreja.repositoryImpl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.slack.levabreja.model.Produto;
import br.com.slack.levabreja.repository.ProdutoRepository;

@Repository
public class ProdutoRepositoryImpl implements Serializable, ProdutoRepository {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;
	
	
	@Override
	public Produto buscarProdutoPorId(int idProduto) {
		Produto produto = null;
		try{
			Query query = em.createQuery("SELECT p FROM Produto p WHERE p.idProduto = ?1");
			query.setParameter(1, idProduto);
			produto = (Produto) query.getSingleResult();
		}catch(NoResultException e){
		}catch (Exception e) {
			e.printStackTrace();
		}
		return produto;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> listarProdutosPorSetor(int idSetor, int idEmpresa) {
		List <Produto> listaDeProdutosPorSetor = null;
		try{
			Query query = em.createQuery("SELECT p FROM Produto p WHERE p.setor.idSetor = ?1 and p.empresa.idEmpresa = ?2");
			query.setParameter(1, idSetor);
			query.setParameter(2, idEmpresa);
			listaDeProdutosPorSetor = query.getResultList();
		}catch(NoResultException e){
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listaDeProdutosPorSetor;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Produto> listarProdutosPorEmpresa(int idEmpresa) {
		List <Produto> listaDeProdutos = null;
		try{
			Query query = em.createQuery("SELECT p FROM Produto p WHERE p.empresa.idEmpresa = ?1");
			query.setParameter(1, idEmpresa);
			listaDeProdutos = query.getResultList();
		}catch(NoResultException e){
		}catch(Exception e){
			e.printStackTrace();

		}	
		return listaDeProdutos;
	}

	@Transactional
	@Override
	public boolean salvar(Produto novoProduto) {
		try{
			em.persist(novoProduto);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	@Override
	public boolean atualizar(Produto produtoSelecionado) {
		try{
			em.merge(produtoSelecionado);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	@Override
	public boolean apagar(int idProduto) {
		try{
			em.remove(em.getReference(Produto.class, idProduto));
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
