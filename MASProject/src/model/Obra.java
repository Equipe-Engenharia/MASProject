package model;

public class Obra {
	private String id;
	private Artista artista;
	private String nome;
	private Categoria categoria;
	private Material material;
	private String descricao;
	private String imagem;
	private String dataComposicao;
	private boolean proprietario;
	private String status;
	private Setor setor;
	private String preco;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Artista getArtista() {
		return artista;
	}
	public void setArtista(Artista artista) {
		this.artista = artista;
	}
	public String getNome() {
		return nome;
	}
	public void setNomeObra(String nome) {
		this.nome = nome;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricaoObra(String descricao) {
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
}
