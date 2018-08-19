package br.com.transportadoraBR.modulocontrolecoletadefinicaocargas.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class EmpresaParceira extends AuditModel{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(generator = "empresa_parceira_generator")
    @SequenceGenerator(
            name = "empresa_parceira_generator",
            sequenceName = "empresa_parceira_sequence",
            initialValue = 1000
    )
	private Long codEmpresa;
	private String nome;
	
	public Long getCodEmpresa() {
		return codEmpresa;
	}
	public void setCodEmpresa(Long codEmpresa) {
		this.codEmpresa = codEmpresa;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
