package br.com.fiap.projeto_mottu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.projeto_mottu.dto.LogradouroDTO;
import br.com.fiap.projeto_mottu.model.Logradouro;

@Service
public class LogradouroService {

	@Autowired
	private LogradouroCachingService cacheL;
	
	@Transactional(readOnly = true)
	public Page<LogradouroDTO> paginar(PageRequest req){
		
		Page<Logradouro> paginas_logradouros = cacheL.findAll(req);
		Page<LogradouroDTO> paginas_logradouros_dto = 
				paginas_logradouros.map(logradouro -> new LogradouroDTO(logradouro));
		return paginas_logradouros_dto;
		
	}
}
