import java.awt.*;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class DisplayCard extends Card
{

    public Card card_to_display;
    
    public DisplayCard(){
	super();
	this.setX(905);
	this.setY(310);
	this.setCardWidth(435);
	this.setCardHeight(195);
	this.setAppearance();
    }
    
    public void setCardToDisplay(Card c){
	this.card_to_display = c;
	// take the name of the card to display so we can display it 
	// with the coordinates of our instantiated object
	this.setName(this.card_to_display.getClass().getName());
	this.setAppearance();
    }
    
    public Card getCardToDisplay(){
	return this.card_to_display;
    }
    
    public int getCTDX(){
	return this.card_to_display.getX();
    }
    public int getCTDY(){
	return this.card_to_display.getY();
    }
    public int getCTDWidth(){
	return this.card_to_display.getCardWidth();
    }
    public int getCTDHeight(){
	return this.card_to_display.getCardHeight();
    }
    public void Clear(){
	Card filler = new Card();
	this.card_to_display = filler;
    }
    public void setMainGameDimensions(){
	this.setX(905);
	this.setY(310);
    }
    
    public void setPileScreenDimensions(){
	this.setX(480);
	this.setY(500);
    }
}
