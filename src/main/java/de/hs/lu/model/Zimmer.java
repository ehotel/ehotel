package de.hs.lu.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Zimmer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private int zimmerNr;
	
	@ManyToOne
	@JoinColumn(name="zimmerkategorie_id")
	private Zimmerkategorie zimmerkategorie;
	
	@OneToMany(mappedBy="zimmer")
	private Set<Reservierung> reservierungen; 
	
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

	public Set<Reservierung> getReservierungen() {
		return reservierungen;
	}

	public void setReservierungen(Set<Reservierung> reservierungen) {
		this.reservierungen = reservierungen;
	}	
}