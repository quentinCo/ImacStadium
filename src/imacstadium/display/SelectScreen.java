package imacstadium.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectScreen extends JFrame {
	
	/*JButton[] buttons;
	JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10,
			btn11, btn12, btn13, btn14, btn15, valid;*/
	String player_name;
	JLabel label;

	public SelectScreen(String name) {
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
		
		JPanel panel = new JPanel();//Instanciation d'un objet JPanel
		panel.setLayout(new GridBagLayout());
		
		panel.setBackground(Color.orange);//DÃ©finition de sa couleur de fond
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		this.creatLabel(gbc, panel);
		this.creatImacList(gbc,panel);
		this.creatValidButton(gbc, panel);
		
		return panel;
	}

	public JLabel getLabel(){
		return label;
	}
	
	public String getName(){
		return player_name;
	}
	
	private void creatLabel(GridBagConstraints gbc, JPanel panel){
		gbc.insets= new Insets(10,10,40,10);
		gbc.fill = GridBagConstraints.LINE_START;
		gbc.anchor = GridBagConstraints.PAGE_START; 
		gbc.gridwidth=5;
		
		JLabel label = new JLabel();
		Font font_titre = label.getFont();
		font_titre = new Font("Arial", Font.BOLD, 30);
		label.setFont(font_titre);
		label.setText(this.player_name+", selectionnez vos trois Imacs !");
		panel.add(label, gbc);
	}
	
	private void creatImacList(GridBagConstraints gbc, JPanel panel){
		//Caractéristiques des boutons
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth=1;
		gbc.weightx = 1;//1=Couvre l'écran, 0=Ne s'étend que sur la longueur de la chaine de caractères.
		gbc.ipady = 50;
		gbc.insets= new Insets(5,5,5,5);
		
		int k = 0;
		for(int i = 1; k < 15; i++){
			for(int j = 0; j<5 && k < 15; j++){
				addImacButton("IMAC n°"+k, j, i, gbc, panel);
				k++;
			}
		}
	}
	
	private void addImacButton(String name, int posX, int posY, GridBagConstraints gbc, JPanel panel){
		JButton button = new JButton(name);
		gbc.gridx=posX;
		gbc.gridy=posY;
		panel.add(button, gbc);
	}
	
	private void creatValidButton(GridBagConstraints gbc, JPanel panel){
		JButton valid = new JButton(new StartBtnAction(this , "Commencer"));
		gbc.gridwidth=5;
		gbc.anchor = GridBagConstraints.PAGE_END; 
		gbc.insets= new Insets(30,5,5,5);
		gbc.ipady = 20;
		gbc.gridx=0;
		gbc.gridy=4;
		panel.add(valid, gbc);
	}
}
