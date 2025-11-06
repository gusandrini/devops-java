package br.com.fiap.projeto_mottu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.fiap.projeto_mottu.model.Manutencao;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long>{

	//Busca todas as manutenções e ordena pela data de entrada das motos
	@Query("SELECT m FROM Manutencao m ORDER BY m.dt_entrada DESC")
	List<Manutencao> buscarTodasOrdenadasPorDataEntrada();
	
    //Busca manutenções que ainda não foram concluídas (sem data de saída)
    @Query("SELECT m FROM Manutencao m WHERE m.dt_saida IS NULL")
    List<Manutencao> buscarManutencoesEmAberto();
    
    //Busca manutenções com descrição contendo uma palavra-chave
    @Query("SELECT m FROM Manutencao m WHERE LOWER(m.ds_manutencao) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Manutencao> buscarPorDescricao(@Param("keyword") String keyword);
}
