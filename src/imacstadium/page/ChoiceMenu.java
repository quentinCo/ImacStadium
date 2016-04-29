package imacstadium.page;

import java.awt.event.KeyListener;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;

import imacstadium.commande.Key;
import imacstadium.game.Trainer;

public class ChoiceMenu extends GameMenu implements KeyListener {

	private ArrayList<Key> keys;
	
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
		super("Choisissez votre attaque :");
		this.setLayout(new GridLayout(6, 1));
		this.add(new JLabel("1 - attaque 1"));
		this.add(new JLabel("2 - attaque 2"));
		this.add(new JLabel("3 - attaque 3"));
		this.add(new JLabel("4 - attaque 4"));
		this.add(new JLabel("q - quitte"));
		
		keys = new ArrayList<Key>();
		
		keys.add(new Key('1'){
			public Object action(){
				current.imacAttack(opponnent, 0);
				return null;
			}
		});
		keys.add(new Key('2'){
			public Object action(){
				current.imacAttack(opponnent, 1);
				return null;
			}
		});
		keys.add(new Key('3'){
			public Object action(){
				current.imacAttack(opponnent, 2);
				return null;
			}
		});
		keys.add(new Key('4'){
			public Object action(){
				current.imacAttack(opponnent, 3);
				return null;
			}
		});
		keys.add(new Key('q'){
			public Object action(){
				System.exit(0);
				return null;
			}
		});
	}
	/*-----------------------------------------------------------------------------------------------*/

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		Key key = null;
		boolean find = false;
		Iterator<Key> it = keys.iterator();
		
		while(!find && it.hasNext()){
			key = it.next();
			if(e.getKeyChar() == key.getKey()) find = true;
		}
		if(find) key.action();
	}
	
	

}
