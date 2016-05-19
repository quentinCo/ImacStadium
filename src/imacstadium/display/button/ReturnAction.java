package imacstadium.display.button;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import imacstadium.display.MainScreen;
import imacstadium.game.Game;

/**
 * Button that permit to go back at the MainScreen.
 */
public class ReturnAction extends AbstractAction {
	
	private JFrame screen;
	
	/**
	 * @param screen
	 * 	The screen where is the button.
	 * @param name
	 * 	The button's name.
	 */
	public ReturnAction(JFrame screen, String name) {
		super(name);
		this.screen = screen;
	}

	/**
	 * Return to the main screen.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/* Ouvrir nouvelle fenêtre */
		final MainScreen frame = new MainScreen();
		screen.dispose();
	}

}
