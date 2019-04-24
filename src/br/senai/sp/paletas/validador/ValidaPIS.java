package br.senai.sp.paletas.validador;

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
				if (pis.equals(pis)) {
					return true;
				}else{
					return false;
				}
			}
		}else{
			return false;
		}
	}
}
