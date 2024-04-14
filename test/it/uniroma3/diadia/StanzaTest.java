package it.uniroma3.diadia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
    private Stanza stanza;
    private Stanza stanzaAdiacente;

    @Before
    public void setUp() throws Exception {
        stanza = new Stanza("Stanza");
        stanzaAdiacente = new Stanza("Stanza adiacente");
    }

    @Test
    public void testImpostaStanzaAdiacente() {
        stanza.impostaStanzaAdiacente("nord", stanzaAdiacente);
        assertEquals(stanzaAdiacente, stanza.getStanzaAdiacente("nord"));
    }

    @Test
    public void testGetStanzaAdiacente() {
        assertNull(stanza.getStanzaAdiacente("nord"));
        stanza.impostaStanzaAdiacente("nord", stanzaAdiacente);
        assertEquals(stanzaAdiacente, stanza.getStanzaAdiacente("nord"));
    }

    @Test
    public void testAddAttrezzo() {
        Attrezzo martello = new Attrezzo("Martello", 1);
        assertTrue(stanza.addAttrezzo(martello));
        assertTrue(stanza.hasAttrezzo("Martello"));
    }

    @Test
    public void testRemoveAttrezzo() {
        Attrezzo chiave = new Attrezzo("chiave", 1);
        stanza.addAttrezzo(chiave);
        assertTrue(stanza.hasAttrezzo("chiave"));
        assertTrue(stanza.removeAttrezzo(chiave));
        assertFalse(stanza.removeAttrezzo(chiave)); // The item is not in the room, cannot remove
        assertTrue(stanza.hasAttrezzo("chiave"));
    }

    @Test
    public void testGetAttrezzo() {
        Attrezzo attrezzo = new Attrezzo("Martello", 1);
        stanza.addAttrezzo(attrezzo);
        assertEquals(attrezzo, stanza.getAttrezzo("Martello"));
        assertNull(stanza.getAttrezzo("Libro")); // No such item in the room
    }

    @Test
    public void testGetDirezioni() {
        stanza.impostaStanzaAdiacente("nord", stanzaAdiacente);
        String[] direzioni = stanza.getDirezioni();
        assertEquals(1, direzioni.length);
        assertEquals("nord", direzioni[0]);
    }
}































