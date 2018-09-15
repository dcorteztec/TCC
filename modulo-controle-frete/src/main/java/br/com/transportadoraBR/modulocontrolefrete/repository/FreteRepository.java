package br.com.transportadoraBR.modulocontrolefrete.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.transportadoraBR.modulocontrolefrete.domain.Frete;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long>{

	@Query(value = "select max(km_final) from public.frete", 
			  nativeQuery = true)
	Optional<Integer> findUltimoKmInicial();
	
	@Query(value =  "SELECT custo_por_km FROM public.frete where km_final >= :km and km_inicial <= :km and tipo_carga = :tipoCarga ", nativeQuery = true)
	Double findKmSimu(@Param("km")Integer km,@Param("tipoCarga")String tipoCarga);

}
