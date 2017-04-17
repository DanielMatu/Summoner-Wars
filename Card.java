// The "Card" class.
import java.awt.*;
import java.applet.*;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

//import hsa.Console;
//

public class Card extends Applet
{    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int x;
    public int y;
    public int width;
    public int height;
    public int square_moves;
    public String name;
    public String path;
    public Image appearance;
    public boolean clicked;
    public boolean is_event;
    public boolean event_prompted;
    public int player;
    public int wounds;
    public boolean missed;
    public int life;
    public int attack;
    public int pile_index;

    public Card ()
    {
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double screen_width = screenSize.getWidth();
	double screen_height = screenSize.getHeight();
	this.square_moves = 2;
	this.wounds = 0;
	this.width = (int)(Math.round(screen_width/9.252)); //147
	this.height = (int)(Math.round(screen_height/14.491));   //53

	//this.path = ("C:\\Users\\daniel\\Desktop\\e\\BRUH\\Armies\\Fallen Phoenix\\" + this.name + ".jpg");
    }
	
    
    public int getX(){
	return this.x;
    }
    
    public int getY(){
	return this.y;
    }
    
    public void setX(int passed_x){
	this.x = passed_x;
    }
    
    public void setY(int passed_y){
	this.y = passed_y;
    }
    
    public void setCardWidth(int passed_width){
	this.width = passed_width;
    }

    public void setCardHeight(int passed_height){
	this.height = passed_height;
    }

    public int getCardWidth(){
	return this.width;
    }
    
    public int getCardHeight(){
	return this.height;
    }
    
    public void drawCard1(Graphics g){
	//System.out.println(this.getPath());
	Image local_image;
	String startpath = System.getProperty("user.dir") + "\\images\\";
	local_image = new ImageIcon(startpath +this.name+".jpg").getImage();
	g.drawImage(local_image, this.x, this.y, this.width, this.height, null);  //uncomment this line and the line directly above it when you have all image files, and comment the 3 lines directly below this line.
	//Image local_image_2;
	//local_image_2 = new ImageIcon("C:\\Users\\daniel\\Desktop\\e\\BRUH\\cultist.jpg").getImage();
	//g.drawImage(local_image_2, this.x, this.y, this.width, this.height, null);
	//System.out.println("WHO IS EVEN DOING THIS?");
    }
    
    public void setName(String passed_name){
	this.name = passed_name;
    }
    
    public String getName(){
	return this.name;
    }
    
    public void setPath(String s){
	this.path = "C:\\Users\\daniel\\Desktop\\e\\BRUH\\"+this.name+".jpg";
    }
    
    public String getPath(){
	return this.path;
    }
    
    public void setAppearance(){
	this.appearance = new ImageIcon("C:\\Users\\daniel\\Desktop\\e\\BRUH\\" + this.name + ".jpg").getImage();
	//System.out.println("C:\\Users\\daniel\\Desktop\\e\\BRUH\\Armies\\Fallen Phoenix\\" + this.name + ".jpg");
    }
    
    public Image getAppearance(){
	return this.appearance;
    }
    
    public boolean getClicked(){
	return this.clicked;
    }
    
    public void setClicked(boolean input){
	this.clicked = input;
    }
    
    public boolean getIsEvent(){
	return this.is_event;
    }
    
    public void setIsEvent(boolean input){
	this.is_event = input;
    }
    
    // Event Card Methods
    
    public void setEventPrompted(boolean input){
	this.event_prompted = input;
    }
    
    public boolean getEventPrompted(){
	return this.event_prompted;
    }
    
    public int getPlayer(){
	return this.player;
    }
    
    public void setPlayer(int passed_player){
	this.player = passed_player;
    }
    
    public void takeWound(){
	//System.out.println("took a wound");
	this.wounds++;
    }
    
    public void healWound(){
	if (this.wounds >= 1){
	    this.wounds--;
	}
    }
    
    public int getWounds(){
	return this.wounds;
    }
    
    public void drawWounds(Graphics g){
	if (this.wounds >= 1){
	    Image wound;
	    wound = new ImageIcon("C:\\Users\\daniel\\Desktop\\e\\cav\\BRUH\\wound.jpg").getImage();
	    g.drawImage(wound, this.getX() + 80, this.getY() + 10, 20, 20, null);
	    g.setColor(Color.white);
	    g.setFont (new Font ("Felix Titling", Font.PLAIN, 18));
	    g.drawString(String.valueOf(this.wounds), this.getX() + 85, this.getY() + 25);
	}
    }
    
    public boolean getMissed(){
	return this.missed;
    }
    
    public void setMissed(boolean input){
	this.missed = input;
    }
    
    public int getLife(){
	return this.life;
    }
    
    public void setPileIndex(int i){
	this.pile_index = i;
    } 
    
    public void setPileIndexMinusOne(){
	this.pile_index--;
    } 
    
    public int getPileIndex(){
	return this.pile_index;
    }

    
    
    
} // Card class
