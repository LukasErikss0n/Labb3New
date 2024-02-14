package labb3.modell;

public class Gång {
	//Tilståndsvariabler
	private Rum från;
	private Väderstreck riktningUtUrFrån;
	private Rum till;
	private Väderstreck riktningInITill;

	public Gång(Rum från, Väderstreck riktningUtUrFrån, Rum till, Väderstreck riktningInITill) {
		// Tilldelar tillståndsvariablerna parametervärdena.
		this.från = från;
		this.riktningUtUrFrån = riktningUtUrFrån;
		this.till = till;
		this.riktningInITill = riktningInITill;

	}

	// gettters
	public Rum getFrån() {
		return från;
	}
	public Väderstreck getRiktningUtUrFrån() {
		return riktningUtUrFrån;
	}

	public Rum getTill(){
		return  till;
	}

	public Väderstreck getRiktningInITill(){
		return riktningInITill;
	}
}
