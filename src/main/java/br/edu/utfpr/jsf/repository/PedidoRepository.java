package br.edu.utfpr.jsf.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.jsf.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
