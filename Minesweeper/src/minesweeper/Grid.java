/*
 *	Author:      Francois Farquet
 *	Date:         3 avr. 2011
 */

package minesweeper;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Grid
{
	private int nbRow;
	private int nbCol;
	private int nbBombs;
	private Cell[][] cells;
	private boolean finished;
	private boolean builded;
	
	public Grid(int nbRow, int nbCol, int nbBombs, JLabel label)
	{		
		this.nbRow = nbRow;
		this.nbCol = nbCol;
		this.nbBombs = nbBombs;
		builded = false;
		cells = new Cell[nbRow][nbCol];
		
		for(int i=0; i < nbRow; i++)
			for(int j=0; j<nbCol; j++)
			{
				cells[i][j] = new Cell(this, i, j, label);
			}
		
		label.setText(""+(Settings.nbBombs-Settings.flagCount));
		
	}
	
	public void buildNewGame(int notAvailableRow,int notAvailableCol){

		placeBombs( notAvailableRow, notAvailableCol);
		findNearBombs();
		builded = true;
	}
	
	private void placeBombs(int notAvailableRow,int notAvailableCol)
	{
		Random rand = new Random();
		int randRow, randCol;
		for(int i=0; i < nbBombs; i++)
		{
			do
			{
				randRow = rand.nextInt(nbRow);
				randCol = rand.nextInt(nbCol);
			}
			while(cells[randRow][randCol].getBomb() || (randRow==notAvailableRow && notAvailableCol==randCol));
			
			cells[randRow][randCol].setBomb();
		}
	}
	
	private void findNearBombs()
	{
		for(int i=0; i < nbRow; i++)
			for(int j=0; j < nbCol; j++)
			{
				int totalBombs = 0;
				
				if(isIndexOk(i-1,j) && cells[i-1][j].getBomb()) // up
					totalBombs++;
				if(isIndexOk(i-1,j-1) && cells[i-1][j-1].getBomb()) // up left
					totalBombs++;
				if(isIndexOk(i,j-1) && cells[i][j-1].getBomb()) // left
					totalBombs++;
				if(isIndexOk(i+1,j-1) && cells[i+1][j-1].getBomb()) // down left
					totalBombs++;
				if(isIndexOk(i+1,j) && cells[i+1][j].getBomb()) // down
					totalBombs++;
				if(isIndexOk(i+1,j+1) && cells[i+1][j+1].getBomb()) // down right
					totalBombs++;
				if(isIndexOk(i,j+1) && cells[i][j+1].getBomb()) // right
					totalBombs++;
				if(isIndexOk(i-1,j+1) && cells[i-1][j+1].getBomb()) // up right
					totalBombs++;
				
				cells[i][j].setNbBomb(totalBombs);
			}
	}
	
	private boolean isIndexOk(int i, int j)
	{
		boolean result = false;
		
		if(i >= 0 && i < nbRow && j >= 0 && j < nbCol)
			result = true;
		
		return result;
	}
	
	public void constructPanel(JPanel panel)
	{
		panel.removeAll();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new GridLayout(nbRow, nbCol));
		for(int i=0; i < nbRow; i++)
		{
			for(int j=0; j < nbCol; j++)
			{
				panel.add(cells[i][j].getCase());
				cells[i][j].getCase().addMouseListener(new ButtonAction(cells[i][j], this));
			}
		}
		panel.revalidate();
	}
	
	public void checkAround(Cell aCase)
	{
		int i = aCase.getRow();
		int j = aCase.getCol();
		
		if(isIndexOk(i-1,j) && !cells[i-1][j].getBomb()) // up
			cells[i-1][j].setVisible();
		if(isIndexOk(i-1,j-1) && !cells[i-1][j-1].getBomb()) // up left
			cells[i-1][j-1].setVisible();
		if(isIndexOk(i,j-1) && !cells[i][j-1].getBomb()) // left
			cells[i][j-1].setVisible();
		if(isIndexOk(i+1,j-1) && !cells[i+1][j-1].getBomb()) // down left
			cells[i+1][j-1].setVisible();
		if(isIndexOk(i+1,j) && !cells[i+1][j].getBomb()) // down
			cells[i+1][j].setVisible();
		if(isIndexOk(i+1,j+1) && !cells[i+1][j+1].getBomb()) // down right
			cells[i+1][j+1].setVisible();
		if(isIndexOk(i,j+1) && !cells[i][j+1].getBomb()) // right
			cells[i][j+1].setVisible();
		if(isIndexOk(i-1,j+1) && !cells[i-1][j+1].getBomb()) // up right
			cells[i-1][j+1].setVisible();
	}
	
	public void setFinished()
	{
		finished = true;
		Settings.hasStarted = false;
		builded = false;
		viewBombs();
	}
	
	public boolean finished()
	{
		return finished;
	}
	public boolean builded()
	{
		return builded;
	}
	
	public boolean check() // return true if the player has won
	{
		for(int i=0; i < nbRow; i++)
			for(int j=0; j < nbCol; j++)
			{
				if(!cells[i][j].getBomb() && !cells[i][j].isVisible())
					return false;
			}
		return true;
	}
	
	public void congratulations()
	{
		viewBombs();
		JOptionPane.showMessageDialog(null,"Congratulations !");
	}
	
	public void viewBombs()
	{
		for(int i=0; i < nbRow; i++)
			for(int j=0; j < nbCol; j++)
				if(cells[i][j].getBomb()){
					//cells[i][j].getCase().setText("X");
					 ImageIcon mine_icon = new ImageIcon("imgs/mine.png");
					 cells[i][j].getCase().setIcon(mine_icon);
						
				}
	}
}
