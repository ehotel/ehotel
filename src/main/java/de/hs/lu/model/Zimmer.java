package de.hs.lu.model;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

/**
 * An item in an order
 */
@Entity
public class Zimmer {
	

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private int ZimmerNr;
	

	
	

	public int getZimmerNr() {
		return ZimmerNr;
	}

	public void setZimmerNr(int zimmerNr) {
		ZimmerNr = zimmerNr;
	}

	public Long getId() {
		return id;
	}
	
	
}
