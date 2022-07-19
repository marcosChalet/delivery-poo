package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Compra;
import model.Produto;

public class AdministradorDAO {
	
	public static void cadastraProduto(Produto p) {
		
		String sql = "INSERT INTO produtos (fk_categoria, descricao, preco, estoque) VALUES (?, ?, ?, ?);";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = FabricaConexao.conectaBD();
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, p.getCategoria().getCodigo());
			pstm.setString(2, p.getDescricao());
			pstm.setDouble(3, p.getPreco());
			pstm.setInt(4, p.getEstoque());

			pstm.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {		
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
	}
	
	public static void excluirProduto(int idProduto) {
		String sql = "DELETE FROM produtos WHERE (id = ?);";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = FabricaConexao.conectaBD();
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, idProduto);
			pstm.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {		
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
	}
	
	public static void excluirUsuario(int idUsuario) {
		String sql = "DELETE FROM pessoa WHERE (id = ?);";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = FabricaConexao.conectaBD();
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, idUsuario);
			pstm.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {		
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
	}
	
	public static void alterarEstoque(int id, double estoque) {
		String sql = "UPDATE produtos SET estoque = ? WHERE (id = ?);";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = FabricaConexao.conectaBD();
			pstm = conn.prepareStatement(sql);
			
			pstm.setDouble(1, estoque);
			pstm.setDouble(2, id);
			pstm.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {		
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
	}
	
	
	public static void alteraStatusCompra(int idCompra, String novoStatus) {
		String sql = "UPDATE compras SET status_compra = ? WHERE (id = ?);";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = FabricaConexao.conectaBD();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, novoStatus);
			pstm.setInt(2, idCompra);
			pstm.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {		
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
	}
	
	public static List<Compra> listarCompras() {
		
		List<Compra> compras = new ArrayList<Compra>();
		
		String sql = "SELECT * FROM compras"; 

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		try {
			conn = FabricaConexao.conectaBD();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Compra c = new Compra();
				c.setCodCompra(rset.getInt("id"));
				c.setQtdProdutos(rset.getInt("qtd_produtos"));
				c.setTotal(rset.getDouble("total"));
				c.setStatus(rset.getString("status_compra"));
				compras.add(c);
			}
			
			return compras;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
		
	}
}
