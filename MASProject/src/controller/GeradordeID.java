package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeradordeID {
	private String indice;
	
	public GeradordeID(){
	    indice = geraID();		
	}
	
	private String geraID(){
		DateFormat dateFormat = new SimpleDateFormat("yyMMdd-HHmmss");
		Date date = new Date();
		String id = (dateFormat.format(date));
		return id;
	}

	public String getIndice(){
		return indice;
	}

}