package br.com.slack.levabreja.repository;

import java.util.List;

import br.com.slack.levabreja.model.Mensalidade;

public interface MensalidadeRepository {
	
	public List<Mensalidade> listar();
	
	public boolean salvar(Mensalidade novaMensalidade);
	
	public boolean atualizar(Mensalidade mensalidadeSelecionada);
	
	public boolean apagar(int idMensalidade);

}
