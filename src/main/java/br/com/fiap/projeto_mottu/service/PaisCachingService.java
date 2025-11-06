package br.com.fiap.projeto_mottu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.projeto_mottu.model.Pais;
import br.com.fiap.projeto_mottu.repository.PaisRepository;

@Service
public class PaisCachingService {

	@Autowired
	private PaisRepository repP;
	
	@Cacheable(value = "buscarTodosCache")
	public List<Pais> findAll(){
		return repP.findAll();
	}
	
	@Cacheable(value = "buscarPorID", key = "#id_pais")
	public Optional<Pais> findById(Long id_pais){
		return repP.findById(id_pais);
	}
	
	@Cacheable(value = "buscarPaginasPaises", key = "#req")
	public Page<Pais> findAll(PageRequest req){
		return repP.findAll(req);
	}
	
	@CacheEvict(value = {"buscarTodosCache","buscarPorID", "buscarPaginasPaises"},
			allEntries = true)
	public void limparCache() {
		System.out.println("Limpando cache!");
	}
}
