package imacstadium.display;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JFrame;

public class ReturnAction extends AbstractAction {
	
	private JFrame screen;

	public ReturnAction(BattleScreen screen, String name) {
		super(name);
		this.screen = screen;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/* Ouvrir nouvelle fenêtre */
		final MainScreen frame = new MainScreen();
		screen.dispose();
	}

}
