package de.hs.lu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReservierungsService {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private long Startdatum;
	
	private long Enddatum;

	public long getStartdatum() {
		return Startdatum;
	}

	public void setStartdatum(long startdatum) {
		Startdatum = startdatum;
	}

	public long getEnddatum() {
		return Enddatum;
	}

	public void setEnddatum(long enddatum) {
		Enddatum = enddatum;
	}

	public Long getId() {
		return id;
	}

}
