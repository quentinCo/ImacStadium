package imacstadium.display;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import imacstadium.game.Trainer;
import imacstadium.imac.Attack;

public class AttackButtonAction  extends AbstractAction{
	
	private Trainer current;
	private Trainer opponnent;
	private int idAttack;

	public AttackButtonAction(Trainer current, Trainer opponnent, String attack, int idAttack) {
		super(attack);
		this.current = current;
		this.opponnent = opponnent;
		this.idAttack = idAttack;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(this.current.getCurrentImacAttack(idAttack).getName());
		this.current.imacAttack(this.opponnent, idAttack);
	}
}
