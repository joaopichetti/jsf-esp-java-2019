package br.edu.utfpr.jsf.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.edu.utfpr.jsf.model.Categoria;
import br.edu.utfpr.jsf.model.Produto;
import br.edu.utfpr.jsf.repository.CategoriaRepository;
import br.edu.utfpr.jsf.repository.ProdutoRepository;

@Component
@Scope("view")
public class ProdutoBean extends AbstractBean<Produto, ProdutoRepository> {
	
	private List<Categoria> categorias;
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public ProdutoBean() {
		super(Produto.class);
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	@Override
	protected void carregarLookups() {
		categorias = categoriaRepository.findAll();
	}

}





