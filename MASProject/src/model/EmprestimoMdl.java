package model;

public class EmprestimoMdl {
	private String id;
	private String obraId;
	private String obraNome;
	private String artista;
	private String destino;
	private String dataInicial;
	private String dataFinal;
	private String museuId;
	private String museu;
	private String responsavelId;
	private String responsavel;
	private String custo;
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getObraId() {
		return obraId;
	}
	
	public void setObraId(String obraId) {
		this.obraId = obraId;
	}
	
	public String getObra() {
		return obraNome;
	}
	
	public void setObra(String obraNome) {
		this.obraNome = obraNome;
	}
	
	public String getArtista() {
		return artista;
	}
	
	public void setArtista(String artista) {
		this.artista = artista;
	}
	
	public String getDestino() {
		return destino;
	}
	
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	public String getDataInicial() {
		return dataInicial;
	}
	
	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}
	
	public String getDataFinal() {
		return dataFinal;
	}
	
	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	public String getMuseuId() {
		return museuId;
	}
	
	public void setMuseuId(String idMuseu) {
		this.museuId = idMuseu;
	}
	
	public String getMuseu() {
		return museu;
	}
	
	public void setMuseu(String museu) {
		this.museu = museu;
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