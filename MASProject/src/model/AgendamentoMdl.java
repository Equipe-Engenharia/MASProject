package model;

public class AgendamentoMdl {
	private String id;
	private String expoId;
	private String expoNome;
	private String institutoId;
	private String nome;
	private String institutoTipo;
	private String data;
	private String periodo;
	private String qtdPessoas;
	private String responsavelId;
	private String responsavel;
	private String custo;
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getExpoId() {
		return expoId;
	}
	
	public void setExpoId(String expoId) {
		this.expoId = expoId;
	}
	
	public String getExpo() {
		return expoNome;
	}
	
	public void setExpoNome(String expoNome) {
		this.expoNome = expoNome;
	}
	
	public String getInstitutoId() {
		return institutoId;
	}
	
	public void setInstitutoId(String institutoId) {
		this.institutoId = institutoId;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTipo() {
		return institutoTipo;
	}
	
	public void setTipo(String institutoTipo) {
		this.institutoTipo = institutoTipo;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getPeriodo() {
		return periodo;
	}
	
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	
	public String getQtd() {
		return qtdPessoas;
	}
	
	public void setQtd(String qtdPessoas) {
		this.qtdPessoas = qtdPessoas;
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
	
	public String getCusto() {
		return custo;
	}
	
	public void setCusto(String custo) {
		this.custo = custo;
	}
}