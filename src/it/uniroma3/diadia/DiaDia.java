package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;

public class DiaDia {

	private Partita partita;
	private IO io;

	public DiaDia(IO console, Labirinto labirinto) {
		this.io = console;
		this.partita = new Partita(labirinto);
	}

	public void gioca() throws Exception {
		String istruzione; 
		//		Scanner scannerDiLinee;
		io.mostraMessaggio(Configuratore.getMessaggioBenvenuto());
		do {
			istruzione = io.leggiRiga();

		}while (!processaIstruzione(istruzione) );

	}   

	private boolean processaIstruzione(String istruzione) throws Exception {
		Comando comandoDaEseguire;
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva(this.io);
		try {
			comandoDaEseguire = factory.costruisciComando(istruzione);
		} catch (ClassNotFoundException cne) {
			comandoDaEseguire = factory.costruisciComando("NonValido");
		} catch (NullPointerException npe) {
			comandoDaEseguire = factory.costruisciComando("NonValido");
		}
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			io.mostraMessaggio("Hai vinto!");
		if (!this.partita.giocatoreIsVivo())
			io.mostraMessaggio("Hai esaurito i CFU...");
		return this.partita.isFinita();
	}
	

	public static void main(String[] argc) throws Exception {
		Scanner scanner = new Scanner(System.in);
		IO console = new IOConsole(scanner);
	
		Labirinto labirinto = Labirinto.newBuilder("labirinto5.txt").getLabirinto();

		DiaDia gioco = new DiaDia(console, labirinto);
		gioco.gioca();
		scanner.close();
	}

}