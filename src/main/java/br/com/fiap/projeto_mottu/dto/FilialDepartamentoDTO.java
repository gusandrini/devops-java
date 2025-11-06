package br.com.fiap.projeto_mottu.dto;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import br.com.fiap.projeto_mottu.model.Departamento;
import br.com.fiap.projeto_mottu.model.Filial;
import br.com.fiap.projeto_mottu.model.FilialDepartamento;

public class FilialDepartamentoDTO extends RepresentationModel<FilialDepartamentoDTO>{
	private Long id_filial_departamento;
	private Filial filial;
	private Departamento departamento;
	private LocalDate dt_entrada;
	private LocalDate dt_saida;
	
	public FilialDepartamentoDTO () {}

	public FilialDepartamentoDTO(Long id_filial_departamento, Filial filial, Departamento departamento,
			LocalDate dt_entrada, LocalDate dt_saida) {
		super();
		this.id_filial_departamento = id_filial_departamento;
		this.filial = filial;
		this.departamento = departamento;
		this.dt_entrada = dt_entrada;
		this.dt_saida = dt_saida;
	}
	
	public FilialDepartamentoDTO (FilialDepartamento filialDepartamento) {
		setId_filial_departamento(filialDepartamento.getId_filial_departamento());
		setFilial(filialDepartamento.getFilial());
		setDepartamento(filialDepartamento.getDepartamento());
		setDt_entrada(filialDepartamento.getDt_entrada());
		setDt_saida(filialDepartamento.getDt_saida());
	}

	public Long getId_filial_departamento() {
		return id_filial_departamento;
	}

	public void setId_filial_departamento(Long id_filial_departamento) {
		this.id_filial_departamento = id_filial_departamento;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
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
	
	
}
