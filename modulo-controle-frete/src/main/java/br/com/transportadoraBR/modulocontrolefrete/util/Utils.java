package br.com.transportadoraBR.modulocontrolefrete.util;

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
