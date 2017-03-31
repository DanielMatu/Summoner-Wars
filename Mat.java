// The "Card" class.
import java.awt.*;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

//import hsa.Console;

public class Mat extends JFrame
{
    public Card grid[] [] = new Card [6] [8];
    public boolean clicked;
    public int last_clicked_i;
    public int last_clicked_j;
    public Card last_clicked;
    public Card last_clicked_card_mat;
    public Card cards_on_mat[] = new Card [48];
    public int phase_reference;

    public Mat ()
    {
	this.clicked = false;
    }


    public boolean getClicked ()
    {
	return this.clicked;
    }


    public void setClicked (boolean passed_clicked)
    {
	this.clicked = true;
    }


    public void setCard (Card c, int i, int j)
    {
	this.grid [i] [j] = c;
	//this.setLastClickedSquare(i, j);
    }


    public Card getCard (int i, int j)
    {
	return this.grid [i] [j];
    }


    public void setCreature (Creature cr, int i, int j)
    {
	this.grid [i] [j] = cr;
	//this.setLastClickedSquare(i, j);
    }


    // last clicked refers to the last mat square that was clicked
    // in terms of its row and column numbers
    public void setLastClickedSquare (int i, int j)
    {
	this.last_clicked_i = i;
	this.last_clicked_j = j;
    }


    public int getLastClickedI ()
    {
	return this.last_clicked_i;
    }


    public int getLastClickedJ ()
    {
	return this.last_clicked_j;
    }


    public void clearCard (int i, int j)
    {
	Card filler = new Card ();
	this.grid [i] [j] = filler;
    }


    public void summonWall (Hand h)
    {
	h.getLastClicked ().setX (this.getLastClickedI () * 147 + 15);
	h.getLastClicked ().setY (547 - 53 * this.getLastClickedJ ());
	this.setCard (h.getLastClicked (), this.getLastClickedI (), this.getLastClickedJ ());
    }


    public void ressurect (Card c)
    {
	boolean summoned = false;
	int r = 0;
	int d = 0;
	while (summoned == false && d < 8)
	{
	    while (r < 6 && summoned == false)
	    {
		if (this.getCard (r, d) instanceof Creature == false && this.getCard (r, d) instanceof Wall == false)
		{
		    summoned = true;
		    //
		    c.setX(r*147 + 15);
		    c.setY(547 - 53*d);
		    this.setCard(c, r, d);
		    
		    //this.getCard(r,d).setName(this.getCard(r,d).getClass().getName());
		}
		r++;
	    }
	    d++;
	}
    }


    public boolean isEmpty (int i, int j)
    {
	return (grid [i] [j] instanceof Creature == false && grid[i][j] instanceof Wall == false);
    }


    public void summonCreature (Hand h)
    {
	if (h.getLastClicked ().getClicked () && h.getLastClicked () instanceof Creature && this.isEmpty (this.getLastClickedI (), this.getLastClickedJ ()))
	{
	    h.getLastClicked ().setX (this.getLastClickedI () * 147 + 15);
	    h.getLastClicked ().setY (547 - 53 * this.getLastClickedJ ());
	    this.setCard (h.getLastClicked (), this.getLastClickedI (), this.getLastClickedJ ());
	}
    }



    public boolean AdjacentToWall (int i, int j)
    {
	boolean condition[] = new boolean [4];
	try
	{
	    condition [0] = grid [i + 1] [j] instanceof Wall;
	}
	catch (ArrayIndexOutOfBoundsException e)
	{
	    condition [0] = false;
	}
	try
	{
	    condition [1] = grid [i] [j + 1] instanceof Wall;
	}
	catch (ArrayIndexOutOfBoundsException e)
	{
	    condition [1] = false;
	}
	try
	{
	    condition [2] = grid [i - 1] [j] instanceof Wall;
	}
	catch (ArrayIndexOutOfBoundsException e)
	{
	    condition [2] = false;
	}
	try
	{
	    condition [3] = grid [i] [j - 1] instanceof Wall;
	}
	catch (ArrayIndexOutOfBoundsException e)
	{
	    condition [3] = false;
	}
	return (condition [0] || condition [1] || condition [2] || condition [3]);
    }


    public Card[] [] returnGrid ()
    {
	return this.grid;
    }


    public void setLastClickedCard (Card c)
    {
	this.last_clicked = c;
    }


    public Card getLastClickedCard ()
    {
	return this.last_clicked;
    }


    public void setLastClickedCardOnMat (Card c)
    {
	this.last_clicked_card_mat = c;
    }


    public Card getLastClickedCardOnMat ()
    {
	return this.last_clicked_card_mat;
    }


    public void clearLastClickedCardOnMat ()
    {
	Card filler = new Card ();
	this.last_clicked_card_mat = filler;
    }


    public int getPhaseReference ()
    {
	return this.phase_reference;
    }


    public void setPhaseReference (int ref)
    {
	this.phase_reference = ref;
    }


    public void drawMat (Graphics g)
    {
	for (int i = 0 ; i < 6 ; i++)
	{
	    for (int j = 0 ; j < 8 ; j++)
	    {

		try
		{
		    if (this.grid [i] [j] instanceof Creature || this.grid [i] [j] instanceof Wall)
		    {
			this.grid [i] [j].drawCard1 (g);
			this.grid [i] [j].drawWounds (g);
		    }
		    if (this.grid [i] [j].getMissed ())
		    {
			g.setColor (Color.red);
			g.setFont (new Font ("Felix Titling", Font.PLAIN, 40));
			g.drawString ("missed", 1100, 40);
			this.grid [i] [j].setMissed (false);
		    }
		}
		catch (NullPointerException n)
		{
		}
	    }
	}
    }
} // Card class
