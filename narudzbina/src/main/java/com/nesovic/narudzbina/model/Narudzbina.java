package com.nesovic.narudzbina.model;

import java.sql.Timestamp;

public class Narudzbina {

	private int id_narudzbine;
	private Klijent klijent;
	private GlavnoJelo glavno;
	private Salata salate;
	private Slatkis slatkis;
	private int kolicinaGlavnog;
	//private int kolicinaSalate;
	private String email;
	private Timestamp datumPorudzbine;
	
	public Narudzbina() {
		super();
	}

	public Narudzbina(Klijent klijent, GlavnoJelo glavno, Salata salate, Slatkis slatkis, int kolicinaGlavnog,
			 String email, Timestamp datumPorudzbine) {
		super();
		this.klijent = klijent;
		this.glavno = glavno;
		this.salate = salate;
		this.slatkis = slatkis;
		this.kolicinaGlavnog = kolicinaGlavnog;
		//this.kolicinaSalate = kolicinaSalate;
		this.email = email;
		this.datumPorudzbine = datumPorudzbine;
	}

	public int getId_narudzbine() {
		return id_narudzbine;
	}

	public void setId_narudzbine(int id_narudzbine) {
		this.id_narudzbine = id_narudzbine;
	}

	public Klijent getKlijent() {
		return klijent;
	}

	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}

	public GlavnoJelo getGlavno() {
		return glavno;
	}

	public void setGlavno(GlavnoJelo glavno) {
		this.glavno = glavno;
	}

	public Salata getSalate() {
		return salate;
	}

	public void setSalate(Salata salate) {
		this.salate = salate;
	}

	public Slatkis getSlatkis() {
		return slatkis;
	}

	public void setSlatkis(Slatkis slatkis) {
		this.slatkis = slatkis;
	}

	public int getKolicinaGlavnog() {
		return kolicinaGlavnog;
	}

	public void setKolicinaGlavnog(int kolicinaGlavnog) {
		this.kolicinaGlavnog = kolicinaGlavnog;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getDatumPorudzbine() {
		return datumPorudzbine;
	}

	public void setDatumPorudzbine(Timestamp datumPorudzbine) {
		this.datumPorudzbine = datumPorudzbine;
	}

	@Override
	public String toString() {
		return "Narudzbina " + id_narudzbine + ":  " + klijent.toString() + "  Glavno jelo: " + glavno.toString()+" "+kolicinaGlavnog
				+ "g " +" Email: " + email + " Datum porudzbine: " + datumPorudzbine
				;
	}
	
	
	
}
