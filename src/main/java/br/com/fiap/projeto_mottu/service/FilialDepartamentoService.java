package br.com.fiap.projeto_mottu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.projeto_mottu.dto.FilialDepartamentoDTO;
import br.com.fiap.projeto_mottu.model.FilialDepartamento;

@Service
public class FilialDepartamentoService {

	@Autowired
	private FilialDepartamentoCachingService cacheFD;
	
	@Transactional(readOnly = true)
	public Page<FilialDepartamentoDTO> paginar(PageRequest req){
		
		Page<FilialDepartamento> paginas_filiais_departamentos = cacheFD.findAll(req);
		Page<FilialDepartamentoDTO> paginas_filiais_departamentos_dto = 
				paginas_filiais_departamentos.map(filial_departamento -> new FilialDepartamentoDTO(filial_departamento));
		return paginas_filiais_departamentos_dto;
		
	}
}
