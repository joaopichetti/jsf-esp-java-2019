package br.edu.utfpr.jsf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.jsf.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
