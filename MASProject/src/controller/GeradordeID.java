package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeradordeID {
	private String indice;
	
	public GeradordeID(String indice){
		this.indice = indice;
	}
	
	public String geraID(){
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String id = (indice + dateFormat.format(date));
		return id;
	}

}
