package br.com.fiap.projeto_mottu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fiap.projeto_mottu.model.Logradouro;

public interface LogradouroRepository extends JpaRepository<Logradouro, Long>{
	
	//Busca logradouro por nome
	@Query("SELECT l FROM Logradouro l WHERE LOWER(l.nm_logradouro) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Logradouro> buscarPorNome(String nome);
}
