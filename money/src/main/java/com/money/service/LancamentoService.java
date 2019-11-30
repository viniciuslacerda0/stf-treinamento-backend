package com.money.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.money.model.Categoria;
import com.money.model.Lancamento;
import com.money.repository.LancamentoRepository;

@Service
public class LancamentoService {
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	public List<Lancamento> listagem(){
		return this.lancamentoRepository.findAll();
	}

	public Lancamento criarLancamento(Lancamento lancamento) {
		boolean existeLancamento = this.lancamentoRepository.findById(lancamento.getCodigo()).isPresent();
		
		if (existeLancamento) {
			throw new IllegalArgumentException();
		}
		
		return this.lancamentoRepository.save(lancamento);
	}
	
public Lancamento lancamentoPorCodigo(Long id) {
		
		return this.lancamentoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		
	}
	
	public Lancamento atualizarLancamento(Long id, Categoria categoriaAtualizada) {
		
		Lancamento categoriaRetornada = this.lancamentoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(categoriaAtualizada, categoriaRetornada, "codigo");
		
		return this.lancamentoRepository.save(categoriaRetornada);
	}
	
	public Lancamento removerLancamento(Long id) {
		
		Lancamento lancamentoAchado = this.lancamentoRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		this.lancamentoRepository.deleteById(id);
		
		return lancamentoAchado;
	}
}
