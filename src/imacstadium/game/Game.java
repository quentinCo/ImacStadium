package imacstadium.game;

import java.util.ArrayList;
import java.util.Arrays;

import imacstadium.page.*;
import imacstadium.parser.Parser;
import imacstadium.imac.Imac;
import imacstadium.imac.ImacHeader;


/**
 * <b>Game</b>
 * <p>
 * Game is a singleton.
 * It initialise the Imacs, run the game and manage the Page.
 * </p>
 * @see ImacHeader
 * @see Trainer
 * @see Page
 * @see Parser
 */
public class Game {

	/**
	 * It's the instance of the Game.
	 */
	private static Game instance = null;
	/**
	 * It's the url of the json that contains the imac list.
	 */
	private String url_imacs;
	/**
	 * It contains the main information to indentify and to create a complete imac.
	 * @see ImacHeader
	 */
	private ArrayList<ImacHeader> imacs;
	/**
	 * It contains the two trainers (the player and the IA).
	 * @see Trainer
	 */
	private Trainer trainers[];
	/**
	 * It's the page displayed.
	 * @see Page
	 */
	private Page page;
	/**
	 * It's an object that allow to parse the json file and generate the imacHeader list and the imac.
	 * @see Parser
	 */
	private Parser parser;
	
	/*-----CONSTRUCTOR-------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	private Game() {		
		this.url_imacs = "/data/setting/Imac_List.json";
		this.trainers = new Trainer[2];
		Trainer player = new Trainer();
		Trainer ia = new IA();
		
		trainers[0] = player;
		trainers[1] = ia;
		
		this.page = new Arena(trainers);
		
		this.parser = new Parser(this.url_imacs);
		this.imacs = parser.parseFile();
		
		this.initPlayer();
		//System.out.println(trainers[0].getName()+"\n"+trainers[0]+"\n-----------------\n");
		//System.out.println(trainers[1].getName()+"\n"+trainers[1]+"\n-----------------\n");
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*-----GETTER -SETTER----------------------------------------------------------------------------*/
	/*------------GET INSTANCE-----------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Return the static instance of Game.
	 * @return The Game instance. If it doesn't exist, it is create.
	 */
	public static Game getInstance() {
		if(instance == null) instance = new Game();
		return instance; 
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------GET IMACS--------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Return the ImacHeader list.
	 * @return The list of the main information to identify and recreate an imac.
	 */
	public ArrayList<ImacHeader> getImacs() { return imacs; }
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------GET PLAYER-------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Return the player.
	 * @return The Trainer that corresponds at the game player.
	 */
	public Trainer getPlayer() { return trainers[0]; }
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------GET IA-----------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Return the AI.
	 * @return The Trainer that corresponds at the game ai.
	 */
	public Trainer getIa() { return trainers[1]; }
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------GET TRAINERS-----------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Return the trainers array.
	 * @return The array of Trainer contains the player at index 0, and the ai at index 1.
	 */
	public Trainer[] getTrainers(){ return this.trainers; }
	//public Trainer getTrainer(int id){ return trainers[id]; }
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------GET - SET PAGE---------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Return the current Page.
	 * @return The page that corresponds at the current page (Arena, ...).
	 */
	public Page getPage() { return page; }
	/**
	 * Set the value of the Game page
	 * @param page
	 * 	Page that corresponds at the new current page.
	 * @see Page
	 */
	public void setPage(Page page) { this.page = page; }
	/*-----------------------------------------------------------------------------------------------*/


	/*-----OTHER FUNCTIONS---------------------------------------------------------------------------*/
	/*------------EXECUTE----------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Execute the program and call the page display function.
	 * @see Page#display()
	 */
	public void execute(){
		//while(execute){ page.update(); }
		page.display();
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------INIT PLAYER------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	private void initPlayer(){
		this.trainers[0].setName("IMAC");
		this.distributeImac(this.trainers[0]);
		this.distributeImac(this.trainers[1]);
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------DISTRIBUTE IMAC--------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Initialise the trianer imac list. He distributed randomly an imac to the trainer.
	 * @param trainer
	 * 	The trainer at which you want give an imac.
	 * @see Trainer
	 * @see Parser#find(int)
	 */
	public void distributeImac(Trainer trainer){
		double random = Math.random()*imacs.size();
		int idImac = (int)(random);
		Imac imacsPlayer[] = {this.parser.find(idImac)}; 
		trainer.setImacs(imacsPlayer);
	}
	/*-----------------------------------------------------------------------------------------------*/
	

	@Override
	public String toString() {
		return "Game [url_imacs=" + url_imacs + ", imacs=" + imacs + ", trainers=" + Arrays.toString(trainers)
				+ ", page=" + page + ", parser=" + parser + "]";
	}
	
	
}
