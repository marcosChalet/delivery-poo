package model;

public class Endereco {
	private int id;
	private String cidade;
	private String cep;
	private String rua;
	private int numero;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	@Override
	public String toString() {
		return  "Cidade:   " + this.cidade + "\n" +
				"Rua:      " + this.rua + "\n" +
				"Cep:      " + this.cep + "\n" +
				"Número:   " + this.numero;
	}
}
