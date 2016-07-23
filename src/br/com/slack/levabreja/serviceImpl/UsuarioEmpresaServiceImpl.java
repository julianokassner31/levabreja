package br.com.slack.levabreja.serviceImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.slack.levabreja.model.UsuarioEmpresa;
import br.com.slack.levabreja.repository.UsuarioEmpresaRepository;
import br.com.slack.levabreja.service.UsuarioEmpresaService;

@Service
public class UsuarioEmpresaServiceImpl implements Serializable, UsuarioEmpresaService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UsuarioEmpresaRepository usuarioEmpresaRepositorio;
	
	@Override
	public List<UsuarioEmpresa> listarTodosUsuariosEmpresa(int idEmpresa) {
		return usuarioEmpresaRepositorio.listarTodosUsuariosEmpresa(idEmpresa);
	}
	
	@Override
	public UsuarioEmpresa buscarUsuarioEmpresa(UsuarioEmpresa usuarioEmpresa) {
		return usuarioEmpresaRepositorio.buscarUsuarioEmpresa(usuarioEmpresa);
	}
	
	@Override
	public UsuarioEmpresa verificarSeLoginExiste(String loginDigitado) {
		return usuarioEmpresaRepositorio.verificarSeLoginExiste(loginDigitado);
	}
	
	@Override
	public boolean salvar(UsuarioEmpresa novoUsuario) {
		return usuarioEmpresaRepositorio.salvar(novoUsuario);
	}

	@Override
	public boolean atualizar(UsuarioEmpresa usuarioSelecionado) {
		return usuarioEmpresaRepositorio.atualizar(usuarioSelecionado);
	}

	@Override
	public boolean apagar(int idUsuarioEmpresa) {
		return usuarioEmpresaRepositorio.apagar(idUsuarioEmpresa);
	}
}
