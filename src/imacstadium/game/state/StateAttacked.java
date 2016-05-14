package imacstadium.game.state;

import imacstadium.display.elements.ToolBarPanel;

/**
 * <b>StateAttacked</b>
 * <p>This state is used when the trainer is attacked;</p>
 * @see StateTrainer
 */
public class StateAttacked implements StateTrainer{

	private String sentence;
	
	/**
	 * @param name
	 * 	The name of the trainer who play.
	 * @param life
	 * 	The life of the trainer's current imac.
	 */
	public StateAttacked(String name, float life){
		sentence = "<html>La vie de l'Imac de "+name+" n'est plus que de "+life+"</html>";
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
