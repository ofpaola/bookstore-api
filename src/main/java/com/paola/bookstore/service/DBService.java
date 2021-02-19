package com.paola.bookstore.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paola.bookstore.domain.Categoria;
import com.paola.bookstore.domain.Livro;
import com.paola.bookstore.repositories.CategoriaRepository;
import com.paola.bookstore.repositories.LivroRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private LivroRepository livroRepository;

	public void instanciaBaseDeDados() {
		
		Categoria cat1 = new Categoria(null, "Informática", "Livros de TI");
		Categoria cat2 = new Categoria(null, "Fantasia", "Fantasia");
		Categoria cat3 = new Categoria(null, "Drama", "Drama");
		Categoria cat4 = new Categoria(null, "Suspense e Mistério", "Suspense e Mistério");
	
		Livro l1 = new Livro(null, "Clean code", "Robert Martin", "Lorem ipsum", cat1);
		Livro l2 = new Livro(null, "Os Miseráveis", "Victor Hugo", "Lorem ipsum", cat3);
		Livro l3 = new Livro(null, "Trama", "Michael Jensen & David Powers King", "Lorem ipsum", cat2);
		Livro l4 = new Livro(null, "Forrest Gump", "Winston Groom", "Lorem ipsum", cat3);
		Livro l5 = new Livro(null, "Em Algum Lugar nas Estrelas", "Clare Vanderpool", "Lorem ipsum", cat2);
		Livro l6 = new Livro(null, "O Homem de Giz", "C.J. Tudor", "Lorem ipsum", cat4);
		
		cat1.getLivros().addAll(Arrays.asList(l1));
		cat2.getLivros().addAll(Arrays.asList(l3,l5));
		cat3.getLivros().addAll(Arrays.asList(l2,l4));
		cat4.getLivros().addAll(Arrays.asList(l6));
	
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
		livroRepository.saveAll(Arrays.asList(l1, l2, l3, l4, l5, l6));
	}
}
