package br.edu.utfpr.jsf.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.edu.utfpr.jsf.model.Cliente;
import br.edu.utfpr.jsf.model.Pedido;
import br.edu.utfpr.jsf.model.PedidoItem;
import br.edu.utfpr.jsf.model.Produto;
import br.edu.utfpr.jsf.repository.ClienteRepository;
import br.edu.utfpr.jsf.repository.PedidoRepository;
import br.edu.utfpr.jsf.repository.ProdutoRepository;
import br.edu.utfpr.jsf.util.FacesUtil;

@Component
@Scope("view")
public class PedidoBean extends AbstractBean<Pedido, PedidoRepository> {
	
	private List<Cliente> clientes;
	private List<Produto> produtos;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public PedidoBean() {
		super(Pedido.class);
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

	@Override
	protected void carregarLookups() {
		clientes = clienteRepository.findAll();
		produtos = produtoRepository.findAll();
	}
	
	public void addItem() {
		getObjeto().addItem(new PedidoItem());
	}
	
	public void removerItem(int indice) {
		getObjeto().removerItem(indice);
	}
	
	@Override
	public void salvar() {
		if (getObjeto().getItens() == null || getObjeto().getItens().isEmpty()) {
			FacesUtil.addMensagemErro("Informe, ao menos, um produto");
		} else {
			super.salvar();
		}
	}

}











