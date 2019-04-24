package br.senai.sp.paletas.tela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginAdm extends JFrame implements ActionListener {
	JPanel pnCentral, pnBotao;
	JLabel lbUsuario, lbSenha, imagem,lbAdm;
	JTextField tfUsuario;
	JPasswordField jpSenha;
	Font fontePadrao, fontePadrao2,fontePadrao3;
	JButton btEntrar;

	public LoginAdm() {
		inicializarComponentes();
		definirEventos();

	}

	private void inicializarComponentes() {
		// fonte padrao
		fontePadrao = new Font("Verdana", Font.BOLD | Font.ITALIC, 16);
		
	

		// fonte padrao2
		fontePadrao2 = new Font("Arial", Font.PLAIN, 14);
		
		// fonte padrao3
				fontePadrao3 = new Font("Verdana", Font.BOLD | Font.ITALIC, 20);

		// pnCentral
		pnCentral = new JPanel();
		pnCentral.setBackground(new Color(178, 34 ,34));
		pnCentral.setLayout(null);

		// lbNome(label Usuario)
		lbUsuario = new JLabel("Login:");
		lbUsuario.setBounds(60, 280, 80, 30);
		lbUsuario.setFont(fontePadrao);

		// Text Field Login(tfUsuario)
		tfUsuario = new JTextField();
		tfUsuario.setBackground(Color.white);
		tfUsuario.setBounds(130, 280, 200, 30);
		tfUsuario.setFont(fontePadrao2);

		// lbNome(label Usuario)
		lbSenha = new JLabel("Senha:");
		lbSenha.setBounds(60, 320, 100, 30);
		lbSenha.setFont(fontePadrao);

		// Text Field Login(tfUsuario)
		jpSenha = new JPasswordField();
		jpSenha.setBackground(Color.white);
		jpSenha.setBounds(130, 320, 200, 30);
		jpSenha.setFont(fontePadrao2);

		imagem = new JLabel();
		imagem.setBounds(110, 10, 408, 261);
		imagem.setIcon(new ImageIcon(getClass().getResource(
				"/imagens/logoVermelho.png")));
		
		
		// lbNome(label Usuario)
				lbAdm = new JLabel("Administrador");
				lbAdm.setBounds(150, 240, 180, 30);
				lbAdm.setFont(fontePadrao3);
				
		// btSalvar
		btEntrar = new JButton("Entrar");
		btEntrar.setFont(fontePadrao);
		btEntrar.setBackground(Color.white);
		btEntrar.setMnemonic(KeyEvent.VK_S);

		// Painel do botao salvar,limpar e excluir
		pnBotao = new JPanel();
		GridLayout layout = new GridLayout(1, 3);
		layout.setHgap(5);
		pnBotao.setLayout(layout);
		pnBotao.setBounds(170, 380, 100, 30);
		pnBotao.add(btEntrar);

		// adiconar ao pnCentral(Painel central)
		pnCentral.add(lbUsuario);
		pnCentral.add(tfUsuario);
		pnCentral.add(lbSenha);
		pnCentral.add(jpSenha);
		pnCentral.add(imagem);
		pnCentral.add(pnBotao);
		pnCentral.add(lbAdm);

		// Adicionando os componentes

		add(pnCentral, BorderLayout.CENTER);

		// ****definir tamanho da janela ****
		setSize(500, 500);

		// ****Centralizar janela
		setLocationRelativeTo(null);

		// ****Colocar nome na janela****
		setTitle("Login Administrador");

		// mudar de fundo do da janela do java e nova cor
		// getContentPane().setBackground(Color.red);
		getContentPane().setBackground(new Color(255, 165, 0));
		// ****Nao redimencionar a janela****
		setResizable(true);
		// ****Desenha a tela, ultima coisa a ser colocada apos o inicializaçao
		// componentes****

		setVisible(true);

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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
