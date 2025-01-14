package org.lalaLand.messages;

import java.util.ResourceBundle;

/**
 * Cette classe permet de lire le contenu du fichier messages_erreur.properties
 * @author Utilisateur
 */

public class LecteurMessage {

	private static ResourceBundle rb;
	
	static {
		try {
			rb = ResourceBundle.getBundle("org.lalaLand.message.messages_erreur");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getMessageErreur(int code) {
		String message="";
		
		try {
			if(rb!=null) {
				
				message = rb.getString(String.valueOf(code));
			} else {
				
				message="Probl�me � la lecteure du fichier contenant les messages";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message="Une erreur inconnue est survenue";
		} System.out.println("message= "+message);
		
		return message;
	}


}
