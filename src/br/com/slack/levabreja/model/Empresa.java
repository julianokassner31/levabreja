package br.com.slack.levabreja.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="empresas", schema="public")
public class Empresa implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional=false)
	@Column(name="id_empresa")
	private int idEmpresa;

	@Basic(optional=false)
	@Column(name="nome_fantasia")
	private String nomeFantasia;

	@Basic(optional=false)
	@Column(name="cnpj")
	private String cnpj;
	
	@Basic(optional=true)
	@Column(name="inscr_estadual")
	private String ie;

	@Basic(optional=false)
	@Column(name="telefone")
	private String telefone;

	@Basic(optional=false)
	@Column(name="email")
	private String email;

	@Basic(optional=false)
	@Column(name="rua")
	private String rua;

	@Basic(optional=false)
	@Column(name="numero")
	private String numero;
	
	@Column(name="bairro")
	@Basic(optional = false)
	private String bairro;
	
	@Column(name="complemento")
	@Basic(optional = false)
	private String complemento;

	@Basic(optional=false)
	@Column(name="data_cadastro")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Basic(optional=false)
	@Column(name="dia_pagamento")
	private int pagamento;
	
	@Basic(optional=false)
	@Column(name="ativada")
	private boolean ativada;
	
	@Basic(optional=false)
	@Column(name="taxa_entrega")
	private double taxaEntrega;

	@ManyToOne
	@JoinColumn(name="fk_id_mensalidade", referencedColumnName="id_mensalidade")
	private Mensalidade mensalidade = new Mensalidade();

	@OneToMany(mappedBy="empresa", targetEntity = UsuarioEmpresa.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<UsuarioEmpresa> usuarios;

	@OneToMany(mappedBy="empresa", targetEntity = Produto.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Produto> produtos;

	@OneToMany(mappedBy="empresa", targetEntity = Entregador.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Entregador> entregadores;

	@OneToMany(mappedBy="empresa", targetEntity = Setor.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Setor> setores;
	
	@OneToMany(mappedBy="empresa", targetEntity = FormaDePagamento.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<FormaDePagamento> formasPagamentos;
	
	public int getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia.toLowerCase();
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua.toLowerCase();
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro.toLowerCase();
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento.toLowerCase();
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public int getPagamento() {
		return pagamento;
	}

	public void setPagamento(int pagamento) {
		this.pagamento = pagamento;
	}

	public boolean isAtivada() {
		return ativada;
	}

	public void setAtivada(boolean ativada) {
		this.ativada = ativada;
	}

	public Mensalidade getMensalidade() {
		return mensalidade;
	}

	public void setMensalidade(Mensalidade mensalidade) {
		this.mensalidade = mensalidade;
	}

	public List<UsuarioEmpresa> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioEmpresa> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Entregador> getEntregadores() {
		return entregadores;
	}

	public void setEntregadores(List<Entregador> entregadores) {
		this.entregadores = entregadores;
	}

	public List<Setor> getSetores() {
		return setores;
	}

	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}

	
	public double getTaxaEntrega() {
		return taxaEntrega;
	}

	public void setTaxaEntrega(double taxaEntrega) {
		this.taxaEntrega = taxaEntrega;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativada ? 1231 : 1237);
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((complemento == null) ? 0 : complemento.hashCode());
		result = prime * result + ((dataCadastro == null) ? 0 : dataCadastro.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((entregadores == null) ? 0 : entregadores.hashCode());
		result = prime * result + idEmpresa;
		result = prime * result + ((ie == null) ? 0 : ie.hashCode());
		result = prime * result + ((mensalidade == null) ? 0 : mensalidade.hashCode());
		result = prime * result + ((nomeFantasia == null) ? 0 : nomeFantasia.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + pagamento;
		result = prime * result + ((produtos == null) ? 0 : produtos.hashCode());
		result = prime * result + ((rua == null) ? 0 : rua.hashCode());
		result = prime * result + ((setores == null) ? 0 : setores.hashCode());
		long temp;
		temp = Double.doubleToLongBits(taxaEntrega);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
		result = prime * result + ((usuarios == null) ? 0 : usuarios.hashCode());
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
		Empresa other = (Empresa) obj;
		if (ativada != other.ativada)
			return false;
		if (bairro == null) {
			if (other.bairro != null)
				return false;
		} else if (!bairro.equals(other.bairro))
			return false;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (dataCadastro == null) {
			if (other.dataCadastro != null)
				return false;
		} else if (!dataCadastro.equals(other.dataCadastro))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (entregadores == null) {
			if (other.entregadores != null)
				return false;
		} else if (!entregadores.equals(other.entregadores))
			return false;
		if (idEmpresa != other.idEmpresa)
			return false;
		if (ie == null) {
			if (other.ie != null)
				return false;
		} else if (!ie.equals(other.ie))
			return false;
		if (mensalidade == null) {
			if (other.mensalidade != null)
				return false;
		} else if (!mensalidade.equals(other.mensalidade))
			return false;
		if (nomeFantasia == null) {
			if (other.nomeFantasia != null)
				return false;
		} else if (!nomeFantasia.equals(other.nomeFantasia))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (pagamento != other.pagamento)
			return false;
		if (produtos == null) {
			if (other.produtos != null)
				return false;
		} else if (!produtos.equals(other.produtos))
			return false;
		if (rua == null) {
			if (other.rua != null)
				return false;
		} else if (!rua.equals(other.rua))
			return false;
		if (setores == null) {
			if (other.setores != null)
				return false;
		} else if (!setores.equals(other.setores))
			return false;
		if (Double.doubleToLongBits(taxaEntrega) != Double.doubleToLongBits(other.taxaEntrega))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		if (usuarios == null) {
			if (other.usuarios != null)
				return false;
		} else if (!usuarios.equals(other.usuarios))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Empresa [idEmpresa=" + idEmpresa + ", nomeFantasia=" + nomeFantasia + ", cnpj=" + cnpj + ", ie=" + ie
				+ ", telefone=" + telefone + ", email=" + email + ", rua=" + rua + ", numero=" + numero + ", bairro="
				+ bairro + ", complemento=" + complemento + ", dataCadastro=" + dataCadastro + ", pagamento="
				+ pagamento + ", ativada=" + ativada + ", taxaEntrega=" + taxaEntrega + ", mensalidade=" + mensalidade
				+ ", usuarios=" + usuarios + ", produtos=" + produtos + ", entregadores=" + entregadores + ", setores="
				+ setores + "]";
	}

	
}	
