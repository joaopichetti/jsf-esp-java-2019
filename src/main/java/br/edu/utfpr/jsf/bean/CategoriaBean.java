package br.edu.utfpr.jsf.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import br.edu.utfpr.jsf.dao.DAO;
import br.edu.utfpr.jsf.model.Categoria;
import br.edu.utfpr.jsf.util.FacesUtil;

@ManagedBean
@ViewScoped
public class CategoriaBean {
	
	private Categoria categoria;
	private List<Categoria> categorias;
	private DAO<Categoria> dao;
	private Operacao operacao;
	private boolean categoriaSelecionada;
	
	@PostConstruct
	public void inicializar() {
		dao = new DAO<>(Categoria.class);
		categoria = new Categoria();
		operacao = Operacao.LISTAR;
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

	public boolean isCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(boolean categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

	public void alterar() {
		operacao = Operacao.EDITAR;
	}

	private void listar() {
		categorias = dao.findAll();
	}
	
	public void novo() {
		categoria = new Categoria();
		operacao = Operacao.INSERIR;
	}	
	
	public void remover() {
		if (categoria == null) {
			FacesUtil.addMensagemErro("Selecione uma categoria");
		} else {
			dao.delete(categoria);
		}
		novo();
		listar();
		categoriaSelecionada = false;
	}
	
	public void salvar() {
		if (categoria.getCodigo() != null) {
			dao.update(categoria);
		} else {
			dao.insert(categoria);
		}
		novo();
		listar();
		operacao = Operacao.LISTAR;
		categoriaSelecionada = false;
	}
	
	public void voltarParaListagem() {
		categoria = new Categoria();
		operacao = Operacao.LISTAR;
		categoriaSelecionada = false;
	}
	
	public String getTituloForm() {
		return Operacao.EDITAR == operacao ? 
				"Alterar Categoria" :
				"Nova Categoria";
	}
	
	public void onRowSelect(SelectEvent event) {
		categoriaSelecionada = true;
	}
	
	public boolean mostrarForm() {
		return Operacao.LISTAR != operacao;
	}

}