package br.com.fiap.projeto_mottu.dto;

import org.springframework.hateoas.RepresentationModel;

import br.com.fiap.projeto_mottu.model.Filial;
import br.com.fiap.projeto_mottu.model.Logradouro;

public class FilialDTO extends RepresentationModel<FilialDTO>{
	private Long id_filial;
	private Logradouro logradouro;
	private String nome_filial;
	
	public FilialDTO () {}

	public FilialDTO(Long id_filial, Logradouro logradouro, String nome_filial) {
		super();
		this.id_filial = id_filial;
		this.logradouro = logradouro;
		this.nome_filial = nome_filial;
	}
	
	public FilialDTO (Filial filial) {
		setId_filial(filial.getId_filial());
		setLogradouro(filial.getLogradouro());
		setNome_filial(filial.getNome_filial());
	}

	public Long getId_filial() {
		return id_filial;
	}

	public void setId_filial(Long id_filial) {
		this.id_filial = id_filial;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public String getNome_filial() {
		return nome_filial;
	}

	public void setNome_filial(String nome_filial) {
		this.nome_filial = nome_filial;
	}
	
	
	
}
