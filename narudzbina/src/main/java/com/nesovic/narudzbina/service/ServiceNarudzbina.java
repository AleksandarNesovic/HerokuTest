package com.nesovic.narudzbina.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.nesovic.narudzbina.model.GlavnoJelo;
import com.nesovic.narudzbina.model.Klijent;
import com.nesovic.narudzbina.model.Narudzbina;
import com.nesovic.narudzbina.model.Salata;
import com.nesovic.narudzbina.model.Slatkis;

public class ServiceNarudzbina {

	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/Vezba3 ?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
	}

	public ServiceNarudzbina() {
		super();
	}
	
	public ArrayList<String> sveNarudzbine(){
		ArrayList<String> lista=new ArrayList<>();
		Narudzbina narudzbina=null;
		ServiceKlijent serviceKlijent=new ServiceKlijent();
		ServiceGlavnojelo serviceGlavno=new ServiceGlavnojelo();
	//	ServiceSalata serviceSalata=new ServiceSalata();
	//	ServiceSlatkis serviceSlatkis=new ServiceSlatkis();
		try {
			connect();
			preparedStatement=connect.prepareStatement("SELECT * FROM Narudzbina");
			preparedStatement.executeQuery();
			resultSet=preparedStatement.getResultSet();
			while(resultSet.next()) {
				narudzbina=new Narudzbina();
				narudzbina.setId_narudzbine(resultSet.getInt(1));
				
				Klijent k=serviceKlijent.selectKlijentiById(resultSet.getInt(2));
				narudzbina.setKlijent(k);
				GlavnoJelo glavno=serviceGlavno.selectJelaById(resultSet.getInt(3));
				narudzbina.setGlavno(glavno);
			//	Salata salata=serviceSalata.selsectSalataById(resultSet.getInt(4));
			//	narudzbina.setSalate(salata);
			//	Slatkis slatkis=serviceSlatkis.selectSlatkisById(resultSet.getInt(5));
			//	narudzbina.setSlatkis(slatkis);
				narudzbina.setKolicinaGlavnog(resultSet.getInt(6));
			//	narudzbina.setKolicinaSalate(resultSet.getInt(7));
				narudzbina.setEmail(resultSet.getString(8));
				narudzbina.setDatumPorudzbine(resultSet.getTimestamp(9));
				lista.add(narudzbina.toString());
			}} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		close();
		return lista;
	}
	public ArrayList<String> selectNarudzbineByDatum(String datum){
		ArrayList<String> lista=new ArrayList<>();
		Narudzbina narudzbina=null;
		ServiceKlijent serviceKlijent=new ServiceKlijent();
		ServiceGlavnojelo serviceGlavno=new ServiceGlavnojelo();
	//	ServiceSalata serviceSalata=new ServiceSalata();
	//	ServiceSlatkis serviceSlatkis=new ServiceSlatkis();
		try {
			connect();
			preparedStatement=connect.prepareStatement("SELECT * FROM Narudzbina Where datumPorudzbine=?");
			preparedStatement.setString(1, datum);
			preparedStatement.executeQuery();
			resultSet=preparedStatement.getResultSet();
			while(resultSet.next()) {
				narudzbina=new Narudzbina();
				narudzbina.setId_narudzbine(resultSet.getInt(1));
				
				Klijent k=serviceKlijent.selectKlijentiById(resultSet.getInt(2));
				narudzbina.setKlijent(k);
				GlavnoJelo glavno=serviceGlavno.selectJelaById(resultSet.getInt(3));
				narudzbina.setGlavno(glavno);
			//	Salata salata=serviceSalata.selsectSalataById(resultSet.getInt(4));
			//	narudzbina.setSalate(salata);
			//	Slatkis slatkis=serviceSlatkis.selectSlatkisById(resultSet.getInt(5));
			//	narudzbina.setSlatkis(slatkis);
				narudzbina.setKolicinaGlavnog(resultSet.getInt(6));
			//	narudzbina.setKolicinaSalate(resultSet.getInt(7));
				narudzbina.setEmail(resultSet.getString(8));
				narudzbina.setDatumPorudzbine(resultSet.getTimestamp(9));
				lista.add(narudzbina.toString());
			}} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		close();
		return lista;
	}
	public Narudzbina selectNarudzbineById(int id){
		Narudzbina narudzbina=null;
		ServiceKlijent serviceKlijent=new ServiceKlijent();
		ServiceGlavnojelo serviceGlavno=new ServiceGlavnojelo();
		//ServiceSalata serviceSalata=new ServiceSalata();
		//ServiceSlatkis serviceSlatkis=new ServiceSlatkis();
		try {
			connect();
			preparedStatement=connect.prepareStatement("SELECT * FROM Narudzbina WHERE id_narudzbine=?");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			resultSet=preparedStatement.getResultSet();
			while(resultSet.next()) {
				narudzbina=new Narudzbina();
				narudzbina.setId_narudzbine(resultSet.getInt(1));
				
				Klijent k=serviceKlijent.selectKlijentiById(resultSet.getInt(2));
				narudzbina.setKlijent(k);
				GlavnoJelo glavno=serviceGlavno.selectJelaById(resultSet.getInt(3));
				narudzbina.setGlavno(glavno);
			//	Salata salata=serviceSalata.selsectSalataById(resultSet.getInt(4));
			//	narudzbina.setSalate(salata);
			//	Slatkis slatkis=serviceSlatkis.selectSlatkisById(resultSet.getInt(5));
			//	narudzbina.setSlatkis(slatkis);
				narudzbina.setKolicinaGlavnog(resultSet.getInt(6));
			//	narudzbina.setKolicinaSalate(resultSet.getInt(7));
				narudzbina.setEmail(resultSet.getString(8));
				narudzbina.setDatumPorudzbine(resultSet.getTimestamp(9));
			}} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		close();
		return narudzbina;
	}
	public void deleteNarudzbina(int id) {
		try {
			connect();
			preparedStatement=connect.prepareStatement("DELETE FROM `Narudzbina` WHERE id_narudzbine=?");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		close();
	}
	public Narudzbina insertNarudzbina(Narudzbina n) {
		try {
			connect();
			preparedStatement=connect.prepareStatement("INSERT INTO `Narudzbina`(`id_klijenta`, `id_glj`, `KolicinaGlavnogJela`, `Email`, `datumPorudzbine`) VALUES (?,?,?,?,?)");
			preparedStatement.setInt(1, n.getKlijent().getId_klijenta());
			preparedStatement.setInt(2, n.getGlavno().getId_glj());
			//preparedStatement.setInt(3, n.getSalate().getId_sal());
			//preparedStatement.setInt(4, n.getSlatkis().getId_slat());
			preparedStatement.setInt(3, n.getKolicinaGlavnog());
			//preparedStatement.setInt(6, n.getKolicinaSalate());
			preparedStatement.setString(4, n.getEmail());
			preparedStatement.setTimestamp(5, new Timestamp(new Date().getTime()));
			preparedStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		close();
		return n;
	}
	public void updateNarudzbina(Narudzbina n) {
		try {
			connect();
			preparedStatement=connect.prepareStatement("UPDATE `Narudzbina` SET `id_klijenta`=?,`id_glj`=?,`KolicinaGlavnogJela`=?,`Email`=?,`datumPorudzbine`=? WHERE `id_narudzbine`=?");
			preparedStatement.setInt(1, n.getKlijent().getId_klijenta());
			preparedStatement.setInt(2, n.getGlavno().getId_glj());
			//preparedStatement.setInt(3, n.getSalate().getId_sal());
			//preparedStatement.setInt(4, n.getSlatkis().getId_slat());
			preparedStatement.setInt(3, n.getKolicinaGlavnog());
			//preparedStatement.setInt(6, n.getKolicinaSalate());
			preparedStatement.setString(4, n.getEmail());
			preparedStatement.setTimestamp(5, new Timestamp(new Date().getTime()));
			preparedStatement.setInt(6, n.getId_narudzbine());
			preparedStatement.execute();
			close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
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
