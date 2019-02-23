package br.edu.utfpr.jsf.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.utfpr.jsf.util.FacesUtil;
import br.edu.utfpr.jsf.util.MessageUtil;

@Component
public class LocalDateConverter implements Converter {

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	@Autowired
	private MessageUtil messageUtil;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		try {
			return LocalDate.parse(value, formatter);
		} catch (DateTimeParseException ex) {
			throw new ConverterException(FacesUtil.criarMensagemErro(messageUtil.getMessage("geral.data.invalida")));
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value instanceof LocalDate) {
			LocalDate date = (LocalDate) value;
			return date.format(formatter);
		}
		return null;
	}

}
