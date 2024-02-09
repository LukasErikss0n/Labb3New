package labb3;

import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import labb3.kontroll.Tangentbordslyssnare;
import labb3.modell.Nivå;
import labb3.modell.Rum;
import labb3.verktyg.Punkt;
import labb3.vy.Målarduk;
import sun.security.mscapi.CPublicKey;

import javax.swing.*;


// TODO: Klassen ska ärva klassen JFrame i standardbibliotekets paket 
// javax.swing och implementera gränssnittet Observer i paketet java.util. 
public class GUI extends JFrame implements Observer {

	private Målarduk målarduk;
	private int canvasSizeX;
	private int canvasSizeY;


	public GUI(Nivå enNivå) {
		//TODO: Anropa metoden setDefaultCloseOperation med konstanten
		//JFrame.EXIT_ON_CLOSE.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		// TODO: Gör så att enNivå observerar this (instansen av GUI som denna
		// kod håller på att skapa.
		enNivå.addObserver(this);


		// TODO: Tilldela tillståndsvariabeln målarduk en instans av klassen
		// Målarduk.
		Målarduk målarduk = new Målarduk(enNivå);

		// TODO: Använd setPreferredSize på målarduk och sätt dess dimensioner
		// så alla rum faktiskt syns. Ett tips är att loopa igenom nivåns alla
		// rum och räkna ut vilken bredd och höjd målarduken måste ha(!)
		// Annars går det också att dra till med en storlek och sen bara
		// använda rum som säkert kan visas på målarduken.
		int minX = enNivå.getAllaRum().get(0).getÖvPunkt().x(); //Sätter minsta och största x och y värden( har något att gemföra med i for loopen)
		int maxX = enNivå.getAllaRum().get(0).getÖvPunkt().x();
		int minY = enNivå.getAllaRum().get(0).getÖvPunkt().y();
		int maxY = enNivå.getAllaRum().get(0).getÖvPunkt().y();

		for (int i = 0; i < enNivå.getAllaRum().size() ; i++){
			Rum getRoom = enNivå.getAllaRum().get(i);
			int xKoordinat = getRoom.getÖvPunkt().x();
			int yKoordinat = getRoom.getÖvPunkt().y();

			if (xKoordinat < minX) {
                minX = xKoordinat;
            }
			if (yKoordinat < minY) {
				minY = yKoordinat;
			}
			if (xKoordinat > maxX) {
				maxX = xKoordinat;
			}
			if (yKoordinat > maxY) {
				maxY = yKoordinat;
			}
		}
		canvasSizeX = maxX - minX + 25;
		canvasSizeY = maxY - minY + 25;
		målarduk.setPreferredSize(new Dimension(canvasSizeX, canvasSizeY));


		// TODO: Lägg till en KeyListener på målardiken, dvs en instans av
		// typen Tangentbordslyssnare. Notera att lyssnaren vill ha enNivå
		// som argument till konstruktorn för att kunna påverka just den
		// nivån.
		målarduk.addKeyListener(new Tangentbordslyssnare(enNivå));

		// TODO: Anropa setContentPane med målarduk, så att målarduk är
		// den yta som vår JFrame kommer att ha. Sen återstår bara att
		// göra setVisible(true) och pack().
		setContentPane(målarduk);
		setVisible(true);
		pack();

	}

	// TODO: Metoden update ska vara den i gränssnittet Observer. Lägg därför
	// till @Override på raden innan metodhuvudet. (Klassen måste sen importera
	// java.util.Observer.)
	@Override
	public void update(Observable o, Object arg) {
		// Detta anrop triggar ett anrop till paintComponent i Målarduk.
		// Avkommentera raden (när Målarduk "är-en" JPanel). 
		this.målarduk.repaint();
	}
}
