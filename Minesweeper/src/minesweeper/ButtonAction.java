/*
 *	Author:      Francois Farquet
 *	Date:         3 avr. 2011
 */


package minesweeper;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonAction implements MouseListener
{
	private Cell aCell;
	private Grid grid;
	
	public ButtonAction(Cell aCase, Grid grid)
	{
		this.aCell = aCase;
		this.grid = grid;
	}

	public void mouseClicked(MouseEvent e)
	{	
		if(!grid.finished())
		{
			Settings.hasStarted = true;
			
			if(!aCell.isVisible())
    		{
				if(e.getModifiers() == 16) // if it's a regular clic
	    		{
	    			aCell.setVisible();
	    		}
	    	    else // if it's alt+clic, cmd+clic, maj+clic, ...
	    	    {
	    	    	aCell.toggleFlag();
	    	    }
				
				if(grid.check())
				{
					grid.setFinished();
					grid.congratulations();
				}
    		}
		}
    }

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}
}
