package servlets;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.Dao_usuario_repository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;
import utilitario.ControleLogin;

/**
 * 
 * @author Miguel
 *
 */
@WebServlet("/ServletUsuarioController")
public class ServletUsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Dao_usuario_repository user_repository = new Dao_usuario_repository();// objeto global
   
    public ServletUsuarioController() {
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	    	/*ele é mais usado para consultar e deletar*/
	    	String acao = request.getParameter("acao");
	    	if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
	    		
	    		String idUser = request.getParameter("id_login");
	    		Dao_usuario_repository dao = new Dao_usuario_repository();
	    		dao.ExcluirLogin(idUser);
	    		request.setAttribute("msg","Excluido com sucesso!");
	    		//para listar os todos os usuarios na tela.
	    		List<ModelLogin> logins = user_repository.listarUsuarios(ControleLogin.getUser(request));
	            request.setAttribute("logins",logins);
	    		request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
	    	
	    	}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarAjax")) {
	    		String idUser = request.getParameter("id_login");
	    		Dao_usuario_repository dao = new Dao_usuario_repository();
	    		dao.ExcluirLogin(idUser);
	    		response.getWriter().write("Excluido com sucesso!"); 
	    		/* TODO ajustar o deletar para quando excluir atualizar a lista 
	    		 * */
	    		
	    	}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarAjax")) {
	    		String login = request.getParameter("busca");
	    		List<ModelLogin> usuariosJason = user_repository.listarUsuario(login,ControleLogin.getUser(request));
	    		// este objeto ObjectMapper tem que pega a biblioteca do jackson json maven
	    		ObjectMapper mapa = new ObjectMapper();//criando o obejto para converter para jason
	    		String json = mapa.writeValueAsString(usuariosJason);//ele recebe um objeto ou uma lista e retorna uma String
	    		response.getWriter().write(json);
	    		
	    	}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("editUser")) {
	    		Long id = Long.valueOf(request.getParameter("id"));
	            ModelLogin login = user_repository.editUser(id);
	            String msg = "Edição";
	            request.setAttribute("msg",msg);
                request.setAttribute("formUsuario", login);
                List<ModelLogin> logins = user_repository.listarUsuarios(ControleLogin.getUser(request));
	            request.setAttribute("logins",logins);
	            
                request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
	    		
	    	}else if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
	    		
	            List<ModelLogin> logins = user_repository.listarUsuarios(ControleLogin.getUser(request));
	            request.setAttribute("logins",logins);   
                request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
	    	}
	    	else {
	    		List<ModelLogin> logins = user_repository.listarUsuarios(ControleLogin.getUser(request));
	            request.setAttribute("logins",logins);
	    		request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
	    	}	    	
	    }catch(Exception e) {
	    	e.printStackTrace();
	    	request.setAttribute("msg",e.getMessage());
			RequestDispatcher red = request.getRequestDispatcher("erro.jsp");
			red.forward(request, response);	
	    }		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	/*é mais usado para enviar formulario e atualizar*/
		try {
		String msg = "Operação realizada com sucesso!";
		
		String id_login = request.getParameter("id_login");	
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		ModelLogin usuario = new ModelLogin();
		usuario.setId_login(id_login != null && !id_login.isEmpty() ? Long.parseLong(id_login): null);
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setLogin(login);
		usuario.setSenha(senha);
		
		if(user_repository.existeLogin(usuario.getLogin()) && usuario.getId_login() == null) {
			msg = "Já existe o usuário com o mesmo login!";
		}else {
			 if(usuario.isNovo()) {
			   msg = "Operação realizada com sucesso!";
			 //para listar os todos os usuarios na tela.
	    		List<ModelLogin> logins = user_repository.listarUsuarios(ControleLogin.getUser(request));
	            request.setAttribute("logins",logins);
	    		//request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			 }else {
				msg = "Atualizado com sucesso!";
				//para listar os todos os usuarios na tela.
	    		List<ModelLogin> logins = user_repository.listarUsuarios(ControleLogin.getUser(request));
	            request.setAttribute("logins",logins);
	    		//request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			 }
			usuario = user_repository.cadastrarUsuario(usuario, ControleLogin.getUser(request));			
		}
		
		//para listar os todos os usuarios na tela.
		List<ModelLogin> logins = user_repository.listarUsuarios(ControleLogin.getUser(request));
        request.setAttribute("logins",logins);
		/////////////////////////////////////////////////////
		request.setAttribute("msg",msg);
		//outra forma de fazer
		//este atributo formUsuario para chamar o objeto no formulario
		request.setAttribute("formUsuario", usuario);
		request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
		/*
		RequestDispatcher redirecionar = request.getRequestDispatcher("principal/usuario.jsp");
		redirecionar.forward(request, response);
		 * */
		}catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("msg",e.getMessage());
			request.getRequestDispatcher("erro.jsp").forward(request, response);
 
		 }
	}
}
