package br.edu.utfpr.jsf.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.edu.utfpr.jsf.dao.DAO;
import br.edu.utfpr.jsf.model.Categoria;
import br.edu.utfpr.jsf.model.Produto;

@ManagedBean
public class ProdutoBean {
	
	private Produto produto;
	private List<Produto> produtos;
	private DAO<Produto> dao;
	private List<Categoria> categorias;
	private DAO<Categoria> categoriaDao;
	
	@PostConstruct
	public void inicializar() {
		dao = new DAO<>(Produto.class);
		categoriaDao = new DAO<>(Categoria.class);
		produto = new Produto();
		listar();
		categorias = categoriaDao.findAll();
		System.out.println("ProdutoBean Construído");
	}
	
	@PreDestroy
	public void finalizar() {
		System.out.println("Destruindo ProdutoBean");
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void salvar() {
		if (produto.getCodigo() != null) {
			dao.update(produto);
		} else {
			dao.insert(produto);
		}
		novo();
		listar();
	}
	
	public void novo() {
		produto = new Produto();
	}
	
	private void listar() {
		produtos = dao.findAll();
	}
	
	private void adicionarMensagem(FacesMessage.Severity tipo, String mensagem) {
		FacesMessage message = new FacesMessage(tipo, mensagem, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void alterar() {
		if (produto == null) {
			adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Selecione um produto");
		}	
	}
	
	public void remover() {
		if (produto == null) {
			adicionarMensagem(FacesMessage.SEVERITY_ERROR, "Selecione um produto");
		} else {
			dao.delete(produto);
		}
		novo();
		listar();
	}

}