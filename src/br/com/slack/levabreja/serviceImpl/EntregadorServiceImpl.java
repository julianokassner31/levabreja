package br.com.slack.levabreja.serviceImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.slack.levabreja.model.Entregador;
import br.com.slack.levabreja.repository.EntregadorRepository;
import br.com.slack.levabreja.service.EntregadorService;

@Service
public class EntregadorServiceImpl implements Serializable, EntregadorService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private EntregadorRepository entregadorRepository;
	
	@Override
	public List<Entregador> listar(int idEmpresa) {
		return entregadorRepository.listar(idEmpresa);
	}
	@Override
	public List<Entregador> listarEntregadorAtivo(int idEmpresa){
		return entregadorRepository.listarEntregadorAtivo(idEmpresa);
	}

	@Override
	public boolean salvar(Entregador novoEntregador) {
		return entregadorRepository.salvar(novoEntregador);
	}

	@Override
	public boolean atualizar(Entregador entregadorSelecionado) {
		return entregadorRepository.atualizar(entregadorSelecionado);
	}

	@Override
	public boolean apagar(int idEntregador) {
		return entregadorRepository.apagar(idEntregador);
	}

		
}
