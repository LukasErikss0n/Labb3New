package labb3.kontroll;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import labb3.modell.Nivå;
import labb3.modell.Väderstreck;

public class Tangentbordslyssnare implements KeyListener {
	private Nivå enNivå;

	public Tangentbordslyssnare(Nivå enNivå) {
		this.enNivå = enNivå;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// Lyssnar efter knapptryck från användare
		// w betyder "hoppa åt NORR",
		// d betyder "hoppa åt ÖSTER",
		// s betyder "hoppa åt SÖDER" och
		// a betyder "hoppa åt VÄSTER".


		if (e.getKeyChar() == 'w'){
			enNivå.hoppaÅt(Väderstreck.NORR);
		}
		if (e.getKeyChar() == 'a') {
			enNivå.hoppaÅt(Väderstreck.VÄSTER);
		}
		if (e.getKeyChar() == 's') {
			enNivå.hoppaÅt(Väderstreck.SÖDER);
		}
		if (e.getKeyChar() == 'd') {
			enNivå.hoppaÅt(Väderstreck.ÖSTER);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Används inte men måste implementeras eftersom keyTyped finns i
		// gränssnittet KeyListener.
		System.out.println("keyPressed " + e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Används inte men måste implementeras eftersom keyReleased finns is
		// gränssnittet KeyListener.
		System.out.println("keyReleased " + e.getKeyCode() + "\n");
	}
}
