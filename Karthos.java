import java.awt.*;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
//import java.awt.Math;

//import hsa.Console;

public class Karthos extends Creature implements MouseListener
{
    static Image numbers;
    static Image cultist_appearance;
    static boolean rolled_six;
   

    public Karthos(){
	super();
	this.attack = 2;
	this.life = 5;
	numbers = new ImageIcon("C:\\Users\\daniel\\Desktop\\e\\BRUH\\numbers.jpg").getImage();
	//action_bar = new ImageIcon("C:\\Users\\daniel\\Desktop\\e\\BRUH\\optionbar.jpg").getImage();
	cultist_appearance = new ImageIcon("C:\\Users\\daniel\\Desktop\\e\\BRUH\\cultist.jpeg").getImage();
	addMouseListener(this);
    }
    
    public int karthosEffect(Object Target, Graphics g){
       int damage = 0;
       int one_to_six = (int)(Math.round(Math.random()*6));
       if (one_to_six >= 4){
	   // finish after moving has been programmed
	   g.drawImage(numbers, x, y, width, height, null);
       } 
       return damage;
    }
    
    public void drawCard(Graphics g){
	g.drawImage(cultist_appearance, x, y, width, height, null);
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
