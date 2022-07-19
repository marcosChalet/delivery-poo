package model;

import java.util.Calendar;

public class Compra {
	private int codCompra;
	private int codUsuario;
	private int qtdProdutos;
	private double total;
	private String status;
	private Carrinho carrinho;
	private Endereco enderecoEntrega;
	Calendar data = Calendar.getInstance();
	
	public Compra() {
		carrinho = new Carrinho();
	}
	
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCodCompra() {
		return codCompra;
	}
	public void setCodCompra(int codCompra) {
		this.codCompra = codCompra;
	}
	public int getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}
	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}
	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getQtdProdutos() {
		return qtdProdutos;
	}
	public void setQtdProdutos(int qtdProdutos) {
		this.qtdProdutos = qtdProdutos;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public Carrinho getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	@Override
	public String toString() {
		return  "Código [Compra]: " + codCompra +
			    "\nCódigo [Usuário]: " + codUsuario +
			    //"\nCódigo [Produto]: " + codProduto +
			    //"\nQuantidade: " + qtdProdutos +
			    "\nTotal: " + total +
			    "\nEndereco de Entrega: " + enderecoEntrega +
			    "\nData: " + data.getTime() +
			    "\nStatus: " + getStatus();
	}
}
