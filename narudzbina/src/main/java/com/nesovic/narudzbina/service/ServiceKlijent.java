package com.nesovic.narudzbina.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;

import com.nesovic.narudzbina.exceptions.AuthFaildException;
import com.nesovic.narudzbina.model.Credentials;
import com.nesovic.narudzbina.model.Klijent;

public class ServiceKlijent {
	
	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/Vezba3 ?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
	}
	public ServiceKlijent() {
		super();
	}

	public ArrayList<Klijent> sviKlijenti(){
		ArrayList<Klijent> lista=new ArrayList<>();
		Klijent klijent=null;
		try {
			connect();
			preparedStatement=connect.prepareStatement("SELECT * FROM Klijenti");
			preparedStatement.executeQuery();
			resultSet=preparedStatement.getResultSet();
			while(resultSet.next()) {
				klijent=new Klijent();
				klijent.setId_klijenta(resultSet.getInt(1));
				klijent.setIme(resultSet.getString(2));
				klijent.setPrezime(resultSet.getString(3));
				klijent.setUsername(resultSet.getString(4));
				klijent.setPassword(resultSet.getString(5));
				klijent.setRole(resultSet.getString(6));
				klijent.setBrojTelefona(resultSet.getString(7));
				klijent.setEmail(resultSet.getString(8));
				lista.add(klijent);
			}} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		close();
		return lista;
	}
	public Klijent selectKlijentiById(int id){
		Klijent klijent=null;
		try {
			connect();
			preparedStatement=connect.prepareStatement("SELECT * FROM Klijenti WHERE id_klijenta=?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeQuery();
			resultSet=preparedStatement.getResultSet();
			while(resultSet.next()) {
				klijent=new Klijent();
				klijent.setId_klijenta(resultSet.getInt(1));
				klijent.setIme(resultSet.getString(2));
				klijent.setPrezime(resultSet.getString(3));
				klijent.setUsername(resultSet.getString(4));
				klijent.setPassword(resultSet.getString(5));
				klijent.setRole(resultSet.getString(6));
				klijent.setBrojTelefona(resultSet.getString(7));
				klijent.setEmail(resultSet.getString(8));
			}} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		close();
		return klijent;
	}
	public Klijent insertKlijent(Klijent k) {
		try {
			connect();
			preparedStatement=connect.prepareStatement("INSERT INTO Klijenti (`Ime`, `Prezime`, `Username`,`Password`,`Role`,`BrojTelefona`, `Email`) VALUES (?,?,?,?,?,?,?)");
			preparedStatement.setString(1, k.getIme());
			preparedStatement.setString(2, k.getPrezime());
			preparedStatement.setString(3, k.getUsername());
			preparedStatement.setString(4, encryptPassword(k.getPassword()));
			preparedStatement.setString(5, k.getRole());
			preparedStatement.setString(6, k.getBrojTelefona());
			preparedStatement.setString(7, k.getEmail());
			preparedStatement.execute();
			} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		close();
		return k;
	}
	public void updateKlijent(Klijent k) {
		try {
			connect();
			preparedStatement=connect.prepareStatement("UPDATE `Klijenti` SET `Ime`=?,`Prezime`=?,`Username`=?,`Password`=?,`Role`=?,`BrojTelefona`=?,`Email`=? WHERE id_klijenta=?");
			preparedStatement.setString(1, k.getIme());
			preparedStatement.setString(2, k.getPrezime());
			preparedStatement.setString(3, k.getUsername());
			preparedStatement.setString(4, encryptPassword(k.getPassword()));
			preparedStatement.setString(5, k.getRole());
			preparedStatement.setString(6, k.getBrojTelefona());
			preparedStatement.setString(7, k.getEmail());
			preparedStatement.setInt(8, k.getId_klijenta());
			preparedStatement.execute();
			} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		close();
	}
	public void deleteKlijent(int id) {
		try {
			connect();
			preparedStatement=connect.prepareStatement("DELETE FROM `Klijenti` WHERE id_klijenta=?");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		close();
	}
	public Klijent Login(Credentials crd) throws ClassNotFoundException, SQLException, AuthFaildException {
		Klijent klijent=new Klijent();
		String password=encryptPassword(crd.getPassword());
		connect();
		preparedStatement=connect.prepareStatement("select * from Klijenti where username=?");
		preparedStatement.setString(1, crd.getUsername());
		preparedStatement.execute();
		resultSet=preparedStatement.getResultSet();
		if(resultSet.next()) {
			klijent.setId_klijenta(resultSet.getInt(1));
			klijent.setIme(resultSet.getString(2));
			klijent.setPrezime(resultSet.getString(3));
			klijent.setUsername(resultSet.getString(4));
			klijent.setPassword(resultSet.getString(5));
			klijent.setRole(resultSet.getString(6));
			klijent.setBrojTelefona(resultSet.getString(7));
			klijent.setEmail(resultSet.getString(8));
		}
		String userPassword=klijent.getPassword();
		if(userPassword.equals(password)) {
			klijent.setPassword(userPassword);
		}else {
			System.out.println("Pogresna sifra!");
			throw new AuthFaildException();
		}
		close();
		return klijent;
	}
	private static String encryptPassword(String password) {
		String sha1 = "";
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(password.getBytes("UTF-8"));
			sha1 = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sha1;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
