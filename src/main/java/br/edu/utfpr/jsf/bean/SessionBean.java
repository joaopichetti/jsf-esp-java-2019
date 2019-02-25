package br.edu.utfpr.jsf.bean;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.edu.utfpr.jsf.model.Usuario;
import br.edu.utfpr.jsf.util.Theme;

@Component
@Scope("session")
public class SessionBean {
	
	private String theme = "bootstrap";
	private List<Theme> themes = Arrays.asList(
			new Theme("Afterdark", "afterdark"),
			new Theme("Afternoon", "afternoon"),
			new Theme("Black-tie", "black-tie"),
			new Theme("Blitzer", "blitzer"),
			new Theme("Bluesky", "bluesky"),
			new Theme("Bootstrap", "bootstrap"),
			new Theme("Casablanca", "casablanca"),
			new Theme("Cruze", "cruze"),
			new Theme("Cupertino", "cupertino"),
			new Theme("Dark-hive", "dark-hive"),
			new Theme("Delta", "delta"),
			new Theme("Dot-luv", "dot-luv"),
			new Theme("Eggplant", "eggplant"),
			new Theme("Excite-bike", "excite-bike"),
			new Theme("Flick", "flick"),
			new Theme("Glass-x", "glass-x"),
			new Theme("Home", "home"),
			new Theme("Hot-sneaks", "hot-sneaks"),
			new Theme("Humanity", "humanity"),
			new Theme("Le-frog", "le-frog"),
			new Theme("Midnight", "midnight"),
			new Theme("Mint-choc", "mint-choc"),
			new Theme("Omega", "omega"),
			new Theme("Overcast", "overcast"),
			new Theme("Pepper-grinder", "pepper-grinder"),
			new Theme("Redmond", "redmond"),
			new Theme("Rocket", "rocket"),
			new Theme("Sam", "sam"),
			new Theme("Smoothness", "smoothness"),
			new Theme("South-street", "south-street"),
			new Theme("Start", "start"),
			new Theme("Sunny", "sunny"),
			new Theme("Swanky-purse", "swanky-purse"),
			new Theme("Trontastic", "trontastic"),
			new Theme("Ui-darkness", "ui-darkness"),
			new Theme("Ui-lightness", "ui-lightness"),
			new Theme("Vader", "vader")
	);
	private Usuario usuario;

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	
	public List<Theme> getThemes() {
		return themes;
	}

	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}

	public Usuario getUsuario() {
		if (usuario == null) {
			Authentication authentication = SecurityContextHolder.getContext()
					.getAuthentication();
			if (authentication != null && 
					authentication.getPrincipal() instanceof Usuario) {
				usuario = (Usuario) authentication.getPrincipal();
			}
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}