package com.nesovic.narudzbina.service;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.nesovic.narudzbina.model.Salata;

public class ServiceSalata {
	
	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/Vezba3 ?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
	}

	public ServiceSalata() {
		super();
	}
	public ArrayList<Salata> sveSalate(){
		ArrayList<Salata> lista=new ArrayList<>();
		Salata salata=null;
		try {
			connect();
			preparedStatement=connect.prepareStatement("SELECT * FROM Salata");
			preparedStatement.executeQuery();
			resultSet=preparedStatement.getResultSet();
			while(resultSet.next()) {
				salata=new Salata();
				salata.setId_sal(resultSet.getInt(1));
				salata.setNaziv(resultSet.getString(2));
				salata.setCena(resultSet.getDouble(3));
				lista.add(salata);
			}} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		close();
		return lista;
	}
	public Salata selsectSalataById(int id){
		Salata salata=null;
		try {
			connect();
			preparedStatement=connect.prepareStatement("SELECT * FROM Salata WHERE id_sal=?");
			preparedStatement.setInt(1, id);
			preparedStatement.executeQuery();
			resultSet=preparedStatement.getResultSet();
			while(resultSet.next()) {
				salata=new Salata();
				salata.setId_sal(resultSet.getInt(1));
				salata.setNaziv(resultSet.getString(2));
				salata.setCena(resultSet.getDouble(3));
			}} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		close();
		return salata;
	}
	public Salata insertSalata(Salata sal) {
		try {
			connect();
			preparedStatement=connect.prepareStatement("INSERT INTO Salata (`Naziv`, `Cena`) VALUES (?,?)");
			preparedStatement.setString(1, sal.getNaziv());
			preparedStatement.setDouble(2, sal.getCena());
			preparedStatement.execute();
			} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		close();
		return sal;
	}
	public void updateSalata(Salata sal) {
		try {
			connect();
			preparedStatement=connect.prepareStatement("UPDATE `Salata` SET `Naziv`=?,`Cena`=? WHERE `id_sal`=?");
			preparedStatement.setString(1, sal.getNaziv());
			preparedStatement.setDouble(2, sal.getCena());
			preparedStatement.setInt(3, sal.getId_sal());
			preparedStatement.execute();
			} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		close();
	}
	public void deleteSalata(int id) {
		try {
			connect();
			preparedStatement=connect.prepareStatement("DELETE FROM `Salata` WHERE id_sal=?");
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
