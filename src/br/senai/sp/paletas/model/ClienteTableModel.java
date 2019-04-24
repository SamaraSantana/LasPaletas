package br.senai.sp.paletas.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sp.paletas.modelo.Cliente;

public class ClienteTableModel extends AbstractTableModel {
	// lsita de clientes que 'alimentará
	// a tabela

	List<Cliente> clientes;
	// vetor com as Strings relativas as
	// colunas da tabela
	final String[] colunas = { "ID", "NOME", "E-MAIL" , "CPF"};

	public ClienteTableModel(List<Cliente> lista) {
		// cria a lista interna através
		// da lista recebida no contrutor
		this.clientes = new ArrayList<Cliente>(lista);

	}

	@Override
	public int getRowCount() {
		// o número de linhas da tabela será
		// o número de registros na lista clientes
		return clientes.size();
	}

	@Override
	public int getColumnCount() {
		// o numero de colunas será a
		// quantidade de Strings do vetor colunas
		return colunas.length;

	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		// recuperar o Cliente da lista clientes
		// pelo indice da linha
		Cliente cliente = clientes.get(linha);
		switch (coluna) {
		case 0:
			// se for coluna 0, retorna id do cliente
			return cliente.getId();
		case 1:
			// se for coluna 1, retorna nome do cliente
			return cliente.getNome();
		case 2:
			// se for coluna 2, retorna email do cliente
			return cliente.getEmail();
		case 3:
			// se for coluna 2, retorna email do cliente
			return cliente.getCpf();
			
		default:
			// se for qualquer outro índice de coluna
			// dispara um erro
			throw new ArrayIndexOutOfBoundsException("Indice invalido");

		}

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
	public Cliente getCliente(int indice){
		return clientes.get(indice);
	}
}
