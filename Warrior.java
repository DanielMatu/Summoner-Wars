import java.awt.*;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
//import java.awt.Math;

//import hsa.Console;

public class Warrior extends Creature
{
    static Image numbers;
    static Image cultist_appearance;
    static boolean rolled_six;
    public int life;
    public int attack;
   

    public Warrior(){
	super();
	this.attack = 2;
	this.life = 2;
	numbers = new ImageIcon("C:\\Users\\daniel\\Desktop\\e\\BRUH\\numbers.jpg").getImage();
	//action_bar = new ImageIcon("C:\\Users\\daniel\\Desktop\\e\\BRUH\\optionbar.jpg").getImage();
	cultist_appearance = new ImageIcon("C:\\Users\\daniel\\Desktop\\e\\BRUH\\cultist.jpeg").getImage();
    }
    
    public int warriorEffect(Object Target, Graphics g){
       int damage = 0;
       int one_to_six = (int)(Math.round(Math.random()*6));
       if (one_to_six >= 4){
	   // finish after moving has been programmed
	   g.drawImage(numbers, x, y, width, height, null);
       } 
       return damage;
    }
    
    public int getLife(){
	return this.life;
    }

    
} // Card class
