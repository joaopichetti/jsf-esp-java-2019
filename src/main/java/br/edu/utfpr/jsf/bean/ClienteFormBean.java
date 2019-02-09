package br.edu.utfpr.jsf.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.edu.utfpr.jsf.dao.DAO;
import br.edu.utfpr.jsf.model.Cliente;

@ManagedBean
public class ClienteFormBean {
	
	private Cliente cliente;
	private DAO<Cliente> dao;
	private Integer codigo;
	
	@PostConstruct
	public void inicializar() {
		dao = new DAO<>(Cliente.class);
		cliente = new Cliente();		
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String salvar() {
		if (cliente.getCodigo() == null) {
			dao.insert(cliente);
		} else {
			dao.update(cliente);
		}
		return voltarParaListagem();
	}
	
	public String voltarParaListagem() {
		return "lista";
	}
	
	public void carregarCliente() {
		if (codigo != null) {
			cliente = dao.findById(codigo);
		}
		if (cliente == null) {
			cliente = new Cliente();
		}
	}
	
}
















