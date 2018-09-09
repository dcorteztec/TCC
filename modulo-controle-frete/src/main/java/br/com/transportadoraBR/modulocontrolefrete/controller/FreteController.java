package br.com.transportadoraBR.modulocontrolefrete.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.transportadoraBR.modulocontrolefrete.domain.Frete;
import br.com.transportadoraBR.modulocontrolefrete.repository.FreteRepository;
import br.com.transportadoraBR.modulocontrolefrete.util.Utils;

@RestController
public class FreteController {

	@Autowired
	private FreteRepository repository;
	
	@RequestMapping(value="listFrete" ,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<List<Frete>> listSolic(HttpServletRequest req, HttpServletResponse res){
		if(Utils.verificarHeader(req)) {
			List<Frete> list = repository.findAll();
			return new ResponseEntity<List<Frete>>(list, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
	}
	
	@RequestMapping(value = "frete/{id}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Optional<Frete>> getSolic(@PathVariable("id") Long id,HttpServletRequest req, HttpServletResponse res) {
		if(Utils.verificarHeader(req)) {
			Optional<Frete> solicitacao = repository.findById(id);
			return new ResponseEntity<Optional<Frete>>(solicitacao,HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
	}
	
	@RequestMapping(value = "ultimoKinical", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Optional<Integer>> ultimoKinical(HttpServletRequest req, HttpServletResponse res) {
		if(Utils.verificarHeader(req)) {
			Optional<Integer> solicitacao = repository.findUltimoKmInicial();
			return new ResponseEntity<Optional<Integer>>(solicitacao,HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
	}
	
	@PostMapping(value="saveFrete")
	private ResponseEntity<Object> saveSolic(@RequestBody Frete solicitacao,HttpServletRequest req, HttpServletResponse res) {
		if(Utils.verificarHeader(req)) {
			repository.save(solicitacao);
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PostMapping(value="update/{id}")
	private ResponseEntity<Object> updateSolic(@RequestBody Frete solicitacao, @PathVariable long id,
			HttpServletRequest req, HttpServletResponse res) {
		if(Utils.verificarHeader(req)) {
			Optional<Frete> solic = repository.findById(id);
			if(!solic.isPresent()) {
				return ResponseEntity.notFound().build();
			}else {
				solicitacao.setId(id);
				repository.save(solicitacao);
				return ResponseEntity.noContent().build();
			}
			
		}
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
	}
	
	@DeleteMapping("delete/{id}")
	public void deleteSolicitacao(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	@RequestMapping(value = "teste", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getStringTest() {
		return "teste";
	}
}
