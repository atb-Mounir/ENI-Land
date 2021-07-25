package org.lalaLand.bll;

public class BLLFactory {
	
	public static UtilisateurManager getUtilisateurBLL() {
		
		return new UtilisateurManager();
	}
	
	public static PageManager getPageBLL() {
		
		return new PageManager();
	}

}
