package labb3.vy;

import java.awt.*;

import labb3.GlobalaKonstanter;
import labb3.modell.Gång;
import labb3.modell.Nivå;
import labb3.modell.Rum;
import labb3.modell.Väderstreck;
import labb3.verktyg.Punkt;

import javax.swing.*;

// TODO: Ändra nästa rad så att en Målarduk "är-en" JPanel. 
public class Målarduk extends JPanel {

	private final Nivå enNivå;

	public Målarduk(Nivå enNivå) {
		this.enNivå = enNivå;

		// TODO: Sätt bakgrundsfärgen på this till MARKFÄRG.
		Color användFärg = GlobalaKonstanter.MARKFÄRG;
		this.setBackground(användFärg);

		// TODO: Anropa metoden setFocusable på this och med argumentet true.
		// Detta behövs för att lyssnaren i programmet ska reagera.
		this.setFocusable(true);
	}

	// TODO: Lätt till @Override på metoden nedan.

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Lägg till ett anrop till paintComponent i omedelbara
		// överklassen (JPanel). Skicka med g som argument.
		super.paintComponent(g);

		// TODO: Lägg till kod som ritar ut en grafisk vy av enNivå.
		//
		// För att underlätta finns hjälpmetoder som ska skrivas klara. Studera
		// noga bilderna i labbinstruktionen för att få fram formlerna för
		// bas- och pivotpunkternas koordinater. Använd ritmetoderna i klassen
		// labb3.verktyg.Grafik. Anropa hjälpmetoderna från paintComponent.

		//for loop för varje rum gör anrop till RitaRum
		for (int i = 0; i < enNivå.getAllaRum().size(); i++) {
			ritaRum(g, enNivå.getAllaRum().get(i));
			ritaGångarFrånRum(g, enNivå.getAllaRum().get(i));

		}

	}

	private void ritaRum(Graphics g, Rum ettRum) {
		// anropa getters från rumklassen
		int x = ettRum.getÖvPunkt().x();
		int y = ettRum.getÖvPunkt().y();
		int width = ettRum.getBredd();
		int hight = ettRum.getHöjd();
		int Vägg = GlobalaKonstanter.VÄGGTJOCKLEK;

		g.setColor(ettRum.getColor());
		g.fillRect(x, y , width , hight);

		g.setColor(GlobalaKonstanter.VÄGGFÄRG);
		g.fillRect(x,y,Vägg,hight); //Väster
		g.fillRect(x,y,width,Vägg); //Norr
		g.fillRect(x,y + hight,width,Vägg); //Söder
		g.fillRect(x+width-Vägg,y,Vägg,hight); //Öster
	}

	private void ritaGångarFrånRum(Graphics g, Rum ettRum) {
		//ss
	}

	private Punkt baspunkt(Rum ettRum, Väderstreck enRiktning) {
		return null; /* endast här för att Eclipse inte ska klaga */
	}

	private Punkt pivotpunkt(Rum ettRum, Väderstreck enRiktning) {
		return null; /* endast här för att Eclipse inte ska klaga */
	}

	private void ritaGång(Graphics g, Gång enGång) {

	}

	private void ritaMarkörFörVarAnvändarenÄr(Graphics g) {

	}
}
