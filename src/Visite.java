import java.time.LocalDate;
public class Visite 
{
	//Attributi
	private int id;
	private String nomeP;
	private String cognomeP;
	private String nomeM;
	private String cognomeM;
	private Boolean visitaSvolta=false;
	private LocalDate Data;
	
	//Costruttori
	public Visite (int id, String nomeP,String cognomeP,String nomeM,String cognomeM)
	{
		setId(id);
		setNomeP(nomeP);
		setCognomeP(cognomeP);
		setNomeM(nomeM);
		setCognomeM(cognomeM);
		setVisitaSvolta(visitaSvolta);
		setData(null);
	}
	
	public Visite (Visite i)
	{
		setId(i.getId());
		setNomeP(i.getNomeP());
		setCognomeP(i.getCognomeP());
		setNomeM(i.getCognomeM());
		setCognomeM(i.getCognomeM());
		setVisitaSvolta(i.getVisitaSvolta());
		setData(i.getData());
	}
	//getter e setter
	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getNomeP() 
	{
		return nomeP;
	}

	public void setNomeP(String nomeP) 
	{
		this.nomeP = nomeP;
	}

	public String getCognomeP() 
	{
		return cognomeP;
	}

	public void setCognomeP(String cognomeP) 
	{
		this.cognomeP = cognomeP;
	}

	public String getNomeM() 
	{
		return nomeM;
	}

	public void setNomeM(String nomeM) 
	{
		this.nomeM = nomeM;
	}

	public String getCognomeM() 
	{
		return cognomeM;
	}

	public void setCognomeM(String cognomeM) 
	{
		this.cognomeM = cognomeM;
	}

	public Boolean getVisitaSvolta() 
	{
		return visitaSvolta;
	}

	public void setVisitaSvolta(Boolean visitaSvolta) 
	{
		this.visitaSvolta = visitaSvolta;
	}

	public LocalDate getData() 
	{
		return Data;
	}

	public void setData(LocalDate data) 
	{
		Data = data;
	}
	
	/*public String toString() 
	{
		return(getId()+" "+getNomeP()+" "+getCognomeP()+getNomeM()+" "+getCognomeM()+" "+getVisitaSvolta()+" "+getData());
	}*/
	

}
