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
import com.money.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listarCategoria(){
		
		List<Categoria> listagem = this.categoriaService.listarCategoria();
	
		return ResponseEntity.status(HttpStatus.OK).body(listagem);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> categoriaPorId(@PathVariable Long id){
		
		return ResponseEntity.status(HttpStatus.OK).body(this.categoriaService.categoriaPorCodigo(id));
		
	}
	
	@PostMapping
	public ResponseEntity<Categoria> cadastrarCategoria(@RequestBody Categoria categoria) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.categoriaService.cadastrarCategoria(categoria));
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaAtualizada){

		return ResponseEntity.status(HttpStatus.OK).body(this.categoriaService.atualizarCategoria(id, categoriaAtualizada));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Categoria> removerCategoria(@PathVariable Long id){
		
		return ResponseEntity.status(HttpStatus.OK).body(this.categoriaService.removerCategoria(id));
		
	}
	
}
