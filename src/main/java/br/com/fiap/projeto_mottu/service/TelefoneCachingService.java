package br.com.fiap.projeto_mottu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.projeto_mottu.model.Telefone;
import br.com.fiap.projeto_mottu.repository.TelefoneRepository;



@Service
public class TelefoneCachingService {

	@Autowired
	private TelefoneRepository repT;
	
	@Cacheable(value = "buscarTodosCache")
	public List<Telefone> findAll(){
		return repT.findAll();
	}
	
	@Cacheable(value = "buscarPorID", key = "#id_telefone")
	public Optional<Telefone> findById(Long id_telefone){
		return repT.findById(id_telefone);
	}
	
	@Cacheable(value = "buscarPaginasTelefones", key = "#req")
	public Page<Telefone> findAll(PageRequest req){
		return repT.findAll(req);
	}
	
	@CacheEvict(value = {"buscarTodosCache","buscarPorID", "buscarPaginasTelefones"},
			allEntries = true)
	public void limparCache() {
		System.out.println("Limpando cache!");
	}

}
