package imacstadium.page;

import imacstadium.game.state.StateTrainer;

public class StateFailAttack  implements StateTrainer{

	private String sentence;
	
	public StateFailAttack(String name){
		sentence = "L'imac de "+name+" a raté son attaque.";
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
