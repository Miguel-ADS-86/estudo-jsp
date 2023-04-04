package model;

import java.io.Serializable;

/**
 * sempre qaundo criar uma classe java de modelo implementar o Serializable
 * 
 * @author Miguel
 *
 */
public class ModelLogin implements Serializable {
	private static final long serialVersionUID = 1L;// ele serve para a parte de compilação das classes
	
	private String login;
	private String senha;
	private Long id_login;
	private String email;
	private String nome;
    
	public boolean isNovo() {
		if(this.id_login == null) {
			return true;//cadastrar um novo
		}else if(this.id_login != null && this.id_login > 0) {
			return false;// atualiza
		}
		return this.id_login == null;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getId_login() {
		return this.id_login;
	}

	public void setId_login(Long id_login) {
		this.id_login = id_login;
	}

}
