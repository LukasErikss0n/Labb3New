package labb3.modell;

import labb3.GlobalaKonstanter;
import labb3.verktyg.Punkt;

import java.awt.Color;
import java.util.ArrayList;

public class Rum {

	// TODO: Lägg till tillståndsvariabler.
	public Color golvfärg;
	public int bredd;
	public int höjd;
	public Punkt övPunkt;
	//public ArrayList<Gång[]> vädersträckArray = new ArrayList<>(4);
	public Gång[] gångar = new Gång[GlobalaKonstanter.ANTAL_VÄDERSTRECK];



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
	// som ska kontrollera om det från ett rum finns en utgång åt visst
	// väderstreck.
	//
	public Boolean finnsUtgångÅt(Väderstreck väderstreck){
		return gångar[väderstreck.index()] != null;
	}


	// TODO: Skriv instansmetoden
	//
	//Gång gångenÅt(Väderstreck väderstreck) som
	//
	// returnerar den gång som leder från ett rum i riktning väderstreck. Om
	// sådan gång saknas ska ett undantag kastas med lämpligt felmeddelande.

	// TODO: Skrivklar metoden nedan som kopplar ihop två rum med en gång.

	public static void kopplaIhop(Rum från, Väderstreck riktningUtUrFrån, Rum till, Väderstreck riktningInITill) {

		Gång skapaGångFrån = new Gång(från, riktningUtUrFrån, till, riktningInITill);
		från.gångar[riktningUtUrFrån.index()] = skapaGångFrån;

		Gång skapaGångTill = new Gång(till, riktningInITill, från, riktningUtUrFrån);
		till.gångar[riktningInITill.index()] = skapaGångTill;

	}

}
