package it.uniroma3.diadia.giocatore;

import java.util.Comparator;
import java.util.SortedSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComparatorePerPeso implements Comparator<Attrezzo> {

	public int compare(Attrezzo obj1, Attrezzo obj2) {
		if(obj1.getPeso()-obj2.getPeso() == 0) {
			return obj1.getNome().compareTo(obj2.getNome());
		}
		return obj1.getPeso()-obj2.getPeso();
	}
	
}
