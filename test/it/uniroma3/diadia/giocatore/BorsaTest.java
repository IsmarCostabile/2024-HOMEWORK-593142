package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.*;
import it.uniroma3.diadia.giocatore.*;

public class BorsaTest {
	
	private Borsa borsa;
	
	Attrezzo piuma;
	Attrezzo libro;
	Attrezzo piombo;
	
	Attrezzo a;
	Attrezzo b;
	Attrezzo c;

	@Before
	public void setUp() {
		borsa = new Borsa();
		
		piuma = new Attrezzo("piuma", 1);
		libro = new Attrezzo("libro", 2);
		piombo = new Attrezzo("piombo", 3);
		
		a = new Attrezzo("a", 0);
		b = new Attrezzo("b", 0);
		c = new Attrezzo("c", 0);
	}

	@Test
	public void testAddAttrezzo() {
		assertNull(borsa.getAttrezzo("piuma"));
		borsa.addAttrezzo(piuma);
		assertEquals(this.piuma, piuma);
	}

	@Test
	public void testGetPesoMax() {
		Borsa borsaPesoMax20 = new Borsa(20);
		assertEquals(20, borsaPesoMax20.getPesoMax());
	}

	@Test
	public void testGetAttrezzoEsistente() {
		assertNull(borsa.getAttrezzo("piuma"));
		borsa.addAttrezzo(piuma);
		assertEquals(this.piuma, borsa.getAttrezzo("piuma"));
	}
	
	@Test
	public void testGetAttrezzoInsistente() {
		assertNull(borsa.getAttrezzo("piuma"));
		borsa.addAttrezzo(libro);
		assertEquals(null, borsa.getAttrezzo("piuma"));
	}

	@Test
	public void testGetPeso() {
		assertEquals(0, borsa.getPeso());
		borsa.addAttrezzo(piuma);
		assertEquals(1, borsa.getPeso());
		borsa.addAttrezzo(libro);
		assertEquals(3, borsa.getPeso());
		borsa.addAttrezzo(piombo);
		assertEquals(6, borsa.getPeso());
		
		borsa.removeAttrezzo("piuma");
		assertEquals(5, borsa.getPeso());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(borsa.isEmpty());
		borsa.addAttrezzo(piuma);
		assertFalse(borsa.isEmpty());
	}

	@Test
	public void testHasAttrezzo() {
		assertFalse(borsa.hasAttrezzo("a"));
		borsa.addAttrezzo(a);
		assertTrue(borsa.hasAttrezzo("a"));
	}

	@Test
	public void testGetPesoRimanente() {
		assertEquals(true, borsa.getPesoRimanente(piombo));
	}

	@Test
	public void testGetAttrezzi() {
		
		borsa.addAttrezzo(a);
		borsa.addAttrezzo(b);
		borsa.addAttrezzo(c);
		
		Collection<Attrezzo> attrezzi = borsa.getAttrezzi();
		
		assertTrue(attrezzi.contains(a));
		assertTrue(attrezzi.contains(b));
		assertTrue(attrezzi.contains(c));
		
	}

	@Test
	public void testGetContenutoOrdinatoPerPeso() {
		
		borsa.addAttrezzo(piombo);
		borsa.addAttrezzo(piuma);
		borsa.addAttrezzo(libro);
		
		List<Attrezzo> l = borsa.getContenutoOrdinatoPerPeso();
		
		String testString = "[piuma (1kg), libro (2kg), piombo (3kg)]";
		System.out.println(l.toString());
		
		assertEquals(l.toString(), testString);
		
	}

	@Test
	public void testGetContenutoOrdinatoPerNome() {

		borsa.addAttrezzo(b);
		borsa.addAttrezzo(a);
		borsa.addAttrezzo(c);
		
		SortedSet<Attrezzo> s = borsa.getContenutoOrdinatoPerNome();
		
		String testString = "[a (0kg), b (0kg), c (0kg)]";
		System.out.println(s.toString());
		
		assertEquals(s.toString(), testString);
		
	}
	
	@Test
	public void testGetSortedSetOrdinatoPerPeso() {
		
		borsa.addAttrezzo(b);
		borsa.addAttrezzo(a);
		borsa.addAttrezzo(c);
		
		borsa.addAttrezzo(piombo);
		borsa.addAttrezzo(piuma);
		borsa.addAttrezzo(libro);
		
		SortedSet<Attrezzo>  ss = borsa.getSortedSetOrdinatoPerPeso();
		
		String test = borsa.getSortedSetOrdinatoPerPeso().toString();
		
		System.out.println("Set by weight " + test);
		
	}
}

















