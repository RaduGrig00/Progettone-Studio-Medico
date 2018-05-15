import java.io.FileInputStream;
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
	
}
