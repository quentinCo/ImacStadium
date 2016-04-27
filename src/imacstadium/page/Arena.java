package imacstadium.page;

import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import imacstadium.game.IA;
import imacstadium.game.Trainer;

public class Arena extends Page implements Observer{

	private Trainer trainers[];
	private int idCurrentTrainer;
	private GameMenu gameMenu;
	
	public Arena(Trainer[] trainers) {
		super("Arena");
		this.add(new JLabel("ARENA"));
		
		this.trainers = trainers;
		for(Trainer trainer : trainers) trainer.addObserver(this);
		
		this.idCurrentTrainer = -1;
		
		this.gameMenu = new GameMenu("Ouh La La, ça commence.");
		this.displayGameMenu();
	}
	
	private void selectWhoBegin(){
		int deltaChoseTrainer = (int)(Math.random()*2);
		System.out.println("deltaChoseTrainer = "+deltaChoseTrainer);
		this.idCurrentTrainer = deltaChoseTrainer%2;
	}
	
	private void changeCurrentTrainer(){
		this.idCurrentTrainer = (this.idCurrentTrainer+1)%2;
	}
	/*
	private void displayGamePlayer(){
		
	}
	
	private void displayTextPart(){
		
	}
	*/
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

		this.removeGameMenu();

		if(gameMenu instanceof KeyListener){ this.removeKeyListener((KeyListener)gameMenu); }
		
		switch (event){
			case "attack":
				gameMenu = new GameMenu("L'Imac de "+source.getName()+" attaque.");
				System.out.println("L'Imac de "+source.getName()+" attaque.");
				//if(!this.trainers[this.idCurrentTrainer].defeated() || (this.trainers[this.idCurrentTrainer] instanceof IA)) this.fight();
				break;
			case "attacked":
				gameMenu = new GameMenu("La vie de l'Imac de "+source.getName()+" n'est plus que de "+source.currentLife());
				System.out.println("La vie de l'Imac de "+source.getName()+" n'est plus que de "+source.currentLife());
				//if(!this.trainers[this.idCurrentTrainer].defeated() || (this.trainers[this.idCurrentTrainer] instanceof IA)) this.fight();
				break;
			case "dead":
				gameMenu = new GameMenu("L'Imac de "+source.getName()+" est vaincu.");
				System.out.println("L'Imac de "+source.getName()+" est vaincu.");
				//this.turn = true;
				break;
			case "choice":
				gameMenu = new ChoiceMenu(source, this.trainers[(this.idCurrentTrainer+1)%2]);
				this.addKeyListener((KeyListener)gameMenu);
				break;
			case "defeat":
				gameMenu = new GameMenu(source.getName()+" à perdu.");
				System.out.println(source.getName()+" à perdu.");
				break;
			case "changeImac":
				gameMenu = new GameMenu(source.getName()+ " appelle "+source.getCurrentImac().getName()+" .\n"+source.getCurrentImac().getCatchPhrase());
				System.out.println(source.getName()+ " appelle "+source.getCurrentImac().getName()+" .\n"+source.getCurrentImac().getCatchPhrase());
		}
		
		this.displayGameMenu();
		
		if(event == "attacked" || event == "dead"){
			if(!source.defeated() || (source instanceof IA)){
				this.changeCurrentTrainer();
				this.fight();
			}
		}
	}
	
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
