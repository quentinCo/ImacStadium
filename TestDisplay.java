package imacstadium.display;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

public class TestDisplay {
	
	static SplashScreen splash;
	//Test JWindow
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//On crée une instance de JWindow
				splash = new SplashScreen();
			}
		});
		try{
			Thread.sleep(5000);
		}catch(InterruptedException e){}
		
		splash.dispose();
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//On crée une instance de JWindow
				MainScreen screen = new MainScreen();
				//SelectScreen screen2 = new SelectScreen();
				//BattleScreen screen3 = new BattleScreen();
			}
		});
	}

}
