package br.com.slack.levabreja.service;

import java.util.List;

import br.com.slack.levabreja.model.Mensalidade;

public interface MensalidadeService {
	
	public List<Mensalidade> listar();
	
	public boolean salvar(Mensalidade novaMensalidade);
	
	public boolean atualizar(Mensalidade mensalidadeSelecionada);
	
	public boolean apagar(int idMensalidade);

}
