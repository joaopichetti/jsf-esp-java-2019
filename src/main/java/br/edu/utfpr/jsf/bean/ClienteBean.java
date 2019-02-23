package br.edu.utfpr.jsf.bean;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.edu.utfpr.jsf.model.Cidade;
import br.edu.utfpr.jsf.model.Cliente;
import br.edu.utfpr.jsf.repository.CidadeRepository;
import br.edu.utfpr.jsf.repository.ClienteRepository;
import br.edu.utfpr.jsf.util.FacesUtil;

@Component
@Scope("view")
public class ClienteBean extends AbstractBean<Cliente, ClienteRepository> {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	private List<Cidade> cidades;
	
	public ClienteBean() {
		super(Cliente.class);
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	@Override
	protected void carregarLookups() {
		cidades = cidadeRepository.findAll();
	}
	
	public void telefoneValido(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		final String pattern = "\\(?\\d{2}\\)? ?9?\\d{4}-?\\d{4}";
		if (value != null && !value.toString().isEmpty() &&
				!value.toString().matches(pattern)) {
			throw new ValidatorException(
					FacesUtil.criarMensagemErro("Telefone inválido"));
		}
	}

}










