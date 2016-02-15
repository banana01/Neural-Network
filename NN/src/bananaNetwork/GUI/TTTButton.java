package bananaNetwork.GUI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TTTButton extends JButton 
{
	int type;
	Path o,e,x;
	BufferedImage img;
	ImageIcon icon; 
	TTTButton self = this;
	public TTTButton(int t, Path e, Path o, Path x) throws IOException
	{
		setO(o);
		setE(e);
		setXL(x);
		setType(t);
	    img = ImageIO.read(e.toFile());


	}
	public void initIcons() throws IOException
	{
		img = ImageIO.read(e.toFile());
	}
	public Path getO() {
		return o;
	}
	public void setO(Path o) {
		this.o = o;
	}
	public Path getE() {
		return e;
	}
	public void setE(Path e) {
		this.e = e;
	}
	public Path getXL() {
		return x;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setXL(Path x) {
		this.x = x;
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
	}
	 
	public BufferedImage getImg() {
		return img;
	}
	public void setImg(BufferedImage img) {
		this.img = img;
	}
	public void onChange() throws IOException
	{
		if(type == 1)
		{
			img = ImageIO.read(x.toFile());
		}
		else if(type == -1)
		{
			img = ImageIO.read(o.toFile());
		}
		else
		{
			img = ImageIO.read(e.toFile());
		}
		//icon = new ImageIcon(img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH));
	}
}
