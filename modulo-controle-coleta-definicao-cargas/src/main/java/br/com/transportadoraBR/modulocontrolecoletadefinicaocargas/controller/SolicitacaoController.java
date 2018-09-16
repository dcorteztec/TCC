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
import org.springframework.web.bind.annotation.PutMapping;
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
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).contentType(MediaType.APPLICATION_JSON).build();
	}
	
	@RequestMapping(value="listTransportadora" ,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<List<TransportadoraParceira>> listTransportadora(HttpServletRequest req, HttpServletResponse res){
		if(Utils.verificarHeader(req)) {
			List<TransportadoraParceira> list = transportadoraRepository.findAll();
			return new ResponseEntity<List<TransportadoraParceira>>(list, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).contentType(MediaType.APPLICATION_JSON).build();
	}
	
	@RequestMapping(value="listEmpresaParceira" ,method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<List<EmpresaParceira>> listEmpresaParceira(HttpServletRequest req, HttpServletResponse res){
		if(Utils.verificarHeader(req)) {
			List<EmpresaParceira> list = empresaParceiraRepository.findAll();
			return new ResponseEntity<List<EmpresaParceira>>(list, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).contentType(MediaType.APPLICATION_JSON).build();
	}
	
	@RequestMapping(value = "solicitacao/{id}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	private ResponseEntity<Optional<Solicitacao>> getSolic(@PathVariable("id") Long numeroSolic,HttpServletRequest req, HttpServletResponse res) {
		if(Utils.verificarHeader(req)) {
			Optional<Solicitacao> solicitacao = repository.findById(numeroSolic);
			if(solicitacao.get().getTransportadoraParceira()!=null)
				solicitacao.get().setIdTransportadora(solicitacao.get().getTransportadoraParceira().getCodTransportadora());
			if(solicitacao.get().getEmpresaParceira()!=null)
				solicitacao.get().setIdEmpresaParceira(solicitacao.get().getEmpresaParceira().getCodEmpresa());
			return new ResponseEntity<Optional<Solicitacao>>(solicitacao,HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).contentType(MediaType.APPLICATION_JSON).build();
		
	}
	
	@PostMapping(value="saveSolic")
	private ResponseEntity<Object> saveSolic(@RequestBody Solicitacao solicitacao,HttpServletRequest req, HttpServletResponse res) {
		if(Utils.verificarHeader(req)) {
			if(solicitacao.getDemandaTransferida()==null) {
				solicitacao.setDemandaTransferida(false);
			}
			if(solicitacao.getEmpresaParceira()!=null) {
				EmpresaParceira empresa = empresaParceiraRepository.findByName(solicitacao.getEmpresaParceira().getNome());
				if(empresa==null) {
					empresaParceiraRepository.save(solicitacao.getEmpresaParceira());
					EmpresaParceira empresaNova = empresaParceiraRepository.findByName(solicitacao.getEmpresaParceira().getNome());
					solicitacao.setEmpresaParceira(empresaNova);
				}else {
					solicitacao.setEmpresaParceira(empresa);
				}
			}
			if(solicitacao.getDemandaTransferida()&&solicitacao.getIdTransportadora()!=null) {
				Optional<TransportadoraParceira> transportadora = transportadoraRepository.findById(solicitacao.getIdTransportadora());
					if(transportadora!=null&&transportadora.isPresent()) {
						solicitacao.setTransportadoraParceira(transportadora.get());
					}
			}
			repository.flush();
			repository.save(solicitacao);
		}
		return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).build();
	}
	
	@PutMapping(value="update/{id}")
	private ResponseEntity<Object> updateSolic(@PathVariable("id") Long id,@RequestBody Solicitacao solicitacao,
			HttpServletRequest req, HttpServletResponse res) {
		if(Utils.verificarHeader(req)) {
			Optional<Solicitacao> solic = repository.findById(id);
			if(!solic.isPresent()) {
				return ResponseEntity.notFound().build();
			}else {
				solicitacao.setId(id);
				repository.save(solicitacao);
				return ResponseEntity.noContent().build();
			}
			
		}
		return ResponseEntity.status(HttpStatus.BAD_GATEWAY).contentType(MediaType.APPLICATION_JSON).build();
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