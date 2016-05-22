/*
 *	Author:      Francois Farquet
 *	Date:         3 avr. 2011
 */


package minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RestartAction implements ActionListener
{
	private JFrame frame;
	private JPanel panel;
	private JButton restart;
	private JLabel bombsLeft;
	private JLabel time;
	
	public RestartAction(JButton restart, JPanel panel, JFrame frame, JLabel bombsLeft, JLabel time)
	{
		this.panel = panel;
		this.restart = restart;
		this.frame = frame;
		this.bombsLeft = bombsLeft;
		this.time = time;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		Grid grille = new Grid(Settings.nbRow, Settings.nbCol, Settings.nbBombs, bombsLeft);
		grille.constructPanel(panel);
		Settings.flagCount = 0;
		bombsLeft.setText(""+(Settings.nbBombs - Settings.flagCount));
		time.setText("0");
		Settings.hasStarted = false;
		
		frame.setSize(Settings.nbCol*30, Settings.nbRow*30);
		restart.setText("Start again");
	}
	
}
