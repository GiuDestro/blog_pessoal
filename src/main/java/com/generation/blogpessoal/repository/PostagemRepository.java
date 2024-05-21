package com.generation.blogpessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.blogpessoal.model.Postagem;

//JpaRepository - vem da classe JPA que selecionamos - ela funciona como o SELECT * FROM
public interface PostagemRepository extends JpaRepository<Postagem, Long>{

}
