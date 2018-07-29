package com.nesovic.narudzbina.service;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nesovic.narudzbina.model.Slatkis;

public class ServiceSlatkis {

	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/Vezba3 ?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
	}

	public ServiceSlatkis() {
		super();
	}
	
	public ArrayList<Slatkis> sviSlatkisi(){
		ArrayList<Slatkis> lista=new ArrayList<>();
		Slatkis slatkis=null;
		try {
			connect();
			preparedStatement=connect.prepareStatement("SELECT * FROM Slatkis");
			preparedStatement.executeQuery();
			resultSet=preparedStatement.getResultSet();
			while(resultSet.next()) {
				slatkis=new Slatkis();
				slatkis.setId_slat(resultSet.getInt(1));
				slatkis.setNaziv(resultSet.getString(2));
				slatkis.setKolicina(resultSet.getInt(3));
				slatkis.setCena(resultSet.getDouble(4));
				lista.add(slatkis);
			}} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		return lista;
	}
	public Slatkis selectSlatkisById(int id){
		Slatkis slatkis=null;
		try {
			connect();
			preparedStatement=connect.prepareStatement("SELECT * FROM Slatkis WHERE id_slat=?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeQuery();
			resultSet=preparedStatement.getResultSet();
			while(resultSet.next()) {
				slatkis=new Slatkis();
				slatkis.setId_slat(resultSet.getInt(1));
				slatkis.setNaziv(resultSet.getString(2));
				slatkis.setKolicina(resultSet.getInt(3));
				slatkis.setCena(resultSet.getDouble(4));
			}} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		return slatkis;
	}
	public Slatkis insertSlatkis(Slatkis slat) {
		try {
			connect();
			preparedStatement=connect.prepareStatement("INSERT INTO Slatkis (`Naziv`, `Kolicina`,`Cena`) VALUES (?,?,?)");
			preparedStatement.setString(1, slat.getNaziv());
			preparedStatement.setInt(2, slat.getKolicina());
			preparedStatement.setDouble(3, slat.getCena());
			preparedStatement.execute();
			} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		close();
		return slat;
	}
	public void updateSlatkis(Slatkis slat) {
		try {
			connect();
			preparedStatement=connect.prepareStatement("UPDATE `Slatkis` SET `Naziv`=?,`Kolicina`=?,`Cena`=? WHERE `id_slat`=?");
			preparedStatement.setString(1, slat.getNaziv());
			preparedStatement.setInt(2, slat.getKolicina());
			preparedStatement.setDouble(3, slat.getCena());
			preparedStatement.setInt(4, slat.getId_slat());
			preparedStatement.execute();
			} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		close();
	}
	public void deleteSlatkis(int id) {
		try {
			connect();
			preparedStatement=connect.prepareStatement("DELETE FROM `Slatkis` WHERE id_slat=?");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		close();
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
