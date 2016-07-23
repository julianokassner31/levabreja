package br.com.slack.levabreja.service;

import java.util.List;

import br.com.slack.levabreja.model.Empresa;

public interface EmpresaService {
	
	public List<Empresa> listar();
	
	public List<Empresa> listarEmpresasAtivadas();
	
	public Empresa buscar(int idEmpresa);
	
	public boolean salvar(Empresa novaEmpresa);
	
	public boolean atualizar(Empresa empresaSelecionada);
	
	public boolean apagar(int idEmpresa);
	
	
	

}
