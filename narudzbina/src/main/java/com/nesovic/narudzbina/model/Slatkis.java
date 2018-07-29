package com.nesovic.narudzbina.model;

public class Slatkis {

	private int id_slat;
	private String naziv;
	private int kolicina;
	private double cena;
	public Slatkis() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Slatkis(String naziv, int kolicina, double cena) {
		super();
		this.naziv = naziv;
		this.kolicina = kolicina;
		this.cena = cena;
	}
	public int getId_slat() {
		return id_slat;
	}
	public void setId_slat(int id_slat) {
		this.id_slat = id_slat;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
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
