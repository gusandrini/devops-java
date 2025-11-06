package br.com.fiap.projeto_mottu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.projeto_mottu.model.Filial;

public interface FilialRepository extends JpaRepository<Filial, Long>{

	@Query("SELECT f FROM Filial f WHERE LOWER(f.nome_filial) LIKE LOWER(CONCAT('%', :nomeFilial, '%'))")
    List<Filial> buscarPorNome(@Param("nomeFilial") String nomeFilial);
}
