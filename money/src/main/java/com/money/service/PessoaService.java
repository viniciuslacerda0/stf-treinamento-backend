package com.money.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.money.model.Pessoa;
import com.money.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public List<Pessoa> listagemPessoa(){
		
		return this.pessoaRepository.findAll();
		
	}
	
	public Pessoa cadastrarPessoa(Pessoa pessoa) {
		
		boolean existePessoa = this.pessoaRepository.findPessoa(pessoa).isPresent();
		
		if(existePessoa) {
			new IllegalArgumentException();
		}
		
		return this.pessoaRepository.save(pessoa);
	}
	
	public Pessoa pessoaPorCodigo(Long id) {
		
		return this.pessoaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
	
	}
	
	public Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizada) {
		
		Pessoa pessoaAchada = this.pessoaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(pessoaAtualizada, pessoaAchada, "codigo");
		
		return this.pessoaRepository.save(pessoaAchada);
	}
	
	public Pessoa removerPessoa(Long id) {
		
		Pessoa pessoaAchada = this.pessoaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		this.pessoaRepository.deleteById(id);
		
		return pessoaAchada;
	}
}
