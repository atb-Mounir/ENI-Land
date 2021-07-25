package org.lalaLand.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.lalaLand.bo.Utilisateur;
import org.lalaLand.businessException.BusinessException;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	/**
	 * Constantes des requêtes SQL
	 */
	private static final String TEST_CONNEXION_UTILISATEUR = "SELECT id, login,password FROM UTILISATEUR WHERE login=? AND password=?;";
	private static final String SELECT_UTILISATEUR_BY_LOGIN = "SELECT ut.login, ut.password FROM UTILISATEUR ut WHERE ut.login=?";

	@Override
	public void creerUtilisateur(Utilisateur utilisateur) throws BusinessException {

		// TODO Auto-generated method stub
	}

	/**
	 * VERIFICATION DU LOGIN ET PASSWORD DE L'UTILISATEUR
	 */
	@Override
	public Utilisateur testConnexion(String login, String password) throws BusinessException {

		Utilisateur utilisateur = null;
		BusinessException businessException = new BusinessException();

		if (login == null || password == null) {

			businessException.ajouterErreur(CodesResultatDAL.TEST_CONNEXION_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			try {
				// Ouverture de la connexion
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;

				pstmt = cnx.prepareStatement(TEST_CONNEXION_UTILISATEUR);

				pstmt.setString(1, login);
				pstmt.setString(2, password);

				// Execution de la requête
				rs = pstmt.executeQuery();

				if (rs.next()) {
					utilisateur = new Utilisateur();

					utilisateur.setLogin(rs.getString(1));
					utilisateur.setPassword(rs.getString(2));
				} else {
					System.out.println("Erreur de connexion utilisateur");
				}
				rs.close();
				pstmt.close();
				cnx.commit();

			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_ECHEC);
			throw businessException;
		}

		return utilisateur;
	}

	@Override
	public Utilisateur selectUtilisateurById(int noUtilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utilisateur selectUtilisatuerByLogin(String login) throws BusinessException {

		Utilisateur utilisateur = null;
		BusinessException businessException = new BusinessException();

		if (login == null) {
			businessException.ajouterErreur(CodesResultatDAL.SELECT_UTILISATEUR_BY_PSEUDO_NULL);
			throw businessException;

		}
		try (Connection cnx = ConnectionProvider.getConnection()) {

			try {
				cnx.setAutoCommit(false);
				PreparedStatement pstmt;
				ResultSet rs;
				pstmt = cnx.prepareStatement(SELECT_UTILISATEUR_BY_LOGIN);

				pstmt.setString(1, login);

				rs = pstmt.executeQuery();

				if (rs.next()) {
					utilisateur = new Utilisateur();

					utilisateur.setLogin(rs.getString(1));
					utilisateur.setPassword(rs.getString(2));

				} else {
					System.out.println("Erreur de connexion utilisateur");
				}

				rs.close();
				pstmt.close();
				cnx.commit();

			} catch (Exception e) {
				e.printStackTrace();
				cnx.rollback();
				throw e;
			}

		} catch (Exception e) {
			e.printStackTrace();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_UTILISATEUR_BY_PSEUDO_NULL);
			throw businessException;
		}

		return utilisateur;
	}

	@Override
	public void modifierUtilisateur(Utilisateur utilisateurString, String motDePasseActurel) throws BusinessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void supprimerUtilisateur(int noUtilisateur) throws BusinessException {
		// TODO Auto-generated method stub

	}

}
