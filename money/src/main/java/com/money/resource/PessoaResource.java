package com.money.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.money.model.Pessoa;
import com.money.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> listagemPessoa(){
		
		List<Pessoa> listagem = this.pessoaService.listagemPessoa();
		
		if (listagem.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(listagem);
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> pessoaPorId(@PathVariable Long id){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.pessoaService.pessoaPorCodigo(id));
		
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> cadatrarPessoa(Pessoa pessoa){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.pessoaService.cadastrarPessoa(pessoa));
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa){
		
		return ResponseEntity.status(HttpStatus.OK).body(this.pessoaService.atualizarPessoa(id, pessoa));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Pessoa> removerPessoa(@PathVariable Long id){
		
		return ResponseEntity.status(HttpStatus.OK).body(this.pessoaService.removerPessoa(id));
		
	}
	
}
