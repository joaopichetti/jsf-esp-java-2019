package br.edu.utfpr.jsf.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.edu.utfpr.jsf.model.Cidade;
import br.edu.utfpr.jsf.repository.CidadeRepository;

@Component
@Scope("view")
public class CidadeBean extends AbstractBean<Cidade, CidadeRepository> {
	
	public CidadeBean() {
		super(Cidade.class);
	}

}