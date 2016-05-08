package imacstadium.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import imacstadium.game.state.StateAttack;
import imacstadium.game.state.StateAttacked;
import imacstadium.game.state.StateChoiceAttack;
import imacstadium.game.state.StateDead;
import imacstadium.game.state.StateDefeat;
import imacstadium.game.state.StateFailAttack;
import imacstadium.game.state.StateTrainer;
import imacstadium.game.state.StateChangeImac;
import imacstadium.imac.Attack;
import imacstadium.imac.Imac;
import imacstadium.imac.exception.AttackFailExeception;

public class Trainer extends Observable{

	protected ArrayList<Imac> imacs;
	protected ArrayList<Imac> validImacs;
	protected String name;
	protected Imac currentImac;
	protected int score;
	protected StateTrainer state;
	
	
	public static enum TypeNotification{
		ATTACKED, CHANGE_IMAC
	}
	
	/*-----CONSTRUCTOR-------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	public Trainer (){
		this.name = "Esipe";
		
		this.imacs = new ArrayList<Imac>();
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
	public ArrayList<Imac> getImacs(){ return new ArrayList<Imac>(this.imacs); }
	/**
	 * Set the trainer imacs, the valids imacs and the current imac used.
	 * @param imacs
	 * 	An array of Imac.
	 */
	public void setImacs(ArrayList<Imac> imacs){
		this.imacs = imacs;
		if(imacs != null){
			this.validImacs = new ArrayList<Imac>(imacs);
			this.currentImac = this.validImacs.get(0);
		}
		else{
			this.validImacs = null;
			this.currentImac = null;
		}
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
	
	/*------------GET CURRENT IMAC ATTACK------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Return the current imac attack at an index.
	 * @return attack at index id
	 * 	The current imac attack at the id.
	 */
	public Attack getCurrentImacAttack(int id){ return this.currentImac.getAttack(id); }
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------GET CURRENT IMAC IMAGE-------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Return the current imac url image at an index.
	 * @return The url of the image that illustrate the current imac
	 * 	A Strin, that is the url of the image that illustrate the current imac.
	 */
	public String getCurrentImacImage(){ return this.currentImac.getUrlImg(); }
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
	/*------------ADD IMAC--------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Return the trainer state.
	 * @return The current state of the trainer.
	 */
	public void addImac(Imac imac){
		if(this.imacs == null)this.imacs = new ArrayList<Imac>();
		this.imacs.add(imac);
		this.validImacs = new ArrayList<Imac>(imacs);
		if(imacs.size() == 1) this.currentImac = this.validImacs.get(0);
	}
	/*-----------------------------------------------------------------------------------------------*/
	
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
		boolean live = true;
		try{
			live = opponent.imacDamage(currentImac.attack(attackId, opponent.getCurrentImac()));
			if(!live)this.score++;
		}
		catch(AttackFailExeception e){
			state = new StateFailAttack(name);
			this.notifyArena();
		}
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
			this.notifyArena(TypeNotification.ATTACKED);
		}
		else{
			state = new StateAttacked(name, currentImac.getLife());
			this.notifyArena(TypeNotification.ATTACKED);
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
		System.out.println("DEFEATED --> "+name);
		if( validImacs.size() <= 0){
			state = new StateDefeat(name,score);
			this.notifyArena();
			return true;
		}
		else if(validImacs.size() > 0 && !this.currentImac.isAlive()){
			this.changeImac();
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
		this.currentImac = validImacs.get(0);
		state = new StateChangeImac(name, currentImac.getName(),currentImac.getCatchPhrase());
		this.notifyArena(TypeNotification.CHANGE_IMAC);
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------NOTIFY------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Notify the observer.
	 * @param arg
	 * 	The type of notification.
	 */
	public void notifyArena(TypeNotification arg){
		this.setChanged();
		this.notifyObservers(arg);
		this.clearChanged();
	}
	
	public void notifyArena(){
		this.setChanged();
		this.notifyObservers(null);
		this.clearChanged();
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentImac == null) ? 0 : currentImac.hashCode());
		result = prime * result + ((imacs == null) ? 0 : imacs.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + score;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		if (imacs == null) {
			if (other.imacs != null)
				return false;
		} else if (!imacs.equals(other.imacs))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (score != other.score)
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
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
		return "Trainer [\n\timacs=" + imacs + "\n\tvalidImacs=" + validImacs + "\n\tname=" + name + "\n\tcurrentImac="
				+ currentImac + "\n\tscore=" + score + "\n\tstate=" + state + "\n]";
	}
	
	
	
}
