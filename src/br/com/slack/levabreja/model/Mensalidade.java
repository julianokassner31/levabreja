package br.com.slack.levabreja.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="mensalidades")
public class Mensalidade implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_mensalidade")
	private int idMensalidade;
	
	@Column(name="nome")
	@Basic(optional = false)
	private String nome;
	
	@Column(name="valor")
	@Basic(optional = false)
	private Double valor;
	
	@Column(name="level")
	@Basic(optional = false)
	private Integer level;
	
	@OneToMany(mappedBy="mensalidade", targetEntity = Empresa.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Empresa> listaDeEmpresas;
	
	
	public int getIdMensalidade() {
		return idMensalidade;
	}

	public void setIdMensalidade(int idMensalidade) {
		this.idMensalidade = idMensalidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toLowerCase();
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	
	public List<Empresa> getListaDeEmpresas() {
		return listaDeEmpresas;
	}

	public void setListaDeEmpresas(List<Empresa> listaDeEmpresas) {
		this.listaDeEmpresas = listaDeEmpresas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idMensalidade;
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((listaDeEmpresas == null) ? 0 : listaDeEmpresas.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		Mensalidade other = (Mensalidade) obj;
		if (idMensalidade != other.idMensalidade)
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (listaDeEmpresas == null) {
			if (other.listaDeEmpresas != null)
				return false;
		} else if (!listaDeEmpresas.equals(other.listaDeEmpresas))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mensalidade [idMensalidade=" + idMensalidade + ", nome=" + nome + ", valor=" + valor + ", level="
				+ level + ", listaDeEmpresas=" + listaDeEmpresas + "]";
	}

}
