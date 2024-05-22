package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.blogpessoal.model.Postagem;

//JpaRepository - vem da classe JPA que selecionamos - ela funciona como o SELECT * FROM
public interface PostagemRepository extends JpaRepository<Postagem, Long>{
	
	public List <Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);

}
