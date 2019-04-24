package br.senai.sp.paletas.tela;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import br.senai.sp.paletas.dao.FuncionarioDao;
import br.senai.sp.paletas.modelo.Cliente;
import br.senai.sp.paletas.modelo.Funcionario;

/**
 * 
 * @author Samara de jesus Sant ana
 * @author Bruna Tavares Licario
 *
 */

public class ListagemFuncionario extends JFrame {
	JScrollPane pnFuncionarios;
	JList<Funcionario> listFuncionarios;
	Font padrao;

	public ListagemFuncionario() {
		inicializarComponentes();
		definirEventos();
	}

	private void inicializarComponentes() {
		padrao = new Font("Consolas", Font.PLAIN, 18);
		
		//list funcionarios
		listFuncionarios = new JList<Funcionario>();
		listFuncionarios.setFont(padrao);
		listFuncionarios.setBackground(new Color(255, 99, 71));
		listFuncionarios.setForeground(Color.black);
		atualizaTabela();

		// pnFuncionarios
		pnFuncionarios = new JScrollPane(listFuncionarios);

		// adicionar a tela
		add(pnFuncionarios);

		// definiçoes da tela
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Lista de funcionários");
		setVisible(true);

	}

	private void atualizaTabela() {
		DefaultListModel<Funcionario> model = new DefaultListModel<Funcionario>();

		for (Funcionario f : new FuncionarioDao().listar()) {
			model.addElement(f);
		}
		listFuncionarios.setModel(model);
	}

	private void definirEventos() {
		listFuncionarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2
						&& listFuncionarios.getSelectedIndex() >= 0) {
					Funcionario f = listFuncionarios.getSelectedValue();
					new TelaFuncionario(f);
					atualizaTabela();

				}

			}
		});

	}

}
