package imacstadium.game.state;

import imacstadium.display.elements.ChoiceMenu;
import imacstadium.display.elements.ToolBarPanel;
import imacstadium.game.Trainer;
/**
 * <b>StateChoiceAttack</b>
 * <p>Generate the attack menu. This menu permit at the player to choice the imac attack.</p>
 * @see StateTrainer
 */
public class StateChoiceAttack implements StateTrainer{

	private Trainer current;
	private Trainer opponent;
	
	/**
	 * @param current
	 * 	The current trainer.
	 * @param opponent
	 * 	The current opponent.
	 */
	public StateChoiceAttack(Trainer current, Trainer opponent){
		this.current = current;
		this.opponent = opponent;
	}

	@Override
	public ToolBarPanel getContent() {
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
