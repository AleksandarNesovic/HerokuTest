package com.nesovic.narudzbina.jwt;

import java.sql.SQLException;
import java.util.Date;

import javax.ws.rs.core.Application;

import com.nesovic.narudzbina.exceptions.AuthFaildException;
import com.nesovic.narudzbina.model.Credentials;
import com.nesovic.narudzbina.model.Klijent;
import com.nesovic.narudzbina.service.ServiceKlijent;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class LoginService extends Application{
	ServiceKlijent dao=new ServiceKlijent();

	public String Login(Credentials crd) throws ClassNotFoundException, SQLException, AuthFaildException {
		Klijent klijent=dao.Login(crd);
		String jwt="";
		if(klijent.getUsername()!="") {
			Long time=System.currentTimeMillis();
			jwt=Jwts.builder()
					.setSubject(String.valueOf(klijent.getId_klijenta()))
					.claim("ime", klijent.getIme())
					.claim("role", klijent.getRole())
					.setExpiration(new Date(time+900000))
					.signWith(SignatureAlgorithm.HS256, "password".getBytes()).compact();
			//	json=Json.createObjectBuilder().add(jwt).build();
			return jwt;
		}
		return jwt;
	}
}
