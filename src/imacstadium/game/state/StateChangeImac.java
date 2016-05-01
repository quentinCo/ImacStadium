package imacstadium.game.state;

import imacstadium.page.ToolBarPanel;

public class StateChangeImac implements StateTrainer{

	private String sentence;
	
	public StateChangeImac(String name, String imacName, String catchPhrase){
		sentence = name+" appelle "+imacName+" .\n"+catchPhrase;
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
