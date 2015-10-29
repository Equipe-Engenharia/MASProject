package model;

public class Material {
	
	private String idMaterial;
	private String categoria;
	private String nome;
	
	public String getId(){
		return idMaterial;
	}
	
	public void setId(String id){
		this.idMaterial = id;
	}
	
	public String getCategoria(){
		return categoria;
	}
	
	public void setCategoria(String categoria){
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}