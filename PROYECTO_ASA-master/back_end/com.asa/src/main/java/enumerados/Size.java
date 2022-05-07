package enumerados;

public enum Size {

	SMALL("pequeño"),
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
