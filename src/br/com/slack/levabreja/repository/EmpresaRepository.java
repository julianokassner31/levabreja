package br.com.slack.levabreja.repository;

import java.util.List;

import br.com.slack.levabreja.model.Empresa;

public interface EmpresaRepository {
	
	public List<Empresa> listar();
	
	public List<Empresa> listarEmpresasAtivadas();
	
	public Empresa buscar(int id);
	
	public boolean salvar(Empresa novaEmpresa);
	
	public boolean atualizar(Empresa empresaSelecionada);
	
	public boolean apagar(int idEmpresa);
	

}
