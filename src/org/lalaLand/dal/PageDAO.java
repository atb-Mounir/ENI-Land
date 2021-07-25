package org.lalaLand.dal;

import org.lalaLand.bo.Page;
import org.lalaLand.businessException.BusinessException;

public interface PageDAO {

	/**
	 * 
	 * @throws BusinessException
	 */
	
	public Page selectPageById (int idPage) throws BusinessException;
	public void insertNouvellePage (Page newPage) throws BusinessException;
	public void supprimerLaPage(int noPage) throws BusinessException;
	public void modifierPage(Page page);
	
}
