package model;

import java.util.Vector;

public class ExposicaoMdl {
	private String id;
	private String titulo;
	private String dataIni, dataFim;
	private String tema;
	private String descricao;
	private Object[][] obrasExp;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDataIni() {
		return dataIni;
	}

	public void setDataIni(String dataIni) {
		this.dataIni = dataIni;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Object[][] getObrasExp() {
		return obrasExp;
	}

	public void setObrasExp(Object[][] rows) {
		this.obrasExp = rows;
	}

	
}
