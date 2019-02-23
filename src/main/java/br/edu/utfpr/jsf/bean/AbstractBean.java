package br.edu.utfpr.jsf.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.jsf.util.FacesUtil;

public abstract class AbstractBean<M, R extends JpaRepository<M, Integer>> {
	
	private M objeto;
	private List<M> lista;
	private final Class<M> modelClass;
	@Autowired
	protected R repository;
	private Operacao operacao;
	private boolean registroSelecionado;
	
	AbstractBean(Class<M> modelClass) {
		this.modelClass = modelClass;
	}
	
	@PostConstruct
	public void inicializar() {
		listar();
	}
	
	public M getObjeto() {
		return objeto;
	}

	public void setObjeto(M objeto) {
		this.objeto = objeto;
	}

	public List<M> getLista() {
		return lista;
	}

	public void setLista(List<M> lista) {
		this.lista = lista;
	}

	public boolean isRegistroSelecionado() {
		return registroSelecionado;
	}

	public void setRegistroSelecionado(boolean registroSelecionado) {
		this.registroSelecionado = registroSelecionado;
	}

	private void abrirDialog() {
		carregarLookups();
		FacesUtil.abrirDialog("dlgForm");
	}
	
	public void alterar() {
		if (objeto == null) {
			FacesUtil.addMensagemErro("Selecione um registro");
		} else {
			operacao = Operacao.EDITAR;
			abrirDialog();
		}
	}
	
	public void cancelar() {
		objeto = null;
		operacao = Operacao.LISTAR;
		fecharDialog();
	}
	
	protected void carregarLookups() {
		// TODO - implementar nos filhos, se necessário
	}
	
	private void fecharDialog() {
		registroSelecionado = false;
		FacesUtil.fecharDialog("dlgForm");
	}
	
	public String getTituloDialog() {
		return Operacao.EDITAR == operacao ?
				"Alterar Registro" : "Novo Registro";
	}
	
	private void listar() {
		lista = repository.findAll();
	}
	
	public void novo() throws InstantiationException, IllegalAccessException {
		objeto = modelClass.newInstance();
		operacao = Operacao.INSERIR;
		abrirDialog();
	}
	
	public void onRowSelect(SelectEvent event) {
		registroSelecionado = true;
	}
	
	public void remover() {
		if (objeto == null) {
			FacesUtil.addMensagemErro("Selecione um registro");
		} else {
			repository.delete(objeto);
			objeto = null;
			registroSelecionado = false;
			listar();
		}
	}
	
	public void salvar() {
		repository.save(objeto);
		FacesUtil.addMensagemInfo("Registro gravado com sucesso!");
		objeto = null;
		operacao = Operacao.LISTAR;
		fecharDialog();
		listar();
	}
	
}












