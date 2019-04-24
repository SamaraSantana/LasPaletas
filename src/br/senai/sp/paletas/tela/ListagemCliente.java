package br.senai.sp.paletas.tela;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import br.senai.sp.paletas.dao.ClienteDao;
import br.senai.sp.paletas.modelo.Cliente;


/**
 * 
 * @author Samara de jesus Sant ana
 * @author Bruna Tavares Licario
 *
 */

public class ListagemCliente extends JFrame {
	JScrollPane pnClientes;
	JList<Cliente> listClientes;
	Font padrao;

	public ListagemCliente() {
		inicializarComponentes();
		definirEventos();
	}

	private void inicializarComponentes() {
		// listClientes
		padrao = new Font("Consolas", Font.PLAIN, 18);

		listClientes = new JList<Cliente>();
		listClientes.setFont(padrao);
		listClientes.setBackground(new Color(255, 99, 71));
		listClientes.setForeground(Color.black);
		atualizaTabela();

		// pnClientes
		pnClientes = new JScrollPane(listClientes);

		// adicionar a tela
		add(pnClientes);

		// definiçoes da tela
		setSize(600, 600);
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

	}

}
