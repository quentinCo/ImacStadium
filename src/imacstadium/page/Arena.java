package imacstadium.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import imacstadium.game.Game;
import imacstadium.game.Trainer;

public class Arena extends Page {

	//private ArrayList<GameMenu> gameMenu;

	private Trainer trainers[];
	private int idCurrentTrainer;
	
	public Arena(Trainer[] trainers) {
		super("Arena");
		//gameMenu = new ArrayList<GameMenu>();
		this.trainers = Arrays.copyOf(trainers, 2);
		this.idCurrentTrainer = -1;
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
		this.trainers[this.idCurrentTrainer].play();
		this.changeCurrentTrainer();
	}

	public void update(){
		if(this.idCurrentTrainer == -1)this.selectWhoBegin();
		this.fight();
	}
	
}
