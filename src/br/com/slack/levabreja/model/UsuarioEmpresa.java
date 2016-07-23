package br.com.slack.levabreja.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="usuarios_empresas")
public class UsuarioEmpresa implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="id_usuario_emp")
	private int idUsuarioEmpresa;
	
	@Column(name="nome")
	@Basic(optional=false)
	private String nome;
	
	@Column(name="login")
	@Basic(optional=false)
	private String login;
	
	@Column(name="senha")
	@Basic(optional=false)
	private String senha;
	
	@Column(name="ultimo_acesso")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimoAcesso;
	
	@ManyToOne
	@JoinColumn(name="fk_id_empresa", referencedColumnName="id_empresa")
	private Empresa empresa = new Empresa();

	public int getIdUsuarioEmpresa() {
		return idUsuarioEmpresa;
	}

	public void setIdUsuarioEmpresa(int idUsuarioEmpresa) {
		this.idUsuarioEmpresa = idUsuarioEmpresa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toLowerCase();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login.toLowerCase();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha.toLowerCase();
	}

	public Date getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void setUltimoAcesso(Date ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
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
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + idUsuarioEmpresa;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((ultimoAcesso == null) ? 0 : ultimoAcesso.hashCode());
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
		UsuarioEmpresa other = (UsuarioEmpresa) obj;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (idUsuarioEmpresa != other.idUsuarioEmpresa)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		if (ultimoAcesso == null) {
			if (other.ultimoAcesso != null)
				return false;
		} else if (!ultimoAcesso.equals(other.ultimoAcesso))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsuarioEmpresa [idUsuarioEmpresa=" + idUsuarioEmpresa + ", nome=" + nome + ", login=" + login
				+ ", senha=" + senha + ", ultimoAcesso=" + ultimoAcesso + ", empresa=" + empresa + "]";
	}
	
	

}
