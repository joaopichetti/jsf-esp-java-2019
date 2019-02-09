package br.edu.utfpr.jsf.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import br.edu.utfpr.jsf.model.Produto;

@ManagedBean
public class ProdutoBean {
	
	private Produto produto;
	private List<Produto> produtos;
	
	public ProdutoBean() {
		produto = new Produto();
		produtos = new ArrayList<>();
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
		produtos.add(produto);
	}
	
	public void novo() {
		produto = new Produto();
	}

}









