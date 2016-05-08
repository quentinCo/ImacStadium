package imacstadium.game.state;

import imacstadium.page.ToolBarPanel;

public class StateAttack implements StateTrainer{

	private String sentence;
	
	public StateAttack(String name){
		sentence = "<html>L'Imac de "+name+" attaque.</html>";
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
