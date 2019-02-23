package br.edu.utfpr.jsf.util;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class MessageUtil {
	
	@Autowired
	private MessageSource messageSource;

	public String getMessage(String code) {
		return messageSource.getMessage(code, null, Locale.getDefault());
	}
	
}