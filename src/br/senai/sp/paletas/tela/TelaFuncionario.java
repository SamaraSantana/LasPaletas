package br.senai.sp.paletas.tela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.MaskFormatter;

import br.senai.sp.paletas.dao.FuncionarioDao;
import br.senai.sp.paletas.documento.TextoDocument;
import br.senai.sp.paletas.model.FuncionarioTableModel;
import br.senai.sp.paletas.modelo.Funcionario;
import br.senai.sp.paletas.modelo.Genero;
import br.senai.sp.paletas.modelo.GerenteEFuncionario;
import br.senai.sp.paletas.validador.ValidaCPF;
import br.senai.sp.paletas.validador.ValidaPIS;


/**
 * 
 * @author Samara de jesus Sant ana
 * @author Bruna Tavares Licario
 *
 */

public class TelaFuncionario extends JDialog implements ActionListener {
	JLabel lbRodape, lbNome, lbNascimento, lbEmail, lbEndereco, lbCel,
			lbAdmissao, lbPis, lbCpf, lbTel, lbGenero, lbCargo, imagem,
			lbUsuario, lbSenha;

	Font fontePadrao;
	Font fontePadrao2;
	JPanel pnCentral, pnButtom;
	JTextField tfNome;
	JFormattedTextField tfNascimento, tfAdmissao, tfCpf, tfTel, tfPis, tfCel;
	JTextField tfEmail, tfBuscar, tfUsuario;
	JTextArea taEndereco;
	JScrollPane spEndereco, spFuncionario;
	JPanel pnBotao;
	JButton btSalvar, btExcluir, btLimpar, btBuscar;
	Funcionario funcionario;
	JRadioButton rbButtomFem, rbButtomMasc;
	ButtonGroup groupNews;
	JComboBox<GerenteEFuncionario> cbFunEGer;
	JPasswordField jpSenha;
	JTable tbFuncionario;
	FuncionarioDao dao = new FuncionarioDao();

	public TelaFuncionario() {
		inicializarComponentes();
		definirEventos();
		setModal(true);
		// ****Desenha a tela
		setVisible(true);

	}

	// método para definir o model da tabela e criá-la
	private void criarTabela(List<Funcionario> lista) {
		FuncionarioTableModel model = new FuncionarioTableModel(lista);
		tbFuncionario.setModel(model);
		// definir seleção simples para a JTable
		tbFuncionario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// mudar o tamnho da linha da tabela
		// tbClientes.setRowHeight(30);
		// bloqueia a reogarnização
		tbFuncionario.getTableHeader().setReorderingAllowed(false);
		// bloqueia o redimensionamento
		tbFuncionario.getTableHeader().setResizingAllowed(false);
		// redimensiona a coluna para 100px
		tbFuncionario.getColumnModel().getColumn(0).setPreferredWidth(221);
		tbFuncionario.getColumnModel().getColumn(1).setPreferredWidth(233);
		tbFuncionario.getColumnModel().getColumn(2).setPreferredWidth(220);
		tbFuncionario.getColumnModel().getColumn(3).setPreferredWidth(220);

		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		tbFuncionario.getColumnModel().getColumn(0).setCellRenderer(render);
		// desabilita o autoredimensionamento dele
		tbFuncionario.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

	}

	private void preencherCampos() {
		tfNome.setText(funcionario.getNome());
		tfCpf.setValue(funcionario.getCpf());
		tfTel.setValue(funcionario.getTelefone());
		tfCel.setValue(funcionario.getCelular());
		tfNascimento.setValue(new SimpleDateFormat("dd/MM/yyyy")
				.format(funcionario.getNascimento().getTime()));
		taEndereco.setText(funcionario.getEndereco());
		tfEmail.setText(funcionario.getEmail());
		tfAdmissao.setValue(new SimpleDateFormat("dd/MM/yyyy")
				.format(funcionario.getAdmissao().getTime()));
		tfPis.setValue(funcionario.getPis());
		tfUsuario.setText(funcionario.getUsuario());
		jpSenha.setText(funcionario.getSenha());
		cbFunEGer.setSelectedItem(funcionario.getTipo());
		if (funcionario.getGenero() == Genero.FEMININO) {
			rbButtomFem.setSelected(true);
		} else {
			rbButtomMasc.setSelected(true);
		}
	}

	public TelaFuncionario(Funcionario funcionario) {
		inicializarComponentes();
		definirEventos();
		this.funcionario = funcionario;
		preencherCampos();
		setModal(true);
		// ****Desenha a tela
		setVisible(true);

	}

	private void inicializarComponentes() {

		// fonte padrao
		fontePadrao = new Font("Verdana", Font.BOLD | Font.ITALIC, 16);

		// fonte padrao2
		fontePadrao2 = new Font("Arial", Font.PLAIN, 14);
		// lbRodape
		lbRodape = new JLabel("Las Paletas");

		// Definindo o texto JLabel
		lbRodape.setText("Las Paletas");

		lbRodape.setOpaque(true);

		lbRodape.setBackground(new Color(238, 118, 0));
		lbRodape.setForeground(new Color(34, 139, 34));

		
		lbRodape.setHorizontalAlignment(SwingConstants.CENTER);
		// APLICA FONTE
		lbRodape.setFont(fontePadrao);

		// pnCentral
		pnCentral = new JPanel();
		pnCentral.setBackground(new Color(255, 165, 0));
		pnCentral.setLayout(null);

		imagem = new JLabel();
		imagem.setBounds(670, 10, 408, 281);
		imagem.setIcon(new ImageIcon(getClass().getResource(
				"/imagens/funcionario.png")));

		// lbNome(label nome)

		lbNome = new JLabel("Nome:");
		lbNome.setBounds(10, 20, 80, 30);
		lbNome.setFont(fontePadrao);

		// Text Field Nome(tfNome)
		tfNome = new JTextField();
		tfNome.setBackground(Color.white);
		tfNome.setBounds(100, 20, 500, 30);
		tfNome.setFont(fontePadrao2);

		// lbNascimento
		lbNascimento = new JLabel("Data de Nascimento:");
		lbNascimento.setBounds(10, 60, 200, 30);
		lbNascimento.setFont(fontePadrao);

		// text field Nascimento(tfNascimento)
		try {
			MaskFormatter mskNascimento = new MaskFormatter("##/##/####");
			mskNascimento.setPlaceholderCharacter('_');
			tfNascimento = new JFormattedTextField(mskNascimento);
			tfNascimento.setBounds(200, 60, 180, 30);
			tfNascimento.setFont(fontePadrao2);
			tfNascimento.setHorizontalAlignment(SwingConstants.CENTER);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());

		}

		// lbCpf
		lbCpf = new JLabel("CPF:");
		lbCpf.setBounds(400, 60, 80, 30);
		lbCpf.setFont(fontePadrao);

		// text field CPF(tfCpf)

		MaskFormatter mskCpf;
		try {
			mskCpf = new MaskFormatter("###.###.###-##");
			mskCpf.setPlaceholderCharacter('_');
			// tira os pontos e o traço para fazer validação do cpf
			mskCpf.setValueContainsLiteralCharacters(false);
			tfCpf = new JFormattedTextField(mskCpf);
			tfCpf.setBounds(450, 60, 220, 30);
			tfCpf.setFont(fontePadrao2);
			tfCpf.setHorizontalAlignment(SwingConstants.CENTER);

		} catch (ParseException e) {

			e.printStackTrace();
		}

		// lb Genero
		lbGenero = new JLabel("Genero:");
		lbGenero.setBounds(10, 100, 230, 30);
		lbGenero.setFont(fontePadrao);

		// jbButtom genero masculino
		rbButtomMasc = new JRadioButton("Masculino");
		rbButtomMasc.setFont(fontePadrao2);
		rbButtomMasc.setBackground(Color.white);

		// jbButtom genero Feminino
		rbButtomFem = new JRadioButton("Feminino");
		rbButtomFem.setFont(fontePadrao2);
		rbButtomFem.setBackground(Color.white);

		// groupNews
		// Faz com que o usuario nao clique em mais de uma opçao
		groupNews = new ButtonGroup();
		groupNews.add(rbButtomMasc);
		groupNews.add(rbButtomFem);

		// Painel do Buttom genero
		pnButtom = new JPanel();
		pnButtom.setLayout(new GridLayout(1, 2));
		pnButtom.setBounds(100, 100, 280, 30);
		pnButtom.add(rbButtomMasc);
		pnButtom.add(rbButtomFem);
		pnButtom.setBackground(new Color(255, 165, 0));

		// lb Endereco
		lbEndereco = new JLabel("Endereço:");
		lbEndereco.setBounds(10, 160, 250, 30);
		lbEndereco.setFont(fontePadrao);

		// ta Endereco
		taEndereco = new JTextArea();
		taEndereco.setFont(fontePadrao2);

		// cria lista de teclas
		Set<KeyStroke> teclas = new HashSet<KeyStroke>();
		teclas.add(KeyStroke.getKeyStroke("TAB"));
		// teclas.add(KeyStroke.getKeyStroke("ENTER"));
		taEndereco.setFocusTraversalKeys(
				KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, teclas);
		taEndereco.setDocument(new TextoDocument());
		taEndereco.setWrapStyleWord(true);

		// sp Endereco
		// Cria o ScrollPane passando a ta endereco no construtor
		spEndereco = new JScrollPane(taEndereco);
		spEndereco.setBackground(Color.white);
		spEndereco.setBounds(120, 160, 470, 30);

		// (lbEmail) label email
		lbEmail = new JLabel("E-mail:");
		lbEmail.setBounds(10, 210, 400, 30);
		lbEmail.setFont(fontePadrao);

		// Text Field Email (tfEmail)
		tfEmail = new JTextField();
		tfEmail.setBackground(Color.white);
		tfEmail.setBounds(80, 210, 470, 30);
		tfEmail.setFont(fontePadrao2);

		// lbTel
		lbTel = new JLabel("Telefone:");
		lbTel.setBounds(10, 250, 90, 30);
		lbTel.setFont(fontePadrao);

		// text field Tel(tfTel)

		MaskFormatter mskTel;
		try {
			mskTel = new MaskFormatter("(##)####-####");
			mskTel.setPlaceholderCharacter('_');
			tfTel = new JFormattedTextField(mskTel);
			tfTel.setBounds(100, 250, 220, 30);
			tfTel.setFont(fontePadrao2);
			tfTel.setHorizontalAlignment(SwingConstants.CENTER);

		} catch (ParseException e) {

			e.printStackTrace();
		}

		// lbCel
		lbCel = new JLabel("Celular:");
		lbCel.setBounds(350, 250, 80, 30);
		lbCel.setFont(fontePadrao);

		// text field Cel(tfCel)

		MaskFormatter mskCel;
		try {
			mskCel = new MaskFormatter("(##)#####-####");
			mskCel.setPlaceholderCharacter('_');
			tfCel = new JFormattedTextField(mskCel);
			tfCel.setBounds(430, 250, 220, 30);
			tfCel.setFont(fontePadrao2);
			tfCel.setHorizontalAlignment(SwingConstants.CENTER);

		} catch (ParseException e) {

			e.printStackTrace();
		}

		// lbAdmissao
		lbAdmissao = new JLabel("Data de Admissão:");
		lbAdmissao.setBounds(10, 290, 220, 30);
		lbAdmissao.setFont(fontePadrao);

		// text field Data de Admissão(tfAdmissai)
		try {
			MaskFormatter mskAdmissao = new MaskFormatter("##/##/####");
			mskAdmissao.setPlaceholderCharacter('_');
			tfAdmissao = new JFormattedTextField(mskAdmissao);
			tfAdmissao.setBounds(180, 290, 220, 30);
			tfAdmissao.setFont(fontePadrao2);
			tfAdmissao.setHorizontalAlignment(SwingConstants.CENTER);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());

		}

		// lbPis
		lbPis = new JLabel("PIS:");
		lbPis.setBounds(500, 290, 240, 30);
		lbPis.setFont(fontePadrao);

		// text field PIS(tfPis)

		MaskFormatter mskPis;
		try {
			mskPis = new MaskFormatter("###.#####.##-#");
			mskPis.setPlaceholderCharacter('_');
			// tira os pontos e o traço para fazer validação do cpf
			mskPis.setValueContainsLiteralCharacters(false);
			tfPis = new JFormattedTextField(mskPis);
			tfPis.setBounds(550, 290, 220, 30);
			tfPis.setFont(fontePadrao2);
			tfPis.setHorizontalAlignment(SwingConstants.CENTER);

		} catch (ParseException e) {

			e.printStackTrace();
		}

		// lbUsuario(label Usuario)
		lbUsuario = new JLabel("Login:");
		lbUsuario.setBounds(10, 390, 80, 30);
		lbUsuario.setFont(fontePadrao);

		// Text Field usuario(tfUsuario)
		tfUsuario = new JTextField();
		tfUsuario.setBackground(Color.white);
		tfUsuario.setBounds(80, 390, 200, 30);
		tfUsuario.setFont(fontePadrao2);

		// lbSenha(label senha)
		lbSenha = new JLabel("Senha:");
		lbSenha.setBounds(320, 390, 100, 30);
		lbSenha.setFont(fontePadrao);

		// JPasswordField(jpSenha)
		jpSenha = new JPasswordField();
		jpSenha.setBackground(Color.white);
		jpSenha.setBounds(400, 390, 200, 30);
		jpSenha.setFont(fontePadrao2);

		// btBuscar
		btBuscar = new JButton();
		btBuscar.setBounds(590, 500, 80, 30);
		btBuscar.setFont(fontePadrao);
		btBuscar.setIcon(new ImageIcon(getClass().getResource(
				"/imagens/busca.png")));

		// Text Field Buscar(tfBuscar)
		tfBuscar = new JTextField();
		tfBuscar.setBackground(Color.white);
		tfBuscar.setBounds(40, 500, 550, 30);
		tfBuscar.setFont(fontePadrao2);
		

		// tbFuncionario
		tbFuncionario = new JTable();
		criarTabela(dao.listar());

		// spFuncionario
		spFuncionario = new JScrollPane(tbFuncionario);
		spFuncionario.setBackground(Color.white);
		spFuncionario.setBounds(40, 540, 900, 400);

		// lbCargo
		lbCargo = new JLabel("Cargo:");
		lbCargo.setBounds(10, 350, 80, 30);
		lbCargo.setFont(fontePadrao);

		// cbFuneGer
		cbFunEGer = new JComboBox<GerenteEFuncionario>(
				GerenteEFuncionario.values());
		cbFunEGer.setBounds(103, 350, 220, 30);
		cbFunEGer.setFont(fontePadrao2);
		cbFunEGer.setSelectedIndex(-1);

		// btSalvar
		btSalvar = new JButton("Salvar");
		btSalvar.setFont(fontePadrao);
		btSalvar.setBackground(new Color(190, 200, 190));
		btSalvar.setForeground(new Color(34, 139, 34));
		btSalvar.setMnemonic(KeyEvent.VK_S);

		// btExcluir
		btExcluir = new JButton("Excluir");
		btExcluir.setFont(fontePadrao);
		btExcluir.setBackground(new Color(190, 200, 190));
		btExcluir.setForeground(new Color(34, 139, 34));
		btExcluir.setMnemonic(KeyEvent.VK_E);

		// Bt limpar
		btLimpar = new JButton("Limpar");
		btLimpar.setFont(fontePadrao);
		btLimpar.setBackground(new Color(190, 190, 190));
		btLimpar.setForeground(new Color(34, 139, 34));
		btLimpar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btLimpar.setMnemonic(KeyEvent.VK_L);

		// Painel do botao salvar,limpar e excluir
		pnBotao = new JPanel();
		GridLayout layout = new GridLayout(1, 3);
		layout.setHgap(10);
		pnBotao.setLayout(layout);
		pnBotao.setBounds(600, 440, 340, 30);
		pnBotao.setBackground(new Color(255, 165, 0));
		pnBotao.add(btSalvar);
		pnBotao.add(btExcluir);
		pnBotao.add(btLimpar);

		// adiconar ao pnCentral(Painel central)
		pnCentral.add(lbNome);
		pnCentral.add(tfNome);
		pnCentral.add(lbNascimento);
		pnCentral.add(tfNascimento);
		pnCentral.add(lbEmail);
		pnCentral.add(tfEmail);
		pnCentral.add(lbEndereco);
		pnCentral.add(spEndereco);
		pnCentral.add(lbCpf);
		pnCentral.add(tfCpf);
		pnCentral.add(lbTel);
		pnCentral.add(tfTel);
		pnCentral.add(lbCel);
		pnCentral.add(tfCel);
		pnCentral.add(lbGenero);
		pnCentral.add(lbAdmissao);
		pnCentral.add(tfAdmissao);
		pnCentral.add(lbPis);
		pnCentral.add(tfPis);
		pnCentral.add(pnBotao);
		pnCentral.add(pnButtom);
		pnCentral.add(tfBuscar);
		pnCentral.add(btBuscar);
		pnCentral.add(spFuncionario);
		pnCentral.add(lbCargo);
		pnCentral.add(cbFunEGer);
		pnCentral.add(imagem);
		pnCentral.add(lbUsuario);
		pnCentral.add(lbSenha);
		pnCentral.add(tfUsuario);
		pnCentral.add(jpSenha);

		// Adicionando os componentes

		add(pnCentral, BorderLayout.CENTER);
		add(lbRodape, BorderLayout.SOUTH);

		// ******** definiçoes da janela***********

		// ****setBouds(100,100,250,500)"Junção de location e size****

		// ****definir tamanho da janela ****
		setSize(1000, 1000);

		// ****(localização)Posiçao da janela****
		// setLocation(100,100);

		// ****Centralizar janela
		setLocationRelativeTo(null);

		// ****Colocar nome na janela****
		setTitle("Cadastro de Funcionarios");

		// ****Nao redimencionar a janela****
		setResizable(true);

	}

	private void definirEventos() {
		// Listener da tfUsuario
		// keyListener serve para verificar as teclas que estão sendo
		// presionadas
		tfUsuario.addKeyListener(new KeyAdapter() {
			@Override
			// o objeto keyEvent (e) tem as informaçoes da
			// tecla pressionada
			public void keyTyped(KeyEvent e) {
				// se p caractere da tecla pressionada
				// for númerico não deixa ir para TextField
				if (Character.isDigit(e.getKeyChar())
						|| (tfUsuario.getText().length() >= 15))
					e.consume();
				if (Character.isSpace(e.getKeyChar()))
					e.consume();
			}
		});
		jpSenha.addKeyListener(new KeyAdapter() {
			@Override
			// o objeto keyEvent (e) tem as informaçoes da
			// tecla pressionada
			public void keyTyped(KeyEvent e) {
				// se p caractere da tecla pressionada
				// for númerico não deixa ir para TextField
				if (jpSenha.getText().length() >= 8)
					e.consume();
				if (Character.isSpace(e.getKeyChar()))
					e.consume();
			}
		});

		
		btSalvar.addActionListener(this);

		
		btExcluir.addActionListener(listenerExcluir);

		
		btLimpar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				limpar();

			}
		});

		// listener para o foco da tfCpf
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
				System.out.println(tfCpf.getValue());
			}
		});

		// listener para o foco da tfPis
		tfPis.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try {
					tfPis.commitEdit();
					if (ValidaPIS.validarPIS(tfPis.getValue().toString())) {
						tfPis.setBackground(Color.green);
					} else {
						throw new InputMismatchException();
					}
				} catch (ParseException | InputMismatchException e1) {
					tfPis.setBackground(Color.red);
					tfPis.requestFocus();

				}
				System.out.println(tfPis.getValue());
			}
		});
		// Listener da tfNome
		// keyListener serve para verificar as teclas que estão sendo
		// presionadas
		tfNome.addKeyListener(new KeyAdapter() {
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

		// evento de seleção de item na table
		tbFuncionario.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {
						// referencia para model da table
						FuncionarioTableModel model = (FuncionarioTableModel) tbFuncionario
								.getModel();
						// recuperar a linha selecionada no table
						int linhaSelec = tbFuncionario.getSelectedRow();
						// se a linha for maior ou igual a zero
						if (linhaSelec >= 0) {
							funcionario = model.getFuncionario(linhaSelec);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (tfNome.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Informe o nome", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			tfNome.requestFocus();
		} else if (tfNascimento.getValue() == null) {
			JOptionPane.showMessageDialog(this, "Informe o nascimento",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			tfNascimento.requestFocus();

		} else if (tfCpf.getValue() == null) {
			JOptionPane.showMessageDialog(this, "Informe o CPF", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			tfCpf.requestFocus();
		} else if (!rbButtomMasc.isSelected() && !rbButtomFem.isSelected()) {
			JOptionPane.showMessageDialog(this, "Informe o Genero", "Aviso",
					JOptionPane.WARNING_MESSAGE);
		} else if (taEndereco.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Informe o Endereço", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			spEndereco.requestFocus();
		} else if (tfEmail.getText().trim().isEmpty()
				|| !Pattern.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*"
						+ "@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
						tfEmail.getText().trim())) {

			JOptionPane.showMessageDialog(this, "Informe um e-mail válido",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			tfEmail.requestFocus();

		} else if (tfTel.getValue() == null) {
			JOptionPane.showMessageDialog(this, "Informe o Telefone", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			tfTel.requestFocus();

		} else if (tfAdmissao.getValue() == null) {
			JOptionPane.showMessageDialog(this, "Informe a Data da Admissao",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			tfAdmissao.requestFocus();
		} else if (tfPis.getValue() == null) {
			JOptionPane.showMessageDialog(this, "Informe o Pis", "Aviso",
					JOptionPane.WARNING_MESSAGE);
			tfPis.requestFocus();
		} else if (cbFunEGer.getSelectedIndex() < 0) {
			JOptionPane.showMessageDialog(this, "Informe o Tipo de Cargo",
					"Aviso", JOptionPane.WARNING_MESSAGE);
		} else {

			// Cria um funcionario para ser salvo

			// se o funcionario for nulo, intancia a variável
			if (funcionario == null) {
				funcionario = new Funcionario();
			}
			// define o nome do funcionario de acordo
			// com o texto da tfNome
			funcionario.setNome(tfNome.getText().trim());
			// cria um formatador para formata a data de nascimento
			SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
			// cria um objeto calendar
			Calendar nascimento = Calendar.getInstance();
			try {
				// Converte o conteudo do tfNascimento
				// para o Date e passa ao Calendar através
				// do setTime
				nascimento.setTime(formatador.parse(tfNascimento.getValue()
						.toString()));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(TelaFuncionario.this,
						e1.getMessage());
			}

			// cria um formatador para formata a data de Admissão
			SimpleDateFormat formatadorAdmissao = new SimpleDateFormat(
					"dd/MM/yyyy");
			// cria um objeto calendar
			Calendar admissao = Calendar.getInstance();
			try {
				// Converte o conteudo do tfAdmissao
				// para o Date e passa ao Calendar através
				// do setTime
				admissao.setTime(formatadorAdmissao.parse(tfAdmissao.getValue()
						.toString()));
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(TelaFuncionario.this,
						e1.getMessage());
			}
			// Atribui ao funcionario, o calendar criado
			funcionario.setNascimento(nascimento);
			funcionario.setEmail(tfEmail.getText().trim());
			funcionario.setEndereco(taEndereco.getText().trim());
			funcionario.setCpf(tfCpf.getValue().toString());
			funcionario.setTelefone(tfTel.getValue().toString());
			if (tfCel.getValue() != null) {
				funcionario.setCelular(tfCel.getValue().toString());
			}
			funcionario.setPis(tfPis.getValue().toString());
			funcionario.setAdmissao(admissao);

			funcionario.setTipo((GerenteEFuncionario) cbFunEGer
					.getSelectedItem());
			funcionario.setUsuario(tfUsuario.getText().trim());
			funcionario.setSenha(jpSenha.getText().trim());
			
			// incluir e alterar funcionarios

			if (funcionario.getId() == 0) {
				dao.incluir(funcionario);
			}
			// caso contrario, atualiza
			else {
				dao.alterar(funcionario);
			}
			limpar();
			criarTabela(dao.listar());
		}

	}

	ActionListener listenerExcluir = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {

			// se funcionario for diferente de null exclui funcionario
			if (funcionario != null) {

				dao.excluir(funcionario);
				criarTabela(dao.listar());
			}
			limpar();

		}

	};

	// Método para limnpar os campos da tela
	private void limpar() {
		tfNome.setText(null);
		tfNascimento.setValue(null);
		tfAdmissao.setValue(null);
		tfEmail.setText(null);
		taEndereco.setText(null);
		tfCpf.setValue(null);
		tfPis.setValue(null);
		tfTel.setValue(null);
		tfCel.setValue(null);
		groupNews.clearSelection();
		cbFunEGer.setSelectedIndex(-1);
		tfUsuario.setText(null);
		jpSenha.setText(null);
		tfCpf.setBackground(Color.white);
		tfNome.requestFocus();
		tfPis.setBackground(Color.white);
		funcionario = null;

	}
}
