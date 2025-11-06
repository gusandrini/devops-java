package br.com.fiap.projeto_mottu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.projeto_mottu.dto.BairroDTO;
import br.com.fiap.projeto_mottu.model.Bairro;

@Service
public class BairroService {
	@Autowired
	private BairroCachingService cacheB;
	
	@Transactional(readOnly = true)
	public Page<BairroDTO> paginar(PageRequest req){
		
		Page<Bairro> paginas_bairros = cacheB.findAll(req);
		Page<BairroDTO> paginas_bairros_dto = 
				paginas_bairros.map(bairro -> new BairroDTO(bairro));
		return paginas_bairros_dto;
		
	}
}
