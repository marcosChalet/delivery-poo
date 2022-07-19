package controller;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DAO.ClienteDAO;
import model.Carrinho;
import model.Compra;
import model.Pessoa;
import model.Produto;

public class Usuario extends Pessoa{
	private int codigo;
	private String senha;
	private String email;	
	private Produto produto;
	private Carrinho carrinho;
	private Compra compra;
	private List<Compra> historicoCompras;
	
	public Usuario() {
		carrinho = new Carrinho();
		compra = new Compra();
		historicoCompras = new ArrayList<Compra>();
		
		//String msgCompra = "Aguardando compra. [Adicione produtos ao carrinho e finalize a compra]";
		String msgCompra = "andamento";
		compra.setStatus(msgCompra);
	}

	public boolean logar() {
		try {
			ResultSet rset = ClienteDAO.getLogin(this.email, this.senha);
			
			if (rset.next()) {
				Usuario usrTmp = ClienteDAO.getUsuario(this.email, this.senha);
				
				this.codigo = usrTmp.getCodigo();
				this.setNome(usrTmp.getNome());
				this.setRg(usrTmp.getRg());
				this.setCpf(usrTmp.getCpf());
				this.setEndereco(usrTmp.getEndereco());
				this.setTelefone(usrTmp.getTelefone());
				
				return true;
			}else {		
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean alterarDadosPessoais(Cliente cl) {
		ClienteDAO.alteraDadosCliente(cl);
		return true;
	}
	
	public boolean addProdutoCarrinho() {
		if (this.produto != null) {
			carrinho.adicionarProduto(this.produto);
			return true;
		}else {
			System.out.println("Escolha um produto <addProdutoCarrinho>: ");
			return false;
		}
	}
	
	public void remProdCarrinho(int idProduto) {
		escolheProduto(idProduto);
	
		int indexProd = -1, i = 0;
		for (Produto p : carrinho.getProdutos()) {
			if (p.getCodProduto() == idProduto) {
				indexProd = i;
			}
			i++;
		}

		if (indexProd != -1) {
			carrinho.getProdutos().remove(indexProd);
		}else {
			System.out.println("O produto não está no carrinho! Tente novamente.");
		}
	}
	
	public List<Produto> getProdutosCarrinho() {
		return carrinho.getProdutos();
	}
	
	public void escolheProduto(int idProduto) {
		produto = ClienteDAO.escolheProduto(idProduto);
	}
	
	public List<String> buscarProduto(String descricaoProduto) {
		return ClienteDAO.getProdutos(descricaoProduto);
	}
	
	public List<String> listarProdPorCategoria(int idCategoria) {
		return ClienteDAO.getProdutosCategoria(idCategoria);
	}
	
	public List<Compra> listarCompras() {
		return historicoCompras;
	}
	
	public void finalizarCompra() {
		if (this.carrinho != null) {
			compra.setCarrinho(this.carrinho);
			compra.setCodUsuario(this.codigo);
			compra.setEnderecoEntrega(this.getEndereco());
			compra.setQtdProdutos(this.carrinho.getQtd());
			compra.setTotal(this.carrinho.getSubtotal());
			
			//String msgCompra = "Compra finalizada. [Aguardando processamento]";
			String msgCompra = "finalizada";
			compra.setStatus(msgCompra);

			// Salvando na lista de compras e no banco de dados
			historicoCompras.add(this.compra);
			ClienteDAO.addCompra(this.compra, this.codigo);
			ClienteDAO.addProdutosComprados(carrinho, this.codigo);
			
			this.compra = new Compra();
			this.carrinho = new Carrinho();

		} else {
			System.out.println("Adicione produtos ao carrinho para continuar.");	
		}
	}
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return  "Nome:     " + this.getNome()     + "\n" +
				"Email:    " + this.email         + "\n" +
				"Senha:    " + this.senha         + "\n" +
				"CPF:      " + this.getCpf()      + "\n" +
				"RG:       " + this.getRg()       + "\n" +
				"Telefone: " + this.getTelefone() + "\n" +
				this.getEndereco().toString();
	}
}
