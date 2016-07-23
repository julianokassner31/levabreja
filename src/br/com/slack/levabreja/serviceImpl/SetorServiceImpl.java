package br.com.slack.levabreja.serviceImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.slack.levabreja.model.Setor;
import br.com.slack.levabreja.repository.SetorRepository;
import br.com.slack.levabreja.service.SetorService;


@Service
public class SetorServiceImpl implements Serializable, SetorService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private SetorRepository setorRepository;
	
	@Override
	public List<Setor> listar(int idEmpresa) {
		return setorRepository.listar(idEmpresa);
	}
	
	@Override
	public Setor listarSetorPorId(int idEmpresa, int idSetor) {
		return setorRepository.listarSetorPorId(idEmpresa, idSetor);
	}

	@Override
	public boolean salvar(Setor novoSetor) {
		return setorRepository.salvar(novoSetor);
	}

	@Override
	public boolean atualizar(Setor setorSelecionado) {
		return setorRepository.atualizar(setorSelecionado);
	}

	@Override
	public boolean apagar(int idSetorSelecionado) {
		return setorRepository.apagar(idSetorSelecionado);
	}

}
