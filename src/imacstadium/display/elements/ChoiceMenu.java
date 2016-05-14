package imacstadium.display.elements;

import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import imacstadium.display.button.AttackButtonAction;
import imacstadium.display.button.ReturnAction;
import imacstadium.game.Game;
import imacstadium.game.Trainer;
import imacstadium.imac.Imac;

public class ChoiceMenu extends ToolBarPanel {

	private Trainer current;
	private Trainer opponnent;
	private ArrayList<JLabel> imacList;
	
	/*-----CONSTRUCTOR-------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	/**
	 * Generate a choice menu with the different attacks and a quite option.
	 * @param current
	 * 	The current trainer (player).
	 * @param opponnent
	 * 	The opponent (ai).
	 */
	public ChoiceMenu(Trainer current, Trainer opponnent) {
		super(null);
		this.current = current;
		this.opponnent = opponnent;
		this.imacList = new ArrayList<JLabel>();
		
		//Composants de la barre d'actions
		//this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.WHITE);
		
		this.TBGbc = new GridBagConstraints();
		this.TBGbc.fill = GridBagConstraints.HORIZONTAL;
		this.TBGbc.anchor = GridBagConstraints.PAGE_END;
		this.TBGbc.weightx = 1;
		this.TBGbc.ipady = 80;
		this.TBGbc.insets= new Insets(5,5,5,5);
		this.TBGbc.gridx = 0;
		this.TBGbc.gridheight = 3;
		JButton quitButton = new JButton(new ReturnAction(Game.getInstance().getPage(), "Quitter"));
		quitButton.setBackground(Color.WHITE);
		quitButton.setForeground(Color.BLACK);
		quitButton.setFocusPainted(false);
		quitButton.setBorder(new LineBorder(Color.BLACK));
		
		this.add(quitButton, this.TBGbc);
		
		
		this.createImacListe();
		this.createImacAttack();
		
	}
	/*-----------------------------------------------------------------------------------------------*/

	private void createImacListe(){
		this.TBGbc.gridheight=1;
		this.TBGbc.ipady = 10;
		this.TBGbc.ipadx = 10;

		this.addImacListeLabel("Imacs de "+current.getName(), 0);
		
		ArrayList<Imac> imacs = this.current.getImacs();
		Iterator<Imac> it = imacs.iterator();
		Imac imac;
		int i = 1;
		
		while(it.hasNext()){
			imac = it.next();
			if(this.current.getCurrentImac() != imac){
				String text = imacState(imac);
				this.imacList.add(this.addImacListeLabel(text, i));
				i++;
			}
		}
	}
	
	private String imacState(Imac imac){
		String text;
		if(imac.isAlive()) text = imac.getName()+ " - PV : " + imac.getLife() + " / " + imac.getLifeTotal();
		else text = imac.getName() + " - K.O.";
		return text;
	}
	
	private JLabel addImacListeLabel(String text, int posY){
		this.TBGbc.gridx=1;
		this.TBGbc.gridy=posY;
		JLabel titleTeam = new JLabel(text);
		titleTeam.setHorizontalAlignment(JLabel.CENTER);
		//titleTeam.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.add(titleTeam, this.TBGbc);
		return titleTeam;
	}
	
	private void createImacAttack(){
		this.TBGbc.gridx=2;
		this.TBGbc.gridy=0;
		this.TBGbc.gridwidth=2;
		this.TBGbc.anchor=GridBagConstraints.CENTER;
		JLabel TitleAttack = new JLabel("Attaques");
		TitleAttack.setHorizontalAlignment(JLabel.CENTER);
		//TitleAttack.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.add(TitleAttack, this.TBGbc);
		
		int id = 0;
		
		for(int i = 1; i <3; i++){
			for(int j = 2; j<4; j++){
				this.addAttack(current.getCurrentImacAttack(id).getName(), id, j ,i, this.TBGbc);
				id++;
			}
		}
	}
	
	private void addAttack(String attack, int idAttack, int posX, int posY, GridBagConstraints TBGbc){
		TBGbc.gridx=posX;
		TBGbc.gridy=posY;
		TBGbc.gridwidth=1;
		JButton attackButton = new JButton(new AttackButtonAction(this.current, this.opponnent, attack, idAttack));
		attackButton.setBackground(Color.WHITE);
		attackButton.setForeground(Color.BLACK);
		attackButton.setFocusPainted(false);
		attackButton.setBorder(new LineBorder(Color.BLACK));
		
		this.add(attackButton, TBGbc);
	}
}
