package imacstadium.game;

import java.util.ArrayList;
import java.util.Arrays;

import imacstadium.page.*;
import imacstadium.imac.Imac;

public class Game {

	private static Game instance = null;
	private String url_imacs;
	/*private ArrayList<ImacHeader> imacs;*/
	private ArrayList<Imac> imacs;
	private Trainer trainers[];
	private Page page;
	private boolean execute;

	private String testSentence;
	
	/* Costructor */
	private Game() {
		this.testSentence = "Phrase de base";
		
		this.url_imacs = "./data/test.json";
		this.trainers = new Trainer[2];
		Trainer player = new Trainer(){
			public void play(){
				int idAttack = (int)(Math.random()*3);
				this.imacAttack(Game.getInstance().getIa(), idAttack);
			}
		};
		Trainer ia = new Trainer(){
			public void play(){
				int idAttack = (int)(Math.random()*3);
				this.imacAttack(Game.getInstance().getPlayer(), idAttack);
			}
		};
		
		trainers[0] = player;
		trainers[1] = ia;
		
		this.page = new Arena(trainers);
		this.execute = true;
		
		this.initImacs();
		this.initPlayer();
	}
	
	
	/* Getter -- Setter */
	public static Game getInstance() {
		if(instance == null) instance = new Game();
		return instance; 
	}
	
/*	public ImacHeader getImacs() { return imacs; }*/
	
	public Trainer getPlayer() { return trainers[0]; }
	
	public Trainer getIa() { return trainers[1]; }
	
	public Trainer[] getTrainers(){ return Arrays.copyOf(this.trainers, 2); }
	//public Trainer getTrainer(int id){ return trainers[id]; }
	
	//public boolean getPage() { return page; }
	public void setPage(Page page) { this.page = page; }

	public boolean isExecute() { return execute; }
	public void setExecute(boolean execute) { this.execute = execute; }

/*----------------------------------------------------------------------------*/
	public String getTestSentence() {
		return testSentence;
	}


	public void setTestSentence(String testSentence) {
		this.testSentence = testSentence;
	}
/*----------------------------------------------------------------------------*/

	/* Functions */
	public void execute(){
		while(execute){
			update();
		}
	}
	private void update(){
		page.update();
	}

		/* - Functions temp - */
	private void initImacs (){
		imacs = new ArrayList<Imac>();
		imacs.add(new Imac(10, "CouCou", 1, (float)0.1));
		imacs.add(new Imac(15, "BayBay", 1, (float)0.1));
		imacs.add(new Imac(25, "DoDo", 1, (float)0.1));
		imacs.add(new Imac(15, "HinHin", 1, (float)0.9));
	}
	private void initPlayer(){
		this.trainers[0].setName("IMAC");
		this.distributeImac(this.trainers[0]);
		this.distributeImac(this.trainers[1]);
	}
	private void distributeImac(Trainer player){
		int idImac = (int)(Math.random()*(imacs.size()-1));
		Imac imacsPlayer[] = {this.imacs.get(idImac)}; 
		player.setImacs(imacsPlayer);
	}
	
	

	/* Dream Team Function */
	@Override
	public String toString() {
		return "Game [\n\turl_imacs=" + url_imacs + "\n\tpage= [\n\t" + page + "\n\t]\n\texecute=" + execute + "\n\ttestSentence="
				+ testSentence + "\n]";
	}
	
	
}
