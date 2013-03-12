package de.hs.lu.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ZusatzService {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String name;
	
	private float preis;
	
	private int anzahl;
	
	@OneToMany(mappedBy="zusatzService", fetch=FetchType.EAGER)
	private Set<ReservierungsService> reservierungsServices = new HashSet<ReservierungsService>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPreis() {
		return preis;
	}

	public void setPreis(float preis) {
		this.preis = preis;
	}

	public int getAnzahl() {
		return anzahl;
	}

	public void setAnzahl(int anzahl) {
		this.anzahl = anzahl;
	}

	public Set<ReservierungsService> getReservierungsServices() {
		return reservierungsServices;
	}

	public void setReservierungsServices(Set<ReservierungsService> reservierungsServices) {
		this.reservierungsServices = reservierungsServices;
	}

}
