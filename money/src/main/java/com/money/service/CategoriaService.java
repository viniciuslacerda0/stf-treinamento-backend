package com.money.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.money.exception.CategoriaNaoEncontradaException;
import com.money.model.Categoria;
import com.money.repository.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> listar(){
		return this.categoriaRepository.findAll();
	}
	
	public Categoria cadastrar(Categoria categoria) {
		return this.categoriaRepository.save(categoria);
	}
	
	public Optional<Categoria> categoriaPorCodigo(Long id) {
		Optional<Categoria> categoriaRetornada = categoriaRepository.findById(id);
		
		return this.categoriaRepository.findById(id);
	}
	
	public Categoria atualizar(Long id, Categoria categoriaAtualizada) {
		Optional<Categoria> categoriaRetornada = categoriaRepository.findById(id);
		Categoria categoriaPersistida = null;
		if (categoriaRetornada.isPresent()) {
			categoriaPersistida = categoriaRetornada.get();
			BeanUtils.copyProperties(categoriaAtualizada, categoriaPersistida, "codigo");
		} else {
			throw new CategoriaNaoEncontradaException();
		}
		return this.categoriaRepository.save(categoriaPersistida);
	}
}
