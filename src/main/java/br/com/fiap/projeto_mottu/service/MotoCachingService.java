package br.com.fiap.projeto_mottu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.projeto_mottu.model.Moto;
import br.com.fiap.projeto_mottu.repository.MotoRepository;

@Service
public class MotoCachingService {

	@Autowired
	private MotoRepository repM;
	
	@Cacheable(value = "buscarTodosCache")
	public List<Moto> findAll(){
		return repM.findAll();
	}
	
	@Cacheable(value = "buscarPorID", key = "#id_moto")
	public Optional<Moto> findById(Long id_moto){
		return repM.findById(id_moto);
	}
	
	@Cacheable(value = "buscarPaginasMotos", key = "#req")
	public Page<Moto> findAll(PageRequest req){
		return repM.findAll(req);
	}
	
	@CacheEvict(value = {"buscarTodosCache","buscarPorID", "buscarPaginasMotos"},
			allEntries = true)
	public void limparCache() {
		System.out.println("Limpando cache!");
	}
}
