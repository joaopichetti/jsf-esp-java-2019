package br.edu.utfpr.jsf.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.edu.utfpr.jsf.dao.DAO;
import br.edu.utfpr.jsf.model.Categoria;
import br.edu.utfpr.jsf.util.FacesUtil;

@FacesConverter(value="categoriaConverter")
public class CategoriaConverter implements Converter {

	private DAO<Categoria> dao = new DAO<>(Categoria.class);

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
					FacesUtil.criarMensagemErro("Categoria inválida"));
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, 
		Object value) {
		if (value instanceof Categoria) {
			Categoria categoria = (Categoria) value;
			return String.valueOf(categoria.getCodigo());
		} else {
			return null;
		}
	}

}
