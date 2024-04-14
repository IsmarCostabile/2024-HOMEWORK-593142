package it.uniroma3.diadia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

public class PartitaTest {

	public Labirinto labirinto;
	public Partita partita;
	
	@Before
	public void setUp() {
		this.labirinto = new Labirinto();
		this.partita = new Partita(labirinto);
		this.partita.setStanzaCorrente(labirinto.getStanzaIniziale());
	}
	
	// Test vinta
	@Test
	public void testVinta_DaSubito() {
		this.partita.setStanzaCorrente(labirinto.getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
    
	@Test
	public void testVinta_ArrivatoStanzaFinale() {
		assertFalse(this.partita.vinta());
		this.partita.setStanzaCorrente(labirinto.getStanzaVincente());
		assertTrue(this.partita.vinta());
	}
	
	// Test isFinita
	@Test
	public void testIsFinita_NonFinitaCfuNonTerminati() {
		assertNotEquals(this.partita.getCFU(), 0);
		assertFalse(this.partita.isFinita());
	}
	
	@Test
	public void testIsFinita_FinitaCfuTerminati() {
		assertFalse(this.partita.isFinita());
		this.partita.setCFU(0);
		assertEquals(this.partita.getCFU(), 0);
		assertTrue(this.partita.isFinita());
	}
	
}



