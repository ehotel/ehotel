package de.hs.lu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Zimmer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private int zimmerNr;
	
	@ManyToOne
	@JoinColumn(name="zimmerkategorie_id")
	private Zimmerkategorie zimmerkategorie;
	
	public int getZimmerNr() {
		return zimmerNr;
	}

	public void setZimmerNr(int zimmerNr) {
		this.zimmerNr = zimmerNr;
	}

	public Long getId() {
		return id;
	}

	public Zimmerkategorie getZimmerkategorie() {
		return zimmerkategorie;
	}

	public void setZimmerkategorie(Zimmerkategorie zimmerkategorie) {
		this.zimmerkategorie = zimmerkategorie;
	}	
}