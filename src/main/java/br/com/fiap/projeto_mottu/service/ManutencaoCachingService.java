package br.com.fiap.projeto_mottu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.projeto_mottu.model.Manutencao;
import br.com.fiap.projeto_mottu.repository.ManutencaoRepository;

@Service
public class ManutencaoCachingService {

	@Autowired
	private ManutencaoRepository repMan;
	
	@Cacheable(value = "buscarTodosCache")
	public List<Manutencao> findAll(){
		return repMan.findAll();
	}
	
	@Cacheable(value = "buscarPorID", key = "#id_manutencao")
	public Optional<Manutencao> findById(Long id_manutencao){
		return repMan.findById(id_manutencao);
	}
	
	@Cacheable(value = "buscarPaginasManutencoes", key = "#req")
	public Page<Manutencao> findAll(PageRequest req){
		return repMan.findAll(req);
	}
	
	@CacheEvict(value = {"buscarTodosCache","buscarPorID", "buscarPaginasManutencoes"},
			allEntries = true)
	public void limparCache() {
		System.out.println("Limpando cache!");
	}
}
