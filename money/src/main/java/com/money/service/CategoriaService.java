package com.money.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.money.exception.CategoriaNaoEncontradaException;
import com.money.exception.CategoriaRepetidaException;
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
		
		if(this.categoriaRepository.existsByNome(categoria.getNome())) {
			throw new CategoriaRepetidaException();
		} else {
			return this.categoriaRepository.save(categoria);
		}
		
	}
	
	public Categoria categoriaPorCodigo(Long id) {
		Optional<Categoria> categoriaAchada = this.categoriaRepository.findById(id);
		Categoria categoriaProcurada = null;
		if(categoriaAchada.isPresent()) {
			categoriaProcurada = categoriaAchada.get();
		} else {
			throw new CategoriaNaoEncontradaException();
		}
		
		return categoriaProcurada;
	}
	
	public Categoria atualizarCategoria(Long id, Categoria categoriaAtualizada) {
		Optional<Categoria> categoriaRetornada = this.categoriaRepository.findById(id);
		Categoria categoriaPersistida = null;
		if (categoriaRetornada.isPresent()) {
			categoriaPersistida = categoriaRetornada.get();
			BeanUtils.copyProperties(categoriaAtualizada, categoriaPersistida, "codigo");
		} else {
			throw new CategoriaNaoEncontradaException();
		}
		return this.categoriaRepository.save(categoriaPersistida);
	}
	
	public Categoria removerCategoria(Long id) {
		Optional<Categoria> categoriaAchada = this.categoriaRepository.findById(id);
		Categoria categoriaRetorno = null;
		if(categoriaAchada.isPresent()) {
			categoriaRetorno = categoriaAchada.get();
			this.categoriaRepository.deleteById(id);
		} else {
			throw new CategoriaNaoEncontradaException();
		}
		return categoriaRetorno;
	}
}
