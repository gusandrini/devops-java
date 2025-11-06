package br.com.fiap.projeto_mottu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.fiap.projeto_mottu.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	//Busca cliente por substring (nome, cpf ou email) e ordena por nome do cliente
	@Query(nativeQuery = true, value = """
	        select * from cliente c 
	        where c.nm_cliente like concat('%', :sub_cliente, '%')
	            or c.nr_cpf like concat('%', :sub_cliente, '%')
	            or c.nm_email like concat('%', :sub_cliente, '%')
	        order by c.nm_cliente asc
	    """)
	    public List<Cliente> buscarClientePorSubstringOrdenadoPorNome(String sub_cliente);
}
