package br.com.fiap.projeto_mottu.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.projeto_mottu.model.Cidade;
import br.com.fiap.projeto_mottu.repository.CidadeRepository;
import br.com.fiap.projeto_mottu.service.CidadeCachingService;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository repCid;
	
	@Autowired
	private CidadeCachingService cacheCid;
	
	@GetMapping(value = "/todas")
	public List<Cidade> retornaTodasCidades(){
		return repCid.findAll();
	}
	
	@GetMapping(value = "/{id_cidade}")
	public Cidade retornaCidadePorID(@PathVariable Long id_cidade) {
		
		Optional<Cidade> op = cacheCid.findById(id_cidade);
		
		if(op.isPresent()) {
			return op.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping(value = "/inserir")
	public Cidade inserirCidade(@RequestBody Cidade cidade) {
		repCid.save(cidade);
		cacheCid.limparCache();
		return cidade;
	}
	
	@PutMapping(value = "/atualizar/{id_cidade}")
	public Cidade atualizarCidade(@RequestBody Cidade cidade, @PathVariable Long id_cidade) {

		Optional<Cidade> op = cacheCid.findById(id_cidade);

		if (op.isPresent()) {

			Cidade cidade_atual = op.get();

			cidade_atual.setNm_cidade(cidade.getNm_cidade());
			

			repCid.save(cidade_atual);
			cacheCid.limparCache();

			return cidade_atual;

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}
	
	@DeleteMapping(value = "/remover/{id_cidade}")
	public Cidade removerCidade(@PathVariable Long id_cidade) {

		Optional<Cidade> op = cacheCid.findById(id_cidade);

		if (op.isPresent()) {

			Cidade cidade = op.get();
			repCid.delete(cidade);
			cacheCid.limparCache();
			return cidade;

		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}
}
