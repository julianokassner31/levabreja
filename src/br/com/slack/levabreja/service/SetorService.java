package br.com.slack.levabreja.service;

import java.util.List;

import br.com.slack.levabreja.model.Setor;

public interface SetorService {

	public List<Setor> listar(int idEmpresa);
	
	public Setor listarSetorPorId(int idEmpresa, int idSetor);
	
	public boolean salvar(Setor novoSetor);
	
	public boolean atualizar(Setor setorSelecionado);
	
	public boolean apagar(int idSetorSelecionado);
}
