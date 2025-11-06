package br.com.fiap.projeto_mottu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import br.com.fiap.projeto_mottu.model.FilialDepartamento;
import br.com.fiap.projeto_mottu.repository.FilialDepartamentoRepository;

@Service
public class FilialDepartamentoCachingService {

	@Autowired
	private FilialDepartamentoRepository repFD;
	
	@Cacheable(value = "buscarTodosCache")
	public List<FilialDepartamento> findAll(){
		return repFD.findAll();
	}
	
	@Cacheable(value = "buscarPorID", key = "#id_filial_departamento")
	public Optional<FilialDepartamento> findById(Long id_filial_departamento){
		return repFD.findById(id_filial_departamento);
	}
	
	@Cacheable(value = "buscarPaginasFiliaisDepartamentos", key = "#req")
	public Page<FilialDepartamento> findAll(PageRequest req){
		return repFD.findAll(req);
	}
	
	@CacheEvict(value = {"buscarTodosCache","buscarPorID", "buscarPaginasFiliaisDepartamentos"},
			allEntries = true)
	public void limparCache() {
		System.out.println("Limpando cache!");
	}
}
