package br.com.transportadoraBR.modulocontrolefrete.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.transportadoraBR.modulocontrolefrete.domain.Frete;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long>{

	@Query(value = "select max(km_final) from public.frete", 
			  nativeQuery = true)
	Optional<Integer> findUltimoKmInicial();

}
