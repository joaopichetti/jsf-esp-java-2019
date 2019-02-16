package br.edu.utfpr.jsf.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.edu.utfpr.jsf.dao.DAO;
import br.edu.utfpr.jsf.model.Categoria;
import br.edu.utfpr.jsf.util.FacesUtil;

@ManagedBean
public class CategoriaBean {
	
	private Categoria categoria;
	private List<Categoria> categorias;
	private DAO<Categoria> dao;
	
	@PostConstruct
	public void inicializar() {
		dao = new DAO<>(Categoria.class);
		categoria = new Categoria();
		listar();
	}
	
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void alterar() {
		if (categoria == null) {
			FacesUtil.addMensagemErro("Selecione uma categoria");
		}
	}

	private void listar() {
		categorias = dao.findAll();
	}
	
	public void novo() {
		categoria = new Categoria();
	}	
	
	public void remover() {
		if (categoria == null) {
			FacesUtil.addMensagemErro("Selecione uma categoria");
		} else {
			dao.delete(categoria);
		}
		novo();
		listar();
	}
	
	public void salvar() {
		if (categoria.getCodigo() != null) {
			dao.update(categoria);
		} else {
			dao.insert(categoria);
		}
		novo();
		listar();
	}

}
