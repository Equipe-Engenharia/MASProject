package model;

public class IngressoMdl {
	
	private String id;
	private String data;
	private String hora;
	private String bilhete;
	private String expo;
	private String visitante;
	private String ingresso;
	private String valor;
	
	public String getId(){
		return id;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	
	public String getData(){
		return data;
	}
	
	public void setData(String data){
		this.data = data;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public String getBilhete(){
		return bilhete;
	}
	
	public void setBilhete(String bilhete){
		this.bilhete = bilhete;
	}
	
	public String getExpo(){
		return expo;
	}
	
	public void setExpo(String expo){
		this.expo = expo;
	}
	
	public String getVisitante(){
		return visitante;
	}
	
	public void setVisitante(String visitante){
		this.visitante = visitante;
	}
	
	public String getIngresso(){
		return ingresso;
	}
	
	public void setIngresso(String ingresso){
		this.ingresso = ingresso;
	}
	
	public String getValor(){
		return valor;
	}
	
	public void setValor(String valor){
		this.valor = valor;
	}
}