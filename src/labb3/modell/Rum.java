package labb3.modell;

import labb3.verktyg.Punkt;

import java.awt.Color;

public class Rum {

	// TODO: Lägg till tillståndsvariabler.
	private Color golvfärg;
	private int bredd;
	private int höjd;
	Punkt övPunkt;



	public Rum(Color golvfärg, int bredd, int höjd, int övX, int övY) {
		// TODO: Kopiera parametrarna in i tillståndsvariablerna. (övX,övY) är
		// koordinaten för rummets övre vänstra hörn och lagras lämpligen som en
		// instans av klassen Punkt i paketet verktyg.

		this.golvfärg = golvfärg;
		this.bredd = bredd;
		this.höjd = höjd;
		övPunkt = new Punkt(övX, övY);

	}

	// TODO: Skriv "getters", metoder som returnerar tillståndsvariablernas
	// värden.
	public Color getColor(){
		return golvfärg;
	}
	public int getBredd(){
		return bredd;
	}
	public int getHöjd(){
		return höjd;
	}
	public Punkt getÖvPunkt(){
		return övPunkt;
	}
	// TODO: Skriv instansmetoden
	//
	// finnsUtgångÅt(Väderstreck väderstreck)
	//
	// som ska kontrollera om det från ett rum finns en utgång åt visst
	// väderstreck.

	// TODO: Skriv instansmetoden
	//
	// Gång gångenÅt(Väderstreck väderstreck) som
	//
	// returnerar den gång som leder från ett rum i riktning väderstreck. Om
	// sådan gång saknas ska ett undantag kastas med lämpligt felmeddelande.

	// TODO: Skrivklar metoden nedan som kopplar ihop två rum med en gång.

	public static void kopplaIhop(Rum från, Väderstreck riktningUtUrFrån,
			Rum till, Väderstreck riktningInITill) {
	}

}
