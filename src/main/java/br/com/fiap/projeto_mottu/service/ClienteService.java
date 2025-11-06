package br.com.fiap.projeto_mottu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.projeto_mottu.dto.ClienteDTO;
import br.com.fiap.projeto_mottu.model.Cliente;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteCachingService cacheC;
	
	@Transactional(readOnly = true)
	public Page<ClienteDTO> paginar(PageRequest req){
		
		Page<Cliente> paginas_clientes = cacheC.findAll(req);
		Page<ClienteDTO> paginas_clientes_dto = 
				paginas_clientes.map(cliente -> new ClienteDTO(cliente));
		return paginas_clientes_dto;
		
	}
	
	
}
