package br.com.transportadoraBR.modulocontrolecoletadefinicaocargas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.transportadoraBR.modulocontrolecoletadefinicaocargas.domain.Solicitacao;

@Repository
public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {

}
