package imacstadium.display;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import imacstadium.game.Game;
import imacstadium.game.IA;
import imacstadium.game.Trainer;
import imacstadium.game.state.StateTrainer;
import imacstadium.page.ChoiceMenu;
import imacstadium.page.ToolBarPanel;

public class BattleScreen extends JFrame implements Observer{
	
	private String player_name, textAction;
	private JButton AttackButton1, AttackButton2, AttackButton3, AttackButton4;
	private Trainer [] trainers;
	private int idCurrentTrainer;
	private JPanel toolBarPanel;
	private JPanel mainPanel;
	private GridBagConstraints mainGbc;
	

	public BattleScreen(String name){
		super();
		this.player_name = name;
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
		
		mainPanel.setBackground(Color.orange);//D�finition de sa couleur de fond
		
		/* Ajout de ce composant au container en sp�cifiant une contrainte de type GridBagConstraints. */
		mainGbc = new GridBagConstraints();
		mainGbc.gridwidth=GridBagConstraints.REMAINDER;
		
		this.toolBarPanel  = new JPanel();
		
		//Composants de la zone de combat
		this.addTrainerInfoZone();
		this.addTrainerMenu(new ToolBarPanel("Ouh là la, ça commence."));
		
		/*JPanel textActionPanel = new JPanel();
		textActionPanel.setLayout(new FlowLayout());
		
		JLabel labelAction = new JLabel(this.textAction);
		
		Font font_labelAction = labelAction.getFont();
		font_labelAction = new Font("Arial", Font.BOLD, 20);
		labelAction.setFont(font_labelAction);
		
		textActionPanel.add(labelAction);
		
		mainPanel.add(textActionPanel, mainGbc);*/
		
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
	/*
	private void createTrainerLabel(String name, int posX, String url_image, GridBagConstraints BGbc, JPanel panel){
		JLabel label_name = new JLabel(name);
		Font font_Name = label_name.getFont();
		font_Name = new Font("Arial", Font.BOLD, 15);
		label_name.setFont(font_Name);
		BGbc.fill=GridBagConstraints.REMAINDER;
		BGbc.anchor=GridBagConstraints.CENTER;
		BGbc.weightx=0.2;
		BGbc.insets= new Insets(5,5,5,5);
		BGbc.gridx=posX;
		BGbc.gridy=0;
		panel.add(label_name, BGbc);
		
		JLabel LifePoints = new JLabel("25/30");
		Font font_LifePoints = label_name.getFont();
		font_LifePoints = new Font("Arial", Font.BOLD, 10);
		LifePoints.setFont(font_LifePoints);
		BGbc.anchor=GridBagConstraints.PAGE_START;
		BGbc.gridx=posX;
		BGbc.gridy=1;
		panel.add(LifePoints, BGbc);
		
		ImageIcon img = new ImageIcon(url_image);		
		JLabel icon = new JLabel(img, JLabel.CENTER);
		BGbc.anchor=GridBagConstraints.PAGE_START;
		BGbc.gridx=posX;
		BGbc.gridy=2;
		panel.add(icon, BGbc);
	}
	*/
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
		
		Trainer source = (Trainer)o;
		StateTrainer state = source.getState();
/*
		if(!(state instanceof ChoiceMenu)){
			long t1 = System.currentTimeMillis(); 
			while(System.currentTimeMillis()<t1+2000); 
		}
		System.out.println(state);
*/
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
