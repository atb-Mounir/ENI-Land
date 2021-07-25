package org.lalaLand.dal;

import org.lalaLand.bo.Utilisateur;
import org.lalaLand.businessException.BusinessException;

public interface UtilisateurDAO {
	
	public void creerUtilisateur(Utilisateur utilisateur) throws BusinessException;
	
	public Utilisateur testConnexion (String login, String password) throws BusinessException;
	
	public Utilisateur selectUtilisateurById(int noUtilisateur) throws BusinessException;
	
	public Utilisateur selectUtilisatuerByLogin(String login) throws BusinessException;
	
	public void modifierUtilisateur(Utilisateur utilisateurString, String motDePasseActurel) throws BusinessException;
	
	public void supprimerUtilisateur(int noUtilisateur) throws BusinessException;

}
