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
	private Trainer trainers[];
	private Page page;
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
	public static Game getInstance() {
		if(instance == null) instance = new Game();
		return instance; 
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------GET IMACS--------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	public ArrayList<ImacHeader> getImacs() { return imacs; }
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------GET PLAYER-------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	public Trainer getPlayer() { return trainers[0]; }
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------GET IA-----------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	public Trainer getIa() { return trainers[1]; }
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------GET TRAINERS-----------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	public Trainer[] getTrainers(){ return this.trainers; }
	//public Trainer getTrainer(int id){ return trainers[id]; }
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------GET - SET PAGE---------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	public Page getPage() { return page; }
	public void setPage(Page page) { this.page = page; }
	/*-----------------------------------------------------------------------------------------------*/


	/*-----OTHER FUNCTIONS---------------------------------------------------------------------------*/
	/*------------EXECUTE----------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
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
	public void distributeImac(Trainer player){
		double random = Math.random()*imacs.size();
		int idImac = (int)(random);
		Imac imacsPlayer[] = {this.parser.find(idImac)}; 
		player.setImacs(imacsPlayer);
	}
	/*-----------------------------------------------------------------------------------------------*/
	

	@Override
	public String toString() {
		return "Game [url_imacs=" + url_imacs + ", imacs=" + imacs + ", trainers=" + Arrays.toString(trainers)
				+ ", page=" + page + ", parser=" + parser + "]";
	}
	
	
}
