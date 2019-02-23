package br.edu.utfpr.jsf.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.edu.utfpr.jsf.model.Uf;
import br.edu.utfpr.jsf.repository.UfRepository;

@Component
@Scope("view")
public class UfBean extends AbstractBean<Uf, UfRepository> {
	
	public UfBean() {
		super(Uf.class);
	}
	
}















