package br.ufg.inf.fs.enums;

public enum TipoPagamento {
	DINHEIRO(1, "Dinheiro"),
	CARTAO_DEBITO(2, "Cartão de Débito"),
	CARTAO_CREDITO(3, "Cartão de Crédito"),
	PIX(4, "Pix"),
	BOLETO(5, "Boleto"),
	CHEQUE(6, "Cheque");
	
	private int id;
	private String value;
	
	TipoPagamento(int id, String value) {
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
	
	public static TipoPagamento get(int id) {
		TipoPagamento categoriaQuarto = null;
		for(TipoPagamento cq : TipoPagamento.values()) {
			if(cq.getId() == id) {
				categoriaQuarto = cq;
				break;
			}
		}
		return categoriaQuarto;
	}
}
