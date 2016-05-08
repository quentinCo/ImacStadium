package imacstadium.game.state;

import imacstadium.page.ToolBarPanel;

public class StateAttacked implements StateTrainer{

	private String sentence;
	
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
