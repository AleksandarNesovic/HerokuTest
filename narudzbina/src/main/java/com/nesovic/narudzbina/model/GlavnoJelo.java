package com.nesovic.narudzbina.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GlavnoJelo {
	
	private int id_glj;
	private String naziv;
	private double cena;
	public GlavnoJelo() {
		super();
	}
	public GlavnoJelo(String naziv, double cena) {
		super();
		this.naziv = naziv;
		this.cena = cena;
	}
	public int getId_glj() {
		return id_glj;
	}
	public void setId_glj(int id_glj) {
		this.id_glj = id_glj;
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
		return  naziv;
	}
	
	
}
