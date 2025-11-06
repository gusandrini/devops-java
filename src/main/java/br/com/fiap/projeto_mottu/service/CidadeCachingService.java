package br.com.fiap.projeto_mottu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.projeto_mottu.model.Cidade;
import br.com.fiap.projeto_mottu.repository.CidadeRepository;

@Service
public class CidadeCachingService {

	@Autowired
	private CidadeRepository repCid;
	
	@Cacheable(value = "buscarTodosCache")
	public List<Cidade> findAll(){
		return repCid.findAll();
	}
	
	@Cacheable(value = "buscarPorID", key = "#id_cidade")
	public Optional<Cidade> findById(Long id_cidade){
		return repCid.findById(id_cidade);
	}
	
	@Cacheable(value = "buscarPaginasCidades", key = "#req")
	public Page<Cidade> findAll(PageRequest req){
		return repCid.findAll(req);
	}
	
	@CacheEvict(value = {"buscarTodosCache","buscarPorID", "buscarPaginasCidades"},
			allEntries = true)
	public void limparCache() {
		System.out.println("Limpando cache!");
	}
}
