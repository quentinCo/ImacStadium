package imacstadium.display;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import imacstadium.game.Game;

public class HomeBtnAction extends AbstractAction {
	
	private MainScreen main;
	
	public HomeBtnAction(MainScreen screen, String name) {
		super(name);
		this.main = screen;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		/* Ouvrir nouvelle fenêtre */
		String player_name = main.getName();
		Game.getInstance().setNamePlayer(player_name);
		final SelectScreen frame = new SelectScreen(player_name);
		main.dispose();
	}

}
