package br.ufg.inf.fs.enums;

public enum Grupo {
	ALVENARIA(1, "Unidade"),
	PINTURA(2, "Litro"),
	REVESTIMENTO(3, "Kilo"),
	LOUCAS(4, "Caixa"),
	ESQUADRIAS(5, "Saco"),
	TELHADO(6, "Metro");
	
	private int id;
	private String value;
	
	Grupo(int id, String value) {
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
	
	public static Grupo get(int id) {
		Grupo categoriaQuarto = null;
		for(Grupo cq : Grupo.values()) {
			if(cq.getId() == id) {
				categoriaQuarto = cq;
				break;
			}
		}
		return categoriaQuarto;
	}
}
