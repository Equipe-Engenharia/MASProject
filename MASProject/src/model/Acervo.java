package model;

import java.util.List;

public class Acervo {
	private int idAcervo;
	private int status;
	private List<Obra> obras;
	
	public Acervo(List<Obra> obras){
		this.obras = obras;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<Obra> getObras() {
		return obras;
	}
	public void setObras(List<Obra> obras) {
		this.obras = obras;
	}
	public int getIdAcervo() {
		return idAcervo;
	}
	public void setIdAcervo(int idAcervo) {
		this.idAcervo = idAcervo;
	}
}
