package br.com.fiap.projeto_mottu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.projeto_mottu.dto.PaisDTO;
import br.com.fiap.projeto_mottu.model.Pais;

@Service
public class PaisService {
	@Autowired
	private PaisCachingService cacheP;
	
	@Transactional(readOnly = true)
	public Page<PaisDTO> paginar(PageRequest req){
		
		Page<Pais> paginas_paises = cacheP.findAll(req);
		Page<PaisDTO> paginas_paises_dto = 
				paginas_paises.map(pais -> new PaisDTO(pais));
		return paginas_paises_dto;
		
	}
}
