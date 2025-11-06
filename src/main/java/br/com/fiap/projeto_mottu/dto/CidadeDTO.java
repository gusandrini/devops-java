package br.com.fiap.projeto_mottu.dto;

import br.com.fiap.projeto_mottu.model.Cidade;
import br.com.fiap.projeto_mottu.model.Estado;

public class CidadeDTO {
	private Long id_cidade;
	private Estado estado;
	private String nm_cidade;
	
	public CidadeDTO() {}

	public CidadeDTO(Long id_cidade, Estado estado, String nm_cidade) {
		super();
		this.id_cidade = id_cidade;
		this.estado = estado;
		this.nm_cidade = nm_cidade;
	}
	
	public CidadeDTO(Cidade cidade) {
		setId_cidade(cidade.getId_cidade());
		setEstado(cidade.getEstado());
		setNm_cidade(cidade.getNm_cidade());
	}

	public Long getId_cidade() {
		return id_cidade;
	}

	public void setId_cidade(Long id_cidade) {
		this.id_cidade = id_cidade;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getNm_cidade() {
		return nm_cidade;
	}

	public void setNm_cidade(String nm_cidade) {
		this.nm_cidade = nm_cidade;
	}
	
	
}
