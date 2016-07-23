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
@Table(name="pedidos_produtos")
public class PedidoProduto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_pedidos_produtos")
	private int idPedidoProduto;

	@Column(name="nome_produto")
	@Basic(optional = false)
	private String nomeProduto;

	@Column(name="valor_produto")
	@Basic(optional = false)
	private double valorProduto;

	@Column(name="sub_total")
	@Basic(optional = false)
	private double subTotal;

	@Column(name="quantidade")
	@Basic(optional = false)
	private int quantidade;

	@ManyToOne
	@JoinColumn(name="fk_id_pedido", referencedColumnName="id_pedido")
	private Pedido pedido = new Pedido();

	public int getIdPedidoProduto() {
		return idPedidoProduto;
	}

	public void setIdPedidoProduto(int idPedidoProduto) {
		this.idPedidoProduto = idPedidoProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public double getValorProduto() {
		return valorProduto;
	}

	public void setValorProduto(double valorProduto) {
		this.valorProduto = valorProduto;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idPedidoProduto;
		result = prime * result + ((nomeProduto == null) ? 0 : nomeProduto.hashCode());
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
		result = prime * result + quantidade;
		long temp;
		temp = Double.doubleToLongBits(subTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(valorProduto);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		PedidoProduto other = (PedidoProduto) obj;
		if (idPedidoProduto != other.idPedidoProduto)
			return false;
		if (nomeProduto == null) {
			if (other.nomeProduto != null)
				return false;
		} else if (!nomeProduto.equals(other.nomeProduto))
			return false;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		if (quantidade != other.quantidade)
			return false;
		if (Double.doubleToLongBits(subTotal) != Double.doubleToLongBits(other.subTotal))
			return false;
		if (Double.doubleToLongBits(valorProduto) != Double.doubleToLongBits(other.valorProduto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PedidoProduto [idPedidoProduto=" + idPedidoProduto + ", nomeProduto=" + nomeProduto + ", valorProduto="
				+ valorProduto + ", subTotal=" + subTotal + ", quantidade=" + quantidade + ", pedido=" + pedido + "]";
	}


}
