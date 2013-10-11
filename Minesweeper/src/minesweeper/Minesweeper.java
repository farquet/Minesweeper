/*
 *	Author:      Francois Farquet
 *	Date:         3 avr. 2011
 */
 
package minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Minesweeper
{
	
	public static void main(String[] args) throws IOException
	{
		JFrame frame = new JFrame();
		JPanel container = new JPanel();
		JPanel title = new JPanel();
		JPanel panel = new JPanel();
		JLabel bombsLeft = new JLabel("", JLabel.CENTER);
		JLabel time = new JLabel("0", JLabel.CENTER);
		JButton restart = new JButton("Start again");
		restart.addActionListener(new RestartAction(restart,panel,frame,bombsLeft,time));
		
		// Default values
		Settings.nbRow = 15;
		Settings.nbCol = 15;
		Settings.nbBombs = 30;
		Settings.frameSize = 510;
		Settings.flagCount = 0;
		Settings.level = 2;
		
		JMenuBar menuBar = new JMenuBar();
		JMenu size = new JMenu("Size");
		JMenu difficulty = new JMenu("Difficulty");
		
		JMenuItem easy = new JMenuItem("Easy");
		JMenuItem medium = new JMenuItem("Medium");
		JMenuItem hard = new JMenuItem("Hard");
		
		JMenuItem small = new JMenuItem("Small - 10x10");
		JMenuItem big = new JMenuItem("Medium - 15x15");
		JMenuItem huge = new JMenuItem("Large - 20x20");
		
		easy.addActionListener(new MenuAction(frame,panel,bombsLeft,time));
		medium.addActionListener(new MenuAction(frame,panel,bombsLeft,time));
		hard.addActionListener(new MenuAction(frame,panel,bombsLeft,time));
		
		small.addActionListener(new MenuAction(frame,panel,bombsLeft,time));
		big.addActionListener(new MenuAction(frame,panel,bombsLeft,time));
		huge.addActionListener(new MenuAction(frame,panel,bombsLeft,time));
		
		frame.setLayout(new BorderLayout());
		container.setLayout(new BorderLayout());
		title.setLayout(new GridLayout(1,3));
		frame.setBackground(Color.LIGHT_GRAY);
		
		difficulty.add(easy);
		difficulty.add(medium);
		difficulty.add(hard);
		
		size.add(small);
		size.add(big);
		size.add(huge);
		
		menuBar.add(size);
		menuBar.add(difficulty);
		
		title.add(bombsLeft);
		title.add(restart);
		title.add(time);
		
		Timer timer = new Timer();
		timer.schedule(new Counter(time), 1000, 1000);
		
		Grid grille = new Grid(Settings.nbRow, Settings.nbCol, Settings.nbBombs, bombsLeft);
		
		grille.constructPanel(panel);
		
		container.add(title, BorderLayout.NORTH);
		container.add(panel, BorderLayout.CENTER);
		
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    frame.setLocation((dim.width-Settings.frameSize)/2, (dim.height-Settings.frameSize)/2);
		frame.setSize(Settings.frameSize, Settings.frameSize);
		frame.setResizable(false);
		frame.add(menuBar, BorderLayout.NORTH);
		frame.add(container, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}
}

class updatePanel implements ActionListener
{
	private JPanel panel;
	private JTextField fieldRow;
	private JTextField fieldCol;
	
	public updatePanel(JPanel panel, JTextField fieldRow, JTextField fieldCol)
	{
		this.panel = panel;
		this.fieldRow = fieldRow;
		this.fieldCol = fieldCol;
	}
	public void actionPerformed(ActionEvent e)
	{
		panel.removeAll();
		int nbRow = Integer.valueOf(fieldRow.getText());
		int nbCol = Integer.valueOf(fieldCol.getText());
		panel.setLayout(new GridLayout(nbRow, nbCol));
		
		for(int i=0; i < nbRow; i++)
		{
			for(int j=0; j < nbCol; j++)
			{
				panel.add(new JButton((nbCol*i+j)+""));
			}
		}
		panel.revalidate();
	}
}