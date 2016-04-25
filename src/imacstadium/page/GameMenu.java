package imacstadium.page;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameMenu extends JPanel{

	private JLabel principaleLabel;
	
	public GameMenu(String sentence){
		principaleLabel = new JLabel(sentence);
		this.add(principaleLabel);
	}
	
}
