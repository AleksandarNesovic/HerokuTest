package com.nesovic.narudzbina.model;

public class Salata {
	
	private int id_sal;
	private String naziv;
	private double cena;
	
	public Salata() {
		super();
	}

	public Salata(String naziv, double cena) {
		super();
		this.naziv = naziv;
		this.cena = cena;
	}

	public int getId_sal() {
		return id_sal;
	}

	public void setId_sal(int id_sal) {
		this.id_sal = id_sal;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	@Override
	public String toString() {
		return naziv;
	}
	
	
}
