package org.lalaLand.bo;

public class Utilisateur {

	private int idUtilisateur;
	private String login;
	private String password;

	
	// -------------- CONSTRUCTEUR	--------------------------
	public Utilisateur() {
		super();
		this.idUtilisateur = idUtilisateur;
		this.login = login;
		this.password = password;
	}
	// -------------- GETTERS/SETTERS	--------------------------

	public int getIdUtilisateur() {
		return idUtilisateur;
	}


	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	// -------------- TO STRING		--------------------------


	@Override
	public String toString() {
		return "Utilisateur [idUtilisateur=" + idUtilisateur + ", login=" + login + ", password=" + password + "]";
	}


}
