package com.asa.CRUD.enumerados;

public enum Size {

	SMALL("pequenio"),
	MEDIANO("mediano"),
	GRANDE("grande");
	
	private final String valor;
	
	private Size(String valor) {

		this.valor=valor;
	}
	
	public String getValorTipo() {
		return valor;
	}
}
