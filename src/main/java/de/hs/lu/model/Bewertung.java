package de.hs.lu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bewertung {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private int bewertungspunkte;
	
	private String text;
	
	private long datum;

	public int getBewertungspunkte() {
		return bewertungspunkte;
	}

	public void setBewertungspunkte(int bewertungspunkte) {
		this.bewertungspunkte = bewertungspunkte;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getDatum() {
		return datum;
	}

	public void setDatum(long datum) {
		this.datum = datum;
	}

	public Long getId() {
		return id;
	}

}
