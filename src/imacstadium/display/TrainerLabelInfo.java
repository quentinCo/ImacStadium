package imacstadium.display;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import imacstadium.game.Trainer;

public class TrainerLabelInfo extends JPanel implements Observer {

	private JLabel label_name;
	private JLabel life_point;
	private Trainer trainer;
	private float total_life;
	private JLabel icon;
	
	public TrainerLabelInfo(Trainer trainer){
		this.trainer = trainer;
		this.total_life = trainer.getCurrentImac().getLifeTotal();
		
		this.trainer.addObserver(this);
		
		GridBagConstraints BGbc = new GridBagConstraints();
		
		BGbc.fill=GridBagConstraints.HORIZONTAL;
		BGbc.anchor=GridBagConstraints.FIRST_LINE_END;
		BGbc.gridwidth=GridBagConstraints.REMAINDER;
		
		label_name = new JLabel(trainer.getName());
		Font font_Name = label_name.getFont();
		font_Name = new Font("Arial", Font.BOLD, 15);
		label_name.setFont(font_Name);
		BGbc.fill=GridBagConstraints.REMAINDER;
		BGbc.anchor=GridBagConstraints.CENTER;
		BGbc.weightx=0.2;
		BGbc.insets= new Insets(5,5,5,5);
		BGbc.gridy=0;
		this.add(label_name, BGbc);
		
		life_point = new JLabel(trainer.getCurrentImac().getLife()+"/"+total_life);
		Font font_LifePoints = label_name.getFont();
		font_LifePoints = new Font("Arial", Font.BOLD, 10);
		life_point.setFont(font_LifePoints);
		BGbc.anchor=GridBagConstraints.PAGE_START;
		BGbc.gridy=1;
		this.add(life_point, BGbc);
		
		ImageIcon img = new ImageIcon("ai.png");
		icon = new JLabel(img, JLabel.CENTER);
		BGbc.anchor=GridBagConstraints.PAGE_START;
		BGbc.gridy=2;
		this.add(icon, BGbc);
	}

	@Override
	public void update(Observable o, Object arg) {
		Trainer.TypeNotification notif = (Trainer.TypeNotification)arg;
		if(notif != null){
			switch (notif){
				case ATTACKED:
					life_point.setText(trainer.getCurrentImac().getLife()+"/"+total_life);
					life_point.revalidate();
					break;
				case CHANGE_IMAC:
					total_life = trainer.getCurrentImac().getLifeTotal();
					icon.setIcon(new ImageIcon("ai.png"));
					icon.revalidate();
					life_point.setText(trainer.getCurrentImac().getLife()+"/"+total_life);
					life_point.revalidate();
					break;
				default:
					break;
			}
		}
		
	}
	
	
}
