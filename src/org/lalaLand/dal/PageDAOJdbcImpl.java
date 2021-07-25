package org.lalaLand.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.lalaLand.bo.Page;
import org.lalaLand.businessException.BusinessException;

public class PageDAOJdbcImpl implements PageDAO {
	
	/**
	 * Constantes des différentes requêtes SQL en CRUD
	 */
	private static final String DETAIL_PAGE = "SELECT titre, nb_habitants, contenu, date_conseil, id FROM Page \r\n" +
												"WHERE id =(select Max(id) from page );";
	
	private static final String INSERT_PAGE = "INSERT INTO PAGE (titre, nb_habitants, contenu, date_conseil) VALUES (?,?,?,?)";
	
	private static final String DELETE_PAGE = "DELETE FROM PAGE WHERE id=?";
	
	private static final String UPDATE_PAGE = "UPDATE PAGE SET titre=?, nb_habitants=?, contenu=?, date_conseil=? WHERE id=?";


	@Override
	public Page selectPageById(int idPage) throws BusinessException {
		
		Page page = null;
		BusinessException businessException = new BusinessException();
		System.out.println("Je suis entrer dans la methode selectPageById");
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			System.out.println("j'ai effectué la connexion avec connectionProvider");
			try {
				System.out.println("je suis dans le try avant cnx");
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				pstmt = cnx.prepareStatement(DETAIL_PAGE);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					System.out.println("je suis dans le if pour rs.next et je vais construire la page");
					// Création de la page
					page = new Page();
					
					page.setTitre(rs.getString(1));
					page.setNbHabitants(rs.getInt(2));
					page.setContenu(rs.getString(3));
					page.setDateConseil((rs.getDate(4)).toLocalDate());
					page.setId(rs.getInt(5));
				} else {
					System.out.println("Erreur de connexion utilisateur");
				}
				// Fermeture des connexions et commit.
				rs.close();
				pstmt.close();
				cnx.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				cnx.rollback();
				throw businessException;
			}
		} catch (SQLException e1) {
			System.out.println("je n'ai pas effectué la connexion avec connectionProvider");
			e1.printStackTrace();
			
		} 
		
		System.out.println("je retourne le titre de la page de la jdbc : "+ page.getTitre());
		
		return page;
	}

	@Override
	public void insertNouvellePage(Page newPage) throws BusinessException {
		System.out.println("Je suis dans insertNouvellePage dans la jdbc");
		if (newPage==null) {
			
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
			throw businessException;
			
		} try(Connection cnx = ConnectionProvider.getConnection()) {
			
			cnx.setAutoCommit(false);
			
			try {
				// Connexion dans la jdbc avec PreparedStatement qui retourne la clé
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_PAGE, PreparedStatement.RETURN_GENERATED_KEYS);
				
				// Construction de la page à inserer avec toute les données récupérées dans le formulaire.
				pstmt.setString(1,  newPage.getTitre());
				pstmt.setInt(2, newPage.getNbHabitants());
				pstmt.setString(3, newPage.getContenu());
				pstmt.setDate(4, java.sql.Date.valueOf(newPage.getDateConseil()));
				
				// Execution de la requête
				pstmt.executeUpdate();
				
				// Récupération de la clé générée
				ResultSet rs = pstmt.getGeneratedKeys();
				
				if (rs.next()) {
					newPage.setId(rs.getInt(1));
				}
				
				// Fermeture de la connexion et commit
				pstmt.close();
				cnx.commit();
				
				
			} catch (Exception e) {
				
				// récupération du message d'erreur et rollback en cas d'échec
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
			
		} catch(Exception e) {
			
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJET_NULL);
		}

	}

	/**
	 * SUPPRIMER LA PAGE -----------------------------------------------
	 */
	@Override
	public void supprimerLaPage(int noPage) throws BusinessException {
		
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(DELETE_PAGE);) {
			
			cnx.setAutoCommit(false);
			
			try {
				pstmt.setInt(1, noPage);
				
			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
			cnx.commit();
			
		}catch (Exception e) {
			
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_NOUVELLE_PAGE);
		}
	}
	
	/**
	 * MODIFICATION DE LA PAGE --------------------------------------------------------
	 */

	@Override
	public void modifierPage(Page page) {
		
		BusinessException businessException = new BusinessException();
		//Page pageAmodifier = page;
		
		if (page==null) {
			
			businessException.ajouterErreur(CodesResultatDAL.MODIFIER_NOUVELLE_PAGE_NULL);
			try {
				throw businessException;
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		System.out.println("je suis dans modifierPageJdbcImpl");
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			
			System.out.println("je suis dans modifierPageJdbcImpl après cnx id = "+ page.getId());
			
			try {
				
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				pstmt = cnx.prepareStatement(UPDATE_PAGE);
				//pstmt.executeQuery();
				
				pstmt.setString(1,  page.getTitre());
				pstmt.setInt(2, page.getNbHabitants());
				pstmt.setString(3, page.getContenu());
				pstmt.setDate(4,  java.sql.Date.valueOf(page.getDateConseil()));
				pstmt.setInt(5,  page.getId());

				pstmt.executeUpdate();
				
				pstmt.close();
				cnx.commit();
				
				
			} catch(Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		}catch(Exception e) {
			
			e.printStackTrace();
			businessException.ajouterErreur(CodesResultatDAL.MODIFIER_NOUVELLE_PAGE_NULL);
			
			try {
				throw businessException;
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
		}



	}

}
