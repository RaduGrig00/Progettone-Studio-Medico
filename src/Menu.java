import java.io.IOException;

public class Menu 
{
	private String titolo;
	private String[] vociMenu;
	private int numeroVociMenu;
	
	public Menu (String titolo, String[] elencoVoci)
	{
		this.titolo=titolo;
		numeroVociMenu=elencoVoci.length;
		vociMenu=new String[numeroVociMenu];
		for (int i = 0; i <numeroVociMenu; i++) 
		{
			vociMenu[i]=elencoVoci[i];
		}
	}
	
	public void visualizza()
	{
		System.out.println(titolo);
		for (int i = 0; i <numeroVociMenu; i++) 
		{
			System.out.println(vociMenu[i]);
		}
	}
	
	public int scelta()
	{
		ConsoleInput tastiera= new ConsoleInput();
		int scelta=-1;
		
		while(scelta<0 || scelta>=numeroVociMenu)
		{
			visualizza();
			System.out.println("Scegli...");
			try 
			{
				scelta=tastiera.readInt();
				if (scelta<0 || scelta>=numeroVociMenu)
					System.out.println("Valore inserito non previsto");
			} 
			catch (NumberFormatException e) 
			{
				System.out.println("Formato inserito non corretto. Reinserire.");
			} 
			catch (IOException e) 
			{
				System.out.println("Impossibile leggere dal dispositivo di input");
			}
						
		}
		return scelta;	
			
		
		
		
	}
}
