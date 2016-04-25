package imacstadium.page;

import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;

import imacstadium.game.Game;
import imacstadium.game.IA;
import imacstadium.game.Trainer;

public class Arena extends Page implements Observer{

	//private ArrayList<GameMenu> gameMenu;

	private Trainer trainers[];
	private int idCurrentTrainer;
	private GameMenu gameMenu;
	private boolean turn;
	
	public Arena(Trainer[] trainers) {
		super("Arena");
		//gameMenu = new ArrayList<GameMenu>();
		this.trainers = trainers;
		for(Trainer trainer : trainers) trainer.addObserver(this);
		
		this.idCurrentTrainer = -1;
		
		this.gameMenu = new GameMenu("Ouh La La, ça commence.");
		this.displayGameMenu();
		
		this.turn = false;
	}
	
	private void selectWhoBegin(){
		int deltaChoseTrainer = (int)(Math.random()*2);
		System.out.println("deltaChoseTrainer = "+deltaChoseTrainer);
		this.idCurrentTrainer = deltaChoseTrainer%2;
	}
	
	private void changeCurrentTrainer(){
		this.idCurrentTrainer = (this.idCurrentTrainer+1)%2;
	}
	
	private void displayGamePlayer(){
		
	}
	
	private void displayTextPart(){
		
	}
	
	private void fight(){
		System.out.println("------------- TURN OF "+this.trainers[this.idCurrentTrainer].getName()+" -------------");
		this.trainers[this.idCurrentTrainer].play(this.trainers[(this.idCurrentTrainer+1)%2]);
	}
	
	public void display(){
		if(this.idCurrentTrainer == -1)this.selectWhoBegin();
		this.fight();
	}

	@Override
	public void update(Observable o, Object arg) {
		//System.out.println("----------------------------------------------------------------------->OK");
		String event = (String)arg;
		Trainer source = (Trainer)o;

		this.remove(gameMenu);
		if(gameMenu instanceof KeyListener){
			System.out.println("--------------------------------->REMOVE");
			this.removeKeyListener((KeyListener)gameMenu);
		}
		
		switch (event){
			case "attack":
				gameMenu = new GameMenu("L'Imac de "+source.getName()+" attaque.\n\nLa vie de l'Imac de "+this.trainers[(this.idCurrentTrainer+1)%2].getName()+" n'est plus que de "+this.trainers[(this.idCurrentTrainer+1)%2].currentLife());
				this.changeCurrentTrainer();
				if(!this.trainers[this.idCurrentTrainer].defeated() || (this.trainers[this.idCurrentTrainer] instanceof IA)) this.fight();
				break;
			case "dead":
				gameMenu = new GameMenu("L'Imac de "+source.getName()+" est mort.");
				//this.turn = true;
				break;
			case "choice":
				gameMenu = new ChoiceMenu(source, this.trainers[(this.idCurrentTrainer+1)%2]);
				this.addKeyListener((KeyListener)gameMenu);
				break;
			case "defeat":
				gameMenu = new GameMenu(source.getName()+" à perdu.");
				break;
		}
		this.displayGameMenu();
	}
	
	private void displayGameMenu(){
		this.add(gameMenu,BorderLayout.NORTH);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	
}
