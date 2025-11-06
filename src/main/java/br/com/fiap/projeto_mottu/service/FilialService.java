package br.com.fiap.projeto_mottu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.projeto_mottu.dto.FilialDTO;
import br.com.fiap.projeto_mottu.model.Filial;

@Service
public class FilialService {

	@Autowired
	private FilialCachingService cacheF;
	
	@Transactional(readOnly = true)
	public Page<FilialDTO> paginar(PageRequest req){
		
		Page<Filial> paginas_filiais = cacheF.findAll(req);
		Page<FilialDTO> paginas_filiais_dto = 
				paginas_filiais.map(filial -> new FilialDTO(filial));
		return paginas_filiais_dto;
		
	}
}
