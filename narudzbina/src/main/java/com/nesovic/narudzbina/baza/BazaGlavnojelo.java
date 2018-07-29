package com.nesovic.narudzbina.baza;

import java.util.HashMap;
import java.util.Map;

import com.nesovic.narudzbina.model.GlavnoJelo;

public class BazaGlavnojelo {

	private static Map<Integer, GlavnoJelo> glavnaJela=new HashMap<>();

	public static Map<Integer, GlavnoJelo> getGlavnaJela() {
		return glavnaJela;
	}
	
	
}
