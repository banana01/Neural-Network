package bananaNetwork.GUI;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;

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
			buttons[i].addComponentListener(new ComponentAdapter() {

                @Override
                public void componentResized(ComponentEvent e) {
                    TTTButton btn = (TTTButton) e.getComponent();
                    Dimension size = btn.getSize();
                    Insets insets = btn.getInsets();
                    size.width -= insets.left + insets.right;
                    size.height -= insets.top + insets.bottom;
                    if (size.width > size.height) {
                        size.width = -1;
                    } else {
                        size.height = -1;
                    }
                    Image scaled = btn.getImg().getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
                    btn.setIcon(new ImageIcon(scaled));
                }
            });
		}
		
		
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

	public TTTButton[] getButtons() {
		return buttons;
	}

	public void setButtons(TTTButton[] buttons) {
		this.buttons = buttons;
	}

	public void initButtons() throws IOException
	{
		for(int i = 0; i < buttons.length; i++)
		{
			buttons[i].initIcons();
		}
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
				temp.setType(1);
			}
			
		}
		else
		{
			if(modifyBoard(Integer.parseInt(temp.getName()), -1))
			{
				turn = true;
				temp.setType(-1);
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
		try {
			temp.onChange();
		} catch (IOException e1) {
		}
		Dimension size = temp.getSize();
        Insets insets = temp.getInsets();
        size.width -= insets.left + insets.right;
        size.height -= insets.top + insets.bottom;
        if (size.width > size.height) {
            size.width = -1;
        } else {
            size.height = -1;
        }
		Image scaled = temp.getImg().getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
		temp.setIcon(new ImageIcon(scaled));
		
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
