package br.com.fiap.projeto_mottu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.projeto_mottu.model.Cliente;
import br.com.fiap.projeto_mottu.repository.ClienteRepository;

@Service
public class ClienteCachingService {

	@Autowired
	private ClienteRepository repC;
	
	@Cacheable(value = "buscarTodosCache")
	public List<Cliente> findAll(){
		return repC.findAll();
	}
	
	@Cacheable(value = "buscarPorID", key = "#id_cliente")
	public Optional<Cliente> findById(Long id_cliente){
		return repC.findById(id_cliente);
	}
	
	@Cacheable(value = "buscarPaginasClientes", key = "#req")
	public Page<Cliente> findAll(PageRequest req){
		return repC.findAll(req);
	}
	
	@CacheEvict(value = {"buscarTodosCache","buscarPorID", "buscarPaginasClientes"},
			allEntries = true)
	public void limparCache() {
		System.out.println("Limpando cache!");
	}
}
