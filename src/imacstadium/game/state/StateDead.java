package imacstadium.game.state;

import imacstadium.page.ToolBarPanel;

public class StateDead implements StateTrainer{

	private String sentence;
	
	public StateDead(String name){
		sentence = "L'Imac de "+name+" est vaincu.";
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
