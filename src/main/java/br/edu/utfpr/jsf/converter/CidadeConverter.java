package br.edu.utfpr.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.edu.utfpr.jsf.dao.DAO;
import br.edu.utfpr.jsf.model.Cidade;
import br.edu.utfpr.jsf.util.FacesUtil;

@FacesConverter(value="cidadeConverter")
public class CidadeConverter implements Converter {
	
	private DAO<Cidade> dao = new DAO<>(Cidade.class);

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		try {
			return dao.findById(Integer.parseInt(value));
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













