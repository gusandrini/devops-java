package br.com.fiap.projeto_mottu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.projeto_mottu.dto.MotoDTO;
import br.com.fiap.projeto_mottu.model.Moto;

@Service
public class MotoService {

	@Autowired
	private MotoCachingService cacheM;
	
	@Transactional(readOnly = true)
	public Page<MotoDTO> paginar(PageRequest req){
		
		Page<Moto> paginas_motos = cacheM.findAll(req);
		Page<MotoDTO> paginas_motos_dto = 
				paginas_motos.map(moto -> new MotoDTO(moto));
		return paginas_motos_dto;
		
	}
}
