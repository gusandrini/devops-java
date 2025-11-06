package br.com.fiap.projeto_mottu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.projeto_mottu.dto.TelefoneDTO;
import br.com.fiap.projeto_mottu.model.Telefone;

@Service
public class TelefoneService {
	
	@Autowired
	private TelefoneCachingService cacheT;
	
	@Transactional(readOnly = true)
	public Page<TelefoneDTO> paginar(PageRequest req){
		
		Page<Telefone> paginas_telefones = cacheT.findAll(req);
		Page<TelefoneDTO> paginas_telefones_dto = 
				paginas_telefones.map(telefone -> new TelefoneDTO(telefone));
		return paginas_telefones_dto;
		
	}
}
