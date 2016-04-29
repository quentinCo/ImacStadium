package imacstadium.page;


import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import imacstadium.commande.*;
import imacstadium.game.Game;

public class PageTest extends Page {

	private ArrayList<Key> keys;
	
	public PageTest(){
		super("PageTest");
		
		keys = new ArrayList<Key>();
		
		keys.add(new Key('a'){
			public Object action(){
				System.exit(0);
				return null;
			}
		});
		
		keys.add(new Key('z'));
		keys.add(new Key('s'));
		keys.add(new Key('q'));
		keys.add(new Key('d'));
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
		if(find){
			System.out.println((String)key.action());
			System.out.println(Game.getInstance());
		}		
	}


	@Override/*
	public void update() {
		this.display();
	}*/
	
	public void display(){
		/* System.out.println(" -- " + i);
		 i++;*/
	}
	
	/* Dream Team Functions */
	@Override
	public String toString() {
		return super.toString() + "\n\tPageTest [keys=" + keys + "]";
	}
	
	
}
