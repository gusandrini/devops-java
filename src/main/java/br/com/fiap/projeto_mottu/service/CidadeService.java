package br.com.fiap.projeto_mottu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.projeto_mottu.dto.CidadeDTO;
import br.com.fiap.projeto_mottu.model.Cidade;

@Service
public class CidadeService {
	@Autowired
	private CidadeCachingService cacheCid;
	
	@Transactional(readOnly = true)
	public Page<CidadeDTO> paginar(PageRequest req){
		
		Page<Cidade> paginas_cidades = cacheCid.findAll(req);
		Page<CidadeDTO> paginas_cidades_dto = 
				paginas_cidades.map(cidade -> new CidadeDTO(cidade));
		return paginas_cidades_dto;
		
	}
}
