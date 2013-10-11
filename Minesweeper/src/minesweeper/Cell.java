/*
 *	Author:      Francois Farquet
 *	Date:         3 avr. 2011
 */

package minesweeper;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Cell
{
	private boolean bomb=false;
	private boolean flagged=false;
	private boolean visible=false;
	private int bombNumberAround=0;
	private JButton aCase;
	private JLabel bombsLeft;
	private int row;
	private int col;
	private Grid grid;
	
	public Cell(Grid grid, int row, int col, JLabel bombsLeft)
	{
		aCase = new JButton("");
		aCase.setBackground(Color.GRAY);
		this.row = row;
		this.col = col;
		this.grid = grid;
		this.bombsLeft = bombsLeft;
	}
	
	public void setBomb()
	{
		bomb=true;
	}
	
	public boolean getBomb()
	{
		return bomb;
	}
	
	public void setVisible()
	{
		if(!visible)
		{
			if(bomb)
			{
				aCase.setText("X");
				Settings.flagCount++;
				bombsLeft.setText(""+(Settings.nbBombs - Settings.flagCount));
				grid.setFinished();
				JOptionPane.showMessageDialog(null,"Boooom !!!");
			}
			else
			{
				if(flagged)
				{
					Settings.flagCount--;
					bombsLeft.setText(""+(Settings.nbBombs - Settings.flagCount));
				}
				
				Color caseColor;
				switch(bombNumberAround)
				{
				case 8 :
					caseColor = Color.WHITE;
					break;
				case 7 :
					caseColor = Color.CYAN;
					break;
				case 6 :
					caseColor = Color.MAGENTA;
					break;
				case 5 :
					caseColor = Color.ORANGE;
					break;
				case 4 :
					caseColor = Color.PINK;
					break;
				case 3 :
					caseColor = Color.RED;
					break;
				case 2 :
					caseColor = Color.BLUE;
					break;
				case 1 :
					caseColor = Color.DARK_GRAY;
					break;
				case 0 :
				default :
					disableCase();
					caseColor = Color.DARK_GRAY;
				}
				if(bombNumberAround != 0)
				{
					aCase.setText(bombNumberAround+"");
					aCase.setForeground(caseColor);
				}
			}
			visible = true;
			
			if(!this.getBomb() && this.getNbBomb() == 0)
				grid.checkAround(this);
		}
	}
	
	public void disableCase()
	{
		aCase.setText("");
		aCase.setEnabled(false);
	}
	
	public boolean isVisible()
	{
		return visible;
	}
	
	public void toggleFlag()
	{
		if(!flagged)
		{
			flagged = true;
			aCase.setText("F");
			Settings.flagCount++;
			bombsLeft.setText(""+(Settings.nbBombs - Settings.flagCount));
		}
		else
		{
			flagged = false;
			aCase.setText("");
			Settings.flagCount--;
			bombsLeft.setText(""+(Settings.nbBombs - Settings.flagCount));
		}
	}
	
	public boolean getFlaged()
	{
		return flagged;
	}
	
	public void setNbBomb(int nb)
	{
		bombNumberAround = nb;
	}
	
	public int getNbBomb()
	{
		return bombNumberAround;
	}
	
	public JButton getCase()
	{
		return aCase;
	}
	
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
}
