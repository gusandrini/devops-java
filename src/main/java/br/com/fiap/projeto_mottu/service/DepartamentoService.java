package br.com.fiap.projeto_mottu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.projeto_mottu.dto.DepartamentoDTO;
import br.com.fiap.projeto_mottu.model.Departamento;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoCachingService cacheD;
	
	@Transactional(readOnly = true)
	public Page<DepartamentoDTO> paginar(PageRequest req){
		
		Page<Departamento> paginas_departamentos = cacheD.findAll(req);
		Page<DepartamentoDTO> paginas_departamentos_dto = 
				paginas_departamentos.map(departamento -> new DepartamentoDTO(departamento));
		return paginas_departamentos_dto;
		
	}
}
