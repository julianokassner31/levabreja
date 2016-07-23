package br.com.slack.levabreja.service;

import java.util.List;

import br.com.slack.levabreja.model.Entregador;

public interface EntregadorService {

	public List<Entregador> listar(int idEmpresa);
	
	public List<Entregador> listarEntregadorAtivo(int idEmpresa);
	
	public boolean salvar(Entregador novoEntregador);
	
	public boolean atualizar(Entregador entregadorSelecionado);
	
	public boolean apagar(int idEntregador);

	
}
