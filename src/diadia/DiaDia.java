package diadia;

import java.util.Scanner;

import diadia.attrezzi.*;
import diadia.ambienti.*;
import diadia.giocatore.*;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};
	
	private IOConsole IOConsole;
	
	private Partita partita;
	private Labirinto labirinto;
	private Giocatore giocatore;

	public DiaDia(IOConsole IOConsole) {
		this.labirinto = new Labirinto();
		this.partita = new Partita(this.labirinto);
		this.giocatore = new Giocatore();
		this.IOConsole = IOConsole;
	}

	public void gioca() {
		String istruzione; 
		Scanner scannerDiLinee;

		IOConsole.mostraMessaggio(MESSAGGIO_BENVENUTO);	
		do		
			istruzione = this.IOConsole.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		if(istruzione.isEmpty()) return false;
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			IOConsole.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			IOConsole.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			System.out.print(elencoComandi[i]+" ");
		IOConsole.mostraMessaggio("\n");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null) {
			IOConsole.mostraMessaggio("Dove vuoi andare ?");
			return;
		}
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			IOConsole.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.giocatore.getCFU();
			this.giocatore.setCFU(--cfu);
		}
		IOConsole.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
	
	private void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			IOConsole.mostraMessaggio("Quale attrezzo vuoi raccogliere?");	
			return;
		}
		Attrezzo attrezzo = null;
		attrezzo = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		if (attrezzo == null)
			IOConsole.mostraMessaggio("Attrezzo non presente");
		else {
			if(this.giocatore.writeToBorsa(attrezzo)) {
				this.partita.getStanzaCorrente().removeAttrezzo(attrezzo);
				IOConsole.mostraMessaggio("Hai raccolto l'attrezzo " + nomeAttrezzo);
			} else {
				IOConsole.mostraMessaggio("L'oggetto è troppo pesante, puoi lasciarne un'altro per liberare spazio");
			}
		}
	}
	
	private void posa(String nomeAttrezzo) {
		if(nomeAttrezzo==null) {
			IOConsole.mostraMessaggio("Quale attrezzo vuoi posare?"); 
			return;
		}
		Attrezzo attrezzo = null;
		attrezzo = this.giocatore.togliDaBorsa(nomeAttrezzo);;
		if (attrezzo == null)
			IOConsole.mostraMessaggio("Attrezzo non presente");
		else {
			this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
			this.giocatore.togliDaBorsa(nomeAttrezzo);
			IOConsole.mostraMessaggio("Hai posato l'attrezzo " + nomeAttrezzo + "nella stanza " + this.partita.getStanzaCorrente());
		}
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		IOConsole.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole IOConsole = new IOConsole();
		DiaDia gioco = new DiaDia(IOConsole);
		gioco.gioca();
	}
}