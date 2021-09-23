package br.com.harley.notification.enums;

public enum Status {

	PREPARING("PREPARING"), DONE("DONE");

	private String valor;

	private Status(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

}
