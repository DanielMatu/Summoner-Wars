// The "Card" class.
import java.awt.*;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

//import hsa.Console;

public class Hand extends JFrame
{    
    public Card hand[] = new Card[5];
    public Card last_clicked;
    public Card filler = new Card();
    public int last_clicked_slot;

    public Hand ()
    {
    }
    
    public void setHandSlot(int slot, Card c){
	this.hand[slot] = c;
    }
    
    public Card getHandSlot(int slot){
	return this.hand[slot];
    }
    
    public void drawHand(Graphics g){
	for (int i=0; i<5; i++){
	    //System.out.println(this.getHandSlot(i) instanceof Card);
	    if (this.getHandSlot(i) instanceof Card){
		this.getHandSlot(i).drawCard1(g);
	    }
	}
    }
    
    public boolean getHandSlotClicked(int player, int slot, int mx, int my){
	return (mx >= 21 + 157*slot && mx <= 171 + 157*slot && my <=742 && my >= 689);
    }
    
    public void drawTrackers(Graphics g, int player){
	g.setColor(Color.yellow);
	for (int slot = 0; slot<5;  slot++){
	    g.drawRect(slot*157 + 21, 684, 157, 63);
	}
    }
    
    public void setLastClicked(Card c){
	this.last_clicked = c;
    }
    
    public Card getLastClicked(){
	return this.last_clicked;
    }
 
    public void clearLastClickedSlot(){
	Card filler = new Card();
	this.last_clicked = filler;
    }
    
    public void setLastClickedSlot(int slot){
	this.last_clicked_slot = slot;
    }
    
    public int getLastClickedSlot(){
	return this.last_clicked_slot;
    }
    
    public void nothingClicked(){
	this.last_clicked = filler;
    }
    
    public void clearHandSlot(int slot){
	Card filler = new Card();
	this.hand[slot] = filler;
    }
    
    public void otherCardsNotClicked(int slot){
	for (int i=0; i<5; i++){
	    if (i != slot){
		this.getHandSlot(i).setEventPrompted(false);
	    }
	}
    }
} // Card class
