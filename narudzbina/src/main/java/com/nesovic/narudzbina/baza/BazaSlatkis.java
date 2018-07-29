package com.nesovic.narudzbina.baza;

import java.util.HashMap;
import java.util.Map;

import com.nesovic.narudzbina.model.Slatkis;

public class BazaSlatkis {

	private static Map<Integer, Slatkis> slatkis=new HashMap<>();

	public static Map<Integer, Slatkis> getSlatkis() {
		return slatkis;
	}
	
}
