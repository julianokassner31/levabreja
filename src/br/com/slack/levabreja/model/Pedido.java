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
@Table(name="pedidos")
public class Pedido implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private int idPedido;
	
	@Column(name="valor_total")
	@Basic(optional=false)
	private double valorTotal;
	
	@Column(name="forma_pagto")
	@Basic(optional=false)
	private String formaDePagamento;
	
	@Column(name="data_pedido")
	@Basic(optional=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPedido;
	
	@Column(name="data_saiu")
	@Basic(optional=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataSaiu;
	
	@Column(name="data_fim")
	@Basic(optional=true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFim;
	
	@Column(name="obs")
	@Basic(optional=true)
	private String obs;
	
	@Column(name="status")
	@Basic(optional=false)
	private String status;
	
	@Column(name="entregador")
	@Basic(optional=true)
	private String entregador;
	
	@ManyToOne
	@JoinColumn(name="fk_id_cliente", referencedColumnName="id_cliente")
	private Cliente cliente = new Cliente();
	
	@ManyToOne
	@JoinColumn(name="fk_id_empresa", referencedColumnName="id_empresa")
	private Empresa empresa = new Empresa();
	
	@OneToMany(mappedBy="pedido", targetEntity = PedidoProduto.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<PedidoProduto> pedidosProdutos;
	
	
	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(String formaDePagamento) {
		this.formaDePagamento = formaDePagamento.toLowerCase();
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs.toLowerCase();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status.toLowerCase();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getEntregador() {
		return entregador;
	}

	public void setEntregador(String entregador) {
		this.entregador = entregador.toLowerCase();
	}

	public List<PedidoProduto> getPedidosProdutos() {
		return pedidosProdutos;
	}

	public void setPedidosProdutos(List<PedidoProduto> pedidosProdutos) {
		this.pedidosProdutos = pedidosProdutos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((dataFim == null) ? 0 : dataFim.hashCode());
		result = prime * result + ((dataPedido == null) ? 0 : dataPedido.hashCode());
		result = prime * result + ((dataSaiu == null) ? 0 : dataSaiu.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((entregador == null) ? 0 : entregador.hashCode());
		result = prime * result + ((formaDePagamento == null) ? 0 : formaDePagamento.hashCode());
		result = prime * result + idPedido;
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result + ((pedidosProdutos == null) ? 0 : pedidosProdutos.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	public Date getDataSaiu() {
		return dataSaiu;
	}

	public void setDataSaiu(Date dataSaiu) {
		this.dataSaiu = dataSaiu;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (dataFim == null) {
			if (other.dataFim != null)
				return false;
		} else if (!dataFim.equals(other.dataFim))
			return false;
		if (dataPedido == null) {
			if (other.dataPedido != null)
				return false;
		} else if (!dataPedido.equals(other.dataPedido))
			return false;
		if (dataSaiu == null) {
			if (other.dataSaiu != null)
				return false;
		} else if (!dataSaiu.equals(other.dataSaiu))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (entregador == null) {
			if (other.entregador != null)
				return false;
		} else if (!entregador.equals(other.entregador))
			return false;
		if (formaDePagamento == null) {
			if (other.formaDePagamento != null)
				return false;
		} else if (!formaDePagamento.equals(other.formaDePagamento))
			return false;
		if (idPedido != other.idPedido)
			return false;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		if (pedidosProdutos == null) {
			if (other.pedidosProdutos != null)
				return false;
		} else if (!pedidosProdutos.equals(other.pedidosProdutos))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (Double.doubleToLongBits(valorTotal) != Double.doubleToLongBits(other.valorTotal))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", valorTotal=" + valorTotal + ", formaDePagamento=" + formaDePagamento
				+ ", dataPedido=" + dataPedido + ", dataSaiu=" + dataSaiu + ", dataFim=" + dataFim + ", obs=" + obs
				+ ", status=" + status + ", entregador=" + entregador + ", cliente=" + cliente + ", empresa=" + empresa
				+ ", pedidosProdutos=" + pedidosProdutos + "]";
	}

	
}
