package imacstadium.game.state;

import imacstadium.page.ToolBarPanel;

public class StateFailAttack  implements StateTrainer{

	private String sentence;
	
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
