/*
 *	Author:      Francois Farquet
 *	Date:         3 avr. 2011
 */


package minesweeper;

import java.util.TimerTask;

import javax.swing.JLabel;

public class Counter extends TimerTask
{
	private JLabel time;
	
	public Counter(JLabel time)
	{
		this.time = time;
	}
	public void run()
	{
		if(Settings.hasStarted)
			time.setText(""+(Integer.valueOf(time.getText())+1));
	}

}
