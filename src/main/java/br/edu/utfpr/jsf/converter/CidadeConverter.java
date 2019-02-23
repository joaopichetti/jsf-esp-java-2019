package br.edu.utfpr.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.jsf.model.Cidade;
import br.edu.utfpr.jsf.repository.CidadeRepository;
import br.edu.utfpr.jsf.util.FacesUtil;

@Component
public class CidadeConverter implements Converter {
	
	@Autowired
	private CidadeRepository repository;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		try {
			return repository.findById(Integer.parseInt(value)).orElse(null);
		} catch (Exception ex) {
			throw new ConverterException(
					FacesUtil.criarMensagemErro("Cidade inválida"));
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof Cidade) {
			Cidade cidade = (Cidade) value;
			return String.valueOf(cidade.getCodigo());
		} else {
			return null;
		}
	}

}













