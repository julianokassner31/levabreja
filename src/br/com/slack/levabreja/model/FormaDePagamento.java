package br.com.slack.levabreja.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="formas_de_pagamentos")
public class FormaDePagamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id 
	@Column(name="id_forma_pagamento")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idFormaDePagamento;
	
	@Column(name="descricao")
	@Basic(optional= false)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name="fk_id_empresa", referencedColumnName="id_empresa")
	private Empresa empresa = new Empresa();

	public int getIdFormaDePagamento() {
		return idFormaDePagamento;
	}

	public void setIdFormaDePagamento(int idFormaDePagamento) {
		this.idFormaDePagamento = idFormaDePagamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao.toLowerCase();
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + idFormaDePagamento;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormaDePagamento other = (FormaDePagamento) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (idFormaDePagamento != other.idFormaDePagamento)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FormaDePagamento [idFormaDePagamento=" + idFormaDePagamento + ", descricao=" + descricao + ", empresa="
				+ empresa + "]";
	}

	
}
