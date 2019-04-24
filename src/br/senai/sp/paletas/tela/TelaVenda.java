package br.senai.sp.paletas.tela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import br.senai.sp.paletas.dao.ClienteDao;
import br.senai.sp.paletas.dao.ProdutoDao;
import br.senai.sp.paletas.model.ItemDoPedidoTableModel;
import br.senai.sp.paletas.model.ProdutoTableModel;
import br.senai.sp.paletas.modelo.Cliente;
import br.senai.sp.paletas.modelo.ItemDoPedido;
import br.senai.sp.paletas.modelo.Pedido;
import br.senai.sp.paletas.modelo.Produto;

/**
 * 
 * @author Samara de jesus Sant ana
 * @author Bruna Tavares Licario
 *
 */

public class TelaVenda extends JDialog implements ActionListener {
	JTable tbProdutos, tbItens;
	Produto produto;
	JScrollPane spProdutos, spItens;
	JPanel pnCentral, pnBotao;
	Font fontePadrao;
	Font fontePadrao2;
	ProdutoDao dao = new ProdutoDao();
	ClienteDao daoCliente = new ClienteDao();
	ItemDoPedido item = new ItemDoPedido();
	JLabel lbRodape, lbProduto, lbItens, lbCliente, lbData, lbQtd, lbTotal,
			total, imagem;
	JTextField tfBuscar, tfTotal;
	JButton btBuscar;
	JComboBox<Cliente> cbCliente;
	JComboBox<Integer> cbQtd;
	JTextField tfData;
	JButton btVender, btCancelar, btExcluir;
	Pedido pedido;

	public TelaVenda() {
		pedido = new Pedido();

		inicializarComponentes();

		definirEventos();
		Calendar dataAtual = Calendar.getInstance();
		pedido.setDataPedido(dataAtual);
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		tfData.setText(formatador.format(dataAtual.getTime()));

		setModal(true);
		setVisible(true);

	}

	private void criarTabela(List<Produto> lista) {
		ProdutoTableModel model = new ProdutoTableModel(lista);
		tbProdutos.setModel(model);

		tbProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tbProdutos.getTableHeader().setReorderingAllowed(false);

		tbProdutos.getTableHeader().setResizingAllowed(false);
		// redimensiona a coluna para 100px
		tbProdutos.getColumnModel().getColumn(0).setPreferredWidth(109);
		tbProdutos.getColumnModel().getColumn(1).setPreferredWidth(148);
		tbProdutos.getColumnModel().getColumn(2).setPreferredWidth(175);
		tbProdutos.getColumnModel().getColumn(3).setPreferredWidth(112);

		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		tbProdutos.getColumnModel().getColumn(0).setCellRenderer(render);
		// desabilita o autoredimensionamento dele
		tbProdutos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

	}

	private void criarTabela2(List<ItemDoPedido> lista) {
		ItemDoPedidoTableModel model = new ItemDoPedidoTableModel(lista);
		tbItens.setModel(model);

		tbItens.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tbItens.getTableHeader().setReorderingAllowed(false);

		tbItens.getTableHeader().setResizingAllowed(false);
		// redimensiona a coluna para 100px
		tbItens.getColumnModel().getColumn(0).setPreferredWidth(121);
		tbItens.getColumnModel().getColumn(1).setPreferredWidth(151);
		tbItens.getColumnModel().getColumn(2).setPreferredWidth(110);
		tbItens.getColumnModel().getColumn(3).setPreferredWidth(112);

		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		tbItens.getColumnModel().getColumn(0).setCellRenderer(render);
		// desabilita o autoredimensionamento dele
		tbItens.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

	}

	public TelaVenda(Produto produto) {
		inicializarComponentes();
		definirEventos();
		this.produto = produto;
		setModal(true);
		// ****Desenha a tela, ultima coisa a ser colocada apos o inicializ
		// componentes****
		setVisible(true);

	}

	public TelaVenda(ItemDoPedido item) {
		inicializarComponentes();
		definirEventos();
		this.item = item;
		setModal(true);
		// ****Desenha a tela, ultima coisa a ser colocada apos o inicializ
		// componentes****
		setVisible(true);

	}

	private void inicializarComponentes() {
		fontePadrao = new Font("Arial", Font.BOLD, 18);
		fontePadrao2 = new Font("Arial", Font.PLAIN, 16);

		// imagem
		imagem = new JLabel();
		imagem.setBounds(680, 450, 410, 400);
		imagem.setIcon(new ImageIcon(getClass().getResource(
				"/imagens/carrinho3.png")));

		// painel Central

		pnCentral = new JPanel();
		pnCentral.setBackground(new Color(255, 165, 0));
		pnCentral.setLayout(null);

		// lbCliente
		lbCliente = new JLabel("Cliente:");
		lbCliente.setBounds(10, 30, 80, 30);
		lbCliente.setFont(fontePadrao);

		// cbCliente
		cbCliente = new JComboBox<Cliente>();
		cbCliente.setBounds(103, 30, 400, 30);
		cbCliente.setFont(fontePadrao2);
		cbCliente.setSelectedIndex(-1);
		cbCliente.setModel(new DefaultComboBoxModel<Cliente>(
				new Vector<Cliente>(daoCliente.listar())));

		// lbData
		lbData = new JLabel("Data:");
		lbData.setBounds(10, 80, 80, 30);
		lbData.setFont(fontePadrao);

		// text field (tfData)

		tfData = new JTextField();
		tfData.setBounds(70, 80, 180, 30);
		tfData.setFont(fontePadrao2);
		tfData.setHorizontalAlignment(SwingConstants.CENTER);
		tfData.setEditable(false);

		lbQtd = new JLabel("Quantidade:");
		lbQtd.setBounds(10, 130, 130, 30);
		lbQtd.setFont(fontePadrao);

		// cbCliente
		cbQtd = new JComboBox<Integer>();
		cbQtd.setBounds(130, 130, 130, 30);
		cbQtd.setFont(fontePadrao2);
		cbQtd.setSelectedIndex(-1);
		for (int i = 0; i < 20; i++) {
			cbQtd.addItem(i + 1);
		}

		// TABELA

		lbProduto = new JLabel();
		lbProduto.setBounds(10, 10, 80, 30);
		lbProduto.setFont(fontePadrao);

		// tbProdutos
		tbProdutos = new JTable();
		criarTabela(dao.listar());

		spProdutos = new JScrollPane(tbProdutos);
		spProdutos.setBackground(Color.white);
		spProdutos.setBounds(580, 60, 550, 400);
		// TABELA

		// tbProdutos
		tbItens = new JTable();
		criarTabela(dao.listar());

		spItens = new JScrollPane(tbItens);
		spItens.setBackground(Color.white);
		spItens.setBounds(10, 200, 500, 230);

		criarTabela2(pedido.getItens());

		// btBuscar
		btBuscar = new JButton();
		btBuscar.setBounds(1030, 10, 80, 30);
		btBuscar.setFont(fontePadrao);
		btBuscar.setIcon(new ImageIcon(getClass().getResource(
				"/imagens/busca.png")));

		// Text Field Buscar(tfBuscar)
		tfBuscar = new JTextField();
		tfBuscar.setBackground(Color.white);
		tfBuscar.setBounds(580, 10, 450, 30);
		tfBuscar.setFont(fontePadrao2);

		// PREÇO
		lbTotal = new JLabel("Total:   R$");
		lbTotal.setBounds(10, 440, 100, 30);
		lbTotal.setFont(fontePadrao);

		tfTotal = new JTextField();
		tfTotal.setBounds(120, 440, 100, 30);
		tfTotal.setFont(fontePadrao2);
		tfTotal.setHorizontalAlignment(SwingConstants.CENTER);
		tfTotal.setEditable(false);

		// btVender
		btVender = new JButton("Vender");
		btVender.setFont(fontePadrao);
		btVender.setBackground(new Color(190, 190, 190));
		btVender.setForeground(new Color(34, 139, 34));
		btVender.setMnemonic(KeyEvent.VK_S);

		// btExcluir
		btExcluir = new JButton("Excluir");
		btExcluir.setFont(fontePadrao);
		btExcluir.setBackground(new Color(190, 200, 190));
		btExcluir.setForeground(new Color(34, 139, 34));
		btExcluir.setMnemonic(KeyEvent.VK_E);

		// btCancelar
		if (btCancelar == null) {
			btCancelar = new JButton("Cancelar");
			btCancelar.setFont(fontePadrao);

			btCancelar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

				}
			});
			btCancelar.setBackground(new Color(190, 190, 190));
			btCancelar.setForeground(new Color(34, 139, 34));
			btCancelar.setMnemonic(KeyEvent.VK_E);
		}

		pnBotao = new JPanel();
		GridLayout layout = new GridLayout(1, 3);
		layout.setHgap(10);
		pnBotao.setLayout(layout);
		pnBotao.setBounds(100, 530, 340, 30);
		pnBotao.setBackground(new Color(255, 165, 0));
		pnBotao.add(btVender);
		pnBotao.add(btCancelar);
		pnBotao.add(btExcluir);

		// CONFIGURAÇÕES JANELA
		setSize(1150, 900);
		setLocationRelativeTo(null);
		setTitle("vendas");
		setResizable(true);
		getContentPane().setBackground(new Color(255, 140, 0));

		// Rodapé
		lbRodape = new JLabel("Las Paletas");

		lbRodape.setText("Las Paletas");
		lbRodape.setOpaque(true);
		lbRodape.setBackground(new Color(255, 140, 0));

		lbRodape.setForeground(new Color(34, 139, 34));

		lbRodape.setHorizontalAlignment(SwingConstants.CENTER);

		lbRodape.setFont(fontePadrao);

		// adicionar ao Painel Central (pnCentro)

		pnCentral.add(spProdutos);
		pnCentral.add(lbProduto);
		pnCentral.add(spItens);
		pnCentral.add(tfBuscar);
		pnCentral.add(btBuscar);
		pnCentral.add(cbCliente);
		pnCentral.add(lbCliente);
		pnCentral.add(lbData);
		pnCentral.add(tfData);
		pnCentral.add(lbQtd);
		pnCentral.add(cbQtd);
		pnCentral.add(pnBotao);
		pnCentral.add(lbTotal);
		pnCentral.add(tfTotal);
		pnCentral.add(imagem);

		// adicionando componentes
		add(pnCentral, BorderLayout.CENTER);
		add(lbRodape, BorderLayout.SOUTH);

	}

	private void definirEventos() {

		btVender.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(TelaVenda.this,
						"Vendido com sucesso", "Sucess",
						JOptionPane.INFORMATION_MESSAGE);
				limpar();

			}
		});

		btExcluir.addActionListener(listenerExcluir);

		btCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TelaVenda.this.dispose(); // Referência this da tela de Venda

			}
		});

		tbProdutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (tbProdutos.getSelectedRow() >= 0) {
						ProdutoTableModel modelProduto = (ProdutoTableModel) tbProdutos
								.getModel();
						Produto produto = modelProduto.getProduto(tbProdutos
								.getSelectedRow());
						ItemDoPedido item = new ItemDoPedido();
						item.setProduto(produto);
						item.setQuantidade((Integer) cbQtd.getSelectedItem());
						ItemDoPedidoTableModel modelItem = (ItemDoPedidoTableModel) tbItens
								.getModel();
						modelItem.add(item);

						DecimalFormat format = new DecimalFormat("#0.00");
						tfTotal.setText(format.format(pedido.getTotal()));

					}
				}

			}
		});

		btBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				criarTabela(dao.buscar((tfBuscar.getText())));

			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
	}

	ActionListener listenerExcluir = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			ItemDoPedidoTableModel modelItem = (ItemDoPedidoTableModel) tbItens
					.getModel();
			ItemDoPedido itemPed = modelItem
					.getPedido(tbItens.getSelectedRow());
			modelItem.remove(itemPed);
			
			DecimalFormat format = new DecimalFormat("#0.00");
			tfTotal.setText(format.format(pedido.getTotal()));
		}

	};

	private void limpar() {
		cbCliente.setSelectedIndex(-1);
		cbQtd.setSelectedIndex(-1);
		tfTotal.setText(null);
		pedido = new Pedido();
		criarTabela2(pedido.getItens());
		

	}
}