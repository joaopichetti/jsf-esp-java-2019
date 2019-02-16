package br.edu.utfpr.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.edu.utfpr.jsf.dao.DAO;
import br.edu.utfpr.jsf.model.Cliente;
import br.edu.utfpr.jsf.util.FacesUtil;

@FacesConverter(value="clienteConverter")
public class ClienteConverter implements Converter {

	private DAO<Cliente> dao = new DAO<>(Cliente.class);

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, 
		String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		try {
			return dao.findById(Integer.parseInt(value));
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
