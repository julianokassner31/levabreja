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
@Table(name="entregadores", schema="public")
public class Entregador implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_entregador")
	@Basic(optional=false)
	private int idEntregador;
	
	@Basic(optional=false)
	@Column(name="nome")
	private String nome;
	
	@Basic(optional=false)
	@Column(name="telefone")
	private String telefone;
	
	@Basic(optional=false)
	@Column(name="ativado")
	private boolean ativado;
	
	@ManyToOne
	@JoinColumn(name = "fk_id_empresa", referencedColumnName = "id_empresa")
	private Empresa empresa = new Empresa();

	public int getIdEntregador() {
		return idEntregador;
	}

	public void setIdEntregador(int idEntregador) {
		this.idEntregador = idEntregador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toLowerCase();
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public boolean isAtivado() {
		return ativado;
	}

	public void setAtivado(boolean ativado) {
		this.ativado = ativado;
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
		result = prime * result + (ativado ? 1231 : 1237);
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + idEntregador;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
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
		Entregador other = (Entregador) obj;
		if (ativado != other.ativado)
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (idEntregador != other.idEntregador)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entregador [idEntregador=" + idEntregador + ", nome=" + nome + ", telefone=" + telefone + ", ativado="
				+ ativado + ", empresa=" + empresa + "]";
	}
	
	
}
