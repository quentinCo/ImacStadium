package imacstadium.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import imacstadium.imac.Imac;

public class Trainer extends Observable{

	protected Imac[] imacs;
	protected ArrayList<Imac> validImacs;
	protected String name;
	protected Imac currentImac;
	protected int score;
	
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
	public Imac[] getImacs(){ return Arrays.copyOf(this.imacs, 3); }
	public void setImacs(Imac[] imacs){
		this.imacs = imacs;
		this.validImacs = new ArrayList<Imac>(Arrays.asList(imacs));
		this.currentImac = this.validImacs.get(0);
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------GET - SET VALID IMACS--------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	public ArrayList<Imac> getValidImacs(){ return new ArrayList<Imac>(this.validImacs); }
	public void setValidImacs(ArrayList<Imac> imacs){
		this.validImacs = imacs;
		this.currentImac = this.validImacs.get(0);
	}
	public void setValidImac(Imac[] imacs){
		this.validImacs = new ArrayList<Imac>(Arrays.asList(imacs));
		this.currentImac = this.validImacs.get(0);
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------GET - SET NAME---------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	public String getName(){ return this.name; }
	public void setName(String newName){ this.name = newName;} 
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------GET - SET CURRENT IMAC-------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	public Imac getCurrentImac(){ return this.currentImac; }
	public void setCurrentImac(Imac imac){ this.currentImac = imac; }
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------GET SCORE--------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	public int getScore(){ return this.score; }
	/*-----------------------------------------------------------------------------------------------*/
	
	/*-----OTHER FUNCTIONS---------------------------------------------------------------------------*/
	/*------------PLAY-------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	public void play(Trainer opponent){
		this.displayChoseMenu(opponent);
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------DISPLAY CHOSE MENU-----------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	private void displayChoseMenu(Trainer opponent){
		this.notify("choice");
	}
	/*-----------------------------------------------------------------------------------------------*/

	/*------------IMAC ATTACK------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	public boolean imacAttack(Trainer otherPlayer, int attackId){
		this.notify("attack");
		return otherPlayer.imacDamage(currentImac.attack(attackId, otherPlayer.currentType()));
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------IMAC DAMAGE------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	public boolean imacDamage(float damage){
		boolean live;
		currentImac.damage(damage);
		
		live = currentImac.isAlive();
		if(!live){
			validImacs.remove(currentImac);
			this.notify("dead");
		}
		else{
			this.notify("attacked");
		}
		return live;
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------DEFEATED---------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	public boolean defeated(){
		if( validImacs.size() <= 0){
			this.notify("defeat");
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
	public String currentType(){ return currentImac.getType(); }
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------CURRENT LIFE-----------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	public float currentLife(){ return this.currentImac.getLife(); }
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------CHANGE IMAC------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/*public void changeImac(Imac imac){
		if(imac.isAlive()){
			this.currentImac = imac;
			this.notify("changeImac");
		}
	}*/
	public void changeImac(){
		if(!this.defeated()){
			this.currentImac = validImacs.get(0);
			this.notify("changeImac");
		}
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------NOTIFY------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	public void notify(String arg){
		this.setChanged();
		this.notifyObservers(arg);
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
