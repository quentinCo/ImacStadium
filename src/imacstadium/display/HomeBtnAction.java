package imacstadium.display;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

public class HomeBtnAction extends AbstractAction {
	
	private MainScreen main;
	private String player_name;
	
	public HomeBtnAction(MainScreen screen, String name) {
		super(name);
		this.main = screen;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		/* Ouvrir nouvelle fenêtre */
		player_name = main.getName();
		final SelectScreen frame = new SelectScreen(player_name);
		main.dispose();
	}

}
