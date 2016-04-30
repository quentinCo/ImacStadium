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
		build();
	}

	private void build(){
		setTitle("Imac Stadium"); //On donne un titre à l'application
		setSize(800, 600); //On donne une taille à notre fenêtre
		setLocationRelativeTo(null);//On centre la fenêtre sur l'écran
		setResizable(true);//On interdit le redimensionnement de la fenêtre
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Termine le processus lorsqu'on clique sur la croix rouge
		
		setContentPane(buildContentPane());//On prévient notre JFrame que notre JPanel sera son content pane
		setVisible(true); //On la rend visible
	}
	
	private JPanel buildContentPane(){
		
		JPanel MainPanel = new JPanel();//Instanciation d'un objet JPanel
		MainPanel.setLayout(new GridBagLayout());
		
		MainPanel.setBackground(Color.orange);//Définition de sa couleur de fond
		
		/* Ajout de ce composant au container en spécifiant une contrainte de type GridBagConstraints. */
		GridBagConstraints MainGbc = new GridBagConstraints();
		MainGbc.gridwidth=GridBagConstraints.REMAINDER;
		
		MainGbc.fill=GridBagConstraints.HORIZONTAL;
		MainGbc.anchor=GridBagConstraints.PAGE_END;
		MainGbc.weightx=1;
		MainGbc.ipady = 0;
		
		//Composants de la zone de combat
		JPanel BattlePanel = new JPanel();
		
		BattlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		BattlePanel.setLayout(new GridBagLayout());
		
		GridBagConstraints BGbc = new GridBagConstraints();
		
		BGbc.fill=GridBagConstraints.HORIZONTAL;
		
		BGbc.anchor=GridBagConstraints.FIRST_LINE_END;
		
		JLabel NameAI = new JLabel("Adversaire");
		Font font_NameAI = NameAI.getFont();
		font_NameAI = new Font("Arial", Font.BOLD, 15);
		NameAI.setFont(font_NameAI);
		BGbc.fill=GridBagConstraints.REMAINDER;
		BGbc.anchor=GridBagConstraints.CENTER;
		BGbc.weightx=0.2;
		BGbc.insets= new Insets(5,5,5,5);
		BGbc.gridx=1;
		BGbc.gridy=0;
		BattlePanel.add(NameAI, BGbc);
		
		JLabel LifePointsAI = new JLabel("25/30");
		Font font_LifePointsAI = NameAI.getFont();
		font_LifePointsAI = new Font("Arial", Font.BOLD, 10);
		LifePointsAI.setFont(font_LifePointsAI);
		BGbc.anchor=GridBagConstraints.PAGE_START;
		BGbc.gridx=1;
		BGbc.gridy=1;
		BattlePanel.add(LifePointsAI, BGbc);
		
		ImageIcon ai_img = new ImageIcon("ia.png");
		JLabel ai_icon = new JLabel(ai_img, JLabel.CENTER);
		BGbc.anchor=GridBagConstraints.PAGE_START;
		BGbc.gridx=1;
		BGbc.gridy=2;
		BattlePanel.add(ai_icon, BGbc);
		
		JLabel NamePlayer = new JLabel(""+player_name+"");
		Font font_NamePlayer = NameAI.getFont();
		font_NamePlayer = new Font("Arial", Font.BOLD, 15);
		NamePlayer.setFont(font_NamePlayer);
		BGbc.gridx=0;
		BGbc.gridy=0;
		BattlePanel.add(NamePlayer, BGbc);
		
		JLabel LifePointsPlayer = new JLabel("25/30");
		Font font_LifePointsPlayer = NameAI.getFont();
		font_LifePointsPlayer = new Font("Arial", Font.BOLD, 10);
		LifePointsPlayer.setFont(font_LifePointsPlayer);
		BGbc.gridx=0;
		BGbc.gridy=1;
		BattlePanel.add(LifePointsPlayer, BGbc);
		
		ImageIcon player_img = new ImageIcon("player.png");
		JLabel player_icon = new JLabel(player_img, JLabel.CENTER);
		BGbc.gridx=0;
		BGbc.gridy=2;
		BattlePanel.add(player_icon, BGbc);
		
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
		
		TBGbc.gridheight=1;
		TBGbc.ipady = 10;
		TBGbc.ipadx = 10;
		TBGbc.gridx=1;
		TBGbc.gridy=0;
		JLabel TitleTeam = new JLabel("Imacs de "+player_name);
		TitleTeam.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		ToolBarPanel.add(TitleTeam, TBGbc);
		
		TBGbc.gridx=1;
		TBGbc.gridy=1;
		JLabel TeamImac1 = new JLabel("Imac 1 : épuisé");
		TeamImac1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		ToolBarPanel.add(TeamImac1, TBGbc);
		
		TBGbc.gridx=1;
		TBGbc.gridy=2;
		JLabel TeamImac2 = new JLabel("Imac 2 : épuisé");
		TeamImac2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		ToolBarPanel.add(TeamImac2, TBGbc);
		
		TBGbc.gridx=2;
		TBGbc.gridy=0;
		TBGbc.gridwidth=2;
		JLabel TitleAttack = new JLabel("Attaques de l'Imac");
		TitleAttack.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		ToolBarPanel.add(TitleAttack, TBGbc);
		
		TBGbc.gridx=2;
		TBGbc.gridy=1;
		TBGbc.gridwidth=1;
		AttackButton1 = new JButton("Attaque 1");
		ToolBarPanel.add(AttackButton1, TBGbc);
		
		TBGbc.gridx=3;
		TBGbc.gridy=1;
		AttackButton2 = new JButton("Attaque 2");
		ToolBarPanel.add(AttackButton2, TBGbc);
		
		TBGbc.gridx=2;
		TBGbc.gridy=2;
		AttackButton3 = new JButton("Attaque 3");
		ToolBarPanel.add(AttackButton3, TBGbc);
		
		TBGbc.gridx=3;
		TBGbc.gridy=2;
		AttackButton4 = new JButton("Attaque 4");
		ToolBarPanel.add(AttackButton4, TBGbc);
		
		MainPanel.add(BattlePanel, MainGbc);
		MainPanel.add(ToolBarPanel, MainGbc);
		
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

}
