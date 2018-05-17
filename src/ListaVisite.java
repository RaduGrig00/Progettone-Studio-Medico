import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ListaVisite implements Serializable
{
	private Nodo head;
	private int elementi;
	
	//Costruttore lista visite
	public ListaVisite()
	{
		head=null;
		elementi=0;
	}
	//getter che restituisce elementi
	public int getElementi()
	{
		return elementi;
	}
	
	private Nodo creaNodo(Visite visita1, Nodo link)
	{
		Nodo nodo= new Nodo(visita1);
		nodo.setLink(link);
		return nodo;
	}
	
	private Nodo getLinkPosizione(int posizione) throws VisitaException 
	{
		
		Nodo p;
		int n;
		p=head;
		n=1;
		
		if (posizione<1 || posizione>getElementi())
			throw new VisitaException("Posizione non valida");
		if (elementi==0)
			throw new VisitaException("Lista vuota");
			
		while(p.getLink()!=null && n<posizione)
		{
			p=p.getLink();	
			n++;
		}
		
		return p;
	}
	
	public void inserisciInTesta (Visite visita1)
	{
		
		Nodo p=creaNodo(visita1, head);
		head=p;
		elementi++;
	}
	
	public String toString()
	{
		String risultato="Head";
		if (elementi==0)
			return risultato+="-->";
		Nodo p=head;
		while (p!=null)
		{
			risultato+="-->"+p.getInfo().toString();
			p=p.getLink();
		}
		return risultato;
	}
	
	public void inserisciInCoda(Visite visita1) throws VisitaException 
	{
		if(elementi==0)
		{
			inserisciInTesta(visita1);
			return;
		}
		
		Nodo pn=creaNodo(visita1, null);
		Nodo p=getLinkPosizione(elementi);
		p.setLink(pn);
		elementi++;	
	}
	
	public void inserisciInPosizione(Visite visita1,int posizione) throws VisitaException 
	{
		if (posizione<=1)
		{
			inserisciInTesta(visita1);
			return;
		}
		if (posizione>elementi)
		{
			inserisciInCoda(visita1);
			return;
		}
		
		Nodo pn=creaNodo(visita1, getLinkPosizione(posizione));
		Nodo precedente=getLinkPosizione(posizione-1);
		precedente.setLink(pn);
		elementi++;
	}
	
	public void eliminaInTesta() throws VisitaException 
	{
		if (elementi==0)
			throw new VisitaException("Lista vuota");
		head=head.getLink();
		elementi--;
	}
	
	public void eliminaInCoda() throws VisitaException 
	{
		if (elementi==0)
			throw new VisitaException("Lista vuota");
		if (elementi==1)
		{
			eliminaInTesta();
			return;
		}
		
		Nodo p=getLinkPosizione(elementi-1);
		p.setLink(null);
		elementi--;
	}
	
	public void eliminaInPosizione(int posizione) throws VisitaException
	{
		if (elementi==0)
			throw new VisitaException("Lista vuota");
		
		if (posizione<=0 || posizione>elementi)
			throw new VisitaException("Posizione non valida");
	
		if (posizione==1)
		{
			eliminaInTesta();
			return;
		}
		if (posizione==elementi)
		{
			eliminaInCoda();
			return;
		}
		
		Nodo p;
		p=getLinkPosizione(posizione);
		Nodo precedente=getLinkPosizione(posizione-1);
		precedente.setLink(p.getLink());
		elementi--;
		
	}
	
	public void eliminaVisita(int Id) throws VisitaException
	{
		if(elementi==0)
			throw new VisitaException("Lista vuota");
		for (int i = 1; i <= elementi; i++) 
		{
				boolean avvenutaEliminazione = false;
				if((i==1)&&(getLinkPosizione(i).getInfo().getId()==Id))
				{
					eliminaInTesta();
					avvenutaEliminazione=true;
				}
				
				if((i==elementi)&&(getLinkPosizione(i).getInfo().getId()==Id))
				{
					eliminaInCoda();
					avvenutaEliminazione=true;
					return;
				}
				if(avvenutaEliminazione==false)
					{
						if(getLinkPosizione(i).getInfo().getId()==Id)
						{
							Nodo p=getLinkPosizione(i);
							Nodo precedente=getLinkPosizione(i-1);
							precedente.setLink(p.getLink());
							elementi--;
						}
					}
				if(elementi>0 && getLinkPosizione(i).getInfo().getId()==Id)
					i=0;
				}
				
	}
	
	public String visita (int posizione) throws VisitaException 
	{
		if (elementi==0)
			throw new VisitaException("Lista vuota");
		
		if (posizione<=0 || posizione>elementi)
			throw new VisitaException("Posizione non valida");
		
		Nodo p=getLinkPosizione(posizione);
		return p.getInfo().toString();		
	}
	
	public Visite getVisita (int posizione) throws VisitaException 
	{
		if (elementi==0)
			throw new VisitaException("Lista vuota");
		
		if (posizione<=0 || posizione>elementi)
			throw new VisitaException("Posizione non valida");
		
		Nodo p=getLinkPosizione(posizione);
		return p.getInfo();		
	}
	public void eseguiVisita(int Id) throws VisitaException
	{
		if(elementi==0)
			throw new VisitaException("Lista vuota");
	}
	/*public String visualizzaTutteManutenzioni(ListaVisite visite) throws ClassNotFoundException, VisitaException, IOException, FileException
	{
		int elementi = visite.getElementi()+1;
		ListaVisite visite1=new ListaVisite();
		String risultato="";
		if(elementi==0)
			throw new VisitaException("Lista vuota");
		risultato=risultato+Ordinatore.OrdinaDateManutenzioniCrescenti(this).toString();
		return risultato;
	}*/
	
	/*public void ordinaAlfabeto(ListaVisite visite) throws VisitaException
	{
		boolean controllo = true;
		int elementi = visite.getElementi()+1;
		ListaVisite visite1 = new ListaVisite();
		for (int i = 1; i < elementi; i++) 
		{
			visite1.inserisciInPosizione(visite.getVisita(i), i);
		}
		elementi--;
	do
	{
		controllo = true;
		for (int i = 1; i < elementi; i++) 
		{
			if (visite1.getVisita(i).getNomeP().compareTo(visite1.getVisita(i+1).getNomeP())<0)
			{
				
			}
			else if (visite1.getVisita(i).getNomeP().compareTo(visite1.getVisita(i+1).getNomeP())>0)
			{
				visite1.inserisciInPosizione(visite1.getVisita(i+1), i);
				visite1.eliminaInPosizione(i+2);
				controllo = false;
			}
			else if (visite1.getVisita(i).getNomeP().compareTo(visite1.getVisita(i+1).getNomeP())==0)
			{
				if (visite1.getVisita(i).getCognomeP().compareTo(visite1.getVisita(i+1).getCognomeP())==0)
				{
				}
				else if (visite1.getVisita(i).getCognomeP().compareTo(visite1.getVisita(i+1).getCognomeP())>0)
				{
					visite1.inserisciInPosizione(visite1.getVisita(i+1), i);
					visite1.eliminaInPosizione(i+2);
					controllo = false;
				}
				else if (visite1.getVisita(i).getCognomeP().compareTo(visite1.getVisita(i+1).getCognomeP())<0)
				{
				}
			}
			
		}
	}while(controllo == false);			
		elementi++;
		for (int i = 1; i < elementi; i++) 
		{
			visite1.getVisita(i).toString();
		}
		System.out.println("Visualizzazione completata");
		System.out.println("--------------------------------------------------");
		System.out.println();
	}*/
	
	/*public void ordinaData(ListaVisite visita) throws VisitaException
	{
		boolean controllo = true;
		int elementi = visita.getElementi()+1;
		ListaVisite visita1 = new ListaVisite();
		for (int i = 1; i < elementi; i++) 
		{
			visita1.inserisciInPosizione(visita1.getVisita(i), i);
		}
		elementi--;
	do
	{
		controllo = true;
		for (int i = 1; i < elementi; i++) 
		{
			if (visita1.getVisita(i).getData().compareTo(visita1.getVisita(i+1).getData()) > 0)
			{
				visita1.inserisciInPosizione(visita1.getVisita(i+1), i);
				visita1.eliminaInPosizione(i+2);
				controllo = false;
			}
			else
			{
			}
		}
	}while(controllo == false);			
		elementi++;
		for (int i = 1; i < elementi; i++) 
		{
			visita1.getVisita(i).toString();
		}
		System.out.println("Visualizzazione completata");
		System.out.println("--------------------------------------------------");
		System.out.println();
	}*/
	
	public void esportaCSV (String nomeFile) throws IOException, VisitaException, FileException
	{
		TextFile file= new TextFile (nomeFile,'W');
		String visitaCSV;
		Visite visita1;
		
		for (int i = 1; i <= getElementi(); i++) 
		{
			visita1=getVisita(i);
			visitaCSV=visita1.getId()+";"+visita1.getNomeP()+visita1.getCognomeP()+visita1.getNomeM()+visita1.getCognomeM()+";"+visita1.getData()+";";
			file.toFile(visitaCSV);
		}
		file.closeFile();
		
	}

	public ListaVisite importaCSV (String nomeFile) throws IOException, FileException, VisitaException, EccezioneTextFileEOF
	{
		ListaVisite listaVisite=new ListaVisite();
		TextFile file=new TextFile(nomeFile,'R');
		String rigaLetta;
		String[] elementiVisite;
		Visite visita1 = null;
		
			try 
			{
				while(true)
				{
					rigaLetta=file.fromFile();
					elementiVisite=rigaLetta.split(";");
					int Id = visita1.getId();
					String NomeP = visita1.getNomeP();
					String CognomeP = visita1.getCognomeP();
					String NomeM = visita1.getNomeM();
					String CognomeM = visita1.getCognomeM();
					visita1 = new Visite(Id, NomeP, CognomeP, NomeM, CognomeM);
					listaVisite.inserisciInCoda(visita1);
				}
				
			} 
			catch (FileException e) 
			{
				if (e.toString().compareTo("End of file")==0)
					file.closeFile();
				else
					throw new FileException(e.toString());
			}
		
			return listaVisite;		
			
	}
	
	public void salvaListaVisite(String nomeFile) throws IOException
	{
		FileOutputStream file =new FileOutputStream(nomeFile);
		ObjectOutputStream writer=new ObjectOutputStream(file);
		writer.writeObject(this);
		writer.flush();
		file.close();
	}
	
	public ListaVisite caricaListaVisite (String nomeFile) throws IOException, ClassNotFoundException
	{
		FileInputStream file=new FileInputStream(nomeFile);
		ObjectInputStream reader= new ObjectInputStream(file);
		ListaVisite listaVisite;
		listaVisite=(ListaVisite)(reader.readObject());
		file.close();
		return listaVisite;
	}
}
	
	

