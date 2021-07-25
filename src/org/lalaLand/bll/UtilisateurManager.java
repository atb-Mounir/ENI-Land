package org.lalaLand.bll;

import org.lalaLand.bo.Utilisateur;
import org.lalaLand.businessException.BusinessException;
import org.lalaLand.dal.DAOFactory;
import org.lalaLand.dal.UtilisateurDAO;

public class UtilisateurManager implements UtilisateurBLL {
	
	private UtilisateurDAO utilisateurDAO;
	
	public UtilisateurManager() {
		
		this.utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	

	@Override
	public Utilisateur testConnexion(String login, String password) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		
		this.validerLogin(login, businessException);
		
		return this.utilisateurDAO.testConnexion(login, password);
	}
	
	
// VALIDATION DU LOGIN
	private void validerLogin(String login, BusinessException businessException) {
		
		if (login==null || login.trim().length()>30) {
			
			businessException.ajouterErreur(CodeResultatBLL.UTILISATEUR_LOGIN_ERREUR);
		}
	}
	
// VALIDATION DU PASSWORD
	private void validerPassword(String password, String passordConfirm, BusinessException businessException) {
		
		// Verification du password et du login sont identiques
		if(!password.equals(passordConfirm)) {
			businessException.ajouterErreur(CodeResultatBLL.UTILISATEUR_PASSWORD_IDENTIQUE_ERREUR);
		
		} 
		// Verification de la longueur du password
		if(password==null || password.trim().length()>30) {
			businessException.ajouterErreur(CodeResultatBLL.UTILISATEUR_PASSWORD_ERREUR);
		}
		
	}


	@Override
	public Utilisateur selectUtilisateurById(int noUtilisateur) throws BusinessException {

		BusinessException businessException = new BusinessException();
		
		return this.utilisateurDAO.selectUtilisateurById(noUtilisateur);
	}
	

	@Override
	public void supprimerUtilisateur(int noUtilisateur) throws BusinessException {

		BusinessException businessException = new BusinessException();
		
		this.utilisateurDAO.supprimerUtilisateur(noUtilisateur);

	}
	
	

	@Override
	public void modifierUtilisateur(String login, String password) throws BusinessException {
		// TODO Auto-generated method stub
	}

	@Override
	public Utilisateur selectUtilisateurByLogin(String login) throws BusinessException {
		// TODO Auto-generated method stub
		return this.utilisateurDAO.selectUtilisatuerByLogin(login);
	}

}
