package br.edu.utfpr.jsf.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import br.edu.utfpr.jsf.dao.DAO;
import br.edu.utfpr.jsf.model.Cliente;
import br.edu.utfpr.jsf.model.Pedido;
import br.edu.utfpr.jsf.model.PedidoItem;
import br.edu.utfpr.jsf.model.Produto;
import br.edu.utfpr.jsf.util.FacesUtil;

@ManagedBean
@ViewScoped
public class PedidoBean {
	
	private Pedido pedido;
	private List<Pedido> pedidos;
	private List<Cliente> clientes;
	private List<Produto> produtos;
	private DAO<Pedido> dao;
	private DAO<Cliente> clienteDao;
	private DAO<Produto> produtoDao;
	private boolean pedidoSelecionado;
	private Operacao operacao;
	
	@PostConstruct
	public void inicializar() {
		operacao = Operacao.LISTAR;
		dao = new DAO<>(Pedido.class);
		clienteDao = new DAO<>(Cliente.class);
		produtoDao = new DAO<>(Produto.class);
		listar();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public boolean isPedidoSelecionado() {
		return pedidoSelecionado;
	}

	public void setPedidoSelecionado(boolean pedidoSelecionado) {
		this.pedidoSelecionado = pedidoSelecionado;
	}

	public void addItem() {
		pedido.addItem(new PedidoItem());
	}
	
	public void alterar() {
		if (pedido == null) {
			FacesUtil.addMensagemErro("Selecione um pedido");
		} else {
			operacao = Operacao.EDITAR;
			carregarLookups();
		}
	}
	
	private void carregarLookups() {
		clientes = clienteDao.findAll();
		produtos = produtoDao.findAll();
	}
	
	private void listar() {
		pedidos = dao.findAll();
	}
	
	public void novo() {
		pedido = new Pedido();
		carregarLookups();
		pedidoSelecionado = false;
		operacao = Operacao.INSERIR;
	}
	
	public void remover() {
		if (pedido == null) {
			FacesUtil.addMensagemErro("Selecione um pedido");
		} else {
			dao.delete(pedido);
			voltarParaListagem();
			listar();
		}
	}
	
	public void removerItem(int indice) {
		pedido.removerItem(indice);
	}
	
	public void salvar() {
		if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
			FacesUtil.addMensagemErro("Informe, ao menos, um produto");
		} else {
			if (pedido.getCodigo() == null) {
				dao.insert(pedido);
			} else {
				dao.update(pedido);
			}
			FacesUtil.addMensagemInfo("Pedido gravado com suceso!");
			voltarParaListagem();
			listar();
		}
	}
	
	public void voltarParaListagem() {
		pedido = new Pedido();
		pedidoSelecionado = false;
		operacao = Operacao.LISTAR;
	}
	
	public void onRowSelect(SelectEvent event) {
		pedidoSelecionado = true;
	}
	
	public String getTituloForm() {
		return Operacao.EDITAR == operacao ?
				"Alterar Pedido" :
				"Novo Pedido";
	}
	
	public boolean mostrarForm() {
		return Operacao.LISTAR != operacao;
	}

}











