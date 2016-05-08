package imacstadium.display;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import imacstadium.game.Trainer;

public class TrainerLabelInfo extends JPanel implements Observer {

	private JLabel label_name;
	private JLabel label_imac;
	private JLabel life_point;
	private Trainer trainer;
	private float total_life;
	private JLabel icon;
	
	public TrainerLabelInfo(Trainer trainer){
		this.trainer = trainer;
		this.total_life = trainer.getCurrentImac().getLifeTotal();
		
		this.trainer.addObserver(this);
		
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints Gbc = new GridBagConstraints();
		
		Gbc.fill=GridBagConstraints.HORIZONTAL;
		Gbc.anchor=GridBagConstraints.FIRST_LINE_END;
		Gbc.gridwidth=GridBagConstraints.REMAINDER;
		
		label_name = new JLabel(trainer.getName());
		Font font_Name = label_name.getFont();
		font_Name = new Font("Arial", Font.BOLD, 12);
		label_name.setFont(font_Name);
		Gbc.fill=GridBagConstraints.REMAINDER;
		Gbc.anchor=GridBagConstraints.CENTER;
		Gbc.weightx=0.2;
		Gbc.insets= new Insets(5,5,5,5);
		Gbc.gridy=0;
		this.add(label_name, Gbc);
		
		
		String type = trainer.getCurrentImac().getTypeImac();
		if(type == "Default") type = "No Type";
		label_imac = new JLabel(trainer.getCurrentImac().getName()+" -- Type : "+type);
		Font font_Imac = label_name.getFont();
		font_Imac = new Font("Arial", Font.BOLD, 10);
		label_imac.setFont(font_Imac);
		Gbc.anchor=GridBagConstraints.PAGE_START;
		Gbc.gridy=1;
		this.add(label_imac, Gbc);
		
		life_point = new JLabel(trainer.getCurrentImac().getLife()+"/"+total_life);
		Font font_LifePoints = label_name.getFont();
		font_LifePoints = new Font("Arial", Font.BOLD, 10);
		life_point.setFont(font_LifePoints);
		Gbc.anchor=GridBagConstraints.PAGE_START;
		Gbc.gridy=2;
		this.add(life_point, Gbc);
		
		//ImageIcon img = new ImageIcon("ia.png");
		ImageIcon img = new ImageIcon(new ImageIcon(trainer.getCurrentImac().getUrlImg()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		icon = new JLabel(img, JLabel.CENTER);
		System.out.println(trainer.getCurrentImac().getUrlImg());
		Gbc.anchor=GridBagConstraints.PAGE_START;
		Gbc.gridy=3;
		this.add(icon, Gbc);
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
					//icon.setIcon(new ImageIcon("ia.png"));

					icon.setIcon(new ImageIcon(new ImageIcon(trainer.getCurrentImac().getUrlImg()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
					icon.revalidate();
					
					String type = trainer.getCurrentImac().getTypeImac();
					if(type == "Default" || type == null || type == "") type = "No Type";
					label_imac.setText(trainer.getCurrentImac().getName()+" -- Type : "+type);
					label_imac.revalidate();
					
					life_point.setText(trainer.getCurrentImac().getLife()+"/"+total_life);
					life_point.revalidate();					
					break;
				default:
					break;
			}
		}
		
	}
	
	
}
