package diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import diadia.ambienti.Labirinto;

public class LabirintoTest {
    
    private Labirinto labirinto;

    @Before
    public void setUp() {
        labirinto = new Labirinto();
    }

    @Test
    public void testInit() {
        assertNotNull(labirinto.getStanzaIniziale());
        assertNotNull(labirinto.getStanzaVincente());
    }

    @Test
    public void testGetStanzaVincente() {
        assertNotNull(labirinto.getStanzaVincente());
        assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
    }

    @Test
    public void testGetStanzaIniziale() {
        assertNotNull(labirinto.getStanzaIniziale());
        assertEquals("Atrio", labirinto.getStanzaIniziale().getNome());
    }
}