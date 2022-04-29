package enumerados;

public enum Tipo {

	PERRO("perro"),
	GATO("gato");

	private final String valor;
	private Tipo(String valor) {

		this.valor=valor;
	}
	
	public String getValorTipo() {
		return valor;
	}
	
	
}
