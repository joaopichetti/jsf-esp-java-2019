package br.edu.utfpr.jsf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.jsf.model.Pais;

public interface PaisRepository extends JpaRepository<Pais, Integer> {

}
