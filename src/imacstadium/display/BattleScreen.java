package imacstadium.display;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import imacstadium.display.elements.ToolBarPanel;
import imacstadium.display.elements.TrainerLabelInfo;
import imacstadium.game.Game;
import imacstadium.game.IA;
import imacstadium.game.Trainer;
import imacstadium.game.state.StateTrainer;

/**
 * <b>BattleScreen</b>
 * <p>
 * It's the screen of battle.
 * This class that manage the displaying and turn of the different phase of game.
 * </p>
 * @see Game
 * @see Trainer
 * @see IA
 * @see ToolBarPanel
 * @see TrainerLabelInfo
 * @see StateTrainer
 */
public class BattleScreen extends JFrame implements Observer{
	
	private Trainer [] trainers;
	private int idCurrentTrainer;
	private JPanel toolBarPanel;
	private JPanel mainPanel;
	private GridBagConstraints mainGbc;
	
	/**
	 * Generate the battle screen. And begin the turn management.
	 */
	public BattleScreen(){
		super();
		this.trainers = Game.getInstance().getTrainers();
		for(Trainer trainer : trainers) trainer.addObserver(this);
		
		this.selectWhoBegin();
		
		build();
		
		this.fight();
	}

	private void build(){
		setTitle("Imac Stadium"); //On donne un titre à l'application
		setSize(840, 680); //On donne une taille à notre fenêtre
		setLocationRelativeTo(null);//On centre la fenêtre sur l'écran
		setResizable(true);//On interdit le redimensionnement de la fenêtre

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Termine le processus lorsqu'on clique sur la croix rouge
		
		setContentPane(buildContentPane());//On prévient notre JFrame que notre JPanel sera son content pane
		
		setVisible(true); //On la rend visible
	}
	
	private JPanel buildContentPane(){
		
		mainPanel = new JPanel();//Instanciation d'un objet JPanel
		mainPanel.setLayout(new GridBagLayout());
		
		mainPanel.setBackground(Color.white);//D�finition de sa couleur de fond
		
		/* Ajout de ce composant au container en sp�cifiant une contrainte de type GridBagConstraints. */
		mainGbc = new GridBagConstraints();
		mainGbc.gridwidth=GridBagConstraints.REMAINDER;
		
		this.toolBarPanel  = new JPanel();
		this.toolBarPanel.setBackground(Color.white);
		this.toolBarPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		//Composants de la zone de combat
		this.addTrainerInfoZone();
		this.addTrainerMenu(new ToolBarPanel("Ouh là la, ça commence."));
		
		return mainPanel;
	}
	
	/*-----OTHER FUNCTIONS---------------------------------------------------------------------------*/
	private void addTrainerInfoZone(){

		mainGbc.fill=GridBagConstraints.HORIZONTAL;
		mainGbc.weightx=1;
		mainGbc.ipady = 0;
		
		JPanel BattlePanel = new JPanel();
		
		BattlePanel.setLayout(new GridBagLayout());
		BattlePanel.setBackground(Color.WHITE);
		
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
		
		mainPanel.add(BattlePanel, mainGbc);
	}
	
	private void addTrainerMenu(JPanel panel){
		mainGbc.fill=GridBagConstraints.HORIZONTAL;
		mainGbc.weightx=0;
		mainGbc.insets = new Insets(10,0,0,0);
		
		this.toolBarPanel.add(panel);

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
		//System.out.println("------------- TURN OF "+this.trainers[this.idCurrentTrainer].getName()+" -------------");
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
		
			Trainer source = (Trainer)o;
			StateTrainer state = source.getState();
	
			//System.out.println(state);
			
			toolBarPanel.removeAll();
			this.addTrainerMenu(state.getContent());
			toolBarPanel.revalidate();
			
			if(state.getContinu()){
				if(!source.defeated() || (source instanceof IA)){
					this.changeCurrentTrainer();
					this.fight();
				}
			}
	}

}
