package de.hs.lu.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;

import de.hs.lu.orm.dao.ReservierungsServiceDao;

@Entity
public class Reservierung {
	
	@Autowired
	@Transient
	ReservierungsServiceDao rsDao;	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private long Startdatum;
	
	private long Enddatum;
	
	private Status status;
	
	@ManyToOne
	@JoinColumn(name="gast_id")
	private Gast gast;
	
	@ManyToOne
	@JoinColumn(name="zimmer_id")
	private Zimmer zimmer;
	
	@OneToMany(mappedBy="reservierung", fetch=FetchType.EAGER)
	private Set<ReservierungsService> reservierungsServices = new HashSet<ReservierungsService>();

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getId() {
		return id;
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
