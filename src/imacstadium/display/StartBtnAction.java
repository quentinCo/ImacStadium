package imacstadium.display;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class StartBtnAction extends AbstractAction{
	
	private SelectScreen screen;
	private String player_name;

	public StartBtnAction(SelectScreen screen, String name) {
		super(name);
		this.screen=screen;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		/* Ouvrir nouvelle fenêtre */
		player_name = screen.getName();
		//System.out.println(player_name);
		final BattleScreen frame = new BattleScreen(player_name);
		frame.setName(player_name);
		screen.dispose();
	}

}
