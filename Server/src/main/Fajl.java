package main;

import java.io.File;

public class Fajl {
	String id;
	File tekst;
	public Fajl(String naziv) {
		tekst=new File(naziv);
	}
	public File getTekst() {
		return tekst;
	}

	public void setTekst(File tekst) {
		this.tekst = tekst;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
