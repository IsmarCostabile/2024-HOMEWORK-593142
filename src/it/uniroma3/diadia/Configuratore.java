package it.uniroma3.diadia;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class Configuratore {

	private static final String DIADIA_PROPERTIES = "diadia.properties";
	private static final String PESO_MAX = "pesoMax";
	private static final String CFU = "cfu";
	private static final String MESSAGGIO_BENVENUTO = "messaggioBenvenuto";
	private static Properties prop = null;
	
	public static int getCFU() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(CFU));
	}
	
	public static int getPesoMax() {
		if(prop == null)
			carica();
		return Integer.parseInt(prop.getProperty(PESO_MAX));
	}

	public static String getMessaggioBenvenuto() {
		if(prop == null)
			carica();
		return prop.getProperty(MESSAGGIO_BENVENUTO);
	}
	
	private static void carica() {
		prop = new Properties();
		try {
			FileInputStream input = new FileInputStream(DIADIA_PROPERTIES);
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}