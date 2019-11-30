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

import com.money.model.Categoria;
import com.money.model.Lancamento;
import com.money.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	@Autowired
	private LancamentoService lancamentoService;
	
	@GetMapping
	public ResponseEntity<List<Lancamento>> listagemLancamento(){
		List<Lancamento> listagem = this.lancamentoService.listagem();
		if (listagem.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(listagem);
		}
	}
	
	@PostMapping
	public ResponseEntity<Lancamento> criarLancamento(@RequestBody Lancamento lancamento) {
		return ResponseEntity.status(HttpStatus.CREATED).body( this.lancamentoService.criarLancamento(lancamento));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Lancamento> atualizarLancamento(@PathVariable Long id, @RequestBody Categoria categoriaAtualizada){

		return ResponseEntity.status(HttpStatus.OK).body(this.lancamentoService.atualizarLancamento(id, categoriaAtualizada));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Lancamento> removerLancamento(@PathVariable Long id){
		
		return ResponseEntity.status(HttpStatus.OK).body(this.lancamentoService.removerLancamento(id));
		
	}
	
}
