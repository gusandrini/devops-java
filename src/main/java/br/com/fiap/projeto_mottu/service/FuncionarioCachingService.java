package br.com.fiap.projeto_mottu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.projeto_mottu.model.Funcionario;
import br.com.fiap.projeto_mottu.repository.FuncionarioRepository;

@Service
public class FuncionarioCachingService {

	@Autowired
	private FuncionarioRepository repFunc;
	
	@Cacheable(value = "buscarTodosCache")
	public List<Funcionario> findAll(){
		return repFunc.findAll();
	}
	
	@Cacheable(value = "buscarPorID", key = "#id_funcionario")
	public Optional<Funcionario> findById(Long id_funcionario){
		return repFunc.findById(id_funcionario);
	}
	
	@Cacheable(value = "buscarPaginasFuncionarios", key = "#req")
	public Page<Funcionario> findAll(PageRequest req){
		return repFunc.findAll(req);
	}
	
	@CacheEvict(value = {"buscarTodosCache","buscarPorID", "buscarPaginasFuncionarios"},
			allEntries = true)
	public void limparCache() {
		System.out.println("Limpando cache!");
	}
}
