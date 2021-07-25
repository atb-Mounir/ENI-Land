package org.lalaLand.bll;

import java.time.LocalDate;

import org.lalaLand.bo.Page;
import org.lalaLand.businessException.BusinessException;

public interface PageBLL {
	
	public Page selectPageByID(int noPage) throws BusinessException;
	
	public void ModificationPage(String titre, int nbHabitants, String contenu, LocalDate dateConseil);

	public void SupprimmerPage(int noPage); 
	
	public void EnregistrementNouvellePage(String titre, int nbHabitants, String contenu, LocalDate dateConseil);
}
