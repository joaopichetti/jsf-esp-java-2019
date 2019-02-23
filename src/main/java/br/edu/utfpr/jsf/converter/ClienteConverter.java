package br.edu.utfpr.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.jsf.model.Cliente;
import br.edu.utfpr.jsf.repository.ClienteRepository;
import br.edu.utfpr.jsf.util.FacesUtil;

@Component
public class ClienteConverter implements Converter {

	@Autowired
	private ClienteRepository repository;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, 
		String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		try {
			return repository.findById(Integer.parseInt(value)).orElse(null);
		} catch (Exception ex) {
			throw new ConverterException(
					FacesUtil.criarMensagemErro("Cliente inválido"));
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, 
		Object value) {
		if (value instanceof Cliente) {
			Cliente cliente = (Cliente) value;
			return String.valueOf(cliente.getCodigo());
		} else {
			return null;
		}
	}

}
