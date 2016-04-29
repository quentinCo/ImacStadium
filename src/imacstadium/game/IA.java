package imacstadium.game;

import imacstadium.page.StateChangeImac;

public class IA extends Trainer {

	/*-----CONSTRUCTOR-------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	public IA() {
		super();
	}
	public IA(String name) {
		super(name);
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*-----OTHER FUNCTIONS---------------------------------------------------------------------------*/
	/*------------PLAY-------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Chose randomly an attack and attack the opponent.
	 * @param opponent
	 * 	The trainer who is the opponent.
	 * @see Trainer#imacAttack(Trainer, int)
	 */
	@Override
	public void play(Trainer opponent){
		int idAttack = (int)(Math.random()*3);
		this.imacAttack(opponent, idAttack);
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------DEFEATED---------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Verify if there is valid imac. If it's not the case it call changeImac.
	 * @return True if the trainer defeat, and false in the other case.
	 * @see IA#changeImac()
	 */
	@Override
	public boolean defeated() {
		if( validImacs.size() <= 0){
			this.changeImac();
			return true;
		}
		return false;
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------CHANGE IMAC------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Change the current imac in do a new distribution and throw a notification of change.
	 * @see Game#distributeImac(Trainer)
	 */
	@Override
	public void changeImac() {
		Game.getInstance().distributeImac(this);
		state = new StateChangeImac(name, currentImac.getName(),currentImac.getCatchPhrase());
		this.notifyArena();
	}
	/*-----------------------------------------------------------------------------------------------*/

}
