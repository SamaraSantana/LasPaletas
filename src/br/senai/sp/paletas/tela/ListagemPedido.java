package br.senai.sp.paletas.tela;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import br.senai.sp.paletas.dao.PedidoDao;
import br.senai.sp.paletas.modelo.Pedido;
import br.senai.sp.paletas.modelo.Produto;

public class ListagemPedido extends JFrame {
	JScrollPane pnPedido;
	JList<Pedido> listPedidos;

	public ListagemPedido() {
		inicializarComponentes();
		definirEventos();
	}

	private void inicializarComponentes() {
		// listClientes
		listPedidos = new JList<Pedido>();
		listPedidos.setFont(new Font("Monospaced", Font.BOLD, 12));
		atualizaTabela();

		// pnClientes
		pnPedido = new JScrollPane(listPedidos);

		// adicionar ao formulário
		add(pnPedido);

		// definiçoes do formulário
		setSize(500, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Lista de pedidos");
		setVisible(true);

	}

	private void atualizaTabela() {
		DefaultListModel<Pedido> model = new DefaultListModel<Pedido>();

		for (Pedido p : new PedidoDao().listar()) {
			model.addElement(p);
		}
		listPedidos.setModel(model);
	}

	private void definirEventos() {
		listPedidos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2
						&& listPedidos.getSelectedIndex() >= 0) {
					Pedido p = listPedidos.getSelectedValue();
					//new TelaVenda(p);
					atualizaTabela();

				}

			}
		});

	}

}
