package br.edu.utfpr.jsf.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.jsf.util.FacesUtil;
import br.edu.utfpr.jsf.util.MessageUtil;

public abstract class AbstractBean<M, R extends JpaRepository<M, Integer>> {

	private M objeto;
	private List<M> lista;
	private final Class<M> modelClass;
	@Autowired
	protected R repository;
	private Operacao operacao;
	private boolean registroSelecionado;
	@Autowired
	protected MessageUtil messageUtil;

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
			String codigo = String.format("%s.selecione", modelClass.getSimpleName().toLowerCase());
			FacesUtil.addMensagemErro(messageUtil.getMessage(codigo));
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
		String codigo = String.format("%s.%s", modelClass.getSimpleName().toLowerCase(),
				Operacao.EDITAR == operacao ? "editar" : "inserir");
		return messageUtil.getMessage(codigo);
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
			String codigo = String.format("%s.selecione", modelClass.getSimpleName().toLowerCase());
			FacesUtil.addMensagemErro(messageUtil.getMessage(codigo));
		} else {
			repository.delete(objeto);
			objeto = null;
			registroSelecionado = false;
			listar();
		}
	}

	public void salvar() {
		repository.save(objeto);
		String codigo = String.format("%s.salvar.sucesso", modelClass.getSimpleName().toLowerCase());
		FacesUtil.addMensagemInfo(messageUtil.getMessage(codigo));
		objeto = null;
		operacao = Operacao.LISTAR;
		fecharDialog();
		listar();
	}

}
