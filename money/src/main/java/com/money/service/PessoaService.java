package com.money.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.money.exception.PessoaNaoEncontradaException;
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
		return this.pessoaRepository.save(pessoa);
	}
	
	public Pessoa pessoaPorCodigo(Long id) {
		return this.pessoaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		/*Pessoa pessoaProcurada = null;
		if(pessoaAchada.isPresent()) {
			pessoaProcurada = pessoaAchada.get();
		} else {
			throw new PessoaNaoEncontradaException();
		}
		
		return pessoaProcurada;
		*/
	}
	
	public Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizada) {
		Optional<Pessoa> pessoaAchada = this.pessoaRepository.findById(id);
		Pessoa pessoaModificada = null;
		if(pessoaAchada.isPresent()) {
			pessoaModificada = pessoaAchada.get();
			BeanUtils.copyProperties(pessoaAtualizada, pessoaModificada, "codigo");
		} else {
			throw new PessoaNaoEncontradaException();
		}
		
		return this.pessoaRepository.save(pessoaModificada);
	}
	
	public Pessoa removerPessoa(Long id) {
		Optional<Pessoa> pessoaAchada = this.pessoaRepository.findById(id);
		Pessoa pessoaRetorno = null;
		if(pessoaAchada.isPresent()) {
			pessoaRetorno = pessoaAchada.get();
			this.pessoaRepository.deleteById(id);
		} else {
			throw new PessoaNaoEncontradaException();
		}
		return pessoaRetorno;
	}
}
