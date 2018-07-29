package com.nesovic.narudzbina.baza;

import java.util.HashMap;
import java.util.Map;

import com.nesovic.narudzbina.model.Klijent;

public class BazaKlijent {
	
	private static Map<Integer, Klijent> klijent=new HashMap<>();

	public static Map<Integer, Klijent> getKlijent() {
		return klijent;
	}
	
}
