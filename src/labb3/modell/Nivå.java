//Vidar Nilsson & Lukas Eriksson
package labb3.modell;

import labb3.kontroll.Tangentbordslyssnare;

import java.util.ArrayList;
import java.util.Observable;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Nivå extends Observable {

	//tillståndsvariabler
	private Rum aktivt;
	private ArrayList<Rum> allaRum;

	public Nivå(Rum startrum, ArrayList<Rum> rum) {
		aktivt = startrum;
		allaRum = rum;

		// Kontrollera att startrum finns med i rum.
		if(!allaRum.contains(startrum)){
			throw new ArrayIndexOutOfBoundsException("Error ");
		}

		// Kontrollerar att inga rum överlappar varandra. Om det ändå är
		// fallet, kasta undantag med lämpligt felmeddelande.
		for (int i = 0; i < allaRum.size(); i++) {
			int x1 = allaRum.get(i).getÖvPunkt().x();
			int y1 = allaRum.get(i).getÖvPunkt().y();
			int bredd1 =allaRum.get(i).getBredd();
			int höjd1 =	allaRum.get(i).getHöjd();

			Rectangle rum1 = new Rectangle(x1, y1, bredd1, höjd1);

			for (int j = 0; j < allaRum.size() - 1 ; j++) {
				if(j == i){
					continue;
				}
				int x2 = allaRum.get(j).getÖvPunkt().x();
				int y2 = allaRum.get(j).getÖvPunkt().y();
				int bredd2 =allaRum.get(j).getBredd();
				int höjd2 =	allaRum.get(j).getHöjd();

				Rectangle rum2 = new Rectangle(x2, y2, bredd2, höjd2);

				if(rum1.intersects(rum2)){
					System.out.println("Objects overlap");
					System.exit(401);
				}
			}
		}
	}

	// getters
	public ArrayList<Rum> getAllaRum(){
		return allaRum;
	}

	public Rum getAktivt(){
		return aktivt;
	}

	// ändrar det rum som användaren "är i" om det är möjligt genom att följa en gång från
	// rummet och i riktning väderstreck.


	public void hoppaÅt(Väderstreck väderstreck) {
		try {
			Gång gång = aktivt.gångenÅt(väderstreck);
			aktivt = gång.getTill();
			setChanged();
			notifyObservers();
		}catch (ArrayIndexOutOfBoundsException _){
			return;
		}

	}
}
