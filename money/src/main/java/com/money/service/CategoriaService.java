package com.money.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.money.model.Categoria;
import com.money.repository.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> listarCategoria(){
		
		return this.categoriaRepository.findAll();
		
	}
	
	public Categoria cadastrarCategoria(Categoria categoria) {
		
		boolean existeCategoria = this.categoriaRepository.findCategoria(categoria).isPresent();
		
		if(existeCategoria){
			throw new IllegalArgumentException();
		} 
		
		return this.categoriaRepository.save(categoria);
		
	}
	
	public Categoria categoriaPorCodigo(Long id) {
		
		return this.categoriaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		
	}
	
	public Categoria atualizarCategoria(Long id, Categoria categoriaAtualizada) {
		
		Categoria categoriaRetornada = this.categoriaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(categoriaAtualizada, categoriaRetornada, "codigo");
		
		return this.categoriaRepository.save(categoriaRetornada);
	}
	
	public Categoria removerCategoria(Long id) {
		
		Categoria categoriaAchada = this.categoriaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		this.categoriaRepository.deleteById(id);
		
		return categoriaAchada;
	}
}
