// The "Card" class.
import java.awt.*;
import java.awt.Graphics;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
//import java.awt.Math;

//import hsa.Console;

public class Creature extends Card
{
    public int player;
    static int attack;
    static int life;
    static boolean menuOpen = false;
    static Image action_bar;
    public boolean clicked = false;
    private int original_i;
    private int original_j;

    public Creature(){
	super();
	//this.action_bar = new ImageIcon("C:\\Users\\daniel\\Desktop\\e\\BRUH\\optionbar.jpg").getImage();
    }
    

    
    public int attackTarget(Object Target){
       double one_to_six = Math.random()*100/16.7;
       int ceiled_one_to_six = (int)(Math.ceil(one_to_six));
       int damage = 0;
       int count = 0;
       while(attack != count){
	   if (ceiled_one_to_six >= 3){
	       damage += 1;
	   }
	   count += 1;
       } 
       return damage;
    }
    
    public void drawMenu(Graphics g){
	g.drawImage(action_bar, x, y, (int)(width/4), (int)(height/4), null);
    }
    
    public boolean getClickedStatus(){
	return this.clicked;
    }
    
    public int getPlayer(){
	return this.player;
    }
    
    public void setPlayer(int passed_player){
	this.player = passed_player;
    }
    
    public void MoveTo (int i, int j, int destinationI, int destinationJ, int fp, Mat m)
    {
	// if its the first pass replenish the creatures
	// amount of moves possible so that even if it was
	// exhausted by a failed illegal move
	// it can still move legally afterwards\
	if (fp == 1)
	{
	    //this.square_moves = this.max_square_moves;
	    original_i = i;
	    original_j = j;
	    fp = -1;
	}
	//System.out.println("di: " + String.valueOf(destinationI) + "    i: " + String.valueOf(i) + "    dj: " + String.valueOf(destinationJ) + "    j: " + String.valueOf(j));

	if (destinationI > i)
	{
	    this.MoveTo (i + 1, j, destinationI, destinationJ, fp, m);
	}
	if (destinationI < i)
	{
	    this.MoveTo (i - 1, j, destinationI, destinationJ, fp, m);
	}
	if (destinationJ > j)
	{
	    this.MoveTo (i, j + 1, destinationI, destinationJ, fp, m);
	}
	if (destinationJ < j)
	{
	    this.MoveTo (i, j - 1, destinationI, destinationJ, fp, m);
	}
	// need to track if a change was made since if we dont
	// we will set the creature and clear it in the same spot,
	// resulting in only clearing the creature and not actually moving.

	if (destinationI == i && destinationJ == j && (m.returnGrid()[i][j] instanceof Wall == false && m.returnGrid()[i][j] instanceof Creature == false) )
	{
	    this.setX (destinationI * 147 + 15);
	    this.setY (547 - 53 * destinationJ);
	    m.setCreature (this, i, j);
	    m.clearCard (original_i, original_j);
	    m.setPhaseReference (4);
	    m.setLastClickedCardOnMat (this);
	}
    }


    
    
} // Card class
