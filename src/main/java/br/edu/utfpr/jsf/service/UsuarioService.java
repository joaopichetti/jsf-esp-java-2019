package br.edu.utfpr.jsf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.utfpr.jsf.model.Usuario;
import br.edu.utfpr.jsf.repository.UsuarioRepository;
import br.edu.utfpr.jsf.util.MessageUtil;

@Service
public class UsuarioService implements UserDetailsService, CommandLineRunner {
	
	@Autowired
	private UsuarioRepository repository;
	@Autowired
	private MessageUtil messageUtil;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		return repository.findByUsername(username).orElseThrow(() ->
			new UsernameNotFoundException(
					messageUtil.getMessage("usuario.nao_encontrado")));
	}
	
	public void criptografarSenha(Usuario usuario) throws RuntimeException {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    	if (usuario.getCodigo() == null) {
			usuario.setPassword(bCryptPasswordEncoder
				.encode(usuario.getPassword()));
		} else {
			Usuario antigo = repository.findById(usuario.getCodigo()).get();
			if (!usuario.getPassword().equals(antigo.getPassword())) {
				usuario.setPassword(bCryptPasswordEncoder
					.encode(usuario.getPassword()));
			}
		}
	}
	
	@Override
	public void run(String... args) throws Exception {
		Usuario usuario = repository.findByUsername(Usuario.ADMIN_USERNAME)
				.orElse(null);
		if (usuario == null) {
			usuario = new Usuario();
			usuario.setUsername(Usuario.ADMIN_USERNAME);
			usuario.setPassword(Usuario.ADMIN_USERNAME);
			usuario.setNome("Administrador");
			usuario.setRoleName(Usuario.ROLE_ADMIN);
			criptografarSenha(usuario);
			repository.save(usuario);
		}
	}

}
