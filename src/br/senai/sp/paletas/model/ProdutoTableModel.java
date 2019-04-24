package br.senai.sp.paletas.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sp.paletas.modelo.Produto;

public class ProdutoTableModel  extends AbstractTableModel {
		// lsita de clientes que 'alimentará
		// a tabela

		List<Produto> produtos;
		// vetor com as Strings relativas as
		// colunas da tabela
		final String[] colunas = { "ID", "TIPO", "SABOR","PREÇO" };

		public ProdutoTableModel(List<Produto> lista) {
			// cria a lista interna através
			// da lista recebida no contrutor
			this.produtos = new ArrayList<Produto>(lista);

		}

		@Override
		public int getRowCount() {
			// o número de linhas da tabela será
			// o número de registros na lista clientes
			return produtos.size();
		}

		@Override
		public int getColumnCount() {
			// o numero de colunas será a
			// quantidade de Strings do vetor colunas
			return colunas.length;

		}

		@Override
		public Object getValueAt(int linha, int coluna) {
			
			Produto produto = produtos.get(linha);
			switch (coluna) {
			case 0:
	
				return produto.getId();
			case 1:
			
				return  produto.getTipo();
			case 2:
				
				return produto.getSabor();
			case 3: 
			
				DecimalFormat format = new DecimalFormat("#0.00");
				
				return format.format(produto.getPreco());
		
		
				
			default:
			
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
		public Produto getProduto(int indice){
			return produtos.get(indice);
		}
	}



