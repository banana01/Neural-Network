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
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class TicTacToeBoard extends JPanel implements ActionListener
{

	/**
	 * Create the panel.
	 */
	private int temp1;
	JButton[] buttons;
	int[] board = {0,0,0,0,0,0,0,0,0};
	BufferedImage imgx, imgo, imgb;
    ImageIcon iconx, icono, iconb;
    JLabel Turnlabel;
    boolean turn = true;
	public TicTacToeBoard(Path x, Path o, Path empty) throws IOException 
	{
		imgx = ImageIO.read(new File(x.toString()));
		iconx = new ImageIcon(imgx.getScaledInstance(128, 128, Image.SCALE_SMOOTH));
		imgo = ImageIO.read(new File(o.toString()));
		icono = new ImageIcon(imgo.getScaledInstance(128, 128, Image.SCALE_SMOOTH));
		imgb = ImageIO.read(new File(empty.toString()));
		iconb = new ImageIcon(imgb.getScaledInstance(128, 128, Image.SCALE_SMOOTH));
		setLayout(new BorderLayout(0, 0));
		
		Turnlabel = new JLabel("true");
		add(Turnlabel, BorderLayout.NORTH);
		
		JPanel gamepanel = new JPanel();
		add(gamepanel, BorderLayout.CENTER);
		gamepanel.setLayout(new GridLayout(3, 3, 0, 0));
		buttons = new JButton[9];
		for (int i = 0; i < buttons.length; i++) 
		{
			buttons[i] = MyFactory.createBoardPosButton(iconb);
			buttons[i].setName(""+i);
			gamepanel.add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		//repaint();
		
	}
	public void updateButtons()
	{
		for (temp1 = 0; temp1 < buttons.length; temp1++) 
		{
			//System.out.println(temp1);
			if(board[temp1] == 1)
			{
				buttons[temp1].setIcon(iconx);
			}
			else if(board[temp1] == -1)
			{
				buttons[temp1].setIcon(icono);
			}
			else
			{
				buttons[temp1].setIcon(iconb);
			}
			
		}
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton temp = (JButton) e.getSource();
		if(turn == true)
		{
			if(modifyBoard(Integer.parseInt(temp.getName()), 1))
			{
				turn = false;
			}
			
			
		}
		else
		{
			if(modifyBoard(Integer.parseInt(temp.getName()), -1))
			{
				turn = true;
			}
			
			
		}
		Turnlabel.setText(Boolean.toString(turn));
		updateButtons();
		repaint();

	}
	public boolean modifyBoard(int loc, int type)
	{
		if(board[loc] == 0)
		{
			board[loc] = type;
			return true;
		}
		return false;
	}

}
