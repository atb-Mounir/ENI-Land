package org.lalaLand.servlets;

import java.io.IOException;

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

/**
 * Servlet qui redirige vers la jsp accueil de l'application
 */
@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Accueil() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PageManager pageManager = BLLFactory.getPageBLL();

		// Récupération de la page numéro 1 dans la bdd
		getPageById(request, pageManager);

		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/accueil.jsp");

		rd.forward(request, response);

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
