package imacstadium.game.state;

import imacstadium.page.GameMenu;

public class StateAttack implements StateTrainer{

	private String sentence;
	
	public StateAttack(String name){
		sentence = "L'Imac de "+name+" attaque.";
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
