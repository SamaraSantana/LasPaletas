package br.senai.sp.paletas.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.senai.sp.paletas.modelo.Funcionario;

public class FuncionarioTableModel extends AbstractTableModel {
	// lsita de clientes que 'alimentará
	// a tabela

	List<Funcionario> funcionarios;
	// vetor com as Strings relativas as
	// colunas da tabela
	final String[] colunas = { "ID", "NOME", "CPF", "CARGO" };

	public FuncionarioTableModel(List<Funcionario> lista) {
		// cria a lista interna através
		// da lista recebida no contrutor
		this.funcionarios = new ArrayList<Funcionario>(lista);

	}

	@Override
	public int getRowCount() {
		// o número de linhas da tabela será
		// o número de registros na lista clientes
		return funcionarios.size();
	}

	@Override
	public int getColumnCount() {
		// o numero de colunas será a
		// quantidade de Strings do vetor colunas
		return colunas.length;

	}

	@Override
	public Object getValueAt(int linha, int coluna) {
		// recuperar o funcionario da lista funcionarios
		// pelo indice da linha
		Funcionario funcionario = funcionarios.get(linha);
		switch (coluna) {
		case 0:
			// se for coluna 0, retorna id do funcionario
			return funcionario.getId();
		case 1:
			// se for coluna 1, retorna nome do funcionario
			return funcionario.getNome();
		case 2:
			// se for coluna 2, retorna cpf do funcionario
			return funcionario.getCpf();
		case 3:
			// se for coluna 1, retorna tipo do funcionario
			return funcionario.getTipo();
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
	public Funcionario getFuncionario(int indice){
		return funcionarios.get(indice);
	}
}
