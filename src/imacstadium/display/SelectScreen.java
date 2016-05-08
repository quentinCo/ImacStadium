package imacstadium.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import imacstadium.commande.Key;
import imacstadium.game.Game;
import imacstadium.imac.ImacHeader;

public class SelectScreen extends JFrame {
	
	/*JButton[] buttons;
	JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10,
			btn11, btn12, btn13, btn14, btn15, valid;*/
	private String player_name;
	private JLabel label;
	private int numberImacs;

	public SelectScreen(String name) {
		super();
		this.player_name = name;
		this.numberImacs = 0;
		build();
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
		
		JPanel panel = new JPanel();//Instanciation d'un objet JPanel
		panel.setLayout(new GridBagLayout());
		
		panel.setBackground(Color.orange);//Définition de sa couleur de fond
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		this.creatLabel(gbc, panel);
		this.creatImacList(gbc,panel);
		
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
		//Caract�ristiques des boutons
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth=1;
		gbc.weightx = 1;//1=Couvre l'�cran, 0=Ne s'�tend que sur la longueur de la chaine de caract�res.
		gbc.ipady = 50;
		gbc.insets= new Insets(5,5,5,5);
		
		
		ArrayList<ImacHeader> imacs = Game.getInstance().getImacs();
		Iterator<ImacHeader> it = imacs.iterator();
		ImacHeader imac;
		
		int k = 0;
		for(int i = 1; it.hasNext(); i++){
			for(int j = 0; j<5 && it.hasNext(); j++){
				imac = it.next();
				this.addImacButton(imac, j, i, gbc, panel);
				k++;
			}
		}
	}
	
	private void addImacButton(ImacHeader imac, int posX, int posY, GridBagConstraints gbc, JPanel panel){
		String textButton;
		if(imac.getTypeImac() == "") textButton = "<html>"+imac.getName()+"<br>Type : NO TYPE </html>";
		else textButton = "<html>"+imac.getName()+"<br>Type : "+imac.getTypeImac()+"</html>";
		
		JButton button = new JButton(new ImacChoiceBtnAction(imac.getId(),this, textButton));
		gbc.gridx=posX;
		gbc.gridy=posY;
		
		panel.add(button, gbc);
	}
}
