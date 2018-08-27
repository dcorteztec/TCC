package br.com.transportadoraBR.modulocontrolecoletadefinicaocargas.controller;

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

import br.com.transportadoraBR.modulocontrolecoletadefinicaocargas.domain.EmpresaParceira;
import br.com.transportadoraBR.modulocontrolecoletadefinicaocargas.domain.Solicitacao;
import br.com.transportadoraBR.modulocontrolecoletadefinicaocargas.domain.TransportadoraParceira;
import br.com.transportadoraBR.modulocontrolecoletadefinicaocargas.repository.EmpresaParceiraRepository;
import br.com.transportadoraBR.modulocontrolecoletadefinicaocargas.repository.SolicitacaoRepository;
import br.com.transportadoraBR.modulocontrolecoletadefinicaocargas.repository.TransportadoraParceiraRepository;
import br.com.transportadoraBR.modulocontrolecoletadefinicaocargas.util.Utils;

@RestController
public class SolicitacaoController {

	@Autowired
	private SolicitacaoRepository repository;
	
	@Autowired
	private EmpresaParceiraRepository empresaParceiraRepository;
	
	@Autowired
	private TransportadoraParceiraRepository transportadoraRepository;
	
	@RequestMapping(value="listSolic" ,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<List<Solicitacao>> listSolic(HttpServletRequest req, HttpServletResponse res){
		if(Utils.verificarHeader(req)) {
			List<Solicitacao> list = repository.findAll();
			return new ResponseEntity<List<Solicitacao>>(list, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
	}
	
	@RequestMapping(value = "solicitacao/{numeroSolic}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Optional<Solicitacao>> getSolic(@PathVariable("numeroSolic") Long numeroSolic,HttpServletRequest req, HttpServletResponse res) {
		if(Utils.verificarHeader(req)) {
			Optional<Solicitacao> solicitacao = repository.findById(numeroSolic);
			return new ResponseEntity<Optional<Solicitacao>>(solicitacao,HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
		
	}
	
	@PostMapping(value="saveSolic")
	private ResponseEntity<Object> saveSolic(@RequestBody Solicitacao solicitacao,HttpServletRequest req, HttpServletResponse res) {
		if(Utils.verificarHeader(req)) {
			if(solicitacao.getEmpresaParceira()!=null) {
				EmpresaParceira empresa = empresaParceiraRepository.findByName(solicitacao.getEmpresaParceira().getNome());
				if(empresa==null) {
					empresaParceiraRepository.save(solicitacao.getEmpresaParceira());
				}
			}
			if(solicitacao.getDemandaTransferida()&&solicitacao.getTransportadoraParceira()!=null) {
				TransportadoraParceira transportadora = transportadoraRepository.findByName(solicitacao.getTransportadoraParceira().getNome());
				if(transportadora==null) {
					transportadoraRepository.save(solicitacao.getTransportadoraParceira());
				}
			}
			repository.flush();
			repository.save(solicitacao);
		}
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PostMapping(value="update/{numeroSolic}")
	private ResponseEntity<Object> updateSolic(@RequestBody Solicitacao solicitacao, @PathVariable long numeroSolic,
			HttpServletRequest req, HttpServletResponse res) {
		if(Utils.verificarHeader(req)) {
			Optional<Solicitacao> solic = repository.findById(numeroSolic);
			if(!solic.isPresent()) {
				return ResponseEntity.notFound().build();
			}else {
				solicitacao.setNumeroSolic(numeroSolic);
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
	
	@SuppressWarnings("static-access")
	@RequestMapping(value = "teste", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getStringTest(HttpServletRequest req, HttpServletResponse res) {
		if (Utils.verificarHeader(req)) {
			return "teste";
		}
		return String.valueOf(res.SC_FORBIDDEN);

	}
	
	
}