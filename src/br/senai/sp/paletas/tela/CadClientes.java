package br.senai.sp.paletas.tela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.MaskFormatter;

import br.senai.sp.paletas.dao.ClienteDao;
import br.senai.sp.paletas.model.ClienteTableModel;
import br.senai.sp.paletas.modelo.Cliente;
import br.senai.sp.paletas.modelo.Genero;
import br.senai.sp.paletas.validador.ValidaCPF;


/**
 * 
 * @author Samara de jesus Sant ana
 * @author Bruna Tavares Licario
 *
 */

public class CadClientes extends JDialog implements ActionListener {

	Font fontePadrao;
	Font fontePadrao2;
	JPanel pnCentro, pnBotao, pnButton, pnBtBuscar;
	JLabel lbNome, lbNascimento, lbEmail, lbEndereco, lbCpf, lbGenero,
			lbRodape, lbTelefone, lbCelular, imagem;
	JTextField tfNome, tfEmail, tfBuscar;
	JFormattedTextField tfNascimento, tfCpf, tfTelefone, tfCelular;
	JTextArea taEndereco;
	JScrollPane spEndereco, spClientes;
	JButton btSalvar, btExcluir, btLimpar, btBuscar;
	JTable tbClientes;
	ButtonGroup groupNews;
	JRadioButton rbButtomFem, rbButtomMasc;
	Cliente cliente;
	ClienteDao dao = new ClienteDao();

	public CadClientes() {
		inicializarComponentes();
		definirEventos();

		setModal(true);
		setVisible(true);

	}

	// método para definir o model da tabela e criá-la
	private void criarTabela(List<Cliente> lista) {
		ClienteTableModel model = new ClienteTableModel(lista);
		tbClientes.setModel(model);
		// definir seleção simples para a JTable
		tbClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// mudar o tamnho da linha da tabela
		// tbClientes.setRowHeight(30);
		// bloqueia a reogarnização
		tbClientes.getTableHeader().setReorderingAllowed(false);
		// bloqueia o redimensionamento
		tbClientes.getTableHeader().setResizingAllowed(false);
		// redimensiona a coluna para 100px
		tbClientes.getColumnModel().getColumn(0).setPreferredWidth(220);
		tbClientes.getColumnModel().getColumn(1).setPreferredWidth(230);
		tbClientes.getColumnModel().getColumn(2).setPreferredWidth(224);
		tbClientes.getColumnModel().getColumn(3).setPreferredWidth(220);

		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		tbClientes.getColumnModel().getColumn(0).setCellRenderer(render);
		// desabilita o autoredimensionamento dele
		tbClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

	}

	private void preencherCampos() {
		tfNome.setText(cliente.getNome());
		tfCpf.setValue(cliente.getCpf());
		tfTelefone.setValue(cliente.getTelefone());
		tfCelular.setValue(cliente.getCelular());
		tfNascimento.setValue(new SimpleDateFormat("dd/MM/yyyy").format(cliente
				.getNascimento().getTime()));
		taEndereco.setText(cliente.getEndereco());
		tfEmail.setText(cliente.getEmail());
		if (cliente.getGenero() == Genero.FEMININO) {
			rbButtomFem.setSelected(true);
		} else {
			rbButtomMasc.setSelected(true);
		}
	}

	public CadClientes(Cliente cliente) {
		inicializarComponentes();
		definirEventos();
		this.cliente = cliente;
		preencherCampos();
		setModal(true);
		// ****Desenha a tela, ultima coisa a ser colocada apos o inicializ
		// componentes****
		setVisible(true);

	}

	private void inicializarComponentes() {

		fontePadrao = new Font("Arial", Font.BOLD, 18);
		fontePadrao2 = new Font("Arial", Font.PLAIN, 16);

		// Centro

		pnCentro = new JPanel();
		pnCentro.setBackground(new Color(255, 165, 0));
		pnCentro.setLayout(null);

		imagem = new JLabel();
		imagem.setBounds(650, 10, 408, 300);
		imagem.setIcon(new ImageIcon(getClass().getResource(
				"/imagens/mexicano.png")));

		// lb Nome
		lbNome = new JLabel("Nome:");
		lbNome.setBounds(10, 10, 80, 30);
		lbNome.setFont(fontePadrao);

		// tf Nome
		tfNome = new JTextField();
		tfNome.setBackground(Color.white);
		tfNome.setBounds(100, 10, 500, 30);
		tfNome.setFont(fontePadrao2);

		// lb Nascimento
		lbNascimento = new JLabel("Data de Nascimento:");
		lbNascimento.setBounds(10, 50, 200, 30);
		lbNascimento.setFont(fontePadrao);

		try {
			MaskFormatter mskNascimento = new MaskFormatter("##/##/####");
			mskNascimento.setPlaceholderCharacter('_');
			tfNascimento = new JFormattedTextField(mskNascimento);
			tfNascimento.setBounds(200, 50, 180, 30);
			tfNascimento.setFont(fontePadrao2);
			tfNascimento.setHorizontalAlignment(SwingConstants.CENTER);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			System.out.println(e.getMessage());
		}

		// lb cpf

		lbCpf = new JLabel("CPF:");
		lbCpf.setBounds(400, 50, 80, 30);
		lbCpf.setFont(fontePadrao);

		// tf CPF
		MaskFormatter mskCpf;
		try {
			mskCpf = new MaskFormatter("###.###.###-##");
			mskCpf.setPlaceholderCharacter('_');
			mskCpf.setValueContainsLiteralCharacters(false);
			tfCpf = new JFormattedTextField(mskCpf);
			tfCpf.setBounds(450, 50, 220, 30);
			tfCpf.setFont(fontePadrao2);
			tfCpf.setHorizontalAlignment(SwingConstants.CENTER);

		} catch (ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		// lb Genero
		lbGenero = new JLabel("Gênero:");
		lbGenero.setBounds(10, 100, 80, 30);
		lbGenero.setFont(fontePadrao);

		// jbButtom
		// buttom sim
		rbButtomMasc = new JRadioButton("Masculino");
		rbButtomMasc.setFont(fontePadrao2);
		rbButtomMasc.setBackground(Color.white);
		// buttom nao
		rbButtomFem = new JRadioButton("Feminino");
		rbButtomFem.setFont(fontePadrao2);
		rbButtomFem.setBackground(Color.white);
		// groupNews
		// Faz com que o usuario nao clique em mais de uma opçao
		groupNews = new ButtonGroup();
		groupNews.add(rbButtomMasc);
		groupNews.add(rbButtomFem);

		// Painel do Buttom genero
		pnButton = new JPanel();
		pnButton.setLayout(new GridLayout(1, 2));
		pnButton.setBounds(100, 100, 280, 30);
		pnButton.add(rbButtomMasc);
		pnButton.add(rbButtomFem);
		pnButton.setBackground(new Color(255, 165, 0));
		// lb endereco
		lbEndereco = new JLabel("Endereço:");
		lbEndereco.setBounds(10, 150, 250, 30);
		lbEndereco.setFont(fontePadrao);

		// ta endereco
		taEndereco = new JTextArea();
		taEndereco.setFont(fontePadrao2);

		// sp Endereco
		spEndereco = new JScrollPane(taEndereco);
		spEndereco.setBackground(Color.white);
		spEndereco.setBounds(120, 150, 470, 40);

		// lb Email
		lbEmail = new JLabel("Email:");
		lbEmail.setBounds(10, 200, 470, 30);
		lbEmail.setFont(fontePadrao);

		// tf Email
		tfEmail = new JTextField();
		tfEmail.setBounds(120, 200, 470, 30);
		tfEmail.setBackground(Color.white);
		tfEmail.setFont(fontePadrao2);

		// lb Telefone
		lbTelefone = new JLabel("Telefone:");
		lbTelefone.setBounds(10, 250, 200, 30);
		lbTelefone.setFont(fontePadrao);

		// tf Telefone
		MaskFormatter mskTelefone;
		try {
			mskTelefone = new MaskFormatter("(##)####-####");
			mskTelefone.setPlaceholderCharacter('_');
			tfTelefone = new JFormattedTextField(mskTelefone);
			tfTelefone.setBounds(120, 250, 200, 30);
			tfTelefone.setFont(fontePadrao2);
			tfTelefone.setHorizontalAlignment(SwingConstants.CENTER);

		} catch (ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		// lb Celular
		lbCelular = new JLabel("Celular:");
		lbCelular.setBounds(400, 250, 200, 30);
		lbCelular.setFont(fontePadrao);

		// tf Celular
		MaskFormatter mskCelular;
		try {
			mskCelular = new MaskFormatter("(##)#####-####");
			mskCelular.setPlaceholderCharacter('_');
			tfCelular = new JFormattedTextField(mskCelular);
			tfCelular.setBounds(490, 250, 200, 30);
			tfCelular.setFont(fontePadrao2);
			tfCelular.setHorizontalAlignment(SwingConstants.CENTER);

		} catch (ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		// Botões Salvar,Excluir,Limpar

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

		// pnBotao
		pnBotao = new JPanel();
		pnBotao.setBackground(new Color(255, 165, 0));
		GridLayout layout = new GridLayout(1, 3);
		layout.setHgap(10);
		pnBotao.setLayout(layout);
		pnBotao.setBounds(500, 300, 400, 30);
		pnBotao.add(btSalvar);
		pnBotao.add(btExcluir);
		pnBotao.add(btLimpar);

		// tfBuscar
		tfBuscar = new JTextField();
		tfBuscar.setBackground(Color.white);
		tfBuscar.setBounds(40, 350, 550, 30);
		tfBuscar.setFont(fontePadrao2);
		// BtBuscar
		btBuscar = new JButton();
		btBuscar.setFont(fontePadrao);
		btBuscar.setBounds(590, 350, 80, 30);
		btBuscar.setIcon(new ImageIcon(getClass().getResource(
				"/imagens/busca.png")));

		// CONFIGURAÇÕES JANELA
		setSize(1000, 800);
		setLocationRelativeTo(null);
		setTitle("Cadastro Clientes");
		setResizable(true);
		getContentPane().setBackground(new Color(255, 140, 0));

		// TABELA

		tbClientes = new JTable();
		criarTabela(dao.listar());

		spClientes = new JScrollPane(tbClientes);
		spClientes.setBackground(Color.white);
		spClientes.setBounds(20, 400, 900, 320);

		// Rodapé
		lbRodape = new JLabel("Las Paletas");

		lbRodape.setText("Las Paletas");
		lbRodape.setOpaque(true);
		lbRodape.setBackground(new Color(238, 118, 0));

		lbRodape.setForeground(new Color(34, 139, 34));

		lbRodape.setHorizontalAlignment(SwingConstants.CENTER);

		lbRodape.setFont(fontePadrao);

		// adicionar ao Painel Central (pnCentro)
		pnCentro.add(lbNome);
		pnCentro.add(tfNome);
		pnCentro.add(lbNascimento);
		pnCentro.add(tfNascimento);
		pnCentro.add(lbCpf);
		pnCentro.add(tfCpf);
		pnCentro.add(lbGenero);
		pnCentro.add(pnButton);
		pnCentro.add(lbEndereco);
		pnCentro.add(spEndereco);
		pnCentro.add(lbEmail);
		pnCentro.add(tfEmail);
		pnCentro.add(lbTelefone);
		pnCentro.add(tfTelefone);
		pnCentro.add(lbCelular);
		pnCentro.add(tfCelular);
		pnCentro.add(pnBotao);
		pnCentro.add(spClientes);
		pnCentro.add(btBuscar);
		pnCentro.add(tfBuscar);
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

		// listener para foco do tfcpf
		tfCpf.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					tfCpf.commitEdit();
					if (ValidaCPF.validarCPF(tfCpf.getValue().toString())) {
						tfCpf.setBackground(Color.green);
					} else {
						throw new InputMismatchException();
					}
				} catch (ParseException | InputMismatchException e1) {
					tfCpf.setBackground(Color.red);
					tfCpf.requestFocus();
				}

			}
		});
		// listener da tfNome
		tfNome.addKeyListener(new KeyAdapter() {
			@Override
			// o objeto KeyEvent e tem as informações da tecla pressionada
			public void keyTyped(KeyEvent e) {
				// se o caractere da tecla pressionada for númerico, não deixa
				// ir para TextField
				if (Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}

		});

		tbClientes.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						// referência pra model da table
						ClienteTableModel model = (ClienteTableModel) tbClientes
								.getModel();
						// recuperar a linha selecionada no table
						int linhaSelec = tbClientes.getSelectedRow();
						// se a linha for maior ou igual a zero
						if (linhaSelec >= 0) {
							cliente = model.getCliente(linhaSelec);
							// exibe as informações do cliente na tela
							preencherCampos();
						}
					}
				});

		btBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				criarTabela(dao.buscar(tfBuscar.getText()));

			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		// se o nome não foi informado, manda aviso
		// e retorna o foco ao campo Nome
		if (tfNome.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Informe o nome", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			tfNome.requestFocus();
		} else if (tfNascimento.getValue() == null) {
			JOptionPane.showMessageDialog(this, "Informe o nascimento",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			tfNascimento.requestFocus();
		} else if (taEndereco.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Informe o nome", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			spEndereco.requestFocus();
		} else if (tfEmail.getText().trim().isEmpty()
				|| !Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*"
						+ "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
						tfEmail.getText().trim())) {
			JOptionPane.showMessageDialog(this, "Informe um e-mail válido",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			tfEmail.requestFocus();

		} else if (tfCpf.getValue() == null) {
			JOptionPane.showMessageDialog(this, "Informe o CPF", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			tfCpf.requestFocus();
		} else if (tfTelefone.getValue() == null) {
			JOptionPane.showMessageDialog(this, "Informe o Telefone", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			tfTelefone.requestFocus();

		} else if (!rbButtomMasc.isSelected() && !rbButtomFem.isSelected()) {
			JOptionPane.showMessageDialog(this, "Informe o Genero", "Aviso",
					JOptionPane.WARNING_MESSAGE);
		} else if (taEndereco.getText().trim().isEmpty()) {

		} else {
			// se o cliente for nulo, instancia a variável
			if (cliente == null) {
				cliente = new Cliente();
			}
			cliente.setNome(tfNome.getText().trim());
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyy");
			Calendar nasc = Calendar.getInstance();
			try {
				// converte o conteúdo do tfNacimento
				// para Date e passa ao Calendar através do SetTime
				nasc.setTime(formatador.parse(tfNascimento.getValue()
						.toString()));
			} catch (ParseException e1) {
				JOptionPane
						.showMessageDialog(CadClientes.this, e1.getMessage());
			}

			// atribui ao cliente, o Calendar criado
			cliente.setNascimento(nasc);
			cliente.setEmail(tfEmail.getText().trim());
			cliente.setEndereco(taEndereco.getText().trim());
			cliente.setCpf(tfCpf.getValue().toString());
			cliente.setTelefone(tfTelefone.getValue().toString());
			if (tfCelular.getValue() != null) {
				cliente.setCelular(tfCelular.getValue().toString());
			}

			if (cliente.getId() == 0) {
				dao.incluir(cliente);
			}
			// caso contrario, atualiza
			else {
				dao.alterarCliente(cliente);
			}
			limpar();
			criarTabela(dao.listar());
		}

	}

	ActionListener listenerExcluir = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			// se cliente for diferente de nulo exclui cliente
			if (cliente != null) {

				dao.excluir(cliente);
				criarTabela(dao.listar());
			}
			limpar();

		}

	};

	private void limpar() {
		// metodo para limpar os campos da tela,
		tfNome.setText(null);
		tfNascimento.setValue(null);
		tfNascimento.setText(null);
		tfEmail.setText(null);
		taEndereco.setText(null);
		tfCpf.setValue(null);
		tfTelefone.setValue(null);
		tfCelular.setValue(null);
		groupNews.clearSelection();
		cliente = null;
		tfCpf.setBackground(Color.white);
		tfNome.requestFocus();
	}
}