package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LjudskiResursi <T extends Osoba> {

	private List <T> listaLjudskihResursa = new ArrayList<>();
	
	
	public List<T> getListaLjudskihResursa() {
		return listaLjudskihResursa;
	}

	
	public void setListaLjudskihResursa(List<T> listaLjudskihResursa) {
		this.listaLjudskihResursa = listaLjudskihResursa;
	}
	
	
	public T get(Integer i)
	{
		return listaLjudskihResursa.get(i);
	}
	
	
	public void add(Integer index, T o)
	{
		listaLjudskihResursa.add(index, o);
	}
	

    public  List<T> getSortedList()
	{
		 Collections.sort(listaLjudskihResursa, (T o1, T o2) ->{
			                                                     String s1 = o1.getPrezime();
			                                                     String s2 = o2.getPrezime();
			                                                     return s1.compareTo(s2);
		                                                       });
		 return listaLjudskihResursa;
	}
	
}