package br.com.slack.levabreja.testes;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.slack.levabreja.util.FormaPagamento;

public class FormaPagamentoTest {

	private String formaPagto;
	
	//Nesse caso vai dar erro.
	@Test
	public void testFormaPagamento() {
		formaPagto = FormaPagamento.CARTAO_CREDITO.getPagamento() ; 
		assertEquals(formaPagto, FormaPagamento.CARTAO_DEBITO.getPagamento());
	}
	
	//Nesse caso vai estar correto
	@Test
	public void testFormaPagamento2() {
		formaPagto = FormaPagamento.CARTAO_CREDITO.getPagamento() ; 
		assertEquals(formaPagto, FormaPagamento.CARTAO_CREDITO.getPagamento());
	}

}