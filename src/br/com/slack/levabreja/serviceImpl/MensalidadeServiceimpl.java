package br.com.slack.levabreja.serviceImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.slack.levabreja.model.Mensalidade;
import br.com.slack.levabreja.repository.MensalidadeRepository;
import br.com.slack.levabreja.service.MensalidadeService;

@Service
public class MensalidadeServiceimpl implements Serializable, MensalidadeService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private MensalidadeRepository mensalidadeRepository;
	
	@Override
	public List<Mensalidade> listar() {
		return mensalidadeRepository.listar();
	}

	@Override
	public boolean salvar(Mensalidade novaMensalidade) {
		return mensalidadeRepository.salvar(novaMensalidade);
	}

	@Override
	public boolean atualizar(Mensalidade mensalidadeSelecionada) {
		return mensalidadeRepository.atualizar(mensalidadeSelecionada);
	}

	@Override
	public boolean apagar(int idMensalidade) {
		return mensalidadeRepository.apagar(idMensalidade);
	}

}
