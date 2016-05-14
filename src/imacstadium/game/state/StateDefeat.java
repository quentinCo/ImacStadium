package imacstadium.game.state;

import imacstadium.display.elements.ToolBarPanel;
/**
 * <b>StateDefeat</b>
 * <p>This state is use when the trainer has no more valide imac.</p>
 * @see StateTrainer
 */
public class StateDefeat implements StateTrainer{

	private String sentence;
	
	/**
	 * @param name
	 * 	The current player name.
	 * @param score
	 * 	The score player.
	 */
	public StateDefeat(String name, int score){
		sentence = "<html>"+name+" a perdu.<br>Le score est de : "+score+"</html>";
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
