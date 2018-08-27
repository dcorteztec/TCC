package br.com.transportadoraBR.modulocontrolecoletadefinicaocargas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.transportadoraBR.modulocontrolecoletadefinicaocargas.domain.EmpresaParceira;

@Repository
public interface EmpresaParceiraRepository extends JpaRepository<EmpresaParceira, Long>{

	@Query("SELECT e FROM EmpresaParceira e where e.nome = :nome")
	EmpresaParceira findByName(@Param("nome")String nome);

}
