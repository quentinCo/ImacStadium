package imacstadium.game.state;

import imacstadium.display.elements.ToolBarPanel;
/**
 * <b>StateDead</b>
 * <p>This state is use when the trainer imac is dead.</p>
 * @see StateTrainer
 */
public class StateDead implements StateTrainer{

	private String sentence;
	
	/**
	 * @param name
	 * 	The current trainer name.
	 */
	public StateDead(String name){
		sentence = "<html>L'Imac de "+name+" est vaincu.</html>";
	}

	@Override
	public ToolBarPanel getContent() {
		return new ToolBarPanel(sentence);
	}

	@Override
	public boolean getContinu() {
		return true;
	}

	@Override
	public String toString() {
		return sentence;
	}
}
