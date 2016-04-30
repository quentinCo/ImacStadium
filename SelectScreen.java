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
	
	JButton[] buttons;
	JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10,
			btn11, btn12, btn13, btn14, btn15, valid;
	String player_name;
	JLabel label;

	public SelectScreen(String name) {
		super();
		build();
		this.player_name = name;
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
		
		JPanel panel = new JPanel();//Instanciation d'un objet JPanel
		panel.setLayout(new GridBagLayout());
		
		panel.setBackground(Color.orange);//Définition de sa couleur de fond
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets= new Insets(10,10,40,10);
		gbc.fill = GridBagConstraints.LINE_START;
		gbc.anchor = GridBagConstraints.PAGE_START; 
		gbc.gridwidth=5;
		
		label = new JLabel();
		Font font_titre = label.getFont();
		font_titre = new Font("Arial", Font.BOLD, 30);
		label.setFont(font_titre);
		panel.add(label, gbc);
		
		//Caractéristiques des boutons
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth=1;
		gbc.weightx = 1;//1=Couvre l'écran, 0=Ne s'étend que sur la longueur de la chaine de caractères.
		gbc.ipady = 50;
		gbc.insets= new Insets(5,5,5,5);
		
		btn1 = new JButton("IMAC n°1");
		gbc.gridx=0;
		gbc.gridy=1;
		panel.add(btn1, gbc);
		
		btn2 = new JButton("IMAC n°2");
		gbc.gridx=1;
		gbc.gridy=1;
		panel.add(btn2, gbc);
		
		btn3 = new JButton("IMAC n°3");
		gbc.gridx=2;
		gbc.gridy=1;
		panel.add(btn3, gbc);
		
		btn4 = new JButton("IMAC n°4");
		gbc.gridx=3;
		gbc.gridy=1;
		panel.add(btn4, gbc);
		
		btn5 = new JButton("IMAC n°5");
		gbc.gridx=4;
		gbc.gridy=1;
		panel.add(btn5, gbc);
		
		btn6 = new JButton("IMAC n°6");
		gbc.gridx=0;
		gbc.gridy=2;
		panel.add(btn6, gbc);
		
		btn7 = new JButton("IMAC n°7");
		gbc.gridx=1;
		gbc.gridy=2;
		panel.add(btn7, gbc);
		
		btn8 = new JButton("IMAC n°8");
		gbc.gridx=2;
		gbc.gridy=2;
		panel.add(btn8, gbc);
		
		btn9 = new JButton("IMAC n°9");
		gbc.gridx=3;
		gbc.gridy=2;
		panel.add(btn9, gbc);
		
		btn10 = new JButton("IMAC n°10");
		gbc.gridx=4;
		gbc.gridy=2;
		panel.add(btn10, gbc);
		
		btn11 = new JButton("IMAC n°11");
		gbc.gridx=0;
		gbc.gridy=3;
		panel.add(btn11, gbc);
		
		btn12 = new JButton("IMAC n°12");
		gbc.gridx=1;
		gbc.gridy=3;
		panel.add(btn12, gbc);
		
		btn13 = new JButton("IMAC n°13");
		gbc.gridx=2;
		gbc.gridy=3;
		panel.add(btn13, gbc);
		
		btn14 = new JButton("IMAC n°14");
		gbc.gridx=3;
		gbc.gridy=3;
		panel.add(btn14, gbc);
		
		btn15 = new JButton("IMAC n°15");
		gbc.gridx=4;
		gbc.gridy=3;
		panel.add(btn15, gbc);
		
		valid = new JButton(new StartBtnAction(this , "Commencer"));
		gbc.gridwidth=5;
		gbc.anchor = GridBagConstraints.PAGE_END; 
		gbc.insets= new Insets(30,5,5,5);
		gbc.ipady = 20;
		gbc.gridx=0;
		gbc.gridy=4;
		panel.add(valid, gbc);
		/*for(int i=0;i<15;i++){
			buttons[i] = new JButton("IMAC n°"+i);
			panel.add(buttons[i]);
		}*/
		
		return panel;
	}

	public JLabel getLabel(){
		return label;
	}
	
	public String getName(){
		return player_name;
	}
}
