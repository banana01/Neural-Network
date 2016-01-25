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
	TTTButton[] buttons;
	int[] board = {0,0,0,0,0,0,0,0,0};
	BufferedImage imgx, imgo, imgb;
    ImageIcon iconx, icono, iconb;
    JLabel Turnlabel;
    boolean turn = true;
    int buW, buH;
    Path x,o,e;
	public TicTacToeBoard(Path empty, Path o, Path x) throws IOException 
	{
		setXi(x);
		setO(o);
		setE(empty);
		buttons = new TTTButton[9];
		setLayout(new BorderLayout(0, 0));
		Turnlabel = new JLabel("X's Turn!");
		add(Turnlabel, BorderLayout.NORTH);
		JPanel gamepanel = new JPanel();
		add(gamepanel, BorderLayout.CENTER);
		gamepanel.setLayout(new GridLayout(3, 3, 0, 0));
		
		for (int i = 0; i < buttons.length; i++) 
		{
			buttons[i] = MyFactory.createBoardPosButton(0, empty, o, x);
			buttons[i].setName(""+i);
			gamepanel.add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		//for(int i = 0; i < buttons.length; i++)
		//{
		//	buttons[i].initIcons();
		//}
		System.out.println( buttons[5].getSize());
		
		
	}
	
	
	
	
	
	public Path getXi() {
		return x;
	}
	public void setXi(Path x) {
		this.x = x;
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
	
	
	
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		TTTButton temp = (TTTButton) e.getSource();
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
		if(turn == true)
		{
			Turnlabel.setText("X's Turn!");
		}
		else
		{
			Turnlabel.setText("O's Turn!");
		}
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
