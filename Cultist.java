// The "Card" class.
import java.awt.*;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import java.lang.reflect.*;
import java.lang.reflect.Method;
//import java.awt.Math;

//import hsa.Console;

public class Cultist extends Creature
{
    public Image numbers;
    public Image cultist_appearance;
    public boolean rolled_six;
    public int square_moves;
    public int max_square_moves;
    public boolean cultist_moved;
    private int original_i;
    private int original_j;
    private boolean change_made;
    private int current_square_moves;
    public boolean attacked;

    public void init ()
    {
    }


    public Cultist ()
    {
	super ();
	this.attack = 1;
	this.life = 3;
	this.current_square_moves = 2;
	this.square_moves = 2;
	this.max_square_moves = 2;
	numbers = new ImageIcon (".\\numbers.jpg").getImage ();
	//action_bar = new ImageIcon("C:\\Users\\daniel\\Desktop\\e\\BRUH\\optionbar.jpg").getImage();
	this.cultist_appearance = new ImageIcon (".\\cultist.jpeg").getImage ();
    }


    public void drawCard (Graphics g)
    {
	g.drawImage (cultist_appearance, this.x, this.y, null);
    }


    public void paint (Graphics g)
    {
	g.setFont (new Font ("Arial", Font.PLAIN, 72));
	g.setColor(Color.yellow);
	g.drawImage (numbers, this.x, this.y, this.width, this.height, null);
    }

    public int CultistEffect (Object Target, Graphics g)
    {
	int count = 0;
	int damage = 0;
	g.drawImage (numbers, this.x, this.y, this.width, this.height, null);

	return damage;
    }


    public int CultistAttack (Card Target)
    {
	if (this.attacked == false)
	{
	    int rng = -1;
	    int count = 0;
	    while (count < this.attack)
	    {
		count++;
		rng = ((int) Math.round (Math.random () * 6));
		//System.out.println(rng);
		this.attacked = true;
		if (rng >= 3)
		{
		    Target.takeWound ();
		}
		else
		{
		    this.missed = true;
		}
	    }
	    return rng;
	}
	return -1;
    }


    public boolean getAttacked ()
    {
	return this.attacked;
    }
} // Card class
