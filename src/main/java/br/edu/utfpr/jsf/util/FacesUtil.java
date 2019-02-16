package br.edu.utfpr.jsf.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {
	
	private static void addMensagem(FacesMessage.Severity tipo, 
			String mensagem) {
		FacesMessage fm = new FacesMessage(tipo, mensagem, null);
		FacesContext.getCurrentInstance().addMessage(null, fm);
	}
	
	public static void addMensagemErro(String mensagem) {
		addMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
	}
	
	public static void addMensagemInfo(String mensagem) {
		addMensagem(FacesMessage.SEVERITY_INFO, mensagem);
	}

}
