package br.senai.sp.paletas.tela;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.ConnectException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import br.senai.sp.paletas.dao.ConnectionFactory;
import br.senai.sp.paletas.modelo.Funcionario;
import br.senai.sp.paletas.modelo.GerenteEFuncionario;




/**
 * 
 * @author Samara de jesus Sant ana
 * @author Bruna Tavares Licario
 *
 */

public class TelaInicial extends JFrame {
	JMenuBar barraMenu;
	JMenu menuClientes, menuFuncionarios, menuProduto, menuPedido;
	JMenuItem itSair, itNovoCliente, itListaCliente, itListaFuncionario,
			itNovoFuncionario, itNovoProduto, itListaProduto, itNovoPedido;
	Funcionario func;

	public TelaInicial() {
		inicializarComponentes();
		definirEventos();

	}

	public TelaInicial(Funcionario func) {
		inicializarComponentes();
		definirEventos();
		this.func = func;
		if (func.getTipo() == GerenteEFuncionario.FUNCIONARIO) {				
			menuFuncionarios.setVisible(false);
		}

	}

	private void inicializarComponentes() {
		// itNovoCliente
		itNovoCliente = new JMenuItem("Novo cliente");
		itNovoCliente.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.CTRL_MASK));

		// itListaClientes
		itListaCliente = new JMenuItem("Lista de Clientes");

		// menuCliente
		menuClientes = new JMenu("Clientes");
		menuClientes.add(itNovoCliente);
		menuClientes.add(itListaCliente);

		// itNovoFuncionario
		itNovoFuncionario = new JMenuItem("Novo Funcionario");
		itNovoFuncionario.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.CTRL_MASK));

		// itListaFuncinario
		itListaFuncionario = new JMenuItem("Lista de Funcionarios");

		// menuFuncionario
		menuFuncionarios = new JMenu("Funcionarios");
		menuFuncionarios.add(itNovoFuncionario);
		menuFuncionarios.add(itListaFuncionario);

		// itNovoProduto
		itNovoProduto = new JMenuItem("Novo Produto");
		itNovoProduto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.CTRL_MASK));

		// itListaProduto
		itListaProduto = new JMenuItem("Lista de Produtos");

		// menuProduto
		menuProduto = new JMenu("Produtos");
		menuProduto.add(itNovoProduto);
		menuProduto.add(itListaProduto);

		// itNovoPedido
		itNovoPedido = new JMenuItem("Novo Pedido");
		itNovoPedido.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.CTRL_MASK));

		// menuPedido
		menuPedido = new JMenu("Pedidos");
		menuPedido.add(itNovoPedido);

		// barraMenu
		barraMenu = new JMenuBar();
		barraMenu.add(menuClientes);
		barraMenu.add(menuFuncionarios);
		barraMenu.add(menuProduto);
		barraMenu.add(menuPedido);
		setJMenuBar(barraMenu);

		// ******** definiçoes da janela***********

		// ****definir tamanho da janela ****
		setSize(650, 650);

		// ****(localização)Posiçao da janela****
		// setLocation(100,100);

		// ****Centralizar janela
		setLocationRelativeTo(null);

		// ****Colocar nome na janela****
		setTitle("Las Paletas");

		// ****operaçãop padra de Fechar a tela****
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Fazer com que a janela abra maximizado
		setExtendedState(MAXIMIZED_BOTH);
		setIconImage(new ImageIcon(getClass().getResource(
				"/imagens/las-paletas-logo.png")).getImage());

		setContentPane(new Background());
		setVisible(true);

	}

	private void definirEventos() {
		itNovoFuncionario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaFuncionario();

			}
		});

		itNovoCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new CadClientes();

			}
		});

		itListaCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ListagemCliente();

			}
		});
		itListaFuncionario.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ListagemFuncionario();

			}
		});

		itListaProduto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ListagemProduto();

			}
		});

		itNovoProduto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaCadProduto();

			}
		});
		itNovoPedido.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new TelaVenda();

			}
		});

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				ConnectionFactory.close();
			}
		});

	}

	private class Background extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			ImageIcon back = new ImageIcon(getClass().getResource(
					"/imagens/bg.png"));
			g2d.drawImage(back.getImage(), 0, 0, this.getWidth(),
					this.getHeight(), this);
		}
	}

}
