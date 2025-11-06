package br.com.fiap.projeto_mottu.dto;

import org.springframework.hateoas.RepresentationModel;

import br.com.fiap.projeto_mottu.model.Departamento;

public class DepartamentoDTO extends RepresentationModel<DepartamentoDTO>{
	private Long id_departamento;
	private String nm_departamento;
	private String desc_departamento;
	
	public DepartamentoDTO () {}

	public DepartamentoDTO(Long id_departamento, String nm_departamento, String desc_departamento) {
		super();
		this.id_departamento = id_departamento;
		this.nm_departamento = nm_departamento;
		this.desc_departamento = desc_departamento;
	}
	
	public DepartamentoDTO (Departamento departamento) {
		setId_departamento(departamento.getId_departamento());
		setNm_departamento(departamento.getNm_departamento());
		setDesc_departamento(departamento.getDesc_departamento());
	}

	public Long getId_departamento() {
		return id_departamento;
	}

	public void setId_departamento(Long id_departamento) {
		this.id_departamento = id_departamento;
	}

	public String getNm_departamento() {
		return nm_departamento;
	}

	public void setNm_departamento(String nm_departamento) {
		this.nm_departamento = nm_departamento;
	}

	public String getDesc_departamento() {
		return desc_departamento;
	}

	public void setDesc_departamento(String desc_departamento) {
		this.desc_departamento = desc_departamento;
	}
	
	
}
