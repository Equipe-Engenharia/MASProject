package model;

public class MuseuMdl {
	private String id;
	private String nome;
	private String telefone;
	private String responsavelId;
	private String responsavel;
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getResponsavelId() {
		return responsavelId;
	}
	
	public void setResponsavelId(String responsavelId) {
		this.responsavelId = responsavelId;
	}
	
	public String getResponsavel() {
		return responsavel;
	}
	
	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}
}