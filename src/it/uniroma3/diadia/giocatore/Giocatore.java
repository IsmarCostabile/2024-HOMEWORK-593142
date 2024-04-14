package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Giocatore {

	/* Giocatore ha la responsabilità di gestire i CFU del giocatore e
	 * di memorizzare gli attrezzi in un oggetto istanza della classe
	 * Borsa >>(vedi codice a seguire)
	 **/
	
	static final private int CFU_INIZIALI = 20;
	private int CFU;
	private Borsa borsa;
	
	public Giocatore() {
		this(CFU_INIZIALI);
		this.borsa = new Borsa();
	}
	
	public Giocatore(int cfu_iniziali) {
		this.CFU = cfu_iniziali;
	}
	
	public boolean writeToBorsa(Attrezzo attrezzo) {
		return borsa.addAttrezzo(attrezzo);
	}
	
	public Attrezzo togliDaBorsa (String nomeAttrezzo) {
		return borsa.removeAttrezzo(nomeAttrezzo);
	}
	
	public void setCFU(int CFU) {
		this.CFU = CFU;
	}
	
	public int getCFU() {
		return this.CFU;
	}
	
}
