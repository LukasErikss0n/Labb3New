package labb3.vy;

import java.awt.*;

import labb3.GlobalaKonstanter;
import labb3.modell.Gång;
import labb3.modell.Nivå;
import labb3.modell.Rum;
import labb3.modell.Väderstreck;
import labb3.verktyg.Grafik;
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
			//ritaGång(g,enNivå.getAllaRum().get(i).gångar[i]);
		}

	}

	private void ritaRum(Graphics g, Rum ettRum) {
		// anropa getters från rumklassen
		int x = ettRum.getÖvPunkt().x();
		int y = ettRum.getÖvPunkt().y();
		int bredd = ettRum.getBredd();
		int höjd = ettRum.getHöjd();
		int vägg = GlobalaKonstanter.VÄGGTJOCKLEK;

		g.setColor(ettRum.getColor());
		g.fillRect(x, y , bredd , höjd);

		g.setColor(GlobalaKonstanter.VÄGGFÄRG);
		g.fillRect(x,y,vägg,höjd); //Väster
		g.fillRect(x,y,bredd,vägg); //Norr
		g.fillRect(x,y + höjd - vägg,bredd,vägg); //Söder
		g.fillRect(x+bredd-vägg,y,vägg,höjd); //Öster
	}

	private void ritaGångarFrånRum(Graphics g, Rum ettRum) {
		for (Väderstreck utgång: Väderstreck.values()){
			if (ettRum.finnsUtgångÅt(utgång)){
				Punkt baspunkt = baspunkt(ettRum, utgång);
				Punkt pivotpunkt = pivotpunkt(ettRum, utgång);
				Grafik.drawThickLine(g,baspunkt,pivotpunkt,GlobalaKonstanter.VÄGGTJOCKLEK,
						GlobalaKonstanter.GÅNGFÄRG);
				Grafik.fillCircle(g, pivotpunkt, GlobalaKonstanter.HALV_VÄGGTJOCKLEK,
						GlobalaKonstanter.GÅNGFÄRG);
			}
		}
	}

	private Punkt baspunkt(Rum ettRum, Väderstreck enRiktning) {
		int x = ettRum.getÖvPunkt().x();
		int y = ettRum.getÖvPunkt().y();
		int bredd = ettRum.getBredd();
		int höjd = ettRum.getHöjd();
		int vägg = GlobalaKonstanter.VÄGGTJOCKLEK;
		switch (enRiktning){
			case NORR:
				 Punkt b0 = new Punkt(x + bredd/2,y + vägg);
				 return b0;
			case SÖDER:
				Punkt b2 = new Punkt(x + bredd/2,y + höjd - vägg);
				return b2;
			case VÄSTER:
				Punkt b3 = new Punkt(x + vägg,y + höjd/2);
				return b3;
			case ÖSTER:
				Punkt b1 = new Punkt(x + bredd - vägg,y + höjd/2);
				return b1;
		}
		return null;
	}

	private Punkt pivotpunkt(Rum ettRum, Väderstreck enRiktning) {
		int vägg = GlobalaKonstanter.VÄGGTJOCKLEK;
		int halvVägg = GlobalaKonstanter.HALV_VÄGGTJOCKLEK;
		Punkt baspunkt = baspunkt(ettRum, enRiktning);
		switch (enRiktning) {
			case NORR:
				Punkt p0 = new Punkt(baspunkt.x(), baspunkt.y() - vägg - halvVägg);
				return p0;
			case SÖDER:
				Punkt p2 = new Punkt(baspunkt.x(), baspunkt.y() + vägg + halvVägg);
				return p2;
			case VÄSTER:
				Punkt p3 = new Punkt(baspunkt.x() - vägg - halvVägg, baspunkt.y());
				return p3;
			case ÖSTER:
				Punkt p1 = new Punkt(baspunkt.x() + vägg + halvVägg, baspunkt.y());
				return p1;
		}
		return null;
	}

	private void ritaGång(Graphics g, Gång enGång) {
		//s
	}

	private void ritaMarkörFörVarAnvändarenÄr(Graphics g) {

	}
}
