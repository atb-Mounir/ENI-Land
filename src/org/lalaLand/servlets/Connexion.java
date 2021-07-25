package org.lalaLand.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.lalaLand.bll.BLLFactory;
import org.lalaLand.bll.PageManager;
import org.lalaLand.bll.UtilisateurManager;
import org.lalaLand.bo.Page;
import org.lalaLand.bo.Utilisateur;
import org.lalaLand.businessException.BusinessException;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Connexion() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Récupération de la page numéro 1 dans la bdd
		PageManager pageManager = BLLFactory.getPageBLL();
		getPageById(request, pageManager);

		// Redirection vers la page de connexion
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Integer> listeCodesErreur = new ArrayList<>();

		String login = lireParametrelogin(request, listeCodesErreur);
		String password = lireParametrePassword(request, listeCodesErreur);

		// Récuperation des informations utilisateur de la bdd
		UtilisateurManager utilisateurManager = BLLFactory.getUtilisateurBLL();
		Utilisateur utilisateur = null;

		// Renvoie le code erreur si la requête n'aboutie pas
		if (listeCodesErreur.size() > 0) {
			System.out.println("err" + listeCodesErreur.size());
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");
			rd.forward(request, response);

		} else {

			try {

				// Test d'identification pour la connexion de l'utilisateur

				utilisateur = utilisateurManager.testConnexion(login, password);

				if (utilisateur != null) {

					// l'identification est réussi -> connexion
					response.sendRedirect("/lalaLand/Configuration");

				} else {

					// l'identification n'est pas réussi -> message d'erreur
					listeCodesErreur.add(CodesResultatsServlets.MOT_DE_PASSE_IDENTIFIANT_INCORRECT);
					request.setAttribute("listeCodesErreur", listeCodesErreur);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/connexion.jsp");

					rd.forward(request, response);
				}

			} catch (BusinessException e) {

				e.printStackTrace();
			}
		}
	}

	/**
	 * Récupération et test de validation du mot de passe entré par l'utilisateur
	 * 
	 * @param request
	 * @param listeCodesErreur
	 * @return
	 */
	private String lireParametrePassword(HttpServletRequest request, List<Integer> listeCodesErreur) {

		String password;
		password = request.getParameter("password");

		// Test de validation du mot passe
		if (password == null || password.trim().equals("")) {

			listeCodesErreur.add(CodesResultatsServlets.MOT_DE_PASSE_OBLIGATOIRE);
		}

		return password;
	}

	/**
	 * Récupération et test de validation du login entré par l'utilisateur
	 * 
	 * @param request
	 * @param listeCodesErreur
	 * @return
	 */
	private String lireParametrelogin(HttpServletRequest request, List<Integer> listeCodesErreur) {

		String login;
		login = request.getParameter("login");

		// Test de validation du login
		if (login == null || login.trim().equals("")) {
			listeCodesErreur.add(CodesResultatsServlets.PSEUDO_OBLIGATOIRE);
		}
		return login;
	}

	/**
	 * Récupération des informations de la page
	 * 
	 * @param request
	 * @param pageManager
	 */
	private void getPageById(HttpServletRequest request, PageManager pageManager) {
		try {
			Page page;
			int no_page = 1;
			page = pageManager.selectPageByID(no_page);
			request.setAttribute("page", page);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
