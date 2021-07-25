package org.lalaLand.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

abstract class ConnectionProvider {
	 private static DataSource datasource;
	 
	 /**
	  * Au chargement de la classe, la DataSource est recherché dans l'arbre JNDI
	  */
	 
	 static { 
		 Context context;
		 try { 
			 context = new InitialContext();
			 ConnectionProvider.datasource = (DataSource)context.lookup("java:/comp/env/jdbc/pool_cnx");
		 } catch (NamingException e) {
			 e.printStackTrace();
			 throw new RuntimeException("Impossible d'acceder à la base de données");
		 }
	 }
	 
	 /**
	  * Cette methode retourne une connection operationnelle issue du pool de connexion 
	  * vers la base de données
	  * @return
	  * @throws SQLException
	  */
	 public static Connection getConnection() throws SQLException {
		 return ConnectionProvider.datasource.getConnection();
	 }
	
}
