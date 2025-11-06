package br.com.fiap.projeto_mottu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.projeto_mottu.dto.EstadoDTO;
import br.com.fiap.projeto_mottu.model.Estado;

@Service
public class EstadoService {
	@Autowired
	private EstadoCachingService cacheE;
	
	@Transactional(readOnly = true)
	public Page<EstadoDTO> paginar(PageRequest req){
		
		Page<Estado> paginas_estados = cacheE.findAll(req);
		Page<EstadoDTO> paginas_estados_dto = 
				paginas_estados.map(estado -> new EstadoDTO(estado));
		return paginas_estados_dto;
		
	}
}
