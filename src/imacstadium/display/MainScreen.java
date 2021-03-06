package imacstadium.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import imacstadium.display.button.HomeBtnAction;
import imacstadium.game.Game;
import imacstadium.game.Trainer;

/**
 * <b>MainScreen</b>
 * <p>
 * This class display a screen that permit to enter the player's name.
 * </p>
 * @see Game
 * @see Trainer
 * @see HomeBtnAction
 */
public class MainScreen extends JFrame{
	
	private JTextField player_name_txtfield;
	
	/**
	 * Generate the main screen.
	 * The screen have a button that create the next screen.
	 */
	public MainScreen() {
		super();
		Game.getInstance().resetImacsPlayer();
		build();//On initialise notre fenêtre
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
		
		panel.setBackground(Color.white);//D�finition de sa couleur de fond
		
		/* Ajout de ce composant au container en sp�cifiant une contrainte de type GridBagConstraints. */
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth=GridBagConstraints.REMAINDER;
		gbc.insets= new Insets(20,10,10,10);
		
		Path path = FileSystems.getDefault().getPath(new File("").getAbsolutePath() + "/data/picture/logo/LOGO_IMAC_STADIUM_icon.png");
	    
		ImageIcon logo = new ImageIcon(path.toString());
		JLabel image_icon = new JLabel(logo, JLabel.CENTER);
		panel.add(image_icon, gbc);
		
		JLabel label = new JLabel("<html><div style='text-align: center;'>BIENVENUE DANS <br/><font color='grey'> IMAC STADIUM !</font></div></html>");
		
		Font font_titre = label.getFont();
		font_titre = new Font("Arial", Font.BOLD, 30);
		label.setFont(font_titre);
		gbc.ipady = 0;
		gbc.ipadx = 0;
		panel.add(label, gbc);
		
		player_name_txtfield = new JTextField("Entrez votre nom de dresseur");
		gbc.ipady = 30;
		gbc.ipadx = 50;
		panel.add(player_name_txtfield, gbc);
		
		JButton button = new JButton(new HomeBtnAction(this, "Selectionner son équipe d'IMAC"));
		button.setBackground(Color.WHITE);
		button.setForeground(Color.BLACK);
		button.setFocusPainted(false);
		button.setBorder(new LineBorder(Color.BLACK));
		
		gbc.ipady = 40;
		gbc.ipadx = 30;
		panel.add(button, gbc);
		
		return panel;
	}
	
	/**
	 * Return the name write in the text field.
	 * @return The string that the player have wrote.
	 */
	public String getName(){
		return player_name_txtfield.getText();
	}
	
	/**
	 * Return the text field where the player can write its name.
	 * @return The text field
	 */
	public JTextField getNameTextfield(){
		return player_name_txtfield;
	}

}
