package br.senai.sp.paletas.tela;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import br.senai.sp.paletas.dao.ProdutoDao;
import br.senai.sp.paletas.modelo.Produto;


/**
 * 
 * @author Samara de jesus Sant ana
 * @author Bruna Tavares Licario
 *
 */

public class ListagemProduto extends JFrame {
	JScrollPane pnProduto;
	JList<Produto> listProdutos;
    Font padrao;

	public ListagemProduto() {
		inicializarComponentes();
		definirEventos();
	}

	private void inicializarComponentes() {
		
		padrao = new Font("Consolas", Font.PLAIN, 18);
		
		// listProdutos
		listProdutos = new JList<Produto>();
		listProdutos.setFont(padrao);
		listProdutos.setBackground(new Color(255, 99, 71));
		listProdutos.setForeground(Color.black);
		atualizaTabela();

		// pnProduto
		pnProduto = new JScrollPane(listProdutos);

		// adicionar a tela
		add(pnProduto);

		// definiçoes da tela
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Lista de produtos");
		setVisible(true);

	}

	private void atualizaTabela() {
		DefaultListModel<Produto> model = new DefaultListModel<Produto>();

		for (Produto p : new ProdutoDao().listar()) {
			model.addElement(p);
		}
		listProdutos.setModel(model);
	}

	private void definirEventos() {
		listProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2
						&& listProdutos.getSelectedIndex() >= 0) {
					Produto p = listProdutos.getSelectedValue();
					new TelaCadProduto(p);
					atualizaTabela();

				}

			}
		});

	}

}
