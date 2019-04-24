package br.senai.sp.paletas.main;

import br.senai.sp.paletas.tela.Login;
import br.senai.sp.paletas.tela.TelaInicial;

public class Main {
	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				System.out.println(info);
				if ("Nimbus".equals(info.getName())) {

					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			
				new Login();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
