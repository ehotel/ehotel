package de.hs.lu.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Zimmerkategorie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String zimmertyp;
	
	private float preis;
	
	@OneToMany(mappedBy="zimmerkategorie")
	private Set<Zimmer> zimmer = new HashSet<Zimmer>();
	
	@OneToMany(mappedBy="zimmerkategorie")
	private Set<Bewertung> bewertungen = new HashSet<Bewertung>();
	
	public String getZimmertyp() {
		return zimmertyp;
	}

	public void setZimmertyp(String zimmertyp) {
		this.zimmertyp = zimmertyp;
	}

	public float getPreis() {
		return preis;
	}

	public void setPreis(float preis) {
		this.preis = preis;
	}

	public Long getId() {
		return id;
	}

	public Set<Zimmer> getZimmer() {
		return zimmer;
	}

	public void setZimmer(Set<Zimmer> zimmer) {
		this.zimmer = zimmer;
	}

	public Set<Bewertung> getBewertungen() {
		return bewertungen;
	}

	public void setBewertungen(Set<Bewertung> bewertungen) {
		this.bewertungen = bewertungen;
	}	
	
}
