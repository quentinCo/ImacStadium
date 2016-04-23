package imacstadium.game;

import imacstadium.page.*;

public class Game {

	private static Game instance = null;
	private String url_imacs;
	/*private ImacHeader imacs;
	private Trainer player;
	private Trainer ia;*/
	private Page page;
	private boolean execute;

	private String testSentence;
	
	/* Costructor */
	private Game() {
		this.testSentence = "Phrase de base";
		
		this.url_imacs = "./data/test.json";
	/*	this.imacs = //////////////////////////;
		this.player = new Trainer();
		this.ia = new Trainer();*/
		this.page = new PageTest();
		System.out.println("PO");
		this.execute = true;
	}
	
	
	/* Getter -- Setter */
	public static Game getInstance() {
		if(instance == null) instance = new Game();
		return instance; 
	}
	
/*	public ImacHeader getImacs() { return imacs; }
	
	public Trainer getPlayer() { return player; }
	
	public Trainer getIa() { return ia; }
	
	public Page getPage() { return page; }
	public void setPage(Page page) { this.page = page; }
	*/
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
		page.render();
	}
	
}
