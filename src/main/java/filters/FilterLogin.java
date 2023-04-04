 package filters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connections.SingleConnectionBanco;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@WebFilter(urlPatterns = {"/principal/*"})/*intercepta todas as requisições que vierem do projeto ou mapeamento*/
public class FilterLogin implements Filter {

    private static Connection connection;
    
    public FilterLogin() {
       
    }
    
    //encerra os processos quando o servidor é parado
    //mataria o processo de conexao com o banco
	public void destroy() {
		try {
			connection.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//Intercepta as requisições e da as respostas no sistema.
	// tudo que for feito no sistema vai passar por ele. 
	// exemplo validação de autenticacao, da commit e rolback.
	//validar e fazer redirecionamento especificos
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			
			String usuarioLogado = (String) session.getAttribute("usuario");
			String urlAutenticar = req.getServletPath();// url que esta sendo acessada.
			
			if(usuarioLogado == null && !urlAutenticar.equalsIgnoreCase("/ServletLogin")) {
				
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp?url="+
						urlAutenticar);
				request.setAttribute("msg", "Efetue o login! para acessar a página.");
				redirecionar.forward(request, response);
				return;//para a execucao e redireciona para o login
				
			}else {
				
				chain.doFilter(request, response);//ele deixa o processo do software continuar, sempre vai ter ele.
			}
			
			connection.commit();// deu tudo certo commmita as alterações no banco de dados
			
		}catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher red = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			red.forward(request, response);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				RequestDispatcher re = request.getRequestDispatcher("erro.jsp");
				request.setAttribute("msg", e.getMessage());
				re.forward(request, response);
			}
		}
	}

    /*Inicia os processos ou recurso quando o servidor sobe os projetos*/
	//inicia a conexao com o banco.
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection();
	}

}
