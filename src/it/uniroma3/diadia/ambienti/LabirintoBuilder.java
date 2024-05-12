package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder extends Labirinto {
	
	private Labirinto lab;
	private Stanza lastAdded;
	
	private Map<String, Stanza> n2s;
	
	public LabirintoBuilder() {
		this.lab = new Labirinto();
		this.n2s = new HashMap<String, Stanza>();
	}
	
	private void updateUltimaStanza(Stanza s) {
		this.lastAdded = s;
		this.n2s.put(s.getNome(), s);
	}
	
	public LabirintoBuilder addStanzaIniziale(String nome) {
		Stanza initial = new Stanza(nome);
		this.lab.setStanzaCorrente(initial);
		updateUltimaStanza(initial);
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String nome) {
		Stanza finale = new Stanza(nome);
		this.lab.setStanzaVincente(finale);
		updateUltimaStanza(finale);
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
		Attrezzo a = new Attrezzo(nomeAttrezzo, peso);
		this.lastAdded.addAttrezzo(a);
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String stanzaCorrente, String stanzaAdiecente, String direzione) {
		Stanza c = this.n2s.get(stanzaCorrente);
		Stanza a = this.n2s.get(stanzaAdiecente);
		c.impostaStanzaAdiacente(direzione, a);
		return this;
	}
	
	public LabirintoBuilder addStanza(String nome) {
		Stanza stanza = new Stanza(nome);
		this.updateUltimaStanza(stanza);
		return this;
	}
	
	public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
		Stanza stanza = new StanzaMagica(nome, soglia);
		this.updateUltimaStanza(stanza);
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String nome, String attrezzoPerVedere) {
		Stanza stanza = new StanzaBuia(nome,attrezzoPerVedere);
		this.updateUltimaStanza(stanza);
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nome, String attrezzoSbloccante, String direzioneBloccata) {
		Stanza stanza = new StanzaBloccata(nome, attrezzoSbloccante, direzioneBloccata);
		this.updateUltimaStanza(stanza);
		return this;
	}
	
	public Labirinto getLabirinto() {
		return this.lab;
	}
	
	public Map<String, Stanza> getN2S(){
		return this.n2s;
	}
	
}
