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


public class GUI extends JFrame implements Observer {
	// Klassen ärver JFrame och implementerar gränssnittet Observer
	private Målarduk målarduk;
	private int canvasSizeX;
	private int canvasSizeY;


	public GUI(Nivå enNivå) {
		//Anropar metoden setDefaultCloseOperation med konstanten
		//JFrame.EXIT_ON_CLOSE.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		// Gör att enNivå observerar GUI
		enNivå.addObserver(this);


		// Tilldelar tillståndsvariabeln målarduk en instans av klassen
		// Målarduk.
		this.målarduk = new Målarduk(enNivå);


		// Räknar ut lämplig storlek för målarduken
		int maxX = enNivå.getAllaRum().get(0).getÖvPunkt().x();
		int maxY = enNivå.getAllaRum().get(0).getÖvPunkt().y();

		for (int i = 0; i < enNivå.getAllaRum().size() ; i++){
			Rum getRoom = enNivå.getAllaRum().get(i);
			int xKoordinat = getRoom.getÖvPunkt().x();
			int yKoordinat = getRoom.getÖvPunkt().y();

			if (xKoordinat > maxX) {
				maxX = xKoordinat + getRoom.bredd;
			}
			if (yKoordinat > maxY) {
				maxY = yKoordinat + getRoom.höjd;
			}
		}
		canvasSizeX = maxX + 25;
		canvasSizeY = maxY  + 25;
		målarduk.setPreferredSize(new Dimension(canvasSizeX, canvasSizeY));


		// Lägger till en KeyListener på målardiken, dvs en instans av
		// typen Tangentbordslyssnare.
		målarduk.addKeyListener(new Tangentbordslyssnare(enNivå));

		// Anropar setContentPane med målarduk, så att målarduk är
		// den yta som vår JFrame kommer att ha. Sen utförs
		// setVisible(true) och pack().
		setContentPane(målarduk);
		setVisible(true);
		pack();
	}

	// Metoden update ska vara den i gränssnittet Observer. Därför
	// lägger vi till @Override på metoden.
	@Override
	public void update(Observable o, Object arg) {
		// Detta anrop triggar ett anrop till paintComponent i Målarduk.
		this.målarduk.repaint();
	}
}
