package org.lalaLand.servlets;

import java.io.IOException;
import java.time.LocalDate;
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
import org.lalaLand.bo.Page;
import org.lalaLand.businessException.BusinessException;

@WebServlet("/Configuration")
public class Configuration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Configuration() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Récupération de la page numéro 1 dans la bdd
		PageManager pageManager = BLLFactory.getPageBLL();
		getPageById(request, pageManager);

		// Redirection vers la page de configuration
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/configuration.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Integer> listeCodesErreur = new ArrayList<>();

		if (request.getParameter("enregistrer") == null) {

			System.out.println("err" + listeCodesErreur.size());
			request.setAttribute("listeCodesErreur", listeCodesErreur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/configuration.jsp");
			rd.forward(request, response);

		} else {

			String titre = request.getParameter("titre");
			int nbHabitants = Integer.parseInt(request.getParameter("nbHabitants"));
			String contenue = request.getParameter("contenue");
			LocalDate dateConseil = LocalDate.parse(request.getParameter("dateConseil"));
			int id = Integer.parseInt(request.getParameter("pageId"));

			// Enregistrement de la page en base de données.
			try {

				System.out.println("dans doPost valeur de l'id : " + id);
				PageManager pageManager = BLLFactory.getPageBLL();
				pageManager.ModificationPage(titre, nbHabitants, contenue, dateConseil, id);
				;

			} catch (Exception e) {

				e.printStackTrace();
				listeCodesErreur.add(CodesResultatsServlets.ENVOI_NOUVELLE_PAGE_A_PAGE_MANAGER_ERREUR);
			}

			// Redirection vers la page d'accueil
			response.sendRedirect("/lalaLand/");

		}

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
			System.out.println("le titre de la page = " + page.getNbHabitants());
			request.setAttribute("page", page);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
