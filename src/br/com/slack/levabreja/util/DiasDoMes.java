package br.com.slack.levabreja.util;

public enum DiasDoMes {
	
	DIA05(05),
	DIA10(10),
	DIA15(15),
	DIA20(20),
	DIA25(25),
	DIA30(30);
	
	private int dia;
	
	DiasDoMes(int dia){
		this.dia = dia;
	}
	
	public int getDiaDoMes(){
		return this.dia;
	}

}
