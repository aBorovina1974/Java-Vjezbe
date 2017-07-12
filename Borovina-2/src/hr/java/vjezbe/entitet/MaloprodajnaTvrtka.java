package hr.java.vjezbe.entitet;

public class MaloprodajnaTvrtka extends Tvrtka {
	
	private Artikl[] artikli;
	
	public MaloprodajnaTvrtka(String nazivTvrtke, String oibTvrtke, Artikl[] artikli){
		super(nazivTvrtke, oibTvrtke);
		this.artikli = artikli;
	}

	public Artikl[] getArtikli() {
		return artikli;
	}

	public void setArtikli(Artikl[] artikli) {
		this.artikli = artikli;
	}

}
