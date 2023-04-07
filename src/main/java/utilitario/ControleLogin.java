package utilitario;

import java.sql.SQLException;

import dao.Dao_usuario_repository;
import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;

public class ControleLogin {
   // aqui vou criar um metodo statico para poder chamar no dao 
  //passando o id do usuario logado, para so exibir os usuarios 
  // que o propio usuario cadastrou 
    
 
	public static long getUser (HttpServletRequest request) throws SQLException {
		Dao_usuario_repository obj = new Dao_usuario_repository();
	    // HttpSession session = request.getSession(); -> pode fazer desta forma ou a
		//de baixo sendo mais rapido session.getAttribute("");
	    String usuarioLogado = (String) request.getSession().getAttribute("usuario");
	    return obj.consularUsuario(usuarioLogado).getId_login();
	}
	
}
