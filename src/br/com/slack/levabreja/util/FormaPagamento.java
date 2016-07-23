package br.com.slack.levabreja.util;

public enum FormaPagamento {
	
	CARTAO_CREDITO("Cartão de crédito"),
	CARTAO_DEBITO("Cartão de débito"),
	DINHEIRO("Dinheiro"),
	CHEQUE_AVISTA("Cheque á vista"),
	CHEQUE_PRE("Cheque pré 30 dias"),
	FIADO("Fiado");
	

	private String pagamento;
	
	FormaPagamento(String pagamento){
		this.pagamento = pagamento;
	}
	
	public String getPagamento(){
		return this.pagamento;
	}
}
