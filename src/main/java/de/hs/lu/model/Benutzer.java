package de.hs.lu.model;

import java.security.MessageDigest;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Benutzer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String vorname;
	
	private String nachname;
	
	private String benutzername;
	
	private String password;
	
	private String email;
	
	private boolean istAktiviert;
	
	private String aktivierungsHash;
	
	private String rechte;
	
	public Long getId() {
		return id;
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getBenutzername() {
		return benutzername;
	}

	public void setBenutzername(String benutzername) {
		this.benutzername = benutzername;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {		
		this.password = Benutzer.md5Hash(password);	
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isIstAktiviert() {
		return istAktiviert;
	}

	public void setIstAktiviert(boolean istAktiviert) {
		this.istAktiviert = istAktiviert;
	}

	public String getAktivierungsHash() {
		return aktivierungsHash;
	}

	public void setAktivierungsHash(String aktvierungsHash) {
		this.aktivierungsHash = aktvierungsHash;
	}

	public String getRechte() {
		return rechte;
	}

	public void setRechte(String rechte) {
		this.rechte = rechte;
	}

	public static String md5Hash(String text)
	{
		try{
			
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(text.getBytes());
	 
	        byte byteArray[] = md.digest();
	 
	        StringBuffer buffer = new StringBuffer();
	        for (int i = 0; i < byteArray.length; i++) {
	        	buffer.append(Integer.toString((byteArray[i] & 0xff) + 0x100, 16).substring(1));
	        }
			return buffer.toString();
			
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		
		return "";		
	}
	
}
