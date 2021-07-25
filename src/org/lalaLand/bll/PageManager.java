package org.lalaLand.bll;

import java.time.LocalDate;

import org.lalaLand.bo.Page;
import org.lalaLand.businessException.BusinessException;
import org.lalaLand.dal.DAOFactory;
import org.lalaLand.dal.PageDAO;

public class PageManager implements PageBLL {

	private PageDAO pageDAO;

	public PageManager() {
		this.pageDAO = DAOFactory.getPageDAO();
	}

	public Page selectPageByID(int noPage) throws BusinessException {

		return this.pageDAO.selectPageById(noPage);
	}

	// VALIDATION DU TITRE
	private void validerTitre(String titre, BusinessException businessException) {

		if (titre == null) {

			businessException.ajouterErreur(CodeResultatBLL.PAGE_TITRE_ERREUR);
		}
	}

	// VALIDATION DU NOMBRE HABITANTS
	private void validerNombreHbts(int nbHabitants, BusinessException businessException) {

		if (nbHabitants == 0) {
			businessException.ajouterErreur(CodeResultatBLL.PAGE_NOMBRE_HBTS_ERREUR);
		}
	}

	// VALIDATION DU CONTENU
	private void validerContenu(String contenu, BusinessException businessException) {

		if (contenu == null) {
			businessException.ajouterErreur(CodeResultatBLL.PAGE_CONTENU_ERREUR);
		}
	}

	// VALIDATION DE LA DATE
	private void validerDate(LocalDate dateConseil, BusinessException businessException) {

		if (dateConseil == null) {
			businessException.ajouterErreur(CodeResultatBLL.PAGE_DATE_CONSEIL_ERREUR);
		}
	}

	// ENREGISTREMENT DE LA NOUVELLE PAGE
	public void EnregistrementNouvellePage(String titre, int nbHabitants, String contenu, LocalDate dateConseil) {

		BusinessException businessException = new BusinessException();

		this.validerTitre(titre, businessException);
		this.validerContenu(contenu, businessException);
		this.validerNombreHbts(nbHabitants, businessException);
		this.validerDate(dateConseil, businessException);

		// Construction de la page avec les paramètres obtenus de la JSP aux nouveaux
		// objets

		Page newPage = new Page();

		newPage.setTitre(titre);
		newPage.setContenu(contenu);
		newPage.setNbHabitants(nbHabitants);
		newPage.setDateConseil(dateConseil);

		try {
			pageDAO.insertNouvellePage(newPage);

		} catch (BusinessException e) {
			e.printStackTrace();

		}

	}

	// ENREGISTREMENT DE LA NOUVELLE PAGE
	public void ModificationPage(String titre, int nbHabitants, String contenu, LocalDate dateConseil, int id) {

		BusinessException businessException = new BusinessException();

		this.validerTitre(titre, businessException);
		this.validerContenu(contenu, businessException);
		this.validerNombreHbts(nbHabitants, businessException);
		this.validerDate(dateConseil, businessException);

		// Construction de la page avec les paramètres obtenus de la JSP aux nouveaux
		// objets

		Page page = new Page(titre, nbHabitants, contenu, dateConseil, id);

		page.setTitre(titre);
		page.setContenu(contenu);
		page.setNbHabitants(nbHabitants);
		page.setDateConseil(dateConseil);
		page.setId(id);

		// Envoi de la page construite
		pageDAO.modifierPage(page);

	}

	// SUPPRESSION DE LA PAGE
	public void SupprimmerPage(int noPage) {
		BusinessException businessException = new BusinessException();

		try {
			pageDAO.supprimerLaPage(noPage);

		} catch (BusinessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void ModificationPage(String titre, int nbHabitants, String contenu, LocalDate dateConseil) {
		// TODO Auto-generated method stub

	}

}
