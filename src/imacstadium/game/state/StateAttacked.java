package imacstadium.game.state;

import imacstadium.page.GameMenu;

public class StateAttacked implements StateTrainer{

	private String sentence;
	
	public StateAttacked(String name, float life){
		sentence = "La vie de l'Imac de "+name+" n'est plus que de "+life;
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
