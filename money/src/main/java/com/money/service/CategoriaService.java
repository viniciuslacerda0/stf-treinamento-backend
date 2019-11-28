package com.money.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.money.exception.CategoriaNaoEncontradaException;
import com.money.exception.CategoriaRepetidaException;
import com.money.exception.NaoExisteCategoriaException;
import com.money.model.Categoria;
import com.money.repository.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> listar(){
		List<Categoria> listagem = this.categoriaRepository.findAll();
		if (!listagem.isEmpty()) {
			return listagem;
		} else {
			throw new NaoExisteCategoriaException();
		}
	}
	
	public Categoria cadastrar(Categoria categoria) {
		
		if(this.categoriaRepository.existsByName(categoria.getNome())) {
			return this.categoriaRepository.save(categoria);
		} else {
			throw new CategoriaRepetidaException();
		}
		
		
	}
	
	public Categoria categoriaPorCodigo(Long id) {
		Optional<Categoria> categoriaAchada = categoriaRepository.findById(id);
		Categoria categoriaProcurada = null;
		if(categoriaAchada.isPresent()) {
			categoriaProcurada = categoriaAchada.get();
		} else {
			throw new CategoriaNaoEncontradaException();
		}
		
		return categoriaProcurada;
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
