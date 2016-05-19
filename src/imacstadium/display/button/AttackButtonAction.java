package imacstadium.display.button;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import imacstadium.game.Trainer;

/**
 * <b>AttackButtonAction</b>
 * <p>
 * Button permit to activate an attack.
 * </p>
 * @see Trainer
 */
public class AttackButtonAction  extends AbstractAction{
	
	private Trainer current;
	private Trainer opponnent;
	private int idAttack;

	/**
	 * Generate a button attack
	 * @param current
	 * 	The trainer, the player.
	 * @param opponnent
	 * 	The opponent, the current target of the attack.
	 * @param attack
	 * 	The name of the attack.
	 * @param idAttack
	 * 	The index of the attack in the imac's array attack.
	 */
	public AttackButtonAction(Trainer current, Trainer opponnent, String attack, int idAttack) {
		super(attack);
		this.current = current;
		this.opponnent = opponnent;
		this.idAttack = idAttack;
	}

	/**
	 * Activate the attack process.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//System.out.println(this.current.getCurrentImacAttack(idAttack).getName());
		this.current.imacAttack(this.opponnent, idAttack);
	}
}
