package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connections.SingleConnectionBanco;
import model.ModelLogin;

public class Dao_login {
	private Connection connection;
	
	public Dao_login() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public boolean autenticarLogin(ModelLogin model) {
		try {
			String sql = "select * from model_login where loginn=? and senha=?";
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1,model.getLogin());
			stm.setString(2, model.getSenha());
			
			ResultSet rs = stm.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
