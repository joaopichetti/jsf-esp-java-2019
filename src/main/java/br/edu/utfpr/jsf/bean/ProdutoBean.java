package br.edu.utfpr.jsf.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.edu.utfpr.jsf.dao.DAO;
import br.edu.utfpr.jsf.model.Produto;

@ManagedBean
public class ProdutoBean {
	
	private Produto produto;
	private List<Produto> produtos;
	private DAO<Produto> dao;
	
	@PostConstruct
	public void inicializar() {
		dao = new DAO<>(Produto.class);
		produto = new Produto();
		listar();
		System.out.println("ProdutoBean PostConstruct");
	}
	
	@PreDestroy
	public void finalizar() {
		System.out.println("ProdutoBean PreDestroy");
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

	public void salvar() {
		if (produto.getCodigo() == null) {
			dao.insert(produto);
		} else {
			dao.update(produto);
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
	
	private void adicionarMensagem(FacesMessage.Severity tipo,
			String mensagem) {
		FacesMessage message = new FacesMessage(tipo, mensagem, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void alterar() {
		if (produto == null) {
			adicionarMensagem(FacesMessage.SEVERITY_ERROR,
					"Selecione um produto");
		}
	}
	
	public void remover() {
		if (produto == null) {
			adicionarMensagem(FacesMessage.SEVERITY_ERROR,
					"Selecione um produto");
		} else {
			dao.delete(produto);
		}
		novo();
		listar();
	}

}









