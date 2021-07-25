package org.lalaLand.bll;

import org.lalaLand.bo.Utilisateur;
import org.lalaLand.businessException.BusinessException;

public interface UtilisateurBLL {
	
	public Utilisateur testConnexion(String login, String password) throws BusinessException;
	
	public Utilisateur selectUtilisateurById(int noUtilisateur) throws BusinessException;
	
	public void supprimerUtilisateur(int noUtilisateur) throws BusinessException;
	
	public void modifierUtilisateur(String login, String password) throws BusinessException;
	
	public Utilisateur selectUtilisateurByLogin(String login) throws BusinessException;

}
