package br.edu.utfpr.jsf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.jsf.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
