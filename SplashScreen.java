package imacstadium.display;

import javax.swing.JWindow;

public class SplashScreen extends JWindow{

	public SplashScreen() {
		super();
		build();
	}
	
	private void build(){
		setSize(640,360);
		setLocationRelativeTo(null);//On centre la fenêtre sur l'écran
		setContentPane(new ImagePanel());//On prévient notre JFrame que notre JPanel sera son content pane
		setVisible(true);
	}
	

}
