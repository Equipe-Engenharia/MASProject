package model;

public class Obra {
	private String nomeObra;
	private String descricaoObra;
	private String imagem;
	private String dataComposicao;
	private boolean proprietario;
	private Categoria categoria;
	private String status;

	private Material material;
	private Artista artista;
	private Setor setor;
	private String preco;

	
	public String getNomeObra() {
		return nomeObra;
	}
	public void setNomeObra(String nomeObra) {
		this.nomeObra = nomeObra;
	}
	public String getDescricaoObra() {
		return descricaoObra;
	}
	public void setDescricaoObra(String descricaoObra) {
		this.descricaoObra = descricaoObra;
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
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}
	public Artista getArtista() {
		return artista;
	}
	public void setArtista(Artista artista) {
		this.artista = artista;
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
