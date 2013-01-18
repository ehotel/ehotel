package de.hs.lu.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class Gast extends Benutzer{
	
	@OneToMany(mappedBy="gast")
	private Set<Bewertung> bewertungen = new HashSet<Bewertung>();
	
	@OneToMany(mappedBy="gast")
	private Set<Reservierung> reservierungen = new HashSet<Reservierung>();

	public Set<Bewertung> getBewertungen() {
		return bewertungen;
	}

	public void setBewertungen(Set<Bewertung> bewertungen) {
		this.bewertungen = bewertungen;
	}

	public Set<Reservierung> getReservierungen() {
		return reservierungen;
	}

	public void setReservierungen(Set<Reservierung> reservierungen) {
		this.reservierungen = reservierungen;
	}
		

}
