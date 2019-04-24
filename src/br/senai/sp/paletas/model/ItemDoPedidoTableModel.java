package br.senai.sp.paletas.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sp.paletas.modelo.Cliente;
import br.senai.sp.paletas.modelo.ItemDoPedido;
import br.senai.sp.paletas.modelo.Pedido;

public class ItemDoPedidoTableModel extends AbstractTableModel {
	// lsita de clientes que 'alimentará
	// a tabela

	List<ItemDoPedido> itens;
	// vetor com as Strings relativas as
	// colunas da tabela
	final String[] colunas = { "QUANTIDADE", "SABOR", "PREÇO UNT", "TOTAL" };

	public ItemDoPedidoTableModel(List<ItemDoPedido> lista) {
		// cria a lista interna através
		// da lista recebida no contrutor
		this.itens = lista;

	}

	@Override
	public int getRowCount() {
		// o número de linhas da tabela será
		// o número de registros na lista clientes
		return itens.size();
	}

	@Override
	public int getColumnCount() {
		// o numero de colunas será a
		// quantidade de Strings do vetor colunas
		return colunas.length;

	}

	@Override
	public Object getValueAt(int linha, int coluna) {
	
		ItemDoPedido item = itens.get(linha);
		switch (coluna) {
		case 0:
			return item.getQuantidade();

		case 1:

			return item.getProduto().getSabor();
		case 2:
			DecimalFormat format = new DecimalFormat("#0.00");

			return format.format(item.getProduto().getPreco());

		case 3:
			DecimalFormat formatador = new DecimalFormat("#0.00");

			return formatador.format(item.getValorTotal());
			

		default:

			throw new ArrayIndexOutOfBoundsException("Indice invalido");

		}

	}

	public void add(ItemDoPedido item) {
		this.itens.add(item);
		fireTableRowsInserted(0, itens.size());
	}
	
	public void remove(ItemDoPedido item){
		this.itens.remove(item);
		fireTableRowsDeleted(0, itens.size());
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		switch (columnIndex) {
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return String.class;
		case 3:
			return String.class;
		default:
			throw new ArrayIndexOutOfBoundsException("Indice invalido");
		}
	}

	@Override
	public String getColumnName(int coluna) {
		return colunas[coluna];
	}

	public ItemDoPedido getPedido(int indice) {
		return itens.get(indice);
	}
}
