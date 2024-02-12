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
		// TODO: Skriv klar denna metod som automatiskt anropas så snart
		// användaren tryckt på en tangent på tangentbordet. Metoden ska
		// översätta tangenten till ett väderstreck och sen anropa hoppaÅt i
		// enNivå med detta väderstreck. Här ska
		//
		// w betyda "hoppa åt NORR",
		// d betyda "hoppa åt ÖSTER",
		// s betyda "hoppa åt SÖDER" och
		// a betyda "hoppa åt VÄSTER".
		/*if (e.getKeyChar() == "w") {
			enNivå.hoppaÅt(Väderstreck 0)
		}
		if (e.getKeyChar() == "a") {
			enNivå.hoppaÅt(Väderstreck 3)
		}
		if (e.getKeyChar() == "s") {
			enNivå.hoppaÅt(Väderstreck 2)
		}
		if (e.getKeyChar() == "d") {
			enNivå.hoppaÅt(Väderstreck 1)
		}*/
		System.out.println("keyTyped " + e.getKeyChar());
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
