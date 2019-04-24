package br.senai.sp.paletas.tela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RootPaneContainer;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.MaskFormatter;

import br.senai.sp.paletas.dao.ProdutoDao;
import br.senai.sp.paletas.model.ProdutoTableModel;
import br.senai.sp.paletas.modelo.Produto;
import br.senai.sp.paletas.modelo.Tipo;


/**
 * 
 * @author Samara de jesus Sant ana
 * @author Bruna Tavares Licario
 *
 */

public class TelaCadProduto extends JDialog implements ActionListener {
	Font fontePadrao;
	Font fontePadrao2;
	JPanel pnCentro, pnCheckBox, pnBotao, pnBtBuscar;
	JLabel lbRodape, lbTipo, lbSabor, lbPreco,imagem;
	JTextField tfSabor,tfBuscar;
	JFormattedTextField tfPreco;
	JComboBox<Tipo> cbTipos;
	JScrollPane spProdutos;
	JTable tbProdutos;
	JButton btSalvar, btExcluir, btLimpar, btBuscar;
	Produto produto;
	ProdutoDao dao = new ProdutoDao();

	public TelaCadProduto() {

		inicializarComponentes();
		definirEventos();
		setModal(true);
		setVisible(true);

	}

	private void criarTabela(List<Produto> lista) {
		ProdutoTableModel model = new ProdutoTableModel(lista);
		tbProdutos.setModel(model);

		tbProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		tbProdutos.getTableHeader().setReorderingAllowed(false);

		tbProdutos.getTableHeader().setResizingAllowed(false);
		// redimensiona a coluna 
		tbProdutos.getColumnModel().getColumn(0).setPreferredWidth(80);
		tbProdutos.getColumnModel().getColumn(1).setPreferredWidth(120);
		tbProdutos.getColumnModel().getColumn(2).setPreferredWidth(182);
		tbProdutos.getColumnModel().getColumn(3).setPreferredWidth(112);

		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		tbProdutos.getColumnModel().getColumn(0).setCellRenderer(render);
		// desabilita o autoredimensionamento dele
		tbProdutos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
	}

	private void preencherCampos() {
		
		cbTipos.setSelectedItem(produto.getTipo());
		tfSabor.setText(produto.getSabor());
		String preco = produto.getPreco()+"";
		if (preco.indexOf('.') == 1) {
			preco = "0"+preco;
		} 
		if (preco.length() == 4) {
			preco += "0";
		}
		tfPreco.setValue(preco);
	}

	public TelaCadProduto(Produto produto) {
		inicializarComponentes();
		definirEventos();
		this.produto = produto;
		preencherCampos();
		setModal(true);
		// ****Desenha a tela, 
		setVisible(true);

	}

	private void inicializarComponentes() {
		fontePadrao = new Font("Arial", Font.BOLD, 18);
		fontePadrao2 = new Font("Arial", Font.PLAIN, 16);

		imagem = new JLabel();
		imagem.setBounds(50, 260, 408, 300);
		imagem.setIcon(new ImageIcon(getClass().getResource(
				"/imagens/produto.png")));
		// pnCentro

		pnCentro = new JPanel();
	    pnCentro.setBackground(new Color(255, 165, 0));
		pnCentro.setLayout(null);

	

		// TIPO
		lbTipo = new JLabel("Tipo:");
		lbTipo.setBounds(10, 70, 80, 30);
		lbTipo.setFont(fontePadrao);

		// cbTipo
		cbTipos = new JComboBox<Tipo>(Tipo.values());
		cbTipos.setBounds(100, 70, 220, 30);
		cbTipos.setFont(fontePadrao2);
		cbTipos.setSelectedIndex(-1);

		// lbSabor
		lbSabor = new JLabel("Sabor:");
		lbSabor.setBounds(10, 120, 200, 30);
		lbSabor.setFont(fontePadrao);

		// tfSabor
		tfSabor = new JTextField();
		tfSabor.setBackground(Color.white);
		tfSabor.setBounds(80, 120, 360, 30);
		tfSabor.setFont(fontePadrao2);

		// PREÇO
		lbPreco = new JLabel("Preço:   R$");
		lbPreco.setBounds(10, 160, 100, 30);
		lbPreco.setFont(fontePadrao);

		MaskFormatter mskPreco;
		try {
			mskPreco = new MaskFormatter("##.##");
			tfPreco = new JFormattedTextField(mskPreco);
			tfPreco.setBounds(120, 160, 100, 30);
			tfPreco.setFont(fontePadrao2);
			tfPreco.setHorizontalAlignment(SwingConstants.CENTER);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// TABELA

		// tbProdutos
		tbProdutos = new JTable();
		criarTabela(dao.listar());

		spProdutos = new JScrollPane(tbProdutos);
		spProdutos.setBackground(Color.white);
		spProdutos.setBounds(470, 60, 500, 450);

		btSalvar = new JButton("Salvar");
		btSalvar.setFont(fontePadrao);
		btSalvar.setMnemonic(KeyEvent.VK_S);
		btSalvar.setBackground(new Color(190, 190, 190));
		btSalvar.setForeground(new Color(34, 139, 34));
	

		btExcluir = new JButton("Excluir");
		btExcluir.setFont(fontePadrao);
		btExcluir.setMnemonic(KeyEvent.VK_E);
		btExcluir.setBackground(new Color(190, 190, 190));
		btExcluir.setForeground(new Color(34, 139, 34));
		
		btLimpar = new JButton("Limpar");
		btLimpar.setFont(fontePadrao);
		btLimpar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btLimpar.setMnemonic(KeyEvent.VK_L);
		btLimpar.setBackground(new Color(190, 190, 190));
		btLimpar.setForeground(new Color(34, 139, 34));
		
		
		pnBotao = new JPanel();
		pnBotao.setBackground(new Color(255, 165 ,0));
		GridLayout layout = new GridLayout(1, 3);
		layout.setHgap(10);
		pnBotao.setLayout(layout);
		pnBotao.setBounds(10, 250, 400, 30);
		pnBotao.add(btSalvar);
		pnBotao.add(btExcluir);
		pnBotao.add(btLimpar);

		// btBuscar
				btBuscar = new JButton();
				btBuscar.setBounds(880, 10, 80, 30);
				btBuscar.setFont(fontePadrao);
				btBuscar.setIcon(new ImageIcon(getClass().getResource(
						"/imagens/busca.png")));
		
				
				// Text Field Buscar(tfBuscar)
				tfBuscar = new JTextField();
				tfBuscar.setBackground(Color.white);
				tfBuscar.setBounds(480, 10, 400, 30);
				tfBuscar.setFont(fontePadrao2);
				

	
		// CONFIGURAÇÕES JANELA
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setTitle("Cadastro Produto");
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

		// adicionar ao Painel Centro (pnCentro)
		
		pnCentro.add(lbTipo);
		pnCentro.add(cbTipos);
		pnCentro.add(lbSabor);
		pnCentro.add(tfSabor);
		pnCentro.add(lbPreco);
		pnCentro.add(tfPreco);
		pnCentro.add(spProdutos);
		pnCentro.add(pnBotao);
		pnCentro.add(tfBuscar);
		pnCentro.add(btBuscar);
        pnCentro.add(imagem);
		// adicionando componentes
		add(pnCentro, BorderLayout.CENTER);
		add(lbRodape, BorderLayout.SOUTH);

	}

	private void definirEventos() {
		
		btSalvar.addActionListener(this);

	

		btExcluir.addActionListener(listenerExcluir);

		

		btLimpar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				limpar();
			}
		});

		// Listener da tfSabor
		// keyListener serve para verificar as teclas que estão sendo
		// presionadas
		tfSabor.addKeyListener(new KeyAdapter() {
			@Override
			// o objeto keyEvent (e) tem as informaçoes da
			// tecla pressionada
			public void keyTyped(KeyEvent e) {
				// se p caractere da tecla pressionada
				// for númerico não deixa ir para TextField
				if (Character.isDigit(e.getKeyChar()))
					e.consume();
			}
		});

		tbProdutos.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						// referencia para model da table
						ProdutoTableModel model = (ProdutoTableModel) tbProdutos
								.getModel();
						// recuperar a linha selecionada no table
						int linhaSelec = tbProdutos.getSelectedRow();
						// se a linha for maior ou igual a zero
						if (linhaSelec >= 0) {
							produto = model.getProduto(linhaSelec);
							// exibe as informações do cliente na tela
							preencherCampos();

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

	public void actionPerformed(ActionEvent e) {
	

		if (cbTipos.getSelectedIndex() < 0) {
			JOptionPane.showMessageDialog(this, "Informe o tipo da paleta",
					"Aviso", JOptionPane.WARNING_MESSAGE);
		} else if (tfSabor.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Informe o sabor", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			tfSabor.requestFocus();
		} else if (tfPreco.getValue() == null) {
			JOptionPane.showMessageDialog(this, "Informe o preço", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			tfPreco.requestFocus();
		} else {
			if (produto == null) {
				produto = new Produto();
			}
			// Atribui o produto para ser salvo
			produto.setTipo((Tipo) cbTipos.getSelectedItem());
			produto.setSabor(tfSabor.getText().trim());
			produto.setPreco(Double.parseDouble(tfPreco.getText().toString()));

			// incluri e alterar produtos
			if (produto.getId() == 0) {
				dao.incluir(produto);
			}
			// caso contrario, atualiza
			else {
				dao.alterar(produto);
			}
			limpar();
			criarTabela(dao.listar());

		}

	}

	ActionListener listenerExcluir = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
		
			// se produto for diferente de null exclui produto
			if (produto != null) {
			
				dao.excluir(produto);
				criarTabela(dao.listar());

			}
			limpar();

		}

	};

	private void limpar() {
		// metodo para limpar os campos da tela,
	
		cbTipos.setSelectedIndex(-1);
		tfSabor.setText(null);
		tfPreco.setValue(null);
		produto = null;

	}

}
