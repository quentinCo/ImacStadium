package imacstadium.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import imacstadium.game.state.StateAttack;
import imacstadium.game.state.StateAttacked;
import imacstadium.game.state.StateChoiceAttack;
import imacstadium.game.state.StateDead;
import imacstadium.game.state.StateDefeat;
import imacstadium.game.state.StateTrainer;
import imacstadium.game.state.StateChangeImac;
import imacstadium.imac.Imac;

public class Trainer extends Observable{

	protected Imac[] imacs;
	protected ArrayList<Imac> validImacs;
	protected String name;
	protected Imac currentImac;
	protected int score;
	protected StateTrainer state;
	
	/*
	public static enum State{
		ATTACK, ATTACKED, DEAD, CHOICE_ATTACK, DEFEAT, CHANGE_IMAC
	}
	*/
	/*-----CONSTRUCTOR-------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	public Trainer (){
		this.name = "Esipe";
		
		this.imacs = new Imac[3];
		this.validImacs = new ArrayList<Imac>();
		score = 0;		
	}
	public Trainer (String name){
		this.name = name;
		
		this.imacs = new Imac[3];
		this.validImacs = new ArrayList<Imac>();
		score = 0;		
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*-----GETTER - SETTER---------------------------------------------------------------------------*/
	/*------------GET - SET IMACS--------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Return the trainer imacs
	 * @return The array that contains the different trainer imac
	 */
	public Imac[] getImacs(){ return Arrays.copyOf(this.imacs, 3); }
	/**
	 * Set the trainer imacs, the valids imacs and the current imac used.
	 * @param imacs
	 * 	An array of Imac.
	 */
	public void setImacs(Imac[] imacs){
		this.imacs = imacs;
		this.validImacs = new ArrayList<Imac>(Arrays.asList(imacs));
		this.currentImac = this.validImacs.get(0);
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------GET - SET VALID IMACS--------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Return the live trainer imac.
	 * @return An ArrayLsit that contains the imac who are alive.
	 */
	public ArrayList<Imac> getValidImacs(){ return new ArrayList<Imac>(this.validImacs); }
	/**
	 * Set the valid imac and initalize the current imac at the first from the valid imac.
	 * @param imacs
	 * 	An ArrayList of imac.
	 */
	public void setValidImacs(ArrayList<Imac> imacs){
		this.validImacs = imacs;
		this.currentImac = this.validImacs.get(0);
	}
	/**
	 * Set the valid imac and initalize the current imac at the first from the valid imac.
	 * @param imacs
	 * 	An Array of imac.
	 */
	public void setValidImac(Imac[] imacs){
		this.validImacs = new ArrayList<Imac>(Arrays.asList(imacs));
		this.currentImac = this.validImacs.get(0);
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------GET - SET NAME---------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Return the name of the trainer
	 * @return name
	 */
	public String getName(){ return this.name; }
	/**
	 * Set the name of the trainer
	 * @param newName
	 * 	The new name for the trainer.
	 */
	public void setName(String newName){ this.name = newName;} 
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------GET - SET CURRENT IMAC-------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Return the current imac.
	 * @return currentImac
	 * 	The current imac used by the trainer.
	 */
	public Imac getCurrentImac(){ return this.currentImac; }
	/**
	 * Set the current imac used by the trainer.
	 * @param imac
	 * 	The new current imac.
	 */
	public void setCurrentImac(Imac imac){ this.currentImac = imac; }
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------GET SCORE--------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Return the trainer score.
	 * @return The number of defeated opponent.
	 */
	public int getScore(){ return this.score; }
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------GET STATE--------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Return the trainer state.
	 * @return The current state of the trainer.
	 */
	public StateTrainer getState(){ return this.state; }
	/*-----------------------------------------------------------------------------------------------*/
	
	/*-----OTHER FUNCTIONS---------------------------------------------------------------------------*/
	/*------------PLAY-------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Throw a notification to display the choice menu.
	 * @param opponent
	 * 	The trainer who is the opponent.
	 */
	public void play(Trainer opponent){
		state = new StateChoiceAttack(this,opponent);
		this.notifyArena();
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------IMAC ATTACK------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Throw a notification to display the attack text.And change the imac life opponent in function of the current imac attack value.
	 * @param otherPlayer
	 * 	The opponent.
	 * @param attackId
	 * 	The index of the imac attack used.
	 * @return True if the opponent is not dead, or False in the other case.
	 * @see Imac#attack(int, String)
	 */
	public boolean imacAttack(Trainer opponent, int attackId){
		state = new StateAttack(name);
		this.notifyArena();
		boolean live = opponent.imacDamage(currentImac.attack(attackId, opponent.getCurrentImac()));
		if(!live)this.score++;
		return live;
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------IMAC DAMAGE------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Change the life of the current imac in function of the damage, and throw notification to display text for imac death or damage.
	 * @param damage
	 * 	The value subtracts to the imac life.
	 * @return True if the imac is alive after substraction, or False in the other case.
	 */
	public boolean imacDamage(float damage){
		boolean live;
		currentImac.damage(damage);
		
		live = currentImac.isAlive();
		if(!live){
			validImacs.remove(currentImac);
			state = new StateDead(name);
			this.notifyArena();
		}
		else{
			state = new StateAttacked(name, currentImac.getLife());
			this.notifyArena();
		}
		return live;
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------DEFEATED---------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Verify if there is valid imac and throw notification of defeat.
	 * @return True if the trainer defeat, and false in the other case.
	 */
	public boolean defeated(){
		if( validImacs.size() <= 0){
			state = new StateDefeat(name,score);
			this.notifyArena();
			return true;
		}
		return false;
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*public boolean currentAlive(){
		return currentImac.isAlive();
	}*/
	
	/*------------CURRENT TYPE-----------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Return the type of the current imac.
	 * @return The current imac type name.
	 */
	public String currentType(){ return currentImac.getTypeImac(); }
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------CURRENT LIFE-----------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Return the life of the current imac.
	 * @return The current imac life.
	 */
	public float currentLife(){ return this.currentImac.getLife(); }
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------CHANGE IMAC------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/*public void changeImac(Imac imac){
		if(imac.isAlive()){
			this.currentImac = imac;
			this.notify(State.CHANGE_IMAC);
		}
	}*/
	/**
	 * Change the current imac by the first imac of the valids imac.
	 */
	public void changeImac(){
		if(!this.defeated()){
			this.currentImac = validImacs.get(0);
			state = new StateChangeImac(name, currentImac.getName(),currentImac.getCatchPhrase());
			this.notifyArena();
		}
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------NOTIFY------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Notify the observer.
	 * @param arg
	 * 	The type of notification.
	 */
	public void notifyArena(){
		this.setChanged();
		this.notifyObservers();
		this.clearChanged();
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/* Dream Team Functions */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentImac == null) ? 0 : currentImac.hashCode());
		result = prime * result + Arrays.hashCode(imacs);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + score;
		result = prime * result + ((validImacs == null) ? 0 : validImacs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trainer other = (Trainer) obj;
		if (currentImac == null) {
			if (other.currentImac != null)
				return false;
		} else if (!currentImac.equals(other.currentImac))
			return false;
		if (!Arrays.equals(imacs, other.imacs))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (score != other.score)
			return false;
		if (validImacs == null) {
			if (other.validImacs != null)
				return false;
		} else if (!validImacs.equals(other.validImacs))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Trainer [\n\timacs=" + Arrays.toString(imacs) + "\n\tvalidImacs=" + validImacs + "\n\tname=" + name
				+ "\n\tcurrentImac=" + currentImac + "\n\tscore=" + score + "\n]";
	}
	
	
	
}
