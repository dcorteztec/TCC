package br.com.transportadoraBR.modulocontrolecoletadefinicaocargas.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


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
	private Long id;
	private Date dataSolic;
	private Date dataVolta;
	private String nomeSolic;
	private String emailSolic;
	private String endeSolic;
	private String cidade;
	private String uf;
	private String veiculo;
	private Boolean demandaTransferida;
	@Transient
	private Long idTransportadora;
	@ManyToOne
	@JoinColumn(name = "id_empresa", nullable = true)
	private EmpresaParceira empresaParceira;
	@ManyToOne
	@JoinColumn(name = "id_transportadora", nullable = true)
	private TransportadoraParceira transportadoraParceira;
       
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
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public Long getIdTransportadora() {
		return idTransportadora;
	}
	public void setIdTransportadora(Long idTransportadora) {
		this.idTransportadora = idTransportadora;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
