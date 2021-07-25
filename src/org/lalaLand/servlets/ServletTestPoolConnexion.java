package org.lalaLand.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ServletTestPoolConnexion
 */
@WebServlet(description = "Servlet destin�e � tester la connection � la base de donn�es.", urlPatterns = { "/ServletTestPoolConnexion" })
public class ServletTestPoolConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTestPoolConnexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		try {
			Context context = new InitialContext();
			
			// Rechercher la DataSOurce
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/pool_cnx");
			
			/**
			 * Demande une connexion. La m�thode getConnection met la demande en attente
			 * tant qu'il n'y a pas de connexion disponibles dans le pool.
			 */
			Connection cnx = dataSource.getConnection();
			out.print("La connexion est "+(cnx.isClosed()? "ferm�e ":" ouvert ")+ ".");
			
			// Liberer la connexion lorsque'on en a plus besoin:
			cnx.close(); // la connexion n'est pas ferm�e mais remise dans le pool de connexion.
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			out.println("Une erreur est survenue lors de l'utilisation de la base de donn�es : "+ e.getMessage());
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
