package br.senai.sp.paletas.modelo;


	import java.util.Random;
import java.util.Random;

public class ValidaPIS {

		public static boolean validarPIS(String pis) {

			if (pis.length() == 11) {
				if (pis.equals("00000000000") || pis.equals("11111111111") || pis.equals("22222222222")
						|| pis.equals("33333333333") || pis.equals("44444444444")
						|| pis.equals("55555555555") || pis.equals("66666666666")
						|| pis.equals("77777777777") || pis.equals("88888888888")
						|| pis.equals("99999999999")) {
					return false;
				} else {
					if (calcularDigito(pis).equals(pis.substring(9, 11))) {
						return true;
					}else{
						return false;
					}
				}
			}else{
				return false;
			}
		}
		public static String gerarPis(){
			Random rand = new Random();
			String pis="";
			for(int i=0; i<9;i++){
				pis+=rand.nextInt(10);
				
			}
			return pis+calcularDigito(pis);
		}

		private static String calcularDigito(String pis) {
			String pisAux = pis.substring(0, 9);
			int peso, soma, dig1, dig2;

			peso = 2;
			soma = 0;
			for (int i = 8; i >= 0; i--) {
				// pega o caractere no indice i e subtrai a posição de zero
				// na tabela ASCII (48 para chegar ao valor inteiro
				int n = pisAux.charAt(i) - 48;
				// acrescenta o n* peso a variável soma
				soma += n * peso;
				peso++;
			}

			
			// se o resto da divisao da soma por 11 = 0 ou 1, dig1 será 1, caso
			// contrário
			// será a subtração de 11 pelo resto da divisão

			dig1 = (soma % 11 == 0 || soma % 11 == 1) ? 0 : 11 - soma % 11;
			pisAux += dig1;
			peso = 2;
			soma = 0;
			for (int i = 9; i >= 0; i--) {
				// pega o caractere no indice i e subtrai a posição de zero
				// na tabela ASCII (48 para chegar ao valor inteiro
				int n = pisAux.charAt(i) - 48;
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


