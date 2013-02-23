package de.hs.lu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ReservierungsService {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private long startdatum;
	
	private long enddatum;
	
	@ManyToOne
	@JoinColumn(name="reservierung_id")
	private Reservierung reservierung;
	
	@ManyToOne
	@JoinColumn(name="zusatzService_id")
	private ZusatzService zusatzService;

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

	public Long getId() {
		return id;
	}

	public Reservierung getReservierung() {
		return reservierung;
	}

	public void setReservierung(Reservierung reservierung) {
		this.reservierung = reservierung;
	}

	public ZusatzService getZusatzService() {
		return zusatzService;
	}

	public void setZusatzService(ZusatzService zusatzService) {
		this.zusatzService = zusatzService;
	}

}
