package imacstadium.game.state;

import imacstadium.display.elements.ToolBarPanel;
/**
 * <b>StateFailAttack</b>
 * <p>This state is use when the trainer imac have fail his attack.</p>
 * @see StateTrainer
 */
public class StateFailAttack  implements StateTrainer{

	private String sentence;
	
	/**
	 * @param name
	 * 	The name of the current trainer.
	 */
	public StateFailAttack(String name){
		sentence = "<html>L'imac de "+name+" a raté son attaque.</html>";
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
