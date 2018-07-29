package com.nesovic.narudzbina.baza;

import java.util.HashMap;
import java.util.Map;

import com.nesovic.narudzbina.model.Salata;

public class BazaSalata {
	
	private static Map<Integer, Salata> salata=new HashMap<>();

	public static Map<Integer, Salata> getSalata() {
		return salata;
	}
	
}
