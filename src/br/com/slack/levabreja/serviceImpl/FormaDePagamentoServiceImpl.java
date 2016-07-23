package br.com.slack.levabreja.serviceImpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.slack.levabreja.model.FormaDePagamento;
import br.com.slack.levabreja.repository.FormaDePagamentoRepository;
import br.com.slack.levabreja.service.FormaDePagamentoService;

@Service
public class FormaDePagamentoServiceImpl implements Serializable, FormaDePagamentoService {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private FormaDePagamentoRepository formaDePagamentoRepository;

	@Override
	public List<FormaDePagamento> listarFormasDePagamentos(int idEmpresa) {
		return formaDePagamentoRepository.listarFormasDePagamentos(idEmpresa);
	}

	@Override
	public boolean salvar(FormaDePagamento novaFormaDePagamento) {
		return formaDePagamentoRepository.salvar(novaFormaDePagamento);
	}

	@Override
	public boolean atualizar(FormaDePagamento formaDePagamentoSelecionada) {
		return formaDePagamentoRepository.atualizar(formaDePagamentoSelecionada);
	}

	@Override
	public boolean apagar(int idFormaDePagamento) {
		return formaDePagamentoRepository.apagar(idFormaDePagamento);
	}

}
