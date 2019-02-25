package br.edu.utfpr.jsf.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.edu.utfpr.jsf.model.Usuario;
import br.edu.utfpr.jsf.repository.UsuarioRepository;
import br.edu.utfpr.jsf.service.UsuarioService;
import br.edu.utfpr.jsf.util.FacesUtil;
import br.edu.utfpr.jsf.util.MessageUtil;

@Component
@Scope("view")
public class UsuarioBean extends AbstractBean<Usuario, UsuarioRepository> {
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private MessageUtil messageUtil;
	
	public UsuarioBean() {
		super(Usuario.class);
	}
	
	public boolean isAdmin() {
		return getObjeto() != null && 
				Usuario.ADMIN_USERNAME.equals(getObjeto().getUsername());
	}
	
	@Override
	public void remover() {
		if (isAdmin()) {
			FacesUtil.addMensagemErro(
					messageUtil.getMessage("usuario.remover.admin"));
		} else {
			super.remover();
		}
	}
	
	@Override
	public void salvar() {
		if (getObjeto() != null) {
			if (!repository.findByUsername(getObjeto().getUsername())
					.orElse(getObjeto()).getCodigo().equals(
							getObjeto().getCodigo())) {
				FacesUtil.addMensagemErro(
						messageUtil.getMessage("usuario.salvar.username_repetido"));
			} else {
				usuarioService.criptografarSenha(getObjeto());
				super.salvar();
			}
		}
	}

}
