package com.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

@RestController // notação que fala pro spring que essa é uma controladora de acesso ao método
@RequestMapping("/postagens") // como chegar nessa classe pelo Insomnia
@CrossOrigin(origins = "*", allowedHeaders = "*") // liberar o acesso a outras maquinas // allowed é p/ liberar passagem p/ infos essenciais
public class PostagemController {
	
	@Autowired // injetar dependências, instanciar a classe PostagemRepository
	private PostagemRepository postagemRepository;
	
	@GetMapping // verbo http que vai mostrar o conteudo no insomnia
	public ResponseEntity<List<Postagem>> getAll(){
		// ResponseEntity - formatação da saída de dados
		return ResponseEntity.ok(postagemRepository.findAll());
	}
}
