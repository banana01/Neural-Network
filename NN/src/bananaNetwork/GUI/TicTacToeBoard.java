package bananaNetwork.GUI;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class TicTacToeBoard extends JPanel implements ActionListener
{

	/**
	 * Create the panel.
	 */
	private int temp1;
	JButton[] buttons;
	int[] board = new int[9];
	BufferedImage imgx, imgo, imgb;
    ImageIcon iconx, icono, iconb; 
	public TicTacToeBoard(Path x, Path o, Path empty) throws IOException 
	{
		imgx = ImageIO.read(new File(x.toString()));
		iconx = new ImageIcon(imgx.getScaledInstance(128, 128, Image.SCALE_SMOOTH));
		imgo = ImageIO.read(new File(x.toString()));
		icono = new ImageIcon(imgx.getScaledInstance(128, 128, Image.SCALE_SMOOTH));
		imgb = ImageIO.read(new File(x.toString()));
		iconb = new ImageIcon(imgx.getScaledInstance(128, 128, Image.SCALE_SMOOTH));
		setLayout(new GridLayout(3, 3, 0, 0));
		buttons = new JButton[9];
		for (int i = 0; i < buttons.length; i++) 
		{
			buttons[i] = MyFactory.createBoardPosButton(iconb);
			buttons[i].setName(""+i);
			board[i] = 0;
			add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		
	}
	public void updateButtons()
	{
		for (temp1 = 0; temp1 < buttons.length-1; temp1++) 
		{
			if(board[temp1] == 1)
			{
				buttons[temp1].setIcon(iconx);
			}
			if(board[temp1] == -1)
			{
				buttons[temp1].setIcon(icono);
			}
			else
			{
				buttons[temp1].setIcon(iconb);
			}
			
		}
	}
	public void paint(Graphics g)
	{
		updateButtons();
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton temp = (JButton) e.getSource();
		modifyBoard(Integer.parseInt(temp.getName()), 1);
		repaint();
		System.out.println(board[Integer.parseInt(temp.getName())]);
		
	}
	public void modifyBoard(int loc, int type)
	{
		if(loc == 0)
		{
			board[loc] = type;
		}
	}

}
