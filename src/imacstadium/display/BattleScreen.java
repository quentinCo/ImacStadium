package imacstadium.display;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BattleScreen extends JFrame {
	
	private String player_name, textAction;
	private JButton AttackButton1, AttackButton2, AttackButton3, AttackButton4;

	public BattleScreen(String name){
		super();
		this.player_name = name;
		build();
	}

	private void build(){
		setTitle("Imac Stadium"); //On donne un titre Ã  l'application
		setSize(800, 600); //On donne une taille Ã  notre fenÃªtre
		setLocationRelativeTo(null);//On centre la fenÃªtre sur l'Ã©cran
		setResizable(true);//On interdit le redimensionnement de la fenÃªtre
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Termine le processus lorsqu'on clique sur la croix rouge
		
		setContentPane(buildContentPane());//On prÃ©vient notre JFrame que notre JPanel sera son content pane
		setVisible(true); //On la rend visible
	}
	
	private JPanel buildContentPane(){
		
		JPanel MainPanel = new JPanel();//Instanciation d'un objet JPanel
		MainPanel.setLayout(new GridBagLayout());
		
		MainPanel.setBackground(Color.orange);//Définition de sa couleur de fond
		
		/* Ajout de ce composant au container en spécifiant une contrainte de type GridBagConstraints. */
		GridBagConstraints MainGbc = new GridBagConstraints();
		MainGbc.gridwidth=GridBagConstraints.REMAINDER;
		
		
		//Composants de la zone de combat
		this.createTrainerInfoZone(MainGbc, MainPanel);
		this.createTrainerMenu(MainGbc, MainPanel);
		
		JPanel textActionPanel = new JPanel();
		textActionPanel.setLayout(new FlowLayout());
		
		JLabel labelAction = new JLabel(this.textAction);
		
		Font font_labelAction = labelAction.getFont();
		font_labelAction = new Font("Arial", Font.BOLD, 20);
		labelAction.setFont(font_labelAction);
		
		textActionPanel.add(labelAction);
		
		MainPanel.add(textActionPanel, MainGbc);
		
		return MainPanel;
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

	private void createTrainerInfoZone(GridBagConstraints MainGbc, JPanel MainPanel){

		MainGbc.fill=GridBagConstraints.HORIZONTAL;
		MainGbc.anchor=GridBagConstraints.PAGE_END;
		MainGbc.weightx=1;
		MainGbc.ipady = 0;
		
		JPanel BattlePanel = new JPanel();
		
		BattlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		BattlePanel.setLayout(new GridBagLayout());
		
		GridBagConstraints BGbc = new GridBagConstraints();
		BGbc.fill=GridBagConstraints.HORIZONTAL;
		BGbc.anchor=GridBagConstraints.FIRST_LINE_END;
		
		this.createTrainerLabel("Adversaire", 1, "ia.png", BGbc, BattlePanel);
		this.createTrainerLabel(player_name, 0, "player.png", BGbc, BattlePanel);

		MainPanel.add(BattlePanel, MainGbc);
	}
	
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
	
	private void createTrainerMenu(GridBagConstraints MainGbc, JPanel MainPanel){
		MainGbc.fill=GridBagConstraints.HORIZONTAL;
		MainGbc.weightx=1;
		MainGbc.ipady = 0;
		
		//Composants de la barre d'actions
		JPanel ToolBarPanel = new JPanel();
		
		ToolBarPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		ToolBarPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints TBGbc = new GridBagConstraints();
		TBGbc.fill=GridBagConstraints.HORIZONTAL;
		TBGbc.anchor=GridBagConstraints.PAGE_END;
		TBGbc.weightx=1;
		TBGbc.ipady = 80;
		TBGbc.insets= new Insets(5,5,5,5);
		TBGbc.gridx=0;
		TBGbc.gridheight=3;
		JButton QuitButton = new JButton(new ReturnAction(this, "Quitter"));
		ToolBarPanel.add(QuitButton, TBGbc);
		
		
		this.createImacListe(ToolBarPanel, TBGbc);
		this.createImacAttack(ToolBarPanel, TBGbc);
		
		
		MainPanel.add(ToolBarPanel, MainGbc);
	}
	
	private void createImacListe(JPanel ToolBarPanel, GridBagConstraints TBGbc){
		TBGbc.gridheight=1;
		TBGbc.ipady = 10;
		TBGbc.ipadx = 10;
		

		this.addImacListeLabel("Imacs de "+player_name, 0, ToolBarPanel, TBGbc);
		
		for(int i = 1; i<3; i++){
			this.addImacListeLabel("Imac "+i+" : épuisé", i, ToolBarPanel, TBGbc);
		}
	}
	
	private void addImacListeLabel(String text, int posY, JPanel ToolBarPanel, GridBagConstraints TBGbc){
		TBGbc.gridx=1;
		TBGbc.gridy=posY;
		JLabel TitleTeam = new JLabel(text);
		TitleTeam.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		ToolBarPanel.add(TitleTeam, TBGbc);
	}
	
	private void createImacAttack(JPanel ToolBarPanel, GridBagConstraints TBGbc){
		TBGbc.gridx=2;
		TBGbc.gridy=0;
		TBGbc.gridwidth=2;
		JLabel TitleAttack = new JLabel("Attaques de l'Imac");
		TitleAttack.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		ToolBarPanel.add(TitleAttack, TBGbc);
		
		for(int i = 1; i <3; i++){
			for(int j = 2; j<4; j++){
				this.addAttack("Attaque "+(i+j-2), j ,i, ToolBarPanel, TBGbc);
			}
		}
	}
	
	private void addAttack(String name, int posX, int posY, JPanel ToolBarPanel, GridBagConstraints TBGbc){
		TBGbc.gridx=posX;
		TBGbc.gridy=posY;
		TBGbc.gridwidth=1;
		JButton AttackButton = new JButton(name);
		ToolBarPanel.add(AttackButton, TBGbc);
	}
	
}
