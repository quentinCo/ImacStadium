package imacstadium.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;

//import imacstadium.imac;

public class Trainer {

	private String[] imacs;
	private ArrayList<String> validImacs;
	private String name;
	private String currentImac;
	private int score;
	
	/* Costructor */
	public Trainer (String name){
		this.name = "Esipe";
		
		this.imacs = new String[3];
		this.validImacs = new ArrayList<String>();
		int score = 0;		
	}
	
	/* Getter -- Setter */
	public String[] getImacs(){ return Arrays.copyOf(this.imacs, 3); }
	public void setImacs(String[] imacs){
		this.imacs = imacs;
		//this.validImacs = 
	}
	
	public ArrayList<String> getValidImacs(){ return (ArrayList)this.validImacs.clone(); }
	public void setValidImacs(ArrayList<String> imacs){ this.validImacs = imacs; }
	//public void setValidImac(String[] imacs){ this.validImacs = imacs; }
	
	public String getName(){ return this.name; }
	public void setName(String newName){ this.name = newName;} 
	
	public String getCurrentImac(){ return this.currentImac; }
	public void setCurrentImac(String imac){ this.currentImac = imac; }
	
	public int getScore(){ return this.score; }
	
	/* Functions */	
	public void changeImac(String imac){
		/* A VOIR */
	}
	
	//public void removeValidImac()
	
	public boolean imacAttack(Trainer otherPlayer, int attackId){
		/* Verif résultat combat
		 * otherPlayer.imacDamage(currentImac.attack(attackId,otherPlayer.currentType()));
		 */
		return true;
	}
	
	public boolean imacDamage(float damage){
		/*
		 * currentImac.damage(damage);
		 * 
		 */
		return false;
	}

	public boolean defeated(){
		/*
		 * 
		 */
		return false;
	}
	
	public boolean currentAlive(){
		/*
		 * 
		 */
		return false;
	}
	
	public String currentType(){
		/*
		 * 
		 */
		return "";
	}
	
}
