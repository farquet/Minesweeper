/*
 *	Author:      Francois Farquet
 *	Date:         3 avr. 2011
 */


package minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuAction implements ActionListener
{
	private JLabel bombsLeft;
	private JPanel panel;
	private JLabel time;
	private JFrame frame;
	
	public MenuAction(JFrame frame, JPanel panel, JLabel bombsLeft, JLabel time)
	{
		this.bombsLeft = bombsLeft;
		this.panel = panel;
		this.time = time;
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent e)
	{

		if(e.getActionCommand() == "Easy")
		{
			Settings.level = 1;
			Settings.nbRow = 8;
			Settings.nbCol = 8;
			Settings.nbBombs = 10;
			Settings.frameSize = 390;
		}
		else if(e.getActionCommand() == "Medium")
		{
			Settings.level = 2;
			Settings.nbRow = 16;
			Settings.nbCol = 16;
			Settings.nbBombs = 40;
			Settings.frameSize = 510;
		}
		else if(e.getActionCommand() == "Hard")
		{
			Settings.level = 3;
			Settings.nbRow = 16;
			Settings.nbCol = 30;
			Settings.nbBombs = 99;
			Settings.frameSize = 750;
		}
		
		
		Grid grille = new Grid(Settings.nbRow, Settings.nbCol, Settings.nbBombs, bombsLeft);
		grille.constructPanel(panel);
		Settings.flagCount = 0;
		bombsLeft.setText(String.valueOf(Settings.nbBombs - Settings.flagCount));
		time.setText("0");
		Settings.hasStarted = false;
		
		frame.setSize(Settings.nbCol * 30, Settings.nbRow * 30);
	}

}
