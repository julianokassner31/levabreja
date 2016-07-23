package br.com.slack.levabreja.service;

import java.util.List;

import br.com.slack.levabreja.model.FormaDePagamento;

public interface FormaDePagamentoService {
	
	public List<FormaDePagamento> listarFormasDePagamentos(int idEmpresa);
	
	public boolean salvar(FormaDePagamento novaFormaDePagamento);
	
	public boolean atualizar(FormaDePagamento formaDePagamentoSelecinada);
	
	public boolean apagar(int idFormaDePagamento);
		


}
