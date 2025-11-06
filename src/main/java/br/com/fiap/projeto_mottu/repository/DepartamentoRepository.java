package br.com.fiap.projeto_mottu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.projeto_mottu.model.Departamento;
import br.com.fiap.projeto_mottu.projection.DepartamentoProjection;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long>{

	//Busca departamentos por nome
	@Query("""
		    SELECT 
		        d.nm_departamento AS nmDepartamento,
		        d.desc_departamento AS descDepartamento
		    FROM Departamento d
		    WHERE LOWER(d.nm_departamento) LIKE LOWER(CONCAT('%', :nmDepartamento, '%'))
	""")
	List<DepartamentoProjection> buscarDepartamentoPorNome(@Param("nmDepartamento") String nmDepartamento);
}