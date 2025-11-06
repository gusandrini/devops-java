package br.com.fiap.projeto_mottu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.projeto_mottu.model.Filial;
import br.com.fiap.projeto_mottu.repository.FilialRepository;

@Service
public class FilialCachingService {

	@Autowired
	private FilialRepository repF;
	
	@Cacheable(value = "buscarTodosCache")
	public List<Filial> findAll(){
		return repF.findAll();
	}
	
	@Cacheable(value = "buscarPorID", key = "#id_filial")
	public Optional<Filial> findById(Long id_filial){
		return repF.findById(id_filial);
	}
	
	@Cacheable(value = "buscarPaginasFiliais", key = "#req")
	public Page<Filial> findAll(PageRequest req){
		return repF.findAll(req);
	}
	
	@CacheEvict(value = {"buscarTodosCache","buscarPorID", "buscarPaginasFiliais"},
			allEntries = true)
	public void limparCache() {
		System.out.println("Limpando cache!");
	}
}
