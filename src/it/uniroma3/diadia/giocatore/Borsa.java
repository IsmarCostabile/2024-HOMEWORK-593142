package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.ComparatorePerPeso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.List;

public class Borsa {
	
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	
	private Map<String, Attrezzo> nome2attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	private int pesoAttuale;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.nome2attrezzi = new TreeMap<>(); 
		this.numeroAttrezzi = 0;
		this.pesoAttuale = 0;
	}

	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		this.nome2attrezzi.put(attrezzo.getNome(), attrezzo);
		this.numeroAttrezzi++;
		this.pesoAttuale += attrezzo.getPeso();
		return true;
	}

	public int getPesoMax() {
		return pesoMax;
	}

	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo != null && this.nome2attrezzi.containsKey(nomeAttrezzo)) {
			return this.nome2attrezzi.get(nomeAttrezzo);
		}
		
		return null;
	}

	public int getPeso() {
		return this.pesoAttuale;
	}

	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}

	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo tmp = this.nome2attrezzi.get(nomeAttrezzo);
		if(tmp!=null) {
			this.numeroAttrezzi--;
			this.pesoAttuale -= tmp.getPeso();
			this.nome2attrezzi.remove(nomeAttrezzo);
		}
		return tmp;
		
	}
	
	public boolean getPesoRimanente(Attrezzo a) {
		if(a != null && this.getPesoMax()-this.getPeso()>=a.getPeso())
			return true;
		return false; 
	}
	
	public Collection<Attrezzo> getAttrezzi(){
		return this.nome2attrezzi.values();
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			s.append("\n" + getContenutoOrdinatoPerNome().toString());
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> l = new ArrayList<>();
		l.addAll(this.nome2attrezzi.values());
		Collections.sort(l, new ComparatorePerPeso());
		return l;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
	    TreeSet<Attrezzo> ordinati = new TreeSet<Attrezzo>(new Comparator<Attrezzo>() {
	        @Override
	        public int compare(Attrezzo a1, Attrezzo a2) {
	            return a1.getNome().compareTo(a2.getNome());
	        }
	    });
	    ordinati.addAll(this.nome2attrezzi.values());
	    return ordinati;
	}
	
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> attrezzo2peso = new TreeMap<>();
		
		for(Attrezzo a : this.nome2attrezzi.values()) {
			
			if(attrezzo2peso.containsKey(a.getPeso())) {
				attrezzo2peso.get(a.getPeso()).add(a);
			} else {
				Set<Attrezzo> set = new HashSet<Attrezzo>();
				set.add(a);
				attrezzo2peso.put(a.getPeso(), set);
			}
			
		}
		
		return attrezzo2peso;
		
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> s = new TreeSet<Attrezzo>(new ComparatorePerPeso());
		s.addAll(this.nome2attrezzi.values());
		return s;
	}

}



















































