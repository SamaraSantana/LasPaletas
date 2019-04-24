package br.senai.sp.paletas.tela;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import br.senai.sp.paletas.dao.ClienteDao;
import br.senai.sp.paletas.modelo.Cliente;
import br.senai.sp.paletas.modelo.Funcionario;

public class Listagens extends JFrame {
	JScrollPane pnClientes;
	JList<Cliente> listClientes;
	JList<Funcionario> listFuncionarios;

	public Listagens() {
		inicializarComponentes();
		definirEventos();
	}

	private void inicializarComponentes() {
		// listClientes
		listClientes = new JList<Cliente>();
		listClientes.setFont(new Font("Monospaced", Font.BOLD, 12));
		atualizaTabela();

		// pnClientes
		pnClientes = new JScrollPane(listClientes);

		// adicionar ao formulário
		add(pnClientes);

		// definiçoes do formulário
		setSize(500, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Lista de clientes");
		setVisible(true);

	}

	private void atualizaTabela() {
		DefaultListModel<Cliente> model = new DefaultListModel<Cliente>();

		for (Cliente c : new ClienteDao().listar()) {
			model.addElement(c);
		}
		listClientes.setModel(model);
	}

	private void definirEventos() {
		listClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2
						&& listClientes.getSelectedIndex() >= 0) {
					Cliente c = listClientes.getSelectedValue();
					new CadClientes(c);
					atualizaTabela();

				}

			}
		});

		listFuncionarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2
						&& listClientes.getSelectedIndex() >= 0) {
					Cliente c = listClientes.getSelectedValue();
					new CadClientes(c);
					atualizaTabela();

				}

			}
		});
	}

}
