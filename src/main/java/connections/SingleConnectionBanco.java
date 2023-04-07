package connections;

import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.SQLException;
/**
 * 
 * @author Miguel
 * padrão de projeto Singleton
 */
public class SingleConnectionBanco {
	private static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String banco ="jdbc:sqlserver://localhost\\\\SQLEXPRESS:1433;databaseName=BancoJsp;";
	private static final String login ="miguel";
	private static final String senha="123";
	private static Connection connection = null;
	
	public static Connection getConnection() {// retorna a conexao existente
		return connection;
	}
	static {//para poder chamar a conexao sem precisar instanciar, de uma forma direta.
		conectarBanco();
	}
	
	public SingleConnectionBanco() {// toda vez que for instanciado vai conectar
		conectarBanco();
	}
	
	private static void conectarBanco() {
		try {
			if(connection == null) {
				Class.forName(driver);// carrega o driver de conexão do banco
				connection = DriverManager.getConnection(banco, login, senha);//conecta com o banco
				connection.setAutoCommit(false);// para não fazer operações sem nosso comando.
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
