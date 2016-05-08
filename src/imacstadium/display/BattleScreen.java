package imacstadium.display;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import imacstadium.game.Game;
import imacstadium.game.IA;
import imacstadium.game.Trainer;
import imacstadium.game.state.StateTrainer;
import imacstadium.page.ChoiceMenu;
import imacstadium.page.ToolBarPanel;

public class BattleScreen extends JFrame implements Observer, KeyListener{
	
	private String player_name, textAction;
	private JButton AttackButton1, AttackButton2, AttackButton3, AttackButton4;
	private Trainer [] trainers;
	private int idCurrentTrainer;
	private JPanel toolBarPanel;
	private JPanel mainPanel;
	private GridBagConstraints mainGbc;
	
	private List next;
	

	public BattleScreen(String name){
		super();
		this.player_name = name;
		this.trainers = Game.getInstance().getTrainers();
		for(Trainer trainer : trainers) trainer.addObserver(this);
		
		this.selectWhoBegin();
		
		build();

		this.next = Collections.synchronizedList(new LinkedList());;
		
		this.fight();
	}

	private void build(){
		setTitle("Imac Stadium"); //On donne un titre Ã  l'application
		setSize(840, 680); //On donne une taille Ã  notre fenÃªtre
		setLocationRelativeTo(null);//On centre la fenÃªtre sur l'Ã©cran
		setResizable(true);//On interdit le redimensionnement de la fenÃªtre

		addKeyListener (this);
		addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) { 
				requestFocus();	
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Termine le processus lorsqu'on clique sur la croix rouge
		
		setContentPane(buildContentPane());//On prÃ©vient notre JFrame que notre JPanel sera son content pane
		
		setVisible(true); //On la rend visible
	}
	
	private JPanel buildContentPane(){
		
		mainPanel = new JPanel();//Instanciation d'un objet JPanel
		mainPanel.setLayout(new GridBagLayout());
		
		mainPanel.setBackground(Color.orange);//Dï¿½finition de sa couleur de fond
		
		/* Ajout de ce composant au container en spï¿½cifiant une contrainte de type GridBagConstraints. */
		mainGbc = new GridBagConstraints();
		mainGbc.gridwidth=GridBagConstraints.REMAINDER;
		
		this.toolBarPanel  = new JPanel();
		
		//Composants de la zone de combat
		this.addTrainerInfoZone();
		this.addTrainerMenu(new ToolBarPanel("Ouh lÃ  la, Ã§a commence."));
		
		return mainPanel;
	}
	
	public String getName(){
		return player_name;
	}
	
	public void setName(String name){
		player_name=name;
	}
	
	public String getActionText(){
		return textAction;
	}
	
	public void setActionText(String text){
		textAction = text;
	}

	
	/*-----OTHER FUNCTIONS---------------------------------------------------------------------------*/
	private void addTrainerInfoZone(){

		mainGbc.fill=GridBagConstraints.HORIZONTAL;
		mainGbc.weightx=1;
		mainGbc.ipady = 0;
		
		JPanel BattlePanel = new JPanel();
		
		BattlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		BattlePanel.setLayout(new GridBagLayout());
		
		GridBagConstraints BGbc = new GridBagConstraints();
		BGbc.fill=GridBagConstraints.HORIZONTAL;
		
		BGbc.gridx=0;
		BGbc.gridy=1;
		BGbc.anchor=GridBagConstraints.LAST_LINE_START;
		BattlePanel.add(new TrainerLabelInfo(trainers[0]), BGbc);

		BGbc.gridx=1;
		BGbc.gridy=0;
		BGbc.anchor=GridBagConstraints.FIRST_LINE_END;
		BattlePanel.add(new TrainerLabelInfo(trainers[1]), BGbc);
		
		/*
		this.createTrainerLabel(player_name, 0, "player.png", BGbc, BattlePanel);
		this.createTrainerLabel("Adversaire", 1, "ia.png", BGbc, BattlePanel);
		 */
		mainPanel.add(BattlePanel, mainGbc);
	}
	
	private void addTrainerMenu(JPanel panel){
		mainGbc.fill=GridBagConstraints.HORIZONTAL;
		mainGbc.weightx=0;
		mainGbc.insets = new Insets(10,0,0,0);
		
		this.toolBarPanel.add(panel);
		//this.toolBarPanel.add(new ChoiceMenu(trainers[0], trainers[1]));

		mainPanel.add(toolBarPanel, mainGbc);
	}
	
	/*------------SELECT WHO BEGIN-------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	private void selectWhoBegin(){
		int deltaChoseTrainer = (int)(Math.random()*2);
		this.idCurrentTrainer = deltaChoseTrainer%2;
	}
	/*-----------------------------------------------------------------------------------------------*/

	/*------------CHANGE CURRENT TRAINER-------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	private void changeCurrentTrainer(){
		this.idCurrentTrainer = (this.idCurrentTrainer+1)%2;
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	/*------------FIGHT------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	private void fight(){
		System.out.println("------------- TURN OF "+this.trainers[this.idCurrentTrainer].getName()+" -------------");
		this.trainers[this.idCurrentTrainer].play(this.trainers[(this.idCurrentTrainer+1)%2]);
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

	//	while(!this.next){
	/*	BattleScreen temp = this;


		Trainer source = (Trainer)o;
		StateTrainer state = source.getState();

		System.out.println(state);
		SwingWorker worker = new SwingWorker() {
			 
			// Ce traitement sera exécuté dans un autre thread :
			protected Object doInBackground() throws Exception {

				Thread.sleep(5000);
				
				return null;
			}

			// Ce traitement sera exécuté à la fin dans l'EDT 
			protected void done() {

				toolBarPanel.removeAll();
				temp.addTrainerMenu(state.getContent());
				toolBarPanel.revalidate();
				
				if(state.getContinu()){
					if(!source.defeated() || (source instanceof IA)){
						temp.changeCurrentTrainer();
						temp.fight();
					}
				}
			}
		};

		// On lance l'exécution de la tâche:
		worker.execute();*/
	
		
			Trainer source = (Trainer)o;
			StateTrainer state = source.getState();
	
			System.out.println(state);
			
			toolBarPanel.removeAll();
			this.addTrainerMenu(state.getContent());
			toolBarPanel.revalidate();
			
			if(state.getContinu()){
				if(!source.defeated() || (source instanceof IA)){
					this.changeCurrentTrainer();
					this.fight();
				}
			}
		//}
		//this.next = false;*/
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		System.out.println("pressed");}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("released");}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("typed");
		synchronized (next) {
	
		   // add an element and notify all that an element exists
			next.add(true);
		   System.out.println("New Element:'" + true + "'");
	
		   next.notifyAll();
		   System.out.println("notifyAll called!");
		   }
		   System.out.println("Closing...");

	}
	
}
