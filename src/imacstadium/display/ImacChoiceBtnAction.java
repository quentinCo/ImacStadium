package imacstadium.display;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import imacstadium.game.Game;

public class ImacChoiceBtnAction extends AbstractAction{
	
	private SelectScreen screen;
	private int idImac;
	private static int nbClic = 0;

	public ImacChoiceBtnAction(int idImac, SelectScreen screen, String name) {
		super(name);
		this.screen=screen;
		this.idImac = idImac;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		Game.getInstance().addImacsPlayer(idImac);
		this.nbClic++;
		
		if(nbClic >= 3){
			this.nbClic = 0;

			/* Ouvrir nouvelle fenêtre */
			String player_name = screen.getName();

			final BattleScreen frame = new BattleScreen(player_name);
			frame.setName(player_name);
			Game.getInstance().setPage(frame);
			screen.dispose();
		}
	}

}
