package it.uniroma3.diadia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class BorsaTest {

    private Borsa borsa;

    @Before
    public void setUp() {
        borsa = new Borsa();
    }

    @Test
    public void testAddAttrezzo() {
        Attrezzo attrezzo = new Attrezzo("Martello", 5);
        assertTrue(borsa.addAttrezzo(attrezzo));
        assertEquals(attrezzo, borsa.getAttrezzo("Martello"));
    }

    @Test
    public void testGetAttrezzo() {
        Attrezzo attrezzo = new Attrezzo("Libro", 3);
        borsa.addAttrezzo(attrezzo);
        assertEquals(attrezzo, borsa.getAttrezzo("Libro"));
    }

    @Test
    public void testRemoveAttrezzo() {
        Attrezzo attrezzo = new Attrezzo("Chiave", 2);
        borsa.addAttrezzo(attrezzo);
        assertEquals(attrezzo, borsa.removeAttrezzo("Chiave"));
        assertFalse(borsa.hasAttrezzo("Chiave"));
    }

}
