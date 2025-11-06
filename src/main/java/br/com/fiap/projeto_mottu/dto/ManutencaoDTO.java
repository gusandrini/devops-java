package br.com.fiap.projeto_mottu.dto;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import br.com.fiap.projeto_mottu.model.Manutencao;
import br.com.fiap.projeto_mottu.model.Moto;

public class ManutencaoDTO extends RepresentationModel<ManutencaoDTO>{
	private Long id_manutencao;
	private Moto moto;
	private LocalDate dt_entrada;
	private LocalDate dt_saida;
	private String ds_manutencao;
	
	public ManutencaoDTO() {}

	public ManutencaoDTO(Long id_manutencao, Moto moto, LocalDate dt_entrada, LocalDate dt_saida,
			String ds_manutencao) {
		super();
		this.id_manutencao = id_manutencao;
		this.moto = moto;
		this.dt_entrada = dt_entrada;
		this.dt_saida = dt_saida;
		this.ds_manutencao = ds_manutencao;
	}

	public ManutencaoDTO(Manutencao manutencao) {
		setId_manutencao(manutencao.getId_manutencao());
		setMoto(manutencao.getMoto());
		setDt_entrada(manutencao.getDt_entrada());
		setDt_saida(manutencao.getDt_saida());
		setDs_manutencao(manutencao.getDs_manutencao());
	}
	
	public Long getId_manutencao() {
		return id_manutencao;
	}

	public void setId_manutencao(Long id_manutencao) {
		this.id_manutencao = id_manutencao;
	}

	public Moto getMoto() {
		return moto;
	}

	public void setMoto(Moto moto) {
		this.moto = moto;
	}

	public LocalDate getDt_entrada() {
		return dt_entrada;
	}

	public void setDt_entrada(LocalDate dt_entrada) {
		this.dt_entrada = dt_entrada;
	}

	public LocalDate getDt_saida() {
		return dt_saida;
	}

	public void setDt_saida(LocalDate dt_saida) {
		this.dt_saida = dt_saida;
	}

	public String getDs_manutencao() {
		return ds_manutencao;
	}

	public void setDs_manutencao(String ds_manutencao) {
		this.ds_manutencao = ds_manutencao;
	}
	
	
}
