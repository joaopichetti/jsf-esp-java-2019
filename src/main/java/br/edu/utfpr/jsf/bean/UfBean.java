package br.edu.utfpr.jsf.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.utfpr.jsf.dao.DAO;
import br.edu.utfpr.jsf.model.Uf;
import br.edu.utfpr.jsf.util.FacesUtil;

@ManagedBean
@ViewScoped
public class UfBean {
	
	private Uf uf;
	private List<Uf> ufs;
	private DAO<Uf> dao;
	private Operacao operacao;
	
	@PostConstruct
	public void inicializar() {
		dao = new DAO<>(Uf.class);
		uf = new Uf();
		listar();
		operacao = Operacao.LISTAR;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public List<Uf> getUfs() {
		return ufs;
	}

	public void setUfs(List<Uf> ufs) {
		this.ufs = ufs;
	}

	public void abrirDialog() {
		FacesUtil.abrirDialog("dlgForm");
	}
	
	public void alterar() {
		if (uf == null) {
			FacesUtil.addMensagemErro("Selecione uma UF");
		} else {
			operacao = Operacao.EDITAR;
			abrirDialog();
		}
	}
	
	public void cancelar() {
		uf = null;
		operacao = Operacao.LISTAR;
		fecharDialog();
	}
	
	private void fecharDialog() {
		FacesUtil.fecharDialog("dlgForm");
	}
	
	public String getTituloDialog() {
		return Operacao.EDITAR == operacao ? "Alterar UF" : "Nova UF";
	}
	
	private void listar() {
		ufs = dao.findAll();
	}
	
	public void novo() {
		uf = new Uf();
		operacao = Operacao.INSERIR;
		abrirDialog();
	}
	
	public void remover() {
		if (uf.getCodigo() == null) {
			FacesUtil.addMensagemErro("Selecione uma UF");
		} else {
			dao.delete(uf);
			uf = null;
			listar();
		}
	}
	
	public void salvar() {
		if (uf.getCodigo() == null) {
			dao.insert(uf);
		} else {
			dao.update(uf);
		}
		uf = null;
		listar();
		operacao = Operacao.LISTAR;
		fecharDialog();
	}
	
}















