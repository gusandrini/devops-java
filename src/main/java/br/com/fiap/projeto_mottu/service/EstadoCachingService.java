package br.com.fiap.projeto_mottu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.projeto_mottu.model.Estado;
import br.com.fiap.projeto_mottu.repository.EstadoRepository;

@Service
public class EstadoCachingService {

	@Autowired
	private EstadoRepository repE;
	
	@Cacheable(value = "buscarTodosCache")
	public List<Estado> findAll(){
		return repE.findAll();
	}
	
	@Cacheable(value = "buscarPorID", key = "#id_estado")
	public Optional<Estado> findById(Long id_estado){
		return repE.findById(id_estado);
	}
	
	@Cacheable(value = "buscarPaginasEstados", key = "#req")
	public Page<Estado> findAll(PageRequest req){
		return repE.findAll(req);
	}
	
	@CacheEvict(value = {"buscarTodosCache","buscarPorID", "buscarPaginasEstados"},
			allEntries = true)
	public void limparCache() {
		System.out.println("Limpando cache!");
	}
}
