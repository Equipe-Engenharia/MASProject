package model;

public class EmprestimoObraMdl {
	private String id;
	private String artista;
	private String nome;
	private String categoria;
	private String material;
	private String descricao;
	private String imagem;
	private String dataComposicao;
	private boolean proprietario;
	private String status;
	private String setor;
	private String preco;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getArtista() {
		return artista;
	}
	public void setArtista(String artista) {
		this.artista = artista;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public String getDataComposicao() {
		return dataComposicao;
	}
	public void setDataComposicao(String dataComposicao) {
		this.dataComposicao = dataComposicao;
	}
	public boolean isProprietario() {
		return proprietario;
	}
	public void setProprietario(boolean proprietario) {
		this.proprietario = proprietario;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
}