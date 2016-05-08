package imacstadium.game.state;

import imacstadium.page.ToolBarPanel;

public class StateChangeImac implements StateTrainer{

	private String sentence;
	
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
