package imacstadium.page;

import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import imacstadium.game.IA;
import imacstadium.game.Trainer;
import imacstadium.game.state.StateTrainer;

public class Arena extends Page implements Observer{

	private Trainer trainers[];
	private int idCurrentTrainer;
	private GameMenu gameMenu;
	
	
	/*-----CONSTRUCTOR-------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Initialise a windows and display the different element of the window (imac, action and choice menu).
	 * @param trainers
	 * 	An array of trainer.
	 */
	public Arena(Trainer[] trainers) {
		super("Arena");
		this.add(new JLabel("ARENA"));
		
		this.trainers = trainers;
		for(Trainer trainer : trainers) trainer.addObserver(this);
		
		this.idCurrentTrainer = -1;
		
		this.gameMenu = new GameMenu("Ouh La La, ça commence.");
		this.displayGameMenu();
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*-----OTHER FUNCTIONS---------------------------------------------------------------------------*/
	/*------------SELECT WHO BEGIN-------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	private void selectWhoBegin(){
		int deltaChoseTrainer = (int)(Math.random()*2);
		System.out.println("deltaChoseTrainer = "+deltaChoseTrainer);
		this.idCurrentTrainer = deltaChoseTrainer%2;
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------CHANGE CURRENT TRAINER-------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	private void changeCurrentTrainer(){
		this.idCurrentTrainer = (this.idCurrentTrainer+1)%2;
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*
	private void displayGamePlayer(){
		
	}
	
	private void displayTextPart(){
		
	}
	*/
	
	/*------------FIGHT------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	private void fight(){
		System.out.println("------------- TURN OF "+this.trainers[this.idCurrentTrainer].getName()+" -------------");
		this.trainers[this.idCurrentTrainer].play(this.trainers[(this.idCurrentTrainer+1)%2]);
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------DISPLAY----------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Select who begin to play and run the fight.
	 */
	public void display(){
		if(this.idCurrentTrainer == -1)this.selectWhoBegin();
		this.fight();
	}
	/*-----------------------------------------------------------------------------------------------*/

	
	/*------------UPDATE-----------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Gestion of the displaying of the different text parts and menus in function of the action. And run the next turn of game.
	 * @param o
	 * 	An observable object source of the notification.
	 * @param arg
	 * 	The name of the action.
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		Trainer source = (Trainer)o;
		StateTrainer state = source.getState();

		this.removeGameMenu();

		if(gameMenu instanceof KeyListener){ this.removeKeyListener((KeyListener)gameMenu);}
		
		gameMenu = state.getContent();
		System.out.println(state);
		
		if(gameMenu instanceof KeyListener){ this.addKeyListener((KeyListener)gameMenu);}
		
		this.displayGameMenu();
		
		if(state.getContinu()){
			if(!source.defeated() || (source instanceof IA)){
				this.changeCurrentTrainer();
				this.fight();
			}
		}
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	private void removeGameMenu(){
		this.getContentPane().removeAll();
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	private void displayGameMenu(){
		this.getContentPane().add(gameMenu,BorderLayout.NORTH);
		//this.getContentPane().revalidate();
		SwingUtilities.updateComponentTreeUI(this);
	}
}
