package br.edu.utfpr.jsf.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.edu.utfpr.jsf.dao.DAO;
import br.edu.utfpr.jsf.model.Cidade;
import br.edu.utfpr.jsf.model.Cliente;
import br.edu.utfpr.jsf.util.FacesUtil;

@ManagedBean
public class ClienteFormBean {
	
	private Cliente cliente;
	private DAO<Cliente> dao;
	private Integer codigo;
	private List<Cidade> cidades;
	
	@PostConstruct
	public void inicializar() {
		dao = new DAO<>(Cliente.class);
		cliente = new Cliente();		
		cidades = new DAO<Cidade>(Cidade.class).findAll();
	}

	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}

	public String salvar() {
		if (cliente.getCodigo() == null) {
			dao.insert(cliente);
		} else {
			dao.update(cliente);
		}
		return voltarParaListagem();
	}
	
	public String voltarParaListagem() {
		return "lista";
	}
	
	public void carregarCliente() {
		if (codigo != null) {
			cliente = dao.findById(codigo);
		}
		if (cliente == null) {
			cliente = new Cliente();
		}
	}
	
	public void telefoneValido(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		final String pattern = "\\(?\\d{2}\\)? ?9?\\d{4}-?\\d{4}";
		if (value != null && !value.toString().isEmpty() &&
				!value.toString().matches(pattern)) {
			throw new ValidatorException(
					FacesUtil.criarMensagemErro("Telefone inválido"));
		}
	}
	
}
















