package imacstadium.page;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import imacstadium.game.Game;

public class Page  extends JFrame implements KeyListener{

	private JPanel panel;
	
	/* Costructor */
	public Page (String namePage){
		super(namePage);
		this.panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1));
		panel.setPreferredSize(new Dimension(100, 100));
		panel.setForeground(Color.GREEN);
		add(panel, BorderLayout.NORTH);
		addKeyListener (this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
		public void windowOpened(WindowEvent e) { 
				requestFocus();	
			}
		});
		pack();
		setVisible(true);
	}	
	
	/* Event gestion */
	public void keyTyped(KeyEvent e) {}	 
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
	/* Functions */
	public void update(){}

	/* Dream Team Functions */
	@Override
	public String toString() {
		return "Page [panel=" + panel + "]";
	};
	
	
}
