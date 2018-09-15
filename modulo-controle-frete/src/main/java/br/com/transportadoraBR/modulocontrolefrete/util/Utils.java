package br.com.transportadoraBR.modulocontrolefrete.util;

import javax.servlet.http.HttpServletRequest;

import br.com.transportadoraBR.modulocontrolefrete.domain.Frete;
import br.com.transportadoraBR.modulocontrolefrete.domain.SimulcaoFreteDTO;

public class Utils {

	public static Boolean verificarHeader(HttpServletRequest req) {
		if(req.getHeader("x-force") != null) {
			return true;
		}else {
			return false;
		}
	}

	public static SimulcaoFreteDTO calcularSimulacao(Frete frete, SimulcaoFreteDTO simu) {
		simu.setValorEixo(frete.getCustoPorKm()*simu.getQtdEixos());
		simu.setValorFrete(simu.getValorEixo()*simu.getKmRodado());
		
		return simu;
	}
	
	
}
