package br.com.fiap.projeto_mottu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fiap.projeto_mottu.dto.FuncionarioDTO;
import br.com.fiap.projeto_mottu.model.Funcionario;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioCachingService cacheFunc;
	
	@Transactional(readOnly = true)
	public Page<FuncionarioDTO> paginar(PageRequest req){
		
		Page<Funcionario> paginas_funcionarios = cacheFunc.findAll(req);
		Page<FuncionarioDTO> paginas_funcionarios_dto = 
				paginas_funcionarios.map(funcionario -> new FuncionarioDTO(funcionario));
		return paginas_funcionarios_dto;
		
	}
}
