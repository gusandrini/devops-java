package br.com.fiap.projeto_mottu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.projeto_mottu.model.Bairro;
import br.com.fiap.projeto_mottu.repository.BairroRepository;

@Service
public class BairroCachingService {

	@Autowired
	private BairroRepository repB;
	
	@Cacheable(value = "buscarTodosCache")
	public List<Bairro> findAll(){
		return repB.findAll();
	}
	
	@Cacheable(value = "buscarPorID", key = "#id_bairro")
	public Optional<Bairro> findById(Long id_bairro){
		return repB.findById(id_bairro);
	}
	
	@Cacheable(value = "buscarPaginasBairros", key = "#req")
	public Page<Bairro> findAll(PageRequest req){
		return repB.findAll(req);
	}
	
	@CacheEvict(value = {"buscarTodosCache","buscarPorID", "buscarPaginasBairros"},
			allEntries = true)
	public void limparCache() {
		System.out.println("Limpando cache!");
	}
}
