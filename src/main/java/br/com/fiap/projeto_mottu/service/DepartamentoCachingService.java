package br.com.fiap.projeto_mottu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.fiap.projeto_mottu.model.Departamento;
import br.com.fiap.projeto_mottu.repository.DepartamentoRepository;

@Service
public class DepartamentoCachingService {

	@Autowired
	private DepartamentoRepository repD;
	
	@Cacheable(value = "buscarTodosCache")
	public List<Departamento> findAll(){
		return repD.findAll();
	}
	
	@Cacheable(value = "buscarPorID", key = "#id_departamento")
	public Optional<Departamento> findById(Long id_departamento){
		return repD.findById(id_departamento);
	}
	
	@Cacheable(value = "buscarPaginasDepartamentos", key = "#req")
	public Page<Departamento> findAll(PageRequest req){
		return repD.findAll(req);
	}
	
	@CacheEvict(value = {"buscarTodosCache","buscarPorID", "buscarPaginasDepartamentos"},
			allEntries = true)
	public void limparCache() {
		System.out.println("Limpando cache!");
	}
}
