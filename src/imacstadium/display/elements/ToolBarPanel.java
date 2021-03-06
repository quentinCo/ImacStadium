package imacstadium.display.elements;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ToolBarPanel extends JPanel{

	private JLabel principaleLabel;
	private String sentence;

	protected GridBagConstraints TBGbc;


	/*-----CONSTRUCTOR-------------------------------------------------------------------------------*/
	/*-----------------------------------------------------------------------------------------------*/
	public ToolBarPanel(String sentence){
		this.sentence = sentence;
		this.principaleLabel = new JLabel(sentence);
		
		//this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.WHITE);
		
		this.TBGbc = new GridBagConstraints();
		this.TBGbc.fill=GridBagConstraints.HORIZONTAL;
		this.TBGbc.anchor=GridBagConstraints.PAGE_END;
		this.TBGbc.weightx=1;
		this.TBGbc.ipady = 80;
		this.TBGbc.gridx=0;
		this.TBGbc.gridheight=0;
		
		this.add(principaleLabel);
	}
	/*-----------------------------------------------------------------------------------------------*/
	
	
	public void setSentence(String sentence) {
		this.sentence = sentence;
		this.principaleLabel.setText(sentence);
		this.revalidate();
	}
	
	@Override
	public String toString() {
		return "ToolBarPanel [principaleLabel=" + principaleLabel + ", sentence=" + sentence + ", TBGbc=" + TBGbc + "]";
	}
	
}
