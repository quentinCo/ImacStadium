package imacstadium.game.state;

import imacstadium.game.Trainer;
import imacstadium.page.ChoiceMenu;
import imacstadium.page.GameMenu;

public class StateChoiceAttack implements StateTrainer{

	private Trainer current;
	private Trainer opponent;
	
	public StateChoiceAttack(Trainer current, Trainer opponent){
		this.current = current;
		this.opponent = opponent;
	}

	@Override
	public GameMenu getContent() {
		return new ChoiceMenu(current, opponent);
	}

	@Override
	public boolean getContinu() {
		return false;
	}

	@Override
	public String toString() {
		return "Choisissez une attaque [1,2,3,4] ou quittez q.";
	}
}
