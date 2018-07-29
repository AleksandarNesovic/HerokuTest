package com.nesovic.narudzbina.model;

public class Klijent {
	
	private int id_klijenta;
	private String ime;
	private String prezime;
	private String username;
	private String password;
	private String role;
	private String brojTelefona;
	private String email;
	public Klijent() {
		super();
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}


	public Klijent(String ime, String prezime, String username, String password, String role, String brojTelefona,
			String email) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.username = username;
		this.password = password;
		this.role = role;
		this.brojTelefona = brojTelefona;
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId_klijenta() {
		return id_klijenta;
	}
	public void setId_klijenta(int id_klijenta) {
		this.id_klijenta = id_klijenta;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return ime + " " + prezime;
	}
	
	
}
