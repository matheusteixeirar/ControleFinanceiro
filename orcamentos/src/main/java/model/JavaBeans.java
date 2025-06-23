package model;

public class JavaBeans {

	private String id;
	private String tipo; // "receita" ou "despesa"
	private String categoria;
	private String descricao;
	private String valor;
	private String data;

	public JavaBeans() {
		super();
	}

	public JavaBeans(String id, String tipo, String categoria, String descricao, String valor, String data) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.categoria = categoria;
		this.descricao = descricao;
		this.valor = valor;
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
