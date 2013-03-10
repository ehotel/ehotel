package de.hs.lu.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Reservierung {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private long startdatum;
	
	private long enddatum;
	
	private Status status;
	
	@ManyToOne
	@JoinColumn(name="gast_id")
	private Gast gast;
	
	@ManyToOne
	@JoinColumn(name="zimmer_id")
	private Zimmer zimmer;
	
	@OneToMany(mappedBy="reservierung")
	private Set<ReservierungsService> reservierungsServices = new HashSet<ReservierungsService>(); 
	

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public long getStartdatum() {
		return startdatum;
	}

	public void setStartdatum(long startdatum) {
		this.startdatum = startdatum;
	}

	public long getEnddatum() {
		return enddatum;
	}

	public void setEnddatum(long enddatum) {
		this.enddatum = enddatum;
	}



	public Gast getGast() {
		return gast;
	}

	public void setGast(Gast gast) {
		this.gast = gast;
	}

	public Zimmer getZimmer() {
		return zimmer;
	}

	public void setZimmer(Zimmer zimmer) {
		this.zimmer = zimmer;
	}

	public Set<ReservierungsService> getReservierungsServices() {
		return reservierungsServices;
	}

	public void setReservierungsServices(Set<ReservierungsService> reservierungsServices) {
		this.reservierungsServices = reservierungsServices;
	}

}
