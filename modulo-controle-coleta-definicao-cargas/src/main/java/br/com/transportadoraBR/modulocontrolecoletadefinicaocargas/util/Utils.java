package br.com.transportadoraBR.modulocontrolecoletadefinicaocargas.util;

import javax.servlet.http.HttpServletRequest;

public class Utils {

	public static Boolean verificarHeader(HttpServletRequest req) {
		if(req.getHeader("x-force") != null) {
			
			return true;
		}else {
			return false;
		}
	}
}
