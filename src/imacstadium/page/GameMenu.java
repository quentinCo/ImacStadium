package imacstadium.page;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameMenu extends JPanel{

	private JLabel principaleLabel;
	private String sentence;
	public GameMenu(String sentence){
		this.sentence = sentence;
		this.principaleLabel = new JLabel(sentence);
		this.add(principaleLabel);
	}

	@Override
	public String toString() {
		return "GameMenu [sentence=" + sentence + "]";
	}
	
}
