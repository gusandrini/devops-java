package br.com.fiap.projeto_mottu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.projeto_mottu.model.Logradouro;
import br.com.fiap.projeto_mottu.repository.LogradouroRepository;

@Service
public class LogradouroCachingService {

	@Autowired
	private LogradouroRepository repL;
	
	@Cacheable(value = "buscarTodosCache")
	public List<Logradouro> findAll(){
		return repL.findAll();
	}
	
	@Cacheable(value = "buscarPorID", key = "#id_logradouro")
	public Optional<Logradouro> findById(Long id_logradouro){
		return repL.findById(id_logradouro);
	}
	
	@Cacheable(value = "buscarPaginasLogradouros", key = "#req")
	public Page<Logradouro> findAll(PageRequest req){
		return repL.findAll(req);
	}
	
	@CacheEvict(value = {"buscarTodosCache","buscarPorID", "buscarPaginasLogradouros"},
			allEntries = true)
	public void limparCache() {
		System.out.println("Limpando cache!");
	}
}
