package br.com.transportadoraBR.modulocontrolecoletadefinicaocargas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.transportadoraBR.modulocontrolecoletadefinicaocargas.domain.TransportadoraParceira;

@Repository
public interface TransportadoraParceiraRepository extends JpaRepository<TransportadoraParceira, Long>{

	@Query("SELECT e FROM TransportadoraParceira e where e.nome = :nome")
	TransportadoraParceira findByName(@Param("nome")String nome);

}
