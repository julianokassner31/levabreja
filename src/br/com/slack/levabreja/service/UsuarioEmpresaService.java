package br.com.slack.levabreja.service;

import java.util.List;

import br.com.slack.levabreja.model.UsuarioEmpresa;

public interface UsuarioEmpresaService {

	public List<UsuarioEmpresa> listarTodosUsuariosEmpresa(int idEmpresa);
	
	public UsuarioEmpresa buscarUsuarioEmpresa(UsuarioEmpresa usuarioEmpresa);
	
	public UsuarioEmpresa verificarSeLoginExiste(String loginDigitado);

	public boolean salvar(UsuarioEmpresa novoUsuarioEmpresa);

	public boolean atualizar(UsuarioEmpresa usuarioSelecionado);

	public boolean apagar(int idUsuarioEmpresa);

	
}
