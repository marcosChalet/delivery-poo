package controller;

import java.util.List;

import DAO.AdministradorDAO;
import model.Compra;
import model.Produto;

public class Administrador extends Usuario{
	private int regFuncionario;
	private String funcao;
	private boolean estaLogado;

	public void fazLogin() {
		this.logar();
		this.estaLogado = true;
	}
	
	public boolean cadastrarProduto(Produto p) {
		AdministradorDAO.cadastraProduto(p);
		return true;
	}
	
	public boolean excluirProduto(int idProduto) {
		AdministradorDAO.excluirProduto(idProduto);
		return true;		
	}
	
	public boolean excluirUsuario(int idUsuario) {
		AdministradorDAO.excluirUsuario(idUsuario);
		return true;
	}
	
	public boolean alterarStatusCompra(int idCompra, String novoStatus) {
		AdministradorDAO.alteraStatusCompra(idCompra, novoStatus);
		return true;
	}
	
	public boolean alterarEstoque(int id, double estoque) {
		AdministradorDAO.alterarEstoque(id, estoque);
		return true;
	}
	
	public List<Compra> consultarCompras() {
		return AdministradorDAO.listarCompras();
	}

	
	public int getRegFuncionario() {
		return regFuncionario;
	}
	public void setRegFuncionario(int regFuncionario) {
		this.regFuncionario = regFuncionario;
	}
	public String getFuncao() {
		return funcao;
	}
	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}
	public boolean isEstaLogado() {
		return estaLogado;
	}
	public void setEstaLogado(boolean estaLogado) {
		this.estaLogado = estaLogado;
	}
}
