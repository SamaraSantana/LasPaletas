package br.senai.sp.paletas.modelo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Pedido {
	@Id
	@GeneratedValue
	private int id;
	private Calendar dataPedido;
	@ManyToOne
	private Cliente cliente;
	@OneToMany
	private List<ItemDoPedido> itens = new ArrayList<ItemDoPedido>();
	private double total;
	private double preço;
	private int quantidade;
	

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreço() {
		return preço;
	}

	public void setPreço(double preço) {
		this.preço = preço;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(Calendar dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemDoPedido> getItens() {
		return itens;
	}

	public void setItens(List<ItemDoPedido> itens) {
		this.itens = itens;
	}

	public double getTotal() {
		total = 0;
		for(ItemDoPedido item : itens){
			total += item.getValorTotal();
		}
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	@Override
	public String toString() {
		String retorno = String.format("%-30s| %11s|%10s", cliente, new SimpleDateFormat("dd/MM/yyyy").format(dataPedido.getTime()),total);
		return retorno;
	}
}
