package br.edu.utfpr.jsf.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

public class FacesUtil {
	
	private static void addMensagem(FacesMessage.Severity tipo, 
			String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null, 
				criarMensagem(tipo, mensagem));
	}
	
	public static void addMensagemErro(String mensagem) {
		addMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
	}
	
	public static void addMensagemInfo(String mensagem) {
		addMensagem(FacesMessage.SEVERITY_INFO, mensagem);
	}
	
	private static FacesMessage criarMensagem(FacesMessage.Severity tipo,
			String mensagem) {
		return new FacesMessage(tipo, mensagem, null);
	}
	
	public static FacesMessage criarMensagemErro(String mensagem) {
		return criarMensagem(FacesMessage.SEVERITY_ERROR, mensagem);
	}
	
	public static FacesMessage criarMensagemInfo(String mensagem) {
		return criarMensagem(FacesMessage.SEVERITY_INFO, mensagem);
	}
	
	private static void executarScriptPrimefaces(String comando) {
		PrimeFaces.current().executeScript(comando);
	}
	
	public static void abrirDialog(String widgetVar) {
		executarScriptPrimefaces(String.format("PF('%s').show();", 
				widgetVar));
	}
	
	public static void fecharDialog(String widgetVar) {
		executarScriptPrimefaces(String.format("PF('%s').hide();", 
				widgetVar));
	}

}










