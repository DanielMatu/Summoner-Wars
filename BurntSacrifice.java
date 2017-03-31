import java.awt.*;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
//import java.awt.Math;

//import hsa.Console;

public class BurntSacrifice extends EventCard implements MouseListener
{
    static Image numbers;
    static Image cultist_appearance;
   

    public BurntSacrifice(){
	super();
	cultist_appearance = new ImageIcon(".\\cultist.jpeg").getImage();
	addMouseListener(this);
    }
    
    public int Effect(Object Target, Graphics g){
       int damage = 0;
       int one_to_six = (int)(Math.round(Math.random()*6));
       if (one_to_six >= 4){
	   // finish after moving has been programmed
	   g.drawImage(numbers, x, y, width, height, null);
       } 
       return damage;
    }
    
    public void drawCard(Graphics g){
	g.drawImage(cultist_appearance, this.x, this.y, null);
    }
    
    public void BurntSacrificeEffect(){
    }
    
    public void mouseClicked(MouseEvent click){
    }
    public void mouseReleased(MouseEvent e){
    }
    public void mouseEntered(MouseEvent e){
    }
    public void mouseExited(MouseEvent e){
    }
    public void mousePressed(MouseEvent click){
    }
    
    
} // Card class
