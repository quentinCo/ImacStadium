package imacstadium.game.state;

import imacstadium.display.elements.ToolBarPanel;
/**
 * <b>AttackButtonAction</b>
 * <p>This class is a state that generate the ToolPanel, and permit to know that fight phase can continue.</p>
 * @see ToolBarPanel
 */
public interface StateTrainer {

	/**
	 * Return the content of the BattleScreen ToolBarPanel.
	 * @return A ToolBarPanel that correspond at the current state.
	 */
	public ToolBarPanel getContent();
	
	/**
	 * Return if the fight turn can continue
	 * @return Return a boolean to know if the fight phase can continue.
	 */
	public boolean getContinu();
}
