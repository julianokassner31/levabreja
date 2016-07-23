package br.com.slack.levabreja.util;

public enum StatusEntrega {
	
	EM_ESPERA("Em espera"),
	SAIU_PARA_ENTREGA("Saiu para entrega"),
	ENTREGUE("Entregue"),
	CANCELADO("Cancelado");
	
	private String status;
	
	StatusEntrega(String status){
		this.status = status;
	}
	
	public String getStatus(){
		return this.status;
	}
}
