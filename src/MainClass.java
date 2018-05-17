import java.io.IOException;
import java.time.LocalDate;


public class MainClass {

	public static void main(String[] args) 
	{
		String [] elenco= {
				"1 --> Registra una nuova visita",
				"2 --> Elimina visita",
				"3 --> Eseguire una visita",
				//"3 --> Visualizza tutte le visite",
				"4 --> Visualizza le visite in ordine alfabetico per il nome dei pazienti",
				"5 --> Visualizza le visite in ordine cronologico",
				"0 --> Esci e Salva"};
		
		ConsoleInput console= new ConsoleInput();
		Menu menu= new Menu(elenco);
		ListaVisite listaVisite = new ListaVisite();
		String nomefile= "listaVisite.bin";
			
		int continuare=1;
		
		try 
		{
			listaVisite= listaVisite.caricaListaVisite(nomefile);
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("Impossibile caricare oggetti di tipo visite");
		}
		catch (IOException e) 
		{
			System.out.println("Impossibile accedere al file");
		}
		System.out.println("Lettura corretta");
		try 
		{
			listaVisite.salvaListaVisite(nomefile);
		} 
		catch (IOException e2) 
		{
			System.out.println("Errore salvataggio");
		}
		do
		{
			

		switch (menu.scelta()) 
		{
		
		case 1:
			{
			
			Visite v1 = new Visite(v1);
			LocalDate data = LocalDate.of(1, 1, 1);
			try {
				System.out.println("Inserisci Id visita: ");
				v1.setId(console.readInt());
				System.out.println("Inserisci nome del paziente");
				v1.setNomeP(console.readString());
				System.out.println("Inserisci cognome del paziente");
				v1.setCognomeP(console.readString());
				System.out.println("Inserisci nome del medico");
				v1.setNomeM(console.readString());
				System.out.println("Inserisci cognome del medico");
				v1.setCognomeM(console.readString());
				System.out.println("Inserisci l'anno in cui è stata registrata la visita(numero): ");
				data=data.plusYears(console.readInt()-1);
				System.out.println("Inserisci il mese (numero): ");
				data=data.plusMonths(console.readInt()-1);
				System.out.println("Inserisci il giorno (numero): ");
				data=data.plusDays(console.readInt()-1);
				v1.setData(data);
				listaVisite.inserisciInTesta(v1);
				listaVisite.salvaListaVisite(nomefile);
			}
				catch (NumberFormatException e) 
			{
				System.out.println("Formato dato inserito non corretto, registrazione visita.");
				break;
			} 
				catch (IOException e) 
			{
				System.out.println("Salvataggio fallito");
				break;
			} 
				catch (VisitaException e) 
			{
				System.out.println(e.toString());
				break;
			} 
				catch (FileException e) 
			{
				System.out.println(e.toString());
				break;
			}
			
			
			
			}
		case 2:
		{
			
			System.out.println("Inserisci l'Id della visita che vuoi eliminare: ");
			try 
			{
				listaVisite.eliminaVisita(console.readInt());
			} 
			catch (NumberFormatException e) 
			{
				System.out.println("Formato dato inserito non corretto, eliminazione visita fallita");
				break;
			} 
			catch (VisitaException e) 
			{
				System.out.println(e.toString());
				break;
			} 
			catch (IOException e) 
			{
				System.out.println("Errore di lettura o scrittura");
				break;
			}
			try 
			{
				listaVisite.salvaListaVisite(nomefile);
			} 
			catch (IOException e) 
			{
				System.out.println("Errore salvataggio");
				break;
			}
			System.out.println("Salvataggio avvenuto con successo");
			break;
		}
		}