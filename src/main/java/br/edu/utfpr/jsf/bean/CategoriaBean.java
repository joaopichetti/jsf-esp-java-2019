package br.edu.utfpr.jsf.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.edu.utfpr.jsf.model.Categoria;
import br.edu.utfpr.jsf.repository.CategoriaRepository;

@Component
@Scope("view")
public class CategoriaBean extends AbstractBean<Categoria, CategoriaRepository> {

	public CategoriaBean() {
		super(Categoria.class);
	}	

}