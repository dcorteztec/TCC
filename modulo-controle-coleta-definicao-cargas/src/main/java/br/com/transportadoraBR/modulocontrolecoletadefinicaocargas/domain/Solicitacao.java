package br.com.transportadoraBR.modulocontrolecoletadefinicaocargas.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table
public class Solicitacao extends AuditModel{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(generator = "solicitacao_generator")
    @SequenceGenerator(
            name = "solicitacao_generator",
            sequenceName = "solicitacao_sequence",
            initialValue = 1000
    )
	private Long numeroSolic;
	private Date dataSolic;
	private Date dataVolta;
	private String nomeSolic;
	private String emailSolic;
	private String endeSolic;
	private String veiculo;
	private Boolean demandaTransferida;
	@ManyToOne
	@JoinColumn(name = "id_empresa", nullable = true)
	private EmpresaParceira empresaParceira;
	@ManyToOne
	@JoinColumn(name = "id_transportadora", nullable = false)
	private TransportadoraParceira transportadoraParceira;
       
	public Long getNumeroSolic() {
		return numeroSolic;
	}
	public void setNumeroSolic(Long numeroSolic) {
		this.numeroSolic = numeroSolic;
	}
	public Date getDataSolic() {
		return dataSolic;
	}
	public void setDataSolic(Date dataSolic) {
		this.dataSolic = dataSolic;
	}
	public Date getDataVolta() {
		return dataVolta;
	}
	public void setDataVolta(Date dataVolta) {
		this.dataVolta = dataVolta;
	}
	public String getNomeSolic() {
		return nomeSolic;
	}
	public void setNomeSolic(String nomeSolic) {
		this.nomeSolic = nomeSolic;
	}
	public String getEmailSolic() {
		return emailSolic;
	}
	public void setEmailSolic(String emailSolic) {
		this.emailSolic = emailSolic;
	}
	public String getEndeSolic() {
		return endeSolic;
	}
	public void setEndeSolic(String endeSolic) {
		this.endeSolic = endeSolic;
	}
	public String getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}
	public Boolean getDemandaTransferida() {
		return demandaTransferida;
	}
	public void setDemandaTransferida(Boolean demandaTransferida) {
		this.demandaTransferida = demandaTransferida;
	}
	public EmpresaParceira getEmpresaParceira() {
		return empresaParceira;
	}
	public void setEmpresaParceira(EmpresaParceira empresaParceira) {
		this.empresaParceira = empresaParceira;
	}
	public TransportadoraParceira getTransportadoraParceira() {
		return transportadoraParceira;
	}
	public void setTransportadoraParceira(TransportadoraParceira transportadoraParceira) {
		this.transportadoraParceira = transportadoraParceira;
	}
}