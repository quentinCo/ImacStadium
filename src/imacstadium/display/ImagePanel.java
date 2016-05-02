package imacstadium.display;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.JPanel;

import imacstadium.imac.ImacHeader;

public class ImagePanel extends JPanel {
	
	public void paintComponent(Graphics g){
		Image img;
    	Path path = FileSystems.getDefault().getPath(new File("").getAbsolutePath() + "/data/picture/logo/LOGO_IMAC_STADIUM.png");
	    try {
	      img = ImageIO.read(new File(path.toString()));
	      //g.drawImage(img, 0, 0, this);
	      //Pour une image de fond
	      g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}

}
