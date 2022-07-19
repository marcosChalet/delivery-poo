package model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
	private int id;
	private int codUsuario;
	private int codProduto;
	private int qtd;
	private double subtotal;
	
	private List<Produto> produtos = new ArrayList<Produto>();
	
	public boolean foiInserido(Produto produto){
		for (Produto p: produtos) {
			if (p.getDescricao().equals(produto.getDescricao())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean adicionarProduto(Produto produto) {
		if ( foiInserido(produto) ) {	
			for (int i = 0; i < produtos.size(); i++) {
				if (produtos.get(i).getDescricao().equals(produto.getDescricao())) {
					produtos.get(i).setQtd(produtos.get(i).getQtd()+1);
				}
			}	
		}else {
			produto.setQtd(1);
			produtos.add(produto);
		}
		this.qtd++;
		this.subtotal += produto.getPreco();

		return true;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}
	public int getCodProduto() {
		return codProduto;
	}
	public void setCodProduto(int codProduto) {
		this.codProduto = codProduto;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	public double getSubtotal() {
		return this.subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public List<Produto> getProdutos() {
		return this.produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
}
