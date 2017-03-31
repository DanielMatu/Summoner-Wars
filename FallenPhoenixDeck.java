import java.awt.*;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
//import java.awt.Math;

//import hsa.Console;

public class FallenPhoenixDeck extends Deck
{
    /*
    static Cultist cultists [] = new Cultist [6];
    static Harbinger harbingers [] = new Harbinger [6];
    static Warrior warriors [] = new Warrior [6];
    static Karthos karthos = new Karthos;
    static HellfireDrake hf_drake = new HellfireDrake;
    static Helkar helkar = new Helkar;
    static Purge purge [] = new Purge [3];
    static FromTheAshes from_the_ashes [] = new FromTheAshes [2];
    static ForcedConversion forced_conversion = new ForcedConversion[2];
    static BurntSacrifice burnt_sacrifice = new BurntSacrifice[2];
    */
    public int cultist_count = 6;
    public int harbinger_count = 6;
    public int warrior_count = 6;
    public int karthos_count = 1;
    public int hf_drake_count = 1;
    public int helkar_count = 1;
    public int purge_count = 2;
    public int forced_conversion_count = 2;
    public int from_the_ashes_count = 2;
    public int wall_count = 3;
    public int burnt_sacrifice_count = 2;
    public int rng;

    public FallenPhoenixDeck(){
    }
    
    public int randomize(){
	rng = (int)(Math.round(Math.random()*30));
	return rng;
	/*
	if (rng <= 6){
	    Cultist cultist = new Cultist();
	    cultist.setX(x);
	    cultist.setY(y);
	}
	*/
    
    }
    
    public Card FallenPhoenixDeckPullCard(int x, int y){
	rng = (int)(Math.round(Math.random()*card_count));
	if (rng <= cultist_count){
	    this.setCardCountMinusOne();
	    return pullCultist(x, y); //pullCultist
	}
	else if (rng <= cultist_count + harbinger_count){
	    this.setCardCountMinusOne();
	    return pullHarbinger(x, y); //pullHarbinger
	}
	else if (rng <= cultist_count + harbinger_count
		      + warrior_count){
	    this.setCardCountMinusOne();
	    return pullWarrior(x, y);  //pullWarrior
	}
	else if (rng <= cultist_count + harbinger_count
		      + warrior_count + purge_count){
	    this.setCardCountMinusOne();
	    return  pullPurge(x, y); // PURGE
	}
	else if (rng <= cultist_count + harbinger_count
		      + warrior_count + purge_count
		      + from_the_ashes_count){
	   this.setCardCountMinusOne();
	   return pullFromTheAshes(x, y); //FROMTHEASHES
	}
	else if (rng <= cultist_count + harbinger_count
		      + warrior_count + purge_count
		      + from_the_ashes_count
		      + forced_conversion_count){
	    this.setCardCountMinusOne();
	    return pullForcedConversion(x, y); // should be forced conversion
	}

	else if (rng <= cultist_count + harbinger_count
		      + warrior_count + purge_count
		      + from_the_ashes_count
		      + forced_conversion_count
		      + burnt_sacrifice_count){
	    this.setCardCountMinusOne();
	    return pullBurntSacrifice(x, y); // BURNT SACRIFICE
	}
	/*
	else if (rng <= cultist_count + harbinger_count
		      + warrior_count + purge_count
		      + from_the_ashes_count
		      + forced_conversion_count
		      + burnt_sacrifice_count + karthos_count){
	    card_count -= 1;
	    return pullKarthos(x, y);
	}
	else if (rng <= cultist_count + harbinger_count
		      + warrior_count + purge_count
		      + from_the_ashes_count
		      + forced_conversion_count
		      + burnt_sacrifice_count + karthos_count
		      + hf_drake_count){
	    card_count -= 1;
	    return pullHellfireDrake(x, y);
	}
	else if (rng <= cultist_count + harbinger_count
		      + warrior_count + purge_count
		      + from_the_ashes_count
		      + forced_conversion_count
		      + burnt_sacrifice_count + karthos_count
		      + hf_drake_count + helkar_count){
	    card_count -= 1;
	    return pullHelkar(x, y);
	}
	else{
	    Card a = new Card();
	    return a;
	}
	*/
	else{
	    this.setCardCountMinusOne();
	    return pullWall(x, y);
	}
    }
    
    public Cultist pullCultist(int x, int y){
	cultist_count -= 1;
	Cultist cultist = new Cultist();
	cultist.setX(x);
	cultist.setY(y);
	return cultist;
    } 
    public Harbinger pullHarbinger(int x, int y){
	harbinger_count -= 1;
	Harbinger harbinger = new Harbinger();
	harbinger.setX(x);
	harbinger.setY(y);
	return (Harbinger)(harbinger);
    } 
    public Warrior pullWarrior(int x, int y){
	warrior_count -= 1;
	Warrior warrior = new Warrior();
	warrior.setX(x);
	warrior.setY(y);
	return (Warrior)(warrior);
    } 
    public Karthos pullKarthos(int x, int y){
	karthos_count -= 1;
	Karthos karthos = new Karthos();
	karthos.setX(x);
	karthos.setY(y);
	return (Karthos)(karthos);
    } 
    public HellfireDrake pullHellfireDrake(int x, int y){
	hf_drake_count -= 1;
	HellfireDrake hf_drake = new HellfireDrake();
	hf_drake.setX(x);
	hf_drake.setY(y);
	return (HellfireDrake)(hf_drake);
    } 
    public Helkar pullHelkar(int x, int y){
	helkar_count -= 1;
	Helkar helkar = new Helkar();
	helkar.setX(x);
	helkar.setY(y);
	return (Helkar)(helkar);
    } 
    public Purge pullPurge(int x, int y){
	purge_count -= 1;
	Purge purge = new Purge();
	purge.setX(x);
	purge.setY(y);
	return (Purge)(purge);
    }
    public FromTheAshes pullFromTheAshes(int x, int y){
	from_the_ashes_count -= 1;
	FromTheAshes from_the_ashes = new FromTheAshes();
	from_the_ashes.setX(x);
	from_the_ashes.setY(y);
	return (FromTheAshes)(from_the_ashes);
    } 
    public ForcedConversion pullForcedConversion(int x, int y){
	forced_conversion_count -= 1;
	ForcedConversion forced_conversion = new ForcedConversion();
	forced_conversion.setX(x);
	forced_conversion.setY(y);
	return (ForcedConversion)(forced_conversion);
    } 
    public BurntSacrifice pullBurntSacrifice(int x, int y){
	burnt_sacrifice_count -= 1;
	BurntSacrifice burnt_sacrifice = new BurntSacrifice();
	burnt_sacrifice.setX(x);
	burnt_sacrifice.setY(y);
	return (BurntSacrifice)(burnt_sacrifice);
    }
    
    public Wall pullWall(int x, int y){
	wall_count -= 1;
	Wall wall = new Wall();
	wall.setX(x);
	wall.setY(y);
	return wall;
    }
    
    public int returnNumberOfCards ()
    {
	return this.card_count;
    }
    
    
    /*
    public void pullWall(int x, int y){
	wall_count -= 1;
	Cultist cultist = new Cultist();
	cultist.setX(x);
	cultist.setY(y);
	cultist.drawCultist();
    } 
    */

} // FallenPhoenixDeck class
