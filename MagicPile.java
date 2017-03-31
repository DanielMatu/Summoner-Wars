import java.awt.*;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
//import java.awt.Math;

//import hsa.Console;

public class MagicPile
{
    public Card magic_pile[] = new Card [30];
    public int count;
    public Dimension screenSize;
    public double screen_width;
    public double screen_height;
    public int arrow_count;
    public int c_index;

    public MagicPile ()
    {
	this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	this.screen_width = screenSize.getWidth();
	this.screen_height = screenSize.getHeight();
	this.count = 0;
	this.arrow_count = 0;
    }


    public void addToMagic (Card c)
    {
	this.magic_pile [this.count] = c;
	c.setPileIndex(this.count);
	this.count++;
    }


    public void toDiscard (Card c, DiscardPile dp)
    {
	this.screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
	this.screen_width = screenSize.getWidth ();
	this.screen_height = screenSize.getHeight ();
	dp.addToDiscard (this.magic_pile [this.count]);
	Card filler = new Card ();
	this.magic_pile [this.count] = filler;
    }


    public void magicSearch (Graphics g)
    {
	Image discard;
	discard = new ImageIcon ("C:\\Users\\daniel\\Desktop\\e\\BRUH\\Discard.jpg").getImage ();
	g.drawImage (discard, 0, 0, (int) this.screen_width, (int) this.screen_height, null);
	for (int i = 0 ; i < 5 ; i++)
	{
	    try
	    {
		this.magic_pile [i + this.arrow_count*5].setX (279 + 160 * i);
		this.magic_pile [i + this.arrow_count*5].setY (336);
		this.magic_pile [i + this.arrow_count*5].setCardWidth (magic_pile [i + this.arrow_count*5].getCardWidth ());
		this.magic_pile [i + this.arrow_count*5].setCardHeight (magic_pile [i + this.arrow_count*5].getCardHeight ());
		this.magic_pile [i + this.arrow_count*5].drawCard1(g);
	    }
	    catch (NullPointerException n)
	    {
	    }
	}
    }
    
    public int returnNumberOfCards(){
	return this.count;
    }
    
    public Card[] getMagicPile(){
	return this.magic_pile;
    }
    
    public int getArrowCount(){
	return this.arrow_count;
    }
    
    public void addToArrowCount(){
	if (this.arrow_count <= 5){
	    this.arrow_count++;
	}
    }
    
    public void subtractToArrowCount(){
	if (this.arrow_count > 0){
	    this.arrow_count--;
	}
    }
    
    public void clearCard(Card c){
	Card filler = new Card();
	this.c_index = c.getPileIndex();
	this.magic_pile[this.c_index] = filler;
	this.c_index++;
	while(this.magic_pile[this.c_index] instanceof Creature || this.magic_pile[c_index] instanceof Wall){
	    this.magic_pile[this.c_index].setPileIndexMinusOne();
	    this.magic_pile[this.c_index - 1] = this.magic_pile[this.c_index];
	    this.magic_pile[this.c_index] = filler;
	    this.c_index ++;
	} 
	
	
	 this.count--;

    }


} // FallenPhoenixDeck class
