package imacstadium.page;

import imacstadium.game.state.StateTrainer;

public class StateChangeImac implements StateTrainer{

	private String sentence;
	
	public StateChangeImac(String name, String imacName, String catchPhrase){
		sentence = name+" appelle "+imacName+" .\n"+catchPhrase;
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
