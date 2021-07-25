package org.lalaLand.dal;

public class DAOFactory {
	
	public static UtilisateurDAO getUtilisateurDAO() {
		
		return new UtilisateurDAOJdbcImpl();
	}
	
	public static PageDAO getPageDAO() {
		
		return new PageDAOJdbcImpl();
		
	}

}
