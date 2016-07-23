package br.com.slack.levabreja.repository;

import java.util.List;

import br.com.slack.levabreja.model.Entregador;

public interface EntregadorRepository {
	
	public List<Entregador> listar(int idEmpresa);
	
	public List<Entregador> listarEntregadorAtivo(int idEmpresa);
	
	public boolean salvar(Entregador novoEntregador);
	
	public boolean atualizar(Entregador entregadorSelecionado);
	
	public boolean apagar(int idEntregador);

}
