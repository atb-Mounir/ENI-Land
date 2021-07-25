package org.lalaLand.bo;

import java.time.LocalDate;

public class Page {
	
	private String titre;
	private int nbHabitants;
	private String contenu;
	private LocalDate dateConseil;
	private int id;
	
	
	//	----------------------	CONSTRUCTEURS	------------------------------
	
	
	public Page() {
		super();
	}	

	
	public Page(String titre, int nbHabitants, String contenu, LocalDate dateConseil, int id) {
		super();
		this.titre = titre;
		this.nbHabitants = nbHabitants;
		this.contenu = contenu;
		this.dateConseil = dateConseil;
		this.id = id;
		}
	
	// Constructeur sans l'id
	public Page(String titre, int nbHabitants, String contenu, LocalDate dateConseil) {
		super();
		this.titre = titre;
		this.nbHabitants = nbHabitants;
		this.contenu = contenu;
		this.dateConseil = dateConseil;
		
		}

	// ----------------------	GETTERS ET SETTERS	------------------------
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public int getNbHabitants() {
		return nbHabitants;
	}
	public void setNbHabitants(int nbHabitants) {
		this.nbHabitants = nbHabitants;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public LocalDate getDateConseil() {
		return dateConseil;
	}
	public void setDateConseil(LocalDate dateConseil) {
		this.dateConseil = dateConseil;
	}
	public int getId() {
		return id;
	}
	public void setId(int numeroPage) {
		this.id = numeroPage;
	}
	
	// ------------------------	TO STRING	--------------------------
	
	@Override
	public String toString() {
		return "Page [titre=" + titre + ", nbHabitants=" + nbHabitants + ", contenu=" + contenu + ", dateConseil="
				+ dateConseil + ", numeroPage=" + id + "]";
	}


}
