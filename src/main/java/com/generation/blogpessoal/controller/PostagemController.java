package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;
import com.generation.blogpessoal.repository.TemaRepository;

import jakarta.validation.Valid;


/* VERBOS
 * Get - recuperar as informações do BD
 * Post - inserir no BD
 * Put - update no BD
 * Delete - deleta algum registro no BD
 */

@RestController // notação que fala pro spring que essa é uma controladora de acesso ao método
@RequestMapping("/postagens") // como chegar nessa classe pelo Insomnia
@CrossOrigin(origins = "*", allowedHeaders = "*") // liberar o acesso a outras maquinas // allowed é p/ liberar passagem p/ infos essenciais
public class PostagemController {
	
	@Autowired // injetar dependências, instanciar a classe PostagemRepository
	private PostagemRepository postagemRepository;
	
	@Autowired
	private TemaRepository temaRepository;
	
	@GetMapping // verbo http que vai mostrar o conteudo no insomnia
	public ResponseEntity<List<Postagem>> getAll(){
		// ResponseEntity - formatação da saída de dados
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id) {
		// findById = SELECT * FROM tb_postagens WHERE id = 1;
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/titulo/{titulo}") //localhost:8080/postagens/titulo/Postagem 02
	// = SELECT * FROM tb_postagens WHERE titulo = "titulo";
	public ResponseEntity<List<Postagem>> getByDescricao(@PathVariable String titulo) {
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	// = INSERTO INTO tb_postagens (titulo, texto, data) VALUES ("Titulo", "texto", "2024-12-31 15:02:45");
	public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem) {
		if(temaRepository.existsById(postagem.getTema().getId()))
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(postagemRepository.save(postagem));
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe!", null);
	}
	
	@PutMapping
	public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem){
		if(postagemRepository.existsById(postagem.getId())) {
		
		if(temaRepository.existsById(postagem.getTema().getId()))
		return ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem));
		
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe!", null);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	// DELETE FROM tb_postagens WHERE id = id;
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Postagem> postagem = postagemRepository.findById(id);
		
		if(postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		postagemRepository.deleteById(id);
		
	}
}
