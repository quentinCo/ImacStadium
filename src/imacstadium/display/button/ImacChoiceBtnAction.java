package imacstadium.display.button;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import imacstadium.display.BattleScreen;
import imacstadium.display.SelectScreen;
import imacstadium.game.Game;

/**
 * <b>ImacChoiceBtnAction</b>
 * <p>Button that permit to select an imac and attribute this one at a player.</p>
 * @see BattleScreen
 * @see SelectScreen
 * @see Game
 */
public class ImacChoiceBtnAction extends AbstractAction{
	
	private SelectScreen screen;
	private int idImac;
	private static int nbClic = 0;

	/**
	 * 
	 * @param idImac
	 * 	The index of the imac in the Game imacHeader list.
	 * @param screen
	 * 	The screen where is the button.
	 * @param name
	 * 	The name of the button, so the text write on it.
	 */
	public ImacChoiceBtnAction(int idImac, SelectScreen screen, String name) {
		super(name);
		this.screen=screen;
		this.idImac = idImac;
	}

	/**
	 * Add an Imac at the player.
	 * And generate and display the next screen after 3 selection.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {

		Game.getInstance().addImacsPlayer(idImac);
		this.nbClic++;
		
		if(nbClic >= 3){
			this.nbClic = 0;

			final BattleScreen frame = new BattleScreen();
			Game.getInstance().setPage(frame);
			screen.dispose();
		}
	}

}
