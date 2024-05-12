package it.uniroma3.diadia.ambienti;

import java.util.Map;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
 */

public class StanzaProtected {
	
	static final protected int NUMERO_MASSIMO_DIREZIONI = 4;
	protected static final int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;
	
	protected Map<String, Attrezzo> string2attrezzo;
	protected int numeroAttrezzi;

	protected Map<String, StanzaProtected> string2stanza;
	protected int numeroStanzeAdiacenti;
	
	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 */
	
	public StanzaProtected(String nome) {
		this.nome = nome;
		this.numeroStanzeAdiacenti = 0;
		this.numeroAttrezzi = 0;
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
	 */
	public boolean impostaStanzaAdiacente(String direzione, StanzaProtected stanza) {
		
		boolean aggiornato = false;
		
		if(this.string2stanza.containsKey(direzione)) {
			this.string2stanza.put(direzione, stanza);
			aggiornato = true;
		}
		
		if(aggiornato == false && this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
			this.string2stanza.put(direzione, stanza);
		}
		
		return aggiornato;
		
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * @param direzione
	 */
	public StanzaProtected getStanzaAdiacente(String direzione) {

		if(this.string2stanza.containsKey(direzione)) {
			return this.string2stanza.get(direzione);
		}
		
		return null;

	}

	/**
	 * Restituisce la nome della stanza.
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * @return la collezione di attrezzi nella stanza.
	 */
	public Set<Attrezzo> getAttrezzi() {
		return (Set<Attrezzo>) this.string2attrezzo.values();
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		
		boolean aggiornato = false;
		
		// Caso sovrascrizione
		if(this.string2attrezzo.containsKey(attrezzo.getNome())) {
			this.string2attrezzo.put(attrezzo.getNome(), attrezzo);
			aggiornato = true;
		}
		
		// Caso nuova entrata
		if(aggiornato == false && this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
			this.string2attrezzo.put(attrezzo.getNome(), attrezzo);
			aggiornato = true;
		}
		
		return aggiornato;
		
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza,
	 * stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		risultato.append(this.string2stanza.keySet().toString());
		risultato.append("\nAttrezzi nella stanza: ");
		risultato.append(this.string2attrezzo.keySet().toString());
		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		if(this.string2attrezzo.containsKey(nomeAttrezzo)) {
			return true;
		}
		return false;
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
	 * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		if(this.string2attrezzo.containsKey(nomeAttrezzo)) {
			a = this.string2attrezzo.get(nomeAttrezzo);
		}
		return a;
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		
		if(attrezzo == null) {
			return false;
		}
		
		boolean removed = false;
		
		if(this.string2attrezzo.containsKey(attrezzo.getNome())) {
			this.string2attrezzo.remove(attrezzo.getNome());
			removed = true;
		}
		
		return removed;
		
	}


	public Set<String> getDirezioni() {
		return this.string2stanza.keySet();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}