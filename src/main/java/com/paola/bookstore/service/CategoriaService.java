package com.paola.bookstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.paola.bookstore.domain.Categoria;
import com.paola.bookstore.dtos.CategoriaDTO;
import com.paola.bookstore.repositories.CategoriaRepository;
import com.paola.bookstore.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public Categoria findById(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public List<Categoria> findAll() {
		return repository.findAll();
	}
	
	public Categoria create(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Categoria update(Integer id, CategoriaDTO objDto) {
		Categoria obj = findById(id);
		/*se o usuario nao colocar nada, deixa como estava antes*/
		obj.setNome(objDto.getNome() == null ? obj.getNome() : objDto.getNome());
		obj.setDescricao(objDto.getDescricao() == null ? obj.getDescricao() : objDto.getDescricao());
		return repository.save(obj);
	}

	public void delete(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new com.paola.bookstore.service.exceptions.DataIntegrityViolationException("Categoria não pode ser deletada! Possui livros associados");
		}
	}
}