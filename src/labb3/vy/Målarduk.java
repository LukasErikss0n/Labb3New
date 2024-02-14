//Vidar Nilsson & Lukas Eriksson
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

public class Målarduk extends JPanel {

	private final Nivå enNivå;

	public Målarduk(Nivå enNivå) {
		this.enNivå = enNivå;

		// bakgrundsfärgen på this till MARKFÄRG.
		Color användFärg = GlobalaKonstanter.MARKFÄRG;
		this.setBackground(användFärg);

		// Detta behövs för att lyssnaren i programmet ska reagera.
		this.setFocusable(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		// anrop till paintComponent i omedelbara
		// överklassen (JPanel).
		super.paintComponent(g);

		//ritar ut grafiska detaljer för varje rum för instans av nivå
		for(Rum rum: enNivå.getAllaRum()){
			ritaRum(g, rum);
			ritaGångarFrånRum(g, rum);
		}
		ritaMarkörFörVarAnvändarenÄr(g);

	}

	private void ritaRum(Graphics g, Rum ettRum) {
		// hittar x och y punkterna för överpunkten på rumet
		int x = ettRum.getÖvPunkt().x();
		int y = ettRum.getÖvPunkt().y();
		int bredd = ettRum.getBredd();
		int höjd = ettRum.getHöjd();
		int vägg = GlobalaKonstanter.VÄGGTJOCKLEK;

		//sätter bakrundsfärg och skapar rumet till speciferad info
		g.setColor(ettRum.getColor());
		g.fillRect(x, y , bredd , höjd);

		//Skapar vägarna för rumet
		g.setColor(GlobalaKonstanter.VÄGGFÄRG);
		g.fillRect(x,y,vägg,höjd); //Väster
		g.fillRect(x,y,bredd,vägg); //Norr
		g.fillRect(x,y + höjd - vägg,bredd,vägg); //Söder
		g.fillRect(x+bredd-vägg,y,vägg,höjd); //Öster
	}

	private void ritaGångarFrånRum(Graphics g, Rum ettRum) {
		//Loop som kallar på ritagång som rittar gångar för varje utgång för rumet
		for (Gång gång: ettRum.getGångar()){
			if(gång != null){
				ritaGång(g, gång);
			}
		}
	}

	private Punkt baspunkt(Rum ettRum, Väderstreck enRiktning) {
		//Hämtar koordinaterna för alla baspunkter
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
		//använder sig av baspunkt som refernspunkt
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
		//Sparar baspunkten från utgånen och sen pivotpunkterna
		Punkt baspunkt = baspunkt(enGång.getFrån(), enGång.getRiktningUtUrFrån());
		Punkt pivotpunkt1 = pivotpunkt(enGång.getFrån(), enGång.getRiktningUtUrFrån());
		Punkt pivotpunkt2 = pivotpunkt(enGång.getTill(), enGång.getRiktningInITill());

		Grafik.drawThickLine(g,baspunkt,pivotpunkt1,GlobalaKonstanter.VÄGGTJOCKLEK,
				GlobalaKonstanter.GÅNGFÄRG);
		Grafik.fillCircle(g, pivotpunkt1, GlobalaKonstanter.HALV_VÄGGTJOCKLEK,
				GlobalaKonstanter.GÅNGFÄRG);

		Grafik.drawThickLine(g, pivotpunkt1, pivotpunkt2, GlobalaKonstanter.VÄGGTJOCKLEK, GlobalaKonstanter.GÅNGFÄRG);
	}

	private void ritaMarkörFörVarAnvändarenÄr(Graphics g) {
		//Rittar ut vart användaren är
		Rum aktivt = enNivå.getAktivt();
		int x = aktivt.getÖvPunkt().x() + aktivt.getBredd()/2;
		int y = aktivt.getÖvPunkt().y() + aktivt.getHöjd()/2;
		Punkt mittpunkt = new Punkt(x, y);
		Grafik.fillCircle(g, mittpunkt, GlobalaKonstanter.ANVÄNDARRADIE, GlobalaKonstanter.ANVÄNDARFÄRG);
	}
}
