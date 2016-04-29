package imacstadium.game.state;

import imacstadium.page.GameMenu;

public class StateDefeat implements StateTrainer{

	private String sentence;
	
	public StateDefeat(String name, int score){
		sentence = name+" à perdu.\nLe score est de :"+score;
	}

	@Override
	public GameMenu getContent() {
		return new GameMenu(sentence);
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
