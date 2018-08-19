package br.com.transportadoraBR.modulocontrolecoletadefinicaocargas.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.transportadoraBR.modulocontrolecoletadefinicaocargas.repository.SolicitacaoRepository;

@RestController
public class SolicitacaoController {

	@Autowired
	private SolicitacaoRepository repository;

	@SuppressWarnings("static-access")
	@RequestMapping(value = "teste", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getStringTest(HttpServletRequest req, HttpServletResponse res) {
		if (req.getHeader("x-force") != null) {
			return "teste";
		}
		return String.valueOf(res.SC_FORBIDDEN);

	}
}