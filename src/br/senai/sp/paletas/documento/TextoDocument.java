package br.senai.sp.paletas.documento;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

//classe para definir o comprotamento ao inserir
//um texto em uma textField ou textArea

public class TextoDocument extends PlainDocument {
	// m�todo que � disparado ao inserir o texto

	@Override
	public void insertString(int offs, String str, AttributeSet a)
			throws BadLocationException {
		if (getLength() + str.length() <= 50) {
			super.insertString(offs, str, a);
		}
	}
}
