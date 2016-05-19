package imacstadium.game.state;

import imacstadium.display.elements.ToolBarPanel;

/**
 * <b>StateChangeImac</b>
 * <p>This state is use when the trainer change of imac.</p>
 * @see StateTrainer
 */
public class StateChangeImac implements StateTrainer{

	private String sentence;
	
	/**
	 * @param name
	 * 	The trainer name.
	 * @param imacName
	 * 	The new imac name.
	 * @param catchPhrase
	 * 	The imac catch phrase.
	 */
	public StateChangeImac(String name, String imacName, String catchPhrase){
		sentence = "<html>"+name+" appelle "+imacName+" .<br/>"+catchPhrase+"</html>";
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
