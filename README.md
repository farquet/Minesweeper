#Minesweeper

![Minesweeper game screenshot](http://francois.farquet.com/images/projects/screenshot-minesweeper.png)

This is a simple minesweeper game. 
The game is developped for the Mac OSX JVM. This illustrates how nice look native elements on this JVM compared to the Windows JVM.
Indeed, in this project no image were used. Each cell is represented by a JButton. We use the disabled state of a button if the cell and its neighbours contain no bomb.

On the left side of the "Start again" button there is the counter of bombs that still need to be discovered. On the other side it is just a timer to get some pressure.

You can flag a cell if you think there is a bomb on it. You can do it with alt+click, cmd+click, maj+click, ... (everything that is not a regular click).

There are a few options from the menu bar such as difficulty or size.

The folder contains :

*	The runnable application as a JAR
*	The runnaple OSX app in a zip file
*	All source code in Minesweeper folder

___

Project done by François Farquet in April 2011. &copy; All rights reserved.
