package model;

public class SessaoMdl {

	private String id;
	private String usuario;
	private String nivel;
	private String hora;
	private String tela;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}
	
	public String getTela() {
		return tela;
	}

	public void setTela(String tela) {
		this.tela = tela;
	}
}