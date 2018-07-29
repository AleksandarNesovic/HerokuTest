package com.nesovic.narudzbina.baza;

import java.util.HashMap;
import java.util.Map;

import com.nesovic.narudzbina.model.Narudzbina;

public class BazaNarudzbina {

	private static Map<Integer, Narudzbina> narudzbina=new HashMap<>();

	public static Map<Integer, Narudzbina> getNarudzbina() {
		return narudzbina;
	}
	
}
