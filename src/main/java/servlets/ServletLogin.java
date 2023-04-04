package servlets;

import java.io.IOException;
import dao.Dao_login;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@WebServlet(urlPatterns = {"/ServletLogin","/principal/ServletLogin"})
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Dao_login dao = new Dao_login();
    
    public ServletLogin() {
         
    }
    
    //recebe os dados passado pela url em parametros
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("Logout")) {
			request.getSession().invalidate();// invalida a sessão
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			redirecionar.forward(request, response);
		}else {
		
			doPost(request, response);// chamando o metodo doPost
		}
	}
    //recebe os dados enviados pelo formulário
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		if(login != null && !login.isEmpty()
				&& senha != null && !senha.isEmpty()) {
			ModelLogin model = new ModelLogin();
			model.setLogin(login);
			model.setSenha(senha);
	           if(dao.autenticarLogin(model)) {
	        	   //mantem o usuario logado no sistema atravez do atributo de sessão
	        	   request.getSession().setAttribute("usuario", model.getLogin());// ele recebe um atributo e um objeto
	        	   if(url == null || url.equalsIgnoreCase("null")) {
	        		   url = "principal/principal.jsp";
	        	   }
	        	   RequestDispatcher redirecionar = request.getRequestDispatcher(url);
	        	   redirecionar.forward(request, response);
	           }else {
	        	   RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
	   			   request.setAttribute("msg", "Informe o login e senha corretamente");
	   			   redirecionar.forward(request, response);
	           }
		}else {
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			//quando eu quero passar uma mensagem uso o codigo abaixo
			request.setAttribute("msg", "Informe o login e senha corretamente");
			redirecionar.forward(request, response);// ele faz o redirecionamento
			
		}
	    		
	}catch(Exception e) {
		   e.printStackTrace();
		   RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
		   request.setAttribute("msg", e.getMessage());
		   redirecionar.forward(request, response);
		}
	}
}
