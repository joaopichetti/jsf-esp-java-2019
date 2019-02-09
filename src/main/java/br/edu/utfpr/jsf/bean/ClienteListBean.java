package br.edu.utfpr.jsf.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.edu.utfpr.jsf.dao.DAO;
import br.edu.utfpr.jsf.model.Cliente;

@ManagedBean
public class ClienteListBean {
	
	private Cliente cliente;
	private List<Cliente> clientes;
	private DAO<Cliente> dao;
	
	@PostConstruct
	public void inicializar() {
		dao = new DAO<>(Cliente.class);
		listar();
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	private void listar() {
		clientes = dao.findAll();
	}
	
	public String novo() {
		return "formulario";
	}
}










