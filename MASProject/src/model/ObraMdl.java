package model;

public class ObraMdl {
	private String id;
	private ArtistaMdl artista;
	private String nome;
	private ExposicaoMdl categoria;
	private MaterialMdl material;
	private String descricao;
	private String imagem;
	private String dataComposicao;
	private boolean proprietario;
	private String status;
	private SetorMdl setor;
	private String preco;

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public ArtistaMdl getArtista() {
		return artista;
	}
	public void setArtista(ArtistaMdl artista) {
		this.artista = artista;
	}
	public String getNome() {
		return nome;
	}
	public void setNomeObra(String nome) {
		this.nome = nome;
	}
	public ExposicaoMdl getCategoria() {
		return categoria;
	}
	public void setCategoria(ExposicaoMdl categoria) {
		this.categoria = categoria;
	}
	public MaterialMdl getMaterial() {
		return material;
	}
	public void setMaterial(MaterialMdl material) {
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
	public SetorMdl getSetor() {
		return setor;
	}
	public void setSetor(SetorMdl setor) {
		this.setor = setor;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
}
