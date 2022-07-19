package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;
import model.Produto;

public class ProdutosDAO {

	/* Excluir <função do administrador> */
	public void saveProduto(Produto produto) {
		String sql = "INSERT INTO produtos (cod_categoria, descricao, preco, estoque) VALUES (?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = FabricaConexao.conectaBD();
			pstm = conn.prepareStatement(sql);
			
			// Adicionar valores esperados pela query
			
			pstm.setInt(1, 5);
			pstm.setString(2, "Soda 1litro");
			pstm.setDouble(3, 13.99);
			pstm.setInt(4, 120);

			pstm.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static List<Produto> getProdutos() {
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		String sql = "SELECT * FROM produtos ORDER BY fk_categoria;";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		// Classe que recupera os dados do banco (SELECT)
		ResultSet rset = null;
		
		try {
			conn = FabricaConexao.conectaBD();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Produto produto = new Produto();
				produto.setCodProduto(rset.getInt("id"));
				produto.setDescricao(rset.getString("descricao"));
				produto.setPreco(rset.getDouble("preco"));
				produto.setEstoque(rset.getInt("estoque"));
				produto.setCodCategoria(rset.getInt("fk_categoria"));
				
				produtos.add(produto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				
				if(pstm != null) {
					pstm.close();
				}
				
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return produtos;
	}
	
	public static List<Categoria> getCategorias() {
		
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		String sql = "SELECT * FROM categorias;";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		// Classe que recupera os dados do banco (SELECT)
		ResultSet rset = null;
		
		try {
			conn = FabricaConexao.conectaBD();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Categoria categoria = new Categoria();
				categoria.setCodigo(rset.getInt("id"));
				categoria.setTipo(rset.getString("nome_categoria"));
								
				categorias.add(categoria);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				
				if(pstm != null) {
					pstm.close();
				}
				
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return categorias;
	}
}
