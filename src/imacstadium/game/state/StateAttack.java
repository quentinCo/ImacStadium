package imacstadium.game.state;

import imacstadium.display.elements.ToolBarPanel;

/**
 * <b>StateAttack</b>
 * <p>This state is used when the trainer attack.</p>
 * @see StateTrainer
 */
public class StateAttack implements StateTrainer{

	private String sentence;
	
	/**
	 * @param name
	 * 	The name of the attack to display.
	 */
	public StateAttack(String name){
		sentence = "<html>L'Imac de "+name+" attaque.</html>";
	}

	@Override
	public ToolBarPanel getContent() {
		return new ToolBarPanel(sentence);
	}

	@Override
	public boolean getContinu() {
		return false;
	}

	@Override
	public String toString() {
		return sentence;
	}

}
