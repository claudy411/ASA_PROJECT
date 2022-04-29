package enumerados;

public enum Situacion {
	
	RESIDENCIA("residencia"),
	ADOPTADO("adoptado"),
	ACOGIDA("acogida");

	private final String situacion;
	
	private Situacion(String situacion) {
		
		this.situacion=situacion;
	}

	public String getSituacion() {
		return situacion;
	}

	
}
