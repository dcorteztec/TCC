package br.com.transportadoraBR.modulocontrolefrete.domain;

public class SimulcaoFreteDTO {

	private Double valorFrete;
	private Double valorEixo;
	private Integer kmRodado;
	private String tipoCarga;
	private Integer qtdEixos;
	
	public Double getValorFrete() {
		return valorFrete;
	}
	public void setValorFrete(Double valorFrete) {
		this.valorFrete = valorFrete;
	}
	public Double getValorEixo() {
		return valorEixo;
	}
	public void setValorEixo(Double valorEixo) {
		this.valorEixo = valorEixo;
	}
	public Integer getKmRodado() {
		return kmRodado;
	}
	public void setKmRodado(Integer kmRodado) {
		this.kmRodado = kmRodado;
	}
	public String getTipoCarga() {
		return tipoCarga;
	}
	public void setTipoCarga(String tipoCarga) {
		this.tipoCarga = tipoCarga;
	}
	public Integer getQtdEixos() {
		return qtdEixos;
	}
	public void setQtdEixos(Integer qtdEixos) {
		this.qtdEixos = qtdEixos;
	}
	
	
}
