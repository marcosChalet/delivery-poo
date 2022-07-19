package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import controller.Cliente;
import controller.Usuario;
import model.Carrinho;
import model.Compra;
import model.Endereco;
import model.Produto;

public class ClienteDAO {

	public static ResultSet getLogin(String email, String senha) {
		
		String sql = "SELECT email, senha FROM pessoa WHERE email = ? AND senha = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null; // Classe que recupera os dados do banco (SELECT)
		
		try {
			conn = FabricaConexao.conectaBD();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, email);
			pstm.setString(2, senha);

			rset = pstm.executeQuery();
			return rset;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	public static Usuario getUsuario(String email, String senha) {
		
		Usuario usuario = new Usuario();
		Endereco endereco = new Endereco();
		String sql = "SELECT * FROM pessoa " + 
					 "JOIN endereco e on e.fk_pessoa = pessoa.id " +
					 "WHERE email = ? AND senha = ?;";

		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null; // Classe que recupera os dados do banco (SELECT)
		
		try {
			conn = FabricaConexao.conectaBD();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, email);
			pstm.setString(2, senha);
			
			rset = pstm.executeQuery();
			
			rset.next();
			usuario.setCodigo(rset.getInt("id"));
			usuario.setNome(rset.getString("nome"));
			usuario.setRg(rset.getString("rg"));
			usuario.setCpf(rset.getString("cpf"));
			usuario.setTelefone(rset.getString("telefone"));
			endereco.setCidade(rset.getString("cidade"));
			endereco.setNumero(rset.getInt("numero"));
			endereco.setCep(rset.getString("cep"));
			endereco.setRua(rset.getString("rua"));
			
			usuario.setEndereco(endereco);
			
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
		
		return usuario;
	}
	
	
	public static void alteraDadosCliente(Cliente cliente) {

		String sql = "UPDATE pessoa SET " +
		"nome = ?, email = ?, senha = ?, rg = ?, cpf = ?, telefone = ? " +
		"WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = FabricaConexao.conectaBD();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, cliente.getNome());
			pstm.setString(2, cliente.getEmail());
			pstm.setString(3, cliente.getSenha());
			pstm.setString(4, cliente.getRg());
			pstm.setString(5, cliente.getCpf());
			pstm.setString(6, cliente.getTelefone());
			pstm.setInt(7, cliente.getCodigo());
			
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
	
	public static Produto escolheProduto(int idProduto) {
		
		Produto produto = new Produto();
		String sql = "SELECT * FROM produtos WHERE id = "+ idProduto +";";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null; // Classe que recupera os dados do banco (SELECT)
		
		try {
			conn = FabricaConexao.conectaBD();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			rset.next();

			produto.setCodProduto(rset.getInt("id"));
			produto.setCodCategoria(rset.getInt("fk_categoria"));
			produto.setDescricao(rset.getString("descricao"));
			produto.setPreco(rset.getDouble("preco"));
			produto.setEstoque(rset.getInt("estoque"));
			
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
		
		return produto;
	}
	
	
	public static List<String> getProdutos(String descProduto) {
		
		List<String> produtos = new ArrayList<String>();
		String sql = "SELECT descricao FROM produtos WHERE descricao LIKE \"%"+ descProduto +"%\";";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null; // Classe que recupera os dados do banco (SELECT)
		
		try {
			conn = FabricaConexao.conectaBD();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				String descricao = rset.getString("descricao");
				produtos.add(descricao);
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
	
	
	public static List<String> getProdutosCategoria(int idCategoria) {
		
		List<String> produtos = new ArrayList<String>();
		String sql = "SELECT descricao FROM produtos WHERE fk_categoria ="+ idCategoria +";";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null; // Classe que recupera os dados do banco (SELECT)
		
		try {
			conn = FabricaConexao.conectaBD();
			pstm = conn.prepareStatement(sql);
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				String descricao = rset.getString("descricao");
				produtos.add(descricao);
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
	

	public static void addCompra(Compra c, int codigo) {
		String sql = "INSERT INTO compras (qtd_produtos, total, status_compra, fk_cod_cliente, fk_endereco_entrega) " +
					 "VALUES (?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = FabricaConexao.conectaBD();
			pstm = conn.prepareStatement(sql);
				
			pstm.setInt(1, c.getQtdProdutos());
			pstm.setDouble(2, c.getTotal());
			pstm.setString(3, c.getStatus());
			pstm.setInt(4, codigo);
			pstm.setInt(5, codigo);

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
	
	public static void addProdutosComprados(Carrinho c, int codigo) {
		String sql = "INSERT INTO prod_comprados (descricao, preco, qtd, fk_categoria, fk_compra) " +
				     "VALUES (?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			conn = FabricaConexao.conectaBD();
			pstm = conn.prepareStatement(sql);
			
			for (Produto p : c.getProdutos()) {
				pstm.setString(1, p.getDescricao());
				pstm.setDouble(2, p.getPreco());
				pstm.setInt(3, p.getQtd());
				pstm.setInt(4, p.getCodCategoria());
				pstm.setInt(5, codigo);
				
				pstm.execute();
			}
			
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

}
