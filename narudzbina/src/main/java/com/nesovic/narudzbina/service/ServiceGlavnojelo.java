package com.nesovic.narudzbina.service;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nesovic.narudzbina.model.GlavnoJelo;

public class ServiceGlavnojelo {

	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/Vezba3 ?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
	}

	public ServiceGlavnojelo() {
		super();
	}
	public ArrayList<GlavnoJelo> svaJela(){
		ArrayList<GlavnoJelo> lista=new ArrayList<>();
		GlavnoJelo glavno=null;
		try {
			connect();
			preparedStatement=connect.prepareStatement("SELECT * FROM Glavno_jelo");
			preparedStatement.executeQuery();
			resultSet=preparedStatement.getResultSet();
			while(resultSet.next()) {
				glavno=new GlavnoJelo();
				glavno.setId_glj(resultSet.getInt(1));
				glavno.setNaziv(resultSet.getString(2));
				glavno.setCena(resultSet.getDouble(3));
				lista.add(glavno);
			}} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		return lista;
	}
	public ArrayList<GlavnoJelo> sortJelaByCena(){
		ArrayList<GlavnoJelo> lista=new ArrayList<>();
		GlavnoJelo glavno=null;
		try {
			connect();
			preparedStatement=connect.prepareStatement("SELECT * FROM Glavno_jelo order by cena");
			preparedStatement.executeQuery();
			resultSet=preparedStatement.getResultSet();
			while(resultSet.next()) {
				glavno=new GlavnoJelo();
				glavno.setId_glj(resultSet.getInt(1));
				glavno.setNaziv(resultSet.getString(2));
				glavno.setCena(resultSet.getDouble(3));
				lista.add(glavno);
			}} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		return lista;
	}
	public GlavnoJelo selectJelaById(int id){
		GlavnoJelo glavno=null;
		try {
			connect();
			preparedStatement=connect.prepareStatement("SELECT * FROM Glavno_jelo WHERE id_glj=?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeQuery();
			resultSet=preparedStatement.getResultSet();
			while(resultSet.next()) {
				glavno=new GlavnoJelo();
				glavno.setId_glj(resultSet.getInt(1));
				glavno.setNaziv(resultSet.getString(2));
				glavno.setCena(resultSet.getDouble(3));
			}} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		close();
		return glavno;
	}
	public GlavnoJelo insertGlavnoJelo(GlavnoJelo g) {
		try {
			connect();
			preparedStatement=connect.prepareStatement("INSERT INTO `Glavno_jelo`(`Naziv`, `Cena`) VALUES (?,?)");
			preparedStatement.setString(1, g.getNaziv());
			preparedStatement.setDouble(2, g.getCena());
			preparedStatement.execute();
			} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		close();
		return g;
	}
	public void updateGlavnoJelo(GlavnoJelo g) {
		try {
			connect();
			preparedStatement=connect.prepareStatement("UPDATE `Glavno_jelo` SET `Naziv`=?,`Cena`=? WHERE `id_glj`=?");
			preparedStatement.setString(1, g.getNaziv());
			preparedStatement.setDouble(2, g.getCena());
			preparedStatement.setInt(3, g.getId_glj());
			preparedStatement.execute();
			} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		close();
	}
	public void deleteGlavnoJelo(int id) {
		try {
			connect();
			preparedStatement=connect.prepareStatement("DELETE FROM `Glavno_jelo` WHERE id_glj=?");
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
