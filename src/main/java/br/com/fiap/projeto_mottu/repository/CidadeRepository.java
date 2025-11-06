package br.com.fiap.projeto_mottu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.projeto_mottu.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long>{

}
