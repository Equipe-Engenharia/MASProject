package model;

public class Material {
	
	private String idMaterial;
	private String categoria;
	private String nomeMaterial;
	
	public String getID(){
		return idMaterial;
	}
	
	public void setID(String id){
		this.idMaterial = id;
	}
	
	public String getCategoria(){
		return categoria;
	}
	
	public void setCategoria(String categoria){
		this.categoria = categoria;
	}

	public String getNome() {
		return nomeMaterial;
	}

	public void setNome(String nomeMaterial) {
		this.nomeMaterial = nomeMaterial;
	}
	
}