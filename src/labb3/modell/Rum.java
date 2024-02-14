package labb3.modell;

import labb3.GlobalaKonstanter;
import labb3.verktyg.Punkt;

import java.awt.Color;
import java.util.ArrayList;


public class Rum {

	//Tillståndsvariabler.
	public Color golvfärg;
	public int bredd;
	public int höjd;
	public Punkt övPunkt;
	//public ArrayList<Gång[]> vädersträckArray = new ArrayList<>(4);
	public Gång[] gångar = new Gång[GlobalaKonstanter.ANTAL_VÄDERSTRECK];



	public Rum(Color golvfärg, int bredd, int höjd, int övX, int övY) {
		// Konstruktorn skapar ett rum och sparar tillståndsvariablerna

		this.golvfärg = golvfärg;
		this.bredd = bredd;
		this.höjd = höjd;
		övPunkt = new Punkt(övX, övY);

	}

	// getters metoder som returnerar tillståndsvariablernas värden.
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

	public Boolean finnsUtgångÅt(Väderstreck väderstreck){
		//Kontrollerar om det från ett rum finns en utgång åt visst
		//väderstreck.
		return gångar[väderstreck.index()] != null;
	}

	public Gång[] getGångar() {
		return gångar;
	}
	// TODO: Skriv instansmetoden
	//
	public Gång gångenÅt(Väderstreck väderstreck){
		// Returnerar den gång som leder från ett rum i riktning väderstreck. Om
		// sådan gång saknas kastas ett undantag
		if(finnsUtgångÅt(väderstreck)){
			return  gångar[väderstreck.index()];
		}
		throw new ArrayIndexOutOfBoundsException("Finns ingen utgång åt" + väderstreck);

	}

	public static void kopplaIhop(Rum från, Väderstreck riktningUtUrFrån, Rum till, Väderstreck riktningInITill) {
		//Kopplar ihop två rum med en gång
		Gång skapaGångFrån = new Gång(från, riktningUtUrFrån, till, riktningInITill);
		från.gångar[riktningUtUrFrån.index()] = skapaGångFrån;

		Gång skapaGångTill = new Gång(till, riktningInITill, från, riktningUtUrFrån);
		till.gångar[riktningInITill.index()] = skapaGångTill;

	}

}
