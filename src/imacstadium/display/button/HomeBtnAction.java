package imacstadium.display.button;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import imacstadium.display.MainScreen;
import imacstadium.display.SelectScreen;
import imacstadium.game.Game;
/**
 * <b>HomeBtnAction</b>
 * <p>
 * Button of the main screen, it validate the screen and permit to go at the next screen.
 * </p>
 * @see MainScreen
 * @see SelectScreen
 * @see Game
 */
public class HomeBtnAction extends AbstractAction {
	
	private MainScreen main;
	
	/**
	 * 
	 * @param screen
	 * 	The screen where is place the button.
	 * @param name
	 * 	The button's name.
	 */
	public HomeBtnAction(MainScreen screen, String name) {
		super(name);
		this.main = screen;
	}

	/**
	 * Validate the text field, create and display the next screen.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		/* Ouvrir nouvelle fenêtre */
		String player_name = main.getName();
		Game.getInstance().setNamePlayer(player_name);
		final SelectScreen frame = new SelectScreen(player_name);
		Game.getInstance().setPage(frame);
		main.dispose();
	}

}
