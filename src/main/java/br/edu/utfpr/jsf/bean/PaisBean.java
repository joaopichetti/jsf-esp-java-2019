package br.edu.utfpr.jsf.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.edu.utfpr.jsf.model.Pais;
import br.edu.utfpr.jsf.repository.PaisRepository;

@Component
@Scope("view")
public class PaisBean extends AbstractBean<Pais, PaisRepository> {
	
	public PaisBean() {
		super(Pais.class);
	}

}