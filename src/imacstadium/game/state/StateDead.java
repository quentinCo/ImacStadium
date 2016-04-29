package imacstadium.game.state;

import imacstadium.page.GameMenu;

public class StateDead implements StateTrainer{

	private String sentence;
	
	public StateDead(String name){
		sentence = "L'Imac de "+name+" est vaincu.";
	}

	@Override
	public GameMenu getContent() {
		return new GameMenu(sentence);
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
