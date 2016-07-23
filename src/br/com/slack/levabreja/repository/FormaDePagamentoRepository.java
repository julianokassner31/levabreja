package br.com.slack.levabreja.repository;

import java.util.List;

import br.com.slack.levabreja.model.FormaDePagamento;

public interface FormaDePagamentoRepository {
	
	public List<FormaDePagamento> listarFormasDePagamentos(int idEmpresa);
	
	public boolean salvar(FormaDePagamento formaDePagamento);
	
	public boolean atualizar(FormaDePagamento formaDePagamentoSelecionada);
	
	public boolean apagar(int idFormaDePagamento);
	
	
}
