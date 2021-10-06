package br.ufg.inf.fs.enums;

public enum UnidadeMedida {
	UNIDADE(1, "Unidade"),
	LITRO(2, "Litro"),
	Kg(3, "Kilo"),
	CAIXA(4, "Caixa"),
	SACO(5, "Saco"),
	METRO(6, "Metro");
	
	private int id;
	private String value;
	
	UnidadeMedida(int id, String value) {
		this.id = id;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString() {
		return this.value;
	}
	
	public static UnidadeMedida get(int id) {
		UnidadeMedida categoriaQuarto = null;
		for(UnidadeMedida cq : UnidadeMedida.values()) {
			if(cq.getId() == id) {
				categoriaQuarto = cq;
				break;
			}
		}
		return categoriaQuarto;
	}
}
