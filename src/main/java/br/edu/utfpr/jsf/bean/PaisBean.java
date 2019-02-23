package br.edu.utfpr.jsf.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.edu.utfpr.jsf.dao.DAO;
import br.edu.utfpr.jsf.model.Pais;
import br.edu.utfpr.jsf.util.FacesUtil;

@ManagedBean
@ViewScoped
public class PaisBean {
	
	private Pais pais;
	private List<Pais> paises;
	private DAO<Pais> dao;
	private Operacao operacao;
	
	@PostConstruct
	public void inicializar() {
		dao = new DAO<>(Pais.class);
		pais = new Pais();
		listar();
		operacao = Operacao.LISTAR;
	}
	
	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public List<Pais> getPaises() {
		return paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	private void abrirDialog() {
		FacesUtil.abrirDialog("dlgForm");
	}
	
	public void alterar() {
		if (pais == null) {
			FacesUtil.addMensagemErro("Selecione uma País");
		} else {
			operacao = Operacao.EDITAR;
			abrirDialog();
		}
	}
	
	public void cancelar() {
		pais = new Pais();
		operacao = Operacao.LISTAR;
		fecharDialog();
	}
	
	private void fecharDialog() {
		FacesUtil.fecharDialog("dlgForm");
	}

	public String getTituloDialog() {
		return operacao == Operacao.EDITAR ? "Alterar País" : "Novo País";
	}
	
	private void listar() {
		paises = dao.findAll();
	}
	
	public void novo() {
		pais = new Pais();
		operacao = Operacao.INSERIR;
		abrirDialog();
	}
	
	public void remover() {
		if (pais == null) {
			FacesUtil.addMensagemErro("Selecione um País");
		} else {
			dao.delete(pais);
		}
		pais = new Pais();
		listar();
	}
	
	public void salvar() {
		if (pais.getCodigo() == null) {
			dao.insert(pais);
		} else {
			dao.update(pais);
		}
		pais = new Pais();
		listar();
		operacao = Operacao.LISTAR;
		fecharDialog();
	}

}