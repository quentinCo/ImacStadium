package imacstadium.game;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import imacstadium.page.*;
import imacstadium.parser.Parser;
import imacstadium.display.MainScreen;
import imacstadium.display.SplashScreen;
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
	private JFrame page;
	/**
	 * It's an object that allow to parse the json file and generate the imacHeader list and the imac.
	 * @see Parser
	 */
	private Parser parser;
	
	private SplashScreen splash;
	
	/*-----CONSTRUCTOR-------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	private Game() {
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//On crée une instance de JWindow
				splash = new SplashScreen();
			}
		});
		try{
			Thread.sleep(5000);
		}catch(InterruptedException e){}
		
		splash.dispose(); //????????????????????
		
		this.url_imacs = "/data/setting/Imac_List.json";
		this.trainers = new Trainer[2];
		Trainer player = new Trainer();
		Trainer ia = new IA();
		
		trainers[0] = player;
		trainers[1] = ia;
		
		this.parser = new Parser(this.url_imacs);
		this.imacs = parser.parseFile();
		
		this.distributeImac(this.trainers[1]);
	
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
		if(instance == null)instance = new Game();
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
	
	/*------------SET NAME PLAYER--------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Set the name of the player
	 * @param name
	 * 	The new name.
	 */
	public void setNamePlayer(String name) { trainers[0].setName(name); }
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
	public JFrame getPage() { return page; }
	/**
	 * Set the value of the Game page
	 * @param page
	 * 	Page that corresponds at the new current page.
	 * @see Page
	 */
	public void setPage(JFrame page) { this.page = page; }
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
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//On crée une instance de JWindow
				page = new MainScreen();
				//SelectScreen screen2 = new SelectScreen("Test");
				//BattleScreen screen3 = new BattleScreen("Test");
			}
		});
		//page.display();
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
		ArrayList<Imac> imacs = new ArrayList<Imac>();
		imacs.add(this.parser.find(idImac)); 
		trainer.setImacs(imacs);
	}
	/*-----------------------------------------------------------------------------------------------*/
	

	/*------------RESET IMACS PLAYER-----------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Set the array of imacs of the player at null
	 *
	 */
	public void resetImacsPlayer() { trainers[0].setImacs(null); }
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------ADD IMACS PLAYER-----------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Add an imac at imacs Player
	 * @param idImac
	 * 	The id of the new imac to add at player list imac.
	 */
	public void addImacsPlayer(int idImac) {
		Imac imac = this.parser.find(idImac);
		trainers[0].addImac(imac); 
	}
	/*-----------------------------------------------------------------------------------------------*/
	

	@Override
	public String toString() {
		return "Game [url_imacs=" + url_imacs + ", imacs=" + imacs + ", trainers=" + Arrays.toString(trainers)
				+ ", page=" + page + ", parser=" + parser + "]";
	}
	
	
}
