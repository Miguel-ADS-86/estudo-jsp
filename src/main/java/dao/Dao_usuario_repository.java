package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connections.SingleConnectionBanco;
import model.ModelLogin;

public class Dao_usuario_repository {
	private Connection connection;
    
	public Dao_usuario_repository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public ModelLogin cadastrarUsuario(ModelLogin obj, long userLogado) throws Exception{
		    if(obj.isNovo()) {
			String sql = "insert into model_login(nome,email,loginn,senha,usuario_id) values(?,?,?,?,?)";
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, obj.getNome());
			stm.setString(2, obj.getEmail());
			stm.setString(3, obj.getLogin());
			stm.setString(4, obj.getSenha());
			stm.setLong(5, userLogado);
			stm.execute();
			connection.commit();
		    }else {
		    	String sql = "update model_login set nome=?,email=?,loginn=?,senha=? where id_login ="+obj.getId_login();
		    	PreparedStatement stm = connection.prepareStatement(sql);
		    	stm.setString(1, obj.getNome());
		    	stm.setString(2, obj.getEmail());
		    	stm.setString(3, obj.getLogin());
		    	stm.setString(4, obj.getSenha());		    	
		    	stm.executeUpdate();
		    	connection.commit();
		    }
			
		  return this.consularUsuario(obj.getLogin());
	}
	
	public List<ModelLogin> listarUsuario(String login, long usuarioLogado) throws Exception{
		List<ModelLogin> listaUs = new ArrayList<ModelLogin>();
		
		String sql ="select * from model_login where nome like ? and usuario_id=?";
		PreparedStatement stm = connection.prepareStatement(sql);
		stm.setString(1,"%"+login+"%");
		stm.setLong(2, usuarioLogado);
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			ModelLogin usuario = new ModelLogin();
			usuario.setId_login(rs.getLong("id_login"));
			usuario.setNome(rs.getString("nome"));
			usuario.setEmail(rs.getString("email"));
			usuario.setLogin(rs.getString("loginn"));
			
			listaUs.add(usuario);
		}
		
		return listaUs;
	}
	
	public List<ModelLogin> listarUsuarios(long usuarioLogado) throws Exception{
		List<ModelLogin> listaUs = new ArrayList<ModelLogin>();
		
		String sql ="select * from model_login where isLogin != 1 and usuario_id="+usuarioLogado;
		PreparedStatement stm = connection.prepareStatement(sql);
		ResultSet rs = stm.executeQuery();
		while(rs.next()) {
			ModelLogin usuario = new ModelLogin();
			usuario.setId_login(rs.getLong("id_login"));
			usuario.setNome(rs.getString("nome"));
			usuario.setEmail(rs.getString("email"));
			usuario.setLogin(rs.getString("loginn"));
			
			listaUs.add(usuario);
		}
		
		return listaUs;
	}
	
	
	
	//atençao aqui para o possivel erro
	public ModelLogin consularUsuario(String login) throws SQLException {
		ModelLogin model = new ModelLogin();
		String sql = "select * from model_login where loginn = ?";
		PreparedStatement stm = connection.prepareStatement(sql);
		stm.setString(1, login);
		ResultSet rs = stm.executeQuery();
		if(rs.next()) {
			model.setId_login(rs.getLong("id_login"));
			model.setNome(rs.getString("nome"));
			model.setEmail(rs.getString("email"));
			model.setLogin(rs.getString("loginn"));
			model.setSenha(rs.getString("senha"));
		}
		
		return model;
	}
	
    public boolean existeLogin(String login) throws SQLException {
    	String sql = "select * from model_login where loginn = ?";
    	PreparedStatement stm = connection.prepareStatement(sql);
    	stm.setString(1, login);
    	ResultSet rs = stm.executeQuery();
    	if(rs.next()) {
    		return true;
    	}else {
    		return false;
    	}
    	
    }
    
    public void ExcluirLogin(String idUser) throws SQLException {
    	String sql = "delete from model_login where id_login = ?";
    	PreparedStatement stm = connection.prepareStatement(sql);
    	stm.setLong(1, Long.parseLong(idUser));
    	stm.executeUpdate();
    	connection.commit();
    }
    
    public ModelLogin editUser(long id_login) throws SQLException {
    	String sql = "select * from model_login where id_login = ? and isLogin != 1";
    	ModelLogin m = new ModelLogin();
    	PreparedStatement stm = connection.prepareStatement(sql);
        stm.setLong(1, id_login);
        ResultSet rs = stm.executeQuery();
        while(rs.next()) {
        	m.setId_login(rs.getLong("id_login"));
        	m.setNome(rs.getString("nome"));
        	m.setEmail(rs.getString("email"));
			m.setLogin(rs.getString("loginn"));
			m.setSenha(rs.getString("senha"));
        }
        return m;
        
    }
	
}
