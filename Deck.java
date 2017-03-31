import java.awt.*;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
//import java.awt.Math;

//import hsa.Console;

public class Deck
{

    public int card_count = 30;
 
    public Deck ()
    {
    }
    
    public void setCardCountMinusOne(){
	this.card_count--;
    }
    
    public int getCardCount(){
	return this.card_count;
    }

} // FallenPhoenixDeck class
