package br.com.transportadoraBR.modulocontrolefrete.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FreteController {

	@RequestMapping(value = "teste", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getStringTest() {
		return "teste";
	}
}
