package br.com.fiap.projeto_mottu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.projeto_mottu.dto.ManutencaoDTO;
import br.com.fiap.projeto_mottu.model.Manutencao;

@Service
public class ManutencaoService {

	@Autowired
	private ManutencaoCachingService cacheMan;
	
	@Transactional(readOnly = true)
	public Page<ManutencaoDTO> paginar(PageRequest req){
		
		Page<Manutencao> paginas_manutencoes = cacheMan.findAll(req);
		Page<ManutencaoDTO> paginas_manutencoes_dto = 
				paginas_manutencoes.map(manutencao -> new ManutencaoDTO(manutencao));
		return paginas_manutencoes_dto;
		
	}
}
