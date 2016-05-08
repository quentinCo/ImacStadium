package imacstadium.game.state;

import imacstadium.page.ToolBarPanel;

public class StateDefeat implements StateTrainer{

	private String sentence;
	
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
