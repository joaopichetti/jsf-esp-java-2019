package br.edu.utfpr.jsf.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;

import br.edu.utfpr.jsf.dao.DAO;
import br.edu.utfpr.jsf.model.Cidade;
import br.edu.utfpr.jsf.util.FacesUtil;

@ManagedBean
public class CidadeBean {
	
	private Cidade cidade;
	private List<Cidade> cidades;
	private DAO<Cidade> dao;
	
	@PostConstruct
	public void inicializar() {
		dao = new DAO<>(Cidade.class);
		cidade = new Cidade();
		listar();
		System.out.println("CidadeBean Construído");
	}
	
	@PreDestroy
	public void finalizar() {
		System.out.println("Destruindo CidadeBean");
	}
	
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	public void alterar() {
		if (cidade == null) {
			FacesUtil.addMensagemErro("Selecione uma cidade");
		}
	}

	private void listar() {
		cidades = dao.findAll();
	}
	
	public void novo() {
		cidade = new Cidade();
	}	
	
	public void remover() {
		if (cidade == null) {
			FacesUtil.addMensagemErro("Selecione uma cidade");
		} else {
			dao.delete(cidade);
		}
		novo();
		listar();
	}
	
	public void salvar() {
		if (cidade.getCodigo() != null) {
			dao.update(cidade);
		} else {
			dao.insert(cidade);
		}
		novo();
		listar();
	}

}