package br.com.transportadoraBR.modulocontrolefrete.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Frete extends AuditModel{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "frete_generator")
    @SequenceGenerator(
            name = "frete_generator",
            sequenceName = "frete_sequence",
            initialValue = 1000
    )
	private Long id;
	private Integer kmInicial;
	private Integer kmFinal;
	private Double custoPorKm;
	
	public Integer getKmInicial() {
		return kmInicial;
	}
	public void setKmInicial(Integer kmInicial) {
		this.kmInicial = kmInicial;
	}
	public Integer getKmFinal() {
		return kmFinal;
	}
	public void setKmFinal(Integer kmFinal) {
		this.kmFinal = kmFinal;
	}
	public Double getCustoPorKm() {
		return custoPorKm;
	}
	public void setCustoPorKm(Double custoPorKm) {
		this.custoPorKm = custoPorKm;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}
