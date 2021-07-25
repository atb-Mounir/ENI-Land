package org.lalaLand.servlets;

public abstract class CodesResultatsServlets {

	/**
	 * Problème d'envoie de données vers la BLL
	 *  
	 */
	
	public static final int PSEUDO_OBLIGATOIRE=30003;
	
	public static final int MOT_DE_PASSE_OBLIGATOIRE=30004;
	
	public static final int MOT_DE_PASSE_IDENTIFIANT_INCORRECT=30005;
	
	public static final int MOT_DE_PASSE_OBLIGATOIRE_CREATION=30006;
	
	public static final int MOT_DE_PASSE__CONFIRMATION_NON_IDENTIQUE_CREATION=30007;
	
	public static final int PSEUDO_DEJA_EXISTANT=30008;
	
	public static final int ENVOI_NOUVELLE_PAGE_A_PAGE_MANAGER_ERREUR=30009;
}
