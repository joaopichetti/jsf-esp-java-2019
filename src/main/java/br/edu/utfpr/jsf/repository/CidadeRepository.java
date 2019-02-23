package br.edu.utfpr.jsf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.jsf.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
