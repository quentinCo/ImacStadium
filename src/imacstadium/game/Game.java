package imacstadium.game;

import java.util.ArrayList;
import java.util.Arrays;

import imacstadium.page.*;
import imacstadium.parser.Parser;
import imacstadium.imac.Imac;
import imacstadium.imac.ImacHeader;

public class Game {

	private static Game instance = null;
	private String url_imacs;
	private ArrayList<ImacHeader> imacs;
	//private ArrayList<Imac> imacs;
	private Trainer trainers[];
	private Page page;
	//private boolean execute;
	private Parser parser;

	private String testSentence;
	
	/* Costructor */
	private Game() {
		this.testSentence = "Phrase de base";
		
		this.url_imacs = "/data/setting/Imac_List.json";
		this.trainers = new Trainer[2];
		Trainer player = new Trainer();
		Trainer ia = new IA();
		
		trainers[0] = player;
		trainers[1] = ia;
		
		this.page = new Arena(trainers);
		//this.execute = true;
		
		this.parser = new Parser(this.url_imacs);
		this.imacs = parser.parseFile();
		
		//this.initImacs();
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
	
	public Trainer[] getTrainers(){ return this.trainers; }
	//public Trainer getTrainer(int id){ return trainers[id]; }
	
	public Page getPage() { return page; }
	public void setPage(Page page) { this.page = page; }
/*
	public boolean isExecute() { return execute; }
	public void setExecute(boolean execute) { this.execute = execute; }
*/
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
		//while(execute){ page.update(); }
		page.display();
	}
	/*private void update(){
		page.update();
	}*/

		/* - Functions temp - */
	/*private void initImacs (){
		
		imacs = new ArrayList<Imac>();
		imacs.add(new Imac(10, "CouCou", 1, (float)0.1));
		imacs.add(new Imac(15, "BayBay", 1, (float)0.1));
		imacs.add(new Imac(25, "DoDo", 1, (float)0.1));
		imacs.add(new Imac(15, "HinHin", 1, (float)0.9));
	}*/
	private void initPlayer(){
		this.trainers[0].setName("IMAC");
		this.distributeImac(this.trainers[0]);
		this.distributeImac(this.trainers[1]);
	}
	public void distributeImac(Trainer player){
		double random = Math.random()*imacs.size();
		int idImac = (int)(random);
		/*
		 * System.out.println("random ==> "+random);
		 * System.out.println("idImac ==> "+idImac);
		 */
		Imac imacsPlayer[] = {this.parser.find(idImac)}; 
		player.setImacs(imacsPlayer);
	}
	
	

	@Override
	public String toString() {
		return "Game [url_imacs=" + url_imacs + ", imacs=" + imacs + ", trainers=" + Arrays.toString(trainers)
				+ ", page=" + page + ", parser=" + parser + ", testSentence=" + testSentence + "]";
	}
	
	
}
