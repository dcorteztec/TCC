package br.com.transportadoraBR.modulocontrolefrete.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.transportadoraBR.modulocontrolefrete.domain.Frete;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long>{

}
