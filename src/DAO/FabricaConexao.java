package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class FabricaConexao {
	public static Connection conectaBD() {
		Connection conn = null;
		
		try {
			String url = "jdbc:mysql://localhost:3306/Restaurante?user=marcos&password=1We9r34rc3628r@";
			conn = DriverManager.getConnection(url);
		} catch ( SQLException e ) {
			JOptionPane.showMessageDialog(null, "<Erro ConexaoDAO> " + e.getMessage());
		}
		
		return conn;
	}
}
