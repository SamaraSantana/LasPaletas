package br.senai.sp.paletas.modelo;

import java.util.Random;

public class ValidaCPF {

	public static boolean validarCPF(String cpf) {

		if (cpf.length() == 11) {
			if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
					|| cpf.equals("33333333333") || cpf.equals("44444444444")
					|| cpf.equals("55555555555") || cpf.equals("66666666666")
					|| cpf.equals("77777777777") || cpf.equals("88888888888")
					|| cpf.equals("99999999999")) {
				return false;
			} else {
				if (calcularDigito(cpf).equals(cpf.substring(9, 11))) {
					return true;
				}else{
					return false;
				}
			}
		}else{
			return false;
		}
	}
	public static String gerarCPF(){
		Random rand = new Random();
		String cpf="";
		for(int i=0; i<9;i++){
			cpf+=rand.nextInt(10);
			
		}
		return cpf+calcularDigito(cpf);
	}

	private static String calcularDigito(String cpf) {
		String cpfAux = cpf.substring(0, 9);
		int peso, soma, dig1, dig2;

		peso = 2;
		soma = 0;
		for (int i = 8; i >= 0; i--) {
			// pega o caractere no indice i e subtrai a posição de zero
			// na tabela ASCII (48 para chegar ao valor inteiro
			int n = cpfAux.charAt(i) - 48;
			// acrescenta o n* peso a variável soma
			soma += n * peso;
			peso++;
		}

		/*
		 * int modulo = soma %11; if(modulo ==1|| modulo ==0){ dig1=0; }else{
		 * dig1 = 11-modulo; }
		 */
		// se o resto da divisao da soma por 11 = 0 ou 1, dig1 será 1, caso
		// contrário
		// será a subtração de 11 pelo resto da divisão

		dig1 = (soma % 11 == 0 || soma % 11 == 1) ? 0 : 11 - soma % 11;
		cpfAux += dig1;
		peso = 2;
		soma = 0;
		for (int i = 9; i >= 0; i--) {
			// pega o caractere no indice i e subtrai a posição de zero
			// na tabela ASCII (48 para chegar ao valor inteiro
			int n = cpfAux.charAt(i) - 48;
			// acrescenta o n* peso a variável soma
			soma += n * peso;
			// incrementa o peso
			peso++;
		}
		dig2 = (soma % 11 == 0 || soma % 11 == 1) ? 0 : 11 - soma % 11;
		// concatena os dois digitos para retornar
		String retorno = dig1 + "" + dig2;
		return retorno;
	}
}
