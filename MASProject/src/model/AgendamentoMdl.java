package model;

public class AgendamentoMdl {
	private String id;
	private String institutoId;
	private String instituto;
	private String fone;
	private String responsavelId;
	private String responsavel;
	private String institutoTipo;
	private String data;
	private String periodo;
	private String qtdPessoas;
	private String expoId;
	private String expoNome;
	private String ingresso;
	private String valorUnitario;
	private String valorTotal;
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getInstitutoId() {
		return institutoId;
	}
	
	public void setInstitutoId(String institutoId) {
		this.institutoId = institutoId;
	}
	
	public String getInstituto() {
		return instituto;
	}
	
	public void setInstituto(String instituto) {
		this.instituto = instituto;
	}
	
	public String getFone() {
		return fone;
	}
	
	public void setFone(String fone) {
		this.fone = fone;
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
	
	public String getExpoId() {
		return expoId;
	}
	
	public void setExpoId(String expoId) {
		this.expoId = expoId;
	}
	
	public String getExpoNome() {
		return expoNome;
	}
	
	public void setExpoNome(String expoNome) {
		this.expoNome = expoNome;
	}
	
	public String getIngresso() {
		return ingresso;
	}
	
	public void setIngresso(String ingresso) {
		this.ingresso = ingresso;
	}
	
	public String getVlrUnitario() {
		return valorUnitario;
	}
	
	public void setVlrUnitario(String valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	public String getVlrTotal() {
		return valorTotal;
	}
	
	public void setVlrTotal(String valorTotal) {
		this.valorTotal = valorTotal;
	}
}