package controller;

import java.util.List;

import DAO.ProdutosDAO;
import model.Categoria;
import model.Produto;

public class Cliente extends Usuario{
	boolean estaLogado;

	public List<Produto> listarProdutos() {
		return ProdutosDAO.getProdutos();
	}
	
	public List<Categoria> getCategorias() {
		return ProdutosDAO.getCategorias();
	}
	
	public void fazLogin() {
		this.logar();
		this.estaLogado = true;
	}

	public boolean estaLogado() {
		return estaLogado;
	}

	public void setEstaLogado(boolean estaLogado) {
		this.estaLogado = estaLogado;
	}
}
