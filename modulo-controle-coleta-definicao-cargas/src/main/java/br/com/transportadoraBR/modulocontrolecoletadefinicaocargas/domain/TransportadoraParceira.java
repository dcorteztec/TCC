package br.com.transportadoraBR.modulocontrolecoletadefinicaocargas.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class TransportadoraParceira extends AuditModel{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(generator = "transportadora_parceira_generator")
    @SequenceGenerator(
            name = "transportadora_parceira_generator",
            sequenceName = "transportadora_parceira_sequence",
            initialValue = 1000
    )
	private Long codTransportadora;
	private String nome;
	
	public Long getCodTransportadora() {
		return codTransportadora;
	}
	public void setCodTransportadora(Long codTransportadora) {
		this.codTransportadora = codTransportadora;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
