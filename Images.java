import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.io.*;
import java.awt.Font;
import java.lang.reflect.*;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.awt.image.BufferedImage;

public class Images extends JFrame implements MouseListener
{

    public static void main (String[] args)
    {
	Dimension screenSize = Toolkit.getDefaultToolkit ().getScreenSize ();
	double width = screenSize.getWidth ();
	double height = screenSize.getHeight ();
	DisplayMode dm = new DisplayMode ((int) height, (int) width, 16, DisplayMode.REFRESH_RATE_UNKNOWN);
	Images i = new Images ();
	i.run (dm);

    }


    private Screen s;
    private Image use;
    private Image bg;
    private Image ds;
    private Image mat;
    private Image mat_menu;
    private Image pile_menu;
    private Image die_roll_screen_image;
    private Image ffback;
    private boolean loaded;
    static boolean deck_select = false;
    static boolean menu = true;
    static boolean deck_select_clickable = false;
    static int player_army_selected;
    static int ai_army_selected;
    static boolean player_selected;
    static boolean ai_selected;
    static boolean die_roll_screen = false;
    static boolean game_ready = false;
    static boolean game_started = false;


    // Hand

    static Hand p1_hand_object = new Hand ();
    static Hand p2_hand_object = new Hand ();
    static boolean p1_hand[] = {false, false, false, false, false};
    static boolean p2_hand[] = {false, false, false, false, false};
    static boolean card_clicked;


    // Mat

    static Mat game_mat = new Mat ();
    static boolean mat_clicked = false;
    static boolean p1_discard_clicked;
    static boolean p1_magic_clicked;
    static boolean p2_discard_clicked;
    static boolean p2_magic_clicked;
    static boolean main_game = true;


    // Turn control
    static boolean p1_first;
    static boolean p2_first;
    static boolean p1_turn;
    static boolean p2_turn;
    static boolean first_turn = false;
    static int phase = 1;

    // Misc

    static DisplayCard display_card = new DisplayCard ();
    static boolean play_event_prompt;
    static int use_x;
    static int use_y;
    static int text_count;


    // Decks

    static Deck p1_deck = new Deck ();
    static Deck p2_deck = new Deck ();
    
    
    // Combat
    Object attack_roll;
    static boolean invoke_effects;
    static String attack_notice[] = new String[9];
    static boolean print_new;
    
    // Menus
    
    static boolean draw_mat_menu;
    static boolean draw_pile_menu;
    static boolean mat_menu_clicked;



    // instantiate magic and discard piles
    DiscardPile p1_discard = new DiscardPile ();
    DiscardPile p2_discard = new DiscardPile ();
    MagicPile p1_magic = new MagicPile ();
    MagicPile p2_magic = new MagicPile ();


    public void run (DisplayMode dm)
    {
	addMouseListener (this);
	loaded = false;
	Screen s = new Screen ();
	loadpics ();
	s.setFullScreen (dm, this);
    }


    public void loadpics ()
    {
    String startpath = System.getProperty("user.dir") + "\\images\\";
	bg = new ImageIcon (startpath + "ayyyy.jpg").getImage ();
	ds = new ImageIcon (startpath + "deck select.png").getImage ();
	mat = new ImageIcon (startpath + "mat_room_to_do_shit.jpg").getImage ();
	use = new ImageIcon (startpath + "use.jpg").getImage ();
	die_roll_screen_image = new ImageIcon (startpath + "die roll.png").getImage ();
	ffback = new ImageIcon (startpath + "fallen phoenix.jpg").getImage ();
	mat_menu = new ImageIcon (startpath + "creature menu.jpg").getImage ();
	pile_menu = new ImageIcon (startpath + "pile menu.jpg").getImage ();
	loaded = true;
	repaint ();
    }


    public void paint (Graphics g)
    {
	//anti aliasing
	g.setFont (new Font ("Arial", Font.PLAIN, 24));
	if (g instanceof Graphics2D)
	{
	    Graphics2D g2 = (Graphics2D) g;
	    g2.setRenderingHint (RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	}
	// core
	if (loaded)
	{
	    if (menu)
	    {
		g.drawImage (bg, 0, 0, getWidth (), getHeight (), null);
	    }
	    else if (deck_select)
	    {
		g.drawImage (ds, 0, 0, getWidth (), getHeight (), null);
		g.setColor (Color.yellow);
		if (player_army_selected == 4)
		{
		    p1_deck = setUpMatFallenPhoenix (game_mat);
		    g.drawRect ((int) (getWidth () / 1.289), (int) (getHeight () / 2.621),
			    (int) (getWidth () / 6.30), (int) (getHeight () / 13.25));

		}
		else if (player_army_selected == 1)
		{
		}
		if (ai_army_selected == 4)
		{
		    p2_deck = setUpMatFallenPhoenixAI (game_mat);
		    g.drawRect ((int) (getWidth () / 1.281), (int) (getHeight () / 1.503),
			    (int) (getWidth () / 6.30), (int) (getHeight () / 13.25));
		}



		if (game_ready)
		{
		    g.setColor (Color.gray);
		    g.fill3DRect ((int) (getWidth () / 2.881), (int) (getHeight () / 8),
			    (int) (getWidth () / 2.48), (int) (getHeight () / 10), true);
		    g.setColor (Color.white);
		    g.setFont (new Font ("Felix Titling", Font.PLAIN, 60));
		    g.drawString ("BEGIN", (int) (getWidth () / 2.07), (int) (getHeight () / 4.955));
		    g.setColor (Color.yellow);
		}
	    }

	    else if (die_roll_screen)
	    {
		g.drawImage (die_roll_screen_image, 0, 0, getWidth (), getHeight (), null);
		g.setFont (new Font ("Felix Titling", Font.PLAIN, 20));
		g.setColor (Color.white);
		if (p1_first)
		{
		    g.drawString ("player 1 will go first", 100, 660);
		    p1_turn = true;
		    die_roll_screen = false;
		    game_started = true;
		    //delay(2000);
		}
		else if (p2_first)
		{
		    g.drawString ("player 2 will go first", 100, 660);
		    p2_turn = true;
		    die_roll_screen = false;
		    game_started = true;
		    //delay(2000);

		}

	    }

	    if (game_started)
	    {
		// player 1 draw phase. pull cards until all slots filled in
		// player 1's hand
		if (p1_turn)
		{
		    if (first_turn == false)
		    {
			if (phase == 1)
			{
			    for (int slot = 0 ; slot < 5 ; slot++)
			    {
				if (p1_hand [slot] == false)
				{
				
				    p1_hand [slot] = true;
				    Class[] params = {Integer.TYPE, Integer.TYPE};
				    try{
					Method pullCard = p1_deck.getClass().getMethod(p1_deck.getClass().getName() + "PullCard", params);
					Integer pullx = new Integer(25 + 158 * slot);
					Integer pully = new Integer(689);
					try{
					    if(p1_deck.getCardCount() >= 1){
						p1_hand_object.setHandSlot(slot, ((Card)pullCard.invoke(p1_deck, new Object[]{pullx, pully})));
						p1_hand_object.getHandSlot(slot).setPlayer(1);
					    }
					} catch(Exception e){}
				    } catch(NoSuchMethodException nsm){}

				    g.setColor (Color.yellow);
				    p1_hand_object.getHandSlot (slot).setName (p1_hand_object.getHandSlot (slot).getClass ().getName ());
				}
			    }
			    phase = 2;

			}
		    }
		}

		else if (p2_turn)
		{
		    if (first_turn == false)
		    {
			if (phase == 1)
			{
			    for (int slot = 0 ; slot < 5 ; slot++)
			    {
				if (p2_hand [slot] == false)
				{
				
				    p2_hand [slot] = true;
				    Class[] params = {Integer.TYPE, Integer.TYPE};
				    try{
					Method pullCard = p2_deck.getClass().getMethod(p1_deck.getClass().getName() + "PullCard", params);
					Integer pullx = new Integer(25 + 158 * slot);
					Integer pully = new Integer(29);
					try{
					    if(p2_deck.getCardCount() >= 1){
						p2_hand_object.setHandSlot(slot, ((Card)pullCard.invoke(p2_deck, new Object[]{pullx, pully})));
						p2_hand_object.getHandSlot(slot).setPlayer(-1);
					    }
					} catch(Exception e){}
				    } catch(NoSuchMethodException nsm){}

				    g.setColor (Color.yellow);
				    p2_hand_object.getHandSlot (slot).setName (p2_hand_object.getHandSlot (slot).getClass ().getName ());
				}
			    }
			    phase = 2;

			}
		    }
		}

		// Draw mat
		if (main_game)
		{
		    g.drawImage (mat, 0, 0, getWidth (), getHeight (), null);
		    p1_hand_object.drawHand (g);
		    p2_hand_object.drawHand (g);
		    game_mat.drawMat (g);

		    // Draw the "use event" prompt

		    if (display_card.getCardToDisplay () != null)
		    {
			if (display_card.getCardToDisplay ().getEventPrompted () == true && display_card.getCardToDisplay () instanceof Wall == false)
			{
			    g.drawImage (use, display_card.getCTDX (), display_card.getCTDY (), display_card.getCTDWidth (), display_card.getCTDHeight (), null);
			}
		    }

		    // Draw display Card
		    if (main_game)
		    {
			display_card.setMainGameDimensions ();
			display_card.drawCard1 (g);
		    }

		    // Draw Card back for Magic pile

		    if (p1_magic.returnNumberOfCards () >= 1)
		    {
			g.drawImage (ffback, 562, 607, 147, 53, null);
			g.setColor (Color.white);
			g.setFont (new Font ("Felix Titling", Font.PLAIN, 30));
			g.drawString (String.valueOf (p1_magic.returnNumberOfCards ()), 627, 640);
		    }

		    if (p1_discard.returnNumberOfCards () >= 1)
		    {
			p1_discard.getTopCard().setX(730);
			p1_discard.getTopCard().setY(607);
			p1_discard.getTopCard().drawCard1(g);
			g.setColor (Color.red);
			g.setFont (new Font ("Felix Titling", Font.PLAIN, 30));
			g.drawString (String.valueOf (p1_discard.returnNumberOfCards ()), 795, 640);
		    }
		    
		    if (p2_magic.returnNumberOfCards () >= 1)
		    {
			g.drawImage (ffback, 183, 112, 147, 53, null);
			g.setColor (Color.white);
			g.setFont (new Font ("Felix Titling", Font.PLAIN, 30));
			g.drawString (String.valueOf (p2_magic.returnNumberOfCards ()), 248, 145);
		    }
		    
		    if (p2_discard.returnNumberOfCards () >= 1)
		    {
			p2_discard.getTopCard().setX(17);
			p2_discard.getTopCard().setY(112);
			p2_discard.getTopCard().drawCard1(g);
			g.setColor (Color.red);
			g.setFont (new Font ("Felix Titling", Font.PLAIN, 30));
			g.drawString (String.valueOf (p2_discard.returnNumberOfCards ()), 82, 145);
		    }
		    
		    if (p1_deck.getCardCount() >= 1){
			g.drawImage (ffback, 17, 607, 147, 53, null);
			g.setColor (Color.white);
			g.setFont (new Font ("Felix Titling", Font.PLAIN, 30));
			g.drawString (String.valueOf (p1_deck.getCardCount ()), 76, 642);
		    }
		    
		    if (p2_deck.getCardCount() >= 1){
			g.drawImage (ffback, 730, 112, 147, 53, null);
			g.setColor (Color.white);
			g.setFont (new Font ("Felix Titling", Font.PLAIN, 30));
			g.drawString (String.valueOf (p2_deck.getCardCount ()), 795, 145);
		    }
		}


		// draw the discard pile if it was clicked

		if (p1_discard_clicked)
		{
		    p1_discard.discardSearch (g);
		}

		else if (p1_magic_clicked)
		{
		     p1_magic.magicSearch (g);
		}
		
		else if (p2_discard_clicked){
		    p2_discard.discardSearch (g);
		}
		
		else if (p2_magic_clicked){
		    p2_magic.magicSearch (g);
		}
		
		// draw the display card
		
		if (p1_discard_clicked || p1_magic_clicked || p2_discard_clicked || p2_magic_clicked){
		    display_card.setPileScreenDimensions ();
		    display_card.drawCard1 (g);
		}
		
		// menus

		// draw mat menu if it was clicked
		if (draw_mat_menu){
		    g.drawImage(mat_menu, 900, 10, 200, 300, null);
		}
		if (draw_pile_menu){
		    g.drawImage(pile_menu, 900, 10, 200, 300, null);
		}
	    }
	    // for (int i = 0; i < 8; i++){ 
	    //     g.setColor(Color.yellow);
	    //     g.drawRect(907, 260 - i*35, 185, 29);
	    // }
	    if (true){
		g.setColor(Color.red);
		for (int i=0; i<9; i++){
		    try{
			g.setFont (new Font ("Felix Titling", Font.PLAIN, 18));                        
			g.drawString(attack_notice[i], 900, 540 + 20*i);
		    }catch(NullPointerException nsm){}
		}
		print_new = false;
	    }
	}
    }


    public void delay (int milliseconds)
    {
	try
	{
	    Thread.sleep (milliseconds);
	}
	catch (Exception a)
	{
	}
    }


    public void mouseClicked (MouseEvent click)
    {
    }


    public void mouseReleased (MouseEvent e)
    {
    }


    public void mouseEntered (MouseEvent e)
    {
    }


    public void mouseExited (MouseEvent e)
    {
    }


    public void mousePressed (MouseEvent click)
    {
	int mx = click.getX ();
	int my = click.getY ();
	//System.out.println (mx);
	//System.out.println (my);
	if (click.getButton () == MouseEvent.BUTTON1 && mx >= getWidth () / 2.407 && mx <= getWidth () / 1.813 && my >= getHeight () / 1.359 && my <= getHeight () / 1.290 && menu)
	{
	    deck_select = true;
	    menu = false;
	    repaint ();
	}

	if (deck_select && click.getButton () == MouseEvent.BUTTON1)
	{
	    // use boolean variables to negate effect of main menu click
	    // on this screen  //probably dont need this
	    if (deck_select_clickable)
	    {
		if (mx >= getWidth () / 6.602 && my <= getHeight () / 2.151
			&& mx <= getWidth () / 3.230 && my >= getHeight () / 2.56)
		{
		    player_army_selected = 1;
		}
		else if (mx >= getWidth () / 1.288 && my <= getHeight () / 2.207
			&& mx <= getWidth () / 1.071 && my >= getHeight () / 2.603)
		{
		    player_army_selected = 4;
		    player_selected = true;
		}
		else if (mx >= getWidth () / 1.281 && my <= getHeight () / 1.357
			&& mx <= getWidth () / 1.066 && my >= getHeight () / 1.491)
		{
		    ai_army_selected = 4;
		    ai_selected = true;
		}

		if (ai_selected && player_selected)
		{
		    game_ready = true;
		}

		if (game_ready && mx >= getWidth () / 2.881 && my <= getHeight () / 4.465
			&& mx <= getWidth () / 1.333 && my >= getHeight () / 8.084)
		{
		    die_roll_screen = true;
		    deck_select = false;
		}

	    }
	    else
	    {
		deck_select_clickable = true;
	    }

	}
	if (die_roll_screen)
	{
	    if (mx >= getWidth () / 17.215 && mx <= getWidth () / 2.742 && my <= getHeight () / 1.790 && my >= getHeight () / 2.685)
	    {
		p1_first = true;
	    }
	    else if (mx >= getWidth () / 17 && mx <= getWidth () / 2.747 && my <= getHeight () / 1.221 && my >= getHeight () / 1.574)
	    {
		p2_first = true;
	    }
	}

	if (game_started)
	{

	    //mat_clicked = false;

	    if (draw_mat_menu){
		for (int i = 0; i < 8; i++){
		    if (mx >= 907 && mx <= 1092){
			if (my <= 289 - i*35 && my >= 260 - i*35){
			    // determine which character the card last clicked
			    // belongs to.
			    int player_of_last = game_mat.getLastClickedCardOnMat().getPlayer();
			    if (i == 0){
				game_mat.clearCard(game_mat.getLastClickedI(), game_mat.getLastClickedJ());
				game_mat.clearLastClickedCardOnMat();
				draw_mat_menu = false;
			    }
			    else if (i == 1){
				if(player_of_last == 1){
				    p2_discard.addToDiscard(game_mat.getLastClickedCardOnMat());
				    game_mat.getLastClickedCardOnMat().setPlayer(-1);
				    game_mat.clearCard(game_mat.getLastClickedI(), game_mat.getLastClickedJ());
				    draw_mat_menu = false;
				    game_mat.clearLastClickedCardOnMat();
				}
				else{
				    p1_discard.addToDiscard(game_mat.getLastClickedCardOnMat());
				    game_mat.getLastClickedCardOnMat().setPlayer(1);
				    game_mat.clearCard(game_mat.getLastClickedI(), game_mat.getLastClickedJ());
				    draw_mat_menu = false;
				    game_mat.clearLastClickedCardOnMat();

				}
			    }
			    else if(i == 2){
				if(player_of_last == 1){
				    p2_magic.addToMagic(game_mat.getLastClickedCardOnMat());
				    game_mat.getLastClickedCardOnMat().setPlayer(-1);
				    game_mat.clearCard(game_mat.getLastClickedI(), game_mat.getLastClickedJ());
				    draw_mat_menu = false;
				    game_mat.clearLastClickedCardOnMat();

				}
				else{
				    p1_magic.addToMagic(game_mat.getLastClickedCardOnMat());
				    game_mat.getLastClickedCardOnMat().setPlayer(1);
				    game_mat.clearCard(game_mat.getLastClickedI(), game_mat.getLastClickedJ());
				    draw_mat_menu = false;
				    game_mat.clearLastClickedCardOnMat();

				}
			    
			    }
			    else if(i == 3){
				int count = 0;
				boolean returned = false;
				if (player_of_last == 1){
				    while(returned == false && count < 5){
					if (p2_hand[count] == false){
					    //System.out.println(game_mat.getLastClickedCardOnMat());
					    returned = true;
					    p2_hand_object.setHandSlot(count, game_mat.returnGrid()[game_mat.getLastClickedI()][game_mat.getLastClickedJ()]);
					    game_mat.getLastClickedCardOnMat().setPlayer(-1);
					    p2_hand_object.getHandSlot(count).setX(22 + 157*count);
					    p2_hand_object.getHandSlot(count).setY(30);
					    game_mat.clearCard(game_mat.getLastClickedI(), game_mat.getLastClickedJ());
					    p2_hand[count] = true;
					}
					count++;
				    }
				    game_mat.clearLastClickedCardOnMat();

				}
				else{
				    while(returned == false && count < 5){
					if (p1_hand[count] == false){
					    returned = true;
					    p1_hand_object.setHandSlot(count, game_mat.returnGrid()[game_mat.getLastClickedI()][game_mat.getLastClickedJ()]);
					    game_mat.getLastClickedCardOnMat().setPlayer(1);
					    p1_hand_object.getHandSlot(count).setX(22 + 157*count);
					    p1_hand_object.getHandSlot(count).setY(720);
					    game_mat.clearCard(game_mat.getLastClickedI(), game_mat.getLastClickedJ());
					    p1_hand[count] = true;
					}
					count++;
				    }
				    game_mat.clearLastClickedCardOnMat();

				}
			    }
			    else if (i == 4){
				if(player_of_last == -1){
				    p2_discard.addToDiscard(game_mat.getLastClickedCardOnMat());
				    game_mat.getLastClickedCardOnMat().setPlayer(-1);
				    game_mat.clearCard(game_mat.getLastClickedI(), game_mat.getLastClickedJ());
				    draw_mat_menu = false;
				    game_mat.clearLastClickedCardOnMat();

				}
				else{
				    p1_discard.addToDiscard(game_mat.getLastClickedCardOnMat());
				    game_mat.getLastClickedCardOnMat().setPlayer(1);
				    game_mat.clearCard(game_mat.getLastClickedI(), game_mat.getLastClickedJ());
				    draw_mat_menu = false;
				    game_mat.clearLastClickedCardOnMat();

				}
			    }
			    else if(i == 5){
				if(player_of_last == -1){
				    p2_magic.addToMagic(game_mat.getLastClickedCardOnMat());
				    game_mat.getLastClickedCardOnMat().setPlayer(-1);
				    game_mat.clearCard(game_mat.getLastClickedI(), game_mat.getLastClickedJ());
				    draw_mat_menu = false;
				    game_mat.clearLastClickedCardOnMat();

				}
				else{
				    p1_magic.addToMagic(game_mat.getLastClickedCardOnMat());
				    game_mat.getLastClickedCardOnMat().setPlayer(1);
				    game_mat.clearCard(game_mat.getLastClickedI(), game_mat.getLastClickedJ());
				    draw_mat_menu = false;
				    game_mat.clearLastClickedCardOnMat();

				}
			    
			    }
			    else if(i == 6){
				int count = 0;
				boolean returned = false;
				if (player_of_last == -1){
				    while(returned == false && count < 5){
					if (p2_hand[count] == false){
					    returned = true;
					    p2_hand_object.setHandSlot(count, game_mat.returnGrid()[game_mat.getLastClickedI()][game_mat.getLastClickedJ()]);
					    game_mat.getLastClickedCardOnMat().setPlayer(-1);
					    p2_hand_object.getHandSlot(count).setX(22 + 157*count);
					    p2_hand_object.getHandSlot(count).setY(30);
					    game_mat.clearCard(game_mat.getLastClickedI(), game_mat.getLastClickedJ());
					    p2_hand[count] = true;
					}
					count++;
				    }
				    game_mat.clearLastClickedCardOnMat();

				}
				else{
				    while(returned == false && count < 5){
					if (p1_hand[count] == false){
					    returned = true;
					    p1_hand_object.setHandSlot(count, game_mat.returnGrid()[game_mat.getLastClickedI()][game_mat.getLastClickedJ()]);
					    game_mat.getLastClickedCardOnMat().setPlayer(1);
					    p1_hand_object.getHandSlot(count).setX(22 + 157*count);
					    p1_hand_object.getHandSlot(count).setY(720);
					    game_mat.clearCard(game_mat.getLastClickedI(), game_mat.getLastClickedJ());
					    p1_hand[count] = true;
					}
					count++;
				    }
				    game_mat.clearLastClickedCardOnMat();

				}
			    }
			    else if (i == 7 && my >= 18 && my <= 42){
				mat_menu_clicked = true;
				if (mx >= 1005 && mx <= 1027){
				    game_mat.getLastClickedCardOnMat().takeWound();
				}
				else if (mx >= 1034 && mx <= 1056){
				    game_mat.getLastClickedCardOnMat().healWound();
				}
			    }
			} 
		    }
		}
	    }

	    // set mat_clicked to false since it will be set to 
	    // true if it is clicked in the following for loop
	    mat_clicked = false;

	    // check if the mat was clicked
	    // so we know where to potentially summon/move/attack
	    for (int i = 0 ; i < 6 ; i++)
	    {
		for (int j = 0 ; j < 8 ; j++)
		{
		    if (mx >= 15 + 147 * i && mx <= 157 + 147 * i && my >= 547 - 53 * j && my <= 593 - 53 * j && main_game)
		    {
			mat_clicked = true;
			//System.out.println(
			// check if the card we clicked last was a card
			if (game_mat.getLastClickedCardOnMat () instanceof Creature || game_mat.getLastClickedCardOnMat () instanceof Wall)
			{
			    if (game_mat.getCard(i, j) != null && click.getButton () == MouseEvent.BUTTON3){
				print_new = true;
				if (text_count >= 8){
				    for (int k = 0; k < 9; k++){
					attack_notice[k] = "";
				    }
				    text_count = 0;
				}
				attack_notice[text_count] = (game_mat.getLastClickedCardOnMat ().getClass().getName() + " rolled " + String.valueOf((int)Math.round(Math.random()*6)) + " to attack " + game_mat.getCard(i, j).getClass().getName());
				text_count++;
			    }
			    if (true)
			    {
				Class params[] = {Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Mat.class};
				try
				{
				    Method movement = game_mat.getLastClickedCardOnMat ().getClass ().getMethod ("MoveTo", params); //new Class[] { Integer.TYPE }
				    Integer di1 = new Integer (i);
				    Integer dj1 = new Integer (j);
				    Integer i1 = new Integer (game_mat.getLastClickedI ());
				    Integer j1 = new Integer (game_mat.getLastClickedJ ());
				    Integer fp = new Integer (1);
				    try
				    {
					movement.invoke (game_mat.getLastClickedCardOnMat (), new Object[]
					{
					    i1, j1, di1, dj1, fp, game_mat
					}
					);
					phase = game_mat.getPhaseReference ();
				    }
				    catch (Exception b)
				    {
				    }

				}
				catch (NoSuchMethodException m)
				{
				}
			    }                    

			    game_mat.setLastClickedSquare (i, j);

			}
			if ((p1_hand_object.getLastClicked () instanceof Creature || p1_hand_object.getLastClicked () instanceof Wall) && main_game)
			{
			    game_mat.setLastClickedSquare (i, j);
			    p1_hand[p1_hand_object.getLastClickedSlot()] = false;
			    if (game_mat.getCard(i,j) instanceof Creature == false && game_mat.getCard(i,j) instanceof Wall == false){
			       game_mat.summonWall (p1_hand_object);
			       p1_hand_object.clearHandSlot (p1_hand_object.getLastClickedSlot ());
			    }

			}
			if ((p2_hand_object.getLastClicked () instanceof Creature || p2_hand_object.getLastClicked () instanceof Wall) && main_game)
			{
			    game_mat.setLastClickedSquare (i, j);
			    p2_hand[p2_hand_object.getLastClickedSlot()] = false;
			    if (game_mat.getCard(i,j) instanceof Creature == false && game_mat.getCard(i,j) instanceof Wall == false){
			       game_mat.summonWall (p2_hand_object);
			       p2_hand_object.clearHandSlot (p2_hand_object.getLastClickedSlot ());
			    }

			}
			if (game_mat.returnGrid () [i] [j] instanceof Creature || game_mat.returnGrid () [i] [j] instanceof Wall)
			{
			    display_card.setCardToDisplay (game_mat.returnGrid () [i] [j]);
			    draw_mat_menu = true;
			}

			game_mat.setLastClickedSquare (i, j);
			game_mat.setLastClickedCardOnMat (game_mat.returnGrid () [i] [j]);
			p1_hand_object.nothingClicked ();
			p2_hand_object.nothingClicked();
		    }
		}
	    }

	    // check if a card in player 1's hand was clicked
	    // so we can store the card and display it
	    // btw clean this up after you fix the images
	    if (main_game)
	    {
		card_clicked = false;
		for (int slot = 0 ; slot < 5 ; slot++)
		{
		    if (p1_hand_object.getHandSlotClicked (1, slot, mx, my))
		    {
			p1_hand_object.setLastClickedSlot (slot);
			game_mat.clearLastClickedCardOnMat ();
			draw_mat_menu = false;
			p1_hand_object.otherCardsNotClicked (slot);
			card_clicked = true;

			try
			{

			    p1_hand_object.setLastClicked (p1_hand_object.getHandSlot (slot));
			    p1_hand_object.getHandSlot (slot).setClicked (true);

			    // change the name of the display card to the card
			    // that was just clicked so that its appearance
			    // will consequentially change
			    display_card.setCardToDisplay (p1_hand_object.getHandSlot (slot));
			    if (p1_hand_object.getHandSlot (slot).getIsEvent () && phase <= 3 && p1_hand_object.getHandSlot (slot) instanceof Wall == false)
			    {
				// if our "use" button is visible
				if (p1_hand_object.getHandSlot (slot).getEventPrompted ())
				{
				    phase = 3;
				    p1_hand_object.getHandSlot (slot).setEventPrompted (false);
				    try
				    {
					Method effect = p1_hand_object.getHandSlot (slot).getClass ().getMethod (p1_hand_object.getHandSlot (slot).getClass ().getName () + "Effect", null);
					try
					{
					    effect.invoke (p1_hand_object.getHandSlot (slot), null);
					}
					catch (Exception b)
					{
					}

				    }
				    catch (NoSuchMethodException m)
				    {
				    }
				    p1_hand_object.clearHandSlot (slot);
				    p1_hand [slot] = false;
				}
				else
				{
				    p1_hand_object.getHandSlot (slot).setEventPrompted (true);
				}
			    }
			}
			catch (NullPointerException n)
			{
			}

			if (phase == 6 && (p1_hand_object.getHandSlot (slot) instanceof Creature || p1_hand_object.getHandSlot (slot) instanceof EventCard))
			{
			    p1_magic.addToMagic (p1_hand_object.getHandSlot (slot));
			    p1_hand[slot] = false;
			    p1_hand_object.clearHandSlot (slot);
			    p1_hand_object.clearLastClickedSlot();
			}
		    }
		    
		    
		}



		// check if player 1 discard/magic piles were clicked
		if (my <= 656 && my >= 608)
		{
		    if (mx >= 566 && mx <= 701)
		    {
			main_game = false;
			p1_magic_clicked = true;
			mat_clicked = false;
			p1_hand_object.nothingClicked ();
			p2_hand_object.nothingClicked ();
			game_mat.clearLastClickedCardOnMat ();
			draw_mat_menu = false;
		    }

		    else if (mx >= 731 && mx <= 873)
		    {
			main_game = false;
			p1_discard_clicked = true;
			mat_clicked = false;
			p1_hand_object.nothingClicked ();
			p2_hand_object.nothingClicked ();
			game_mat.clearLastClickedCardOnMat ();
			draw_mat_menu = false;
		    }
		}
		
		// check if either of player 2's piles were clicked
		if (my >= 113 && my <= 163){
		    if (mx >= 188 && mx <= 325){
			main_game = false;
			p2_magic_clicked = true;
			mat_clicked = false;
			p1_hand_object.nothingClicked ();
			p2_hand_object.nothingClicked ();
			game_mat.clearLastClickedCardOnMat ();
			draw_mat_menu = false;
		    }
		    else if (mx >= 21 && mx <= 158){
			main_game = false;
			p2_discard_clicked = true;
			mat_clicked = false;
			p1_hand_object.nothingClicked ();
			p2_hand_object.nothingClicked ();
			game_mat.clearLastClickedCardOnMat ();
			draw_mat_menu = false;
		    }
		}
		// check if the phase control buttons were clicked
		if (mx >= 964 && mx <= 1288)
		{
		    for (int i = 0 ; i < 3 ; i++)
		    {
			if (my >= 134 + 65 * i && my <= 184 + 65 * i && phase <= i)
			{
			    phase = i + 1;
			}
		    }
		    for (int i = 0 ; i < 4 ; i++)
		    {
			if (my >= 491 + 65 * i && my <= 542 + 65 * i)
			{
			    phase = i + 4;
			}
		    }
		}

		if (phase == 7)
		{
		    if (p1_turn)
		    {
			p1_turn = false;
			p2_turn = true;
			phase = 1;
		    }
		    else
		    {
			p2_turn = false;
			p1_turn = true;
			phase = 1;
		    }
		}

		if (mat_clicked)
		{
		    card_clicked = false;
		    for (int slot = 0 ; slot < 5 ; slot++)
		    {
			p1_hand_object.getHandSlot (slot).setEventPrompted (false);
		    }
		}

		else if (card_clicked == false)
		{
		    mat_clicked = false;
		    p1_hand_object.nothingClicked ();
		    display_card.Clear ();
		    for (int slot = 0 ; slot < 5 ; slot++)
		    {
			p1_hand_object.getHandSlot (slot).setEventPrompted (false);
		    }
		    game_mat.clearLastClickedCardOnMat ();
		    if (mat_menu_clicked == false){
			draw_mat_menu = false;
		    }
		}
	    }

	    // check if we're in the pile screen
	    if (p1_magic_clicked || p1_discard_clicked || p2_magic_clicked || p2_discard_clicked)
	    { 
		if (draw_pile_menu){
		   for (int i = 0; i < 8; i++){
			if (mx >= 907 && mx <= 1092){
			    if (my <= 289 - i*35 && my >= 260 - i*35){
				// determine which character the card last clicked
				// belongs to.
				int player_of_last = display_card.getCardToDisplay().getPlayer();
				//System.out.println(player_of_last);
				if (i == 0){
				    clearPileCard(display_card, p1_discard_clicked, p2_discard_clicked, p1_magic_clicked, p2_magic_clicked);
				}
				else if (i == 1){
				    if(player_of_last == 1){
					p2_discard.addToDiscard(display_card.getCardToDisplay());
					display_card.getCardToDisplay().setPlayer(-1);
					clearPileCard(display_card, p1_discard_clicked, p2_discard_clicked, p1_magic_clicked, p2_magic_clicked);
					draw_pile_menu = false;
				    }
				    else{
					p1_discard.addToDiscard(display_card.getCardToDisplay());
					display_card.getCardToDisplay().setPlayer(1);
					clearPileCard(display_card, p1_discard_clicked, p2_discard_clicked, p1_magic_clicked, p2_magic_clicked);
					draw_pile_menu = false;
				    }
				}
				else if(i == 2){
				    if(player_of_last == 1){
					p2_magic.addToMagic(display_card.getCardToDisplay());
					display_card.getCardToDisplay().setPlayer(-1);
					clearPileCard(display_card, p1_discard_clicked, p2_discard_clicked, p1_magic_clicked, p2_magic_clicked);
					draw_pile_menu = false;
				    }
				    else{
					p1_magic.addToMagic(display_card.getCardToDisplay());
					display_card.getCardToDisplay().setPlayer(1);
					clearPileCard(display_card, p1_discard_clicked, p2_discard_clicked, p1_magic_clicked, p2_magic_clicked);
					draw_pile_menu = false;
				    }
			    
				}
				else if(i == 3){
				    int count = 0;
				    boolean returned = false;
				    if (player_of_last == 1){
					while(returned == false && count < 5){
					    if (p2_hand[count] == false){
						returned = true;
						p2_hand_object.setHandSlot(count, display_card.getCardToDisplay());
						display_card.getCardToDisplay().setPlayer(-1);
						p2_hand_object.getHandSlot(count).setX(22 + 157*count);
						p2_hand_object.getHandSlot(count).setY(30);
						clearPileCard(display_card, p1_discard_clicked, p2_discard_clicked, p1_magic_clicked, p2_magic_clicked);
						p2_hand[count] = true;
					    }
					    count++;
					}
				    }
				    else{
					while(returned == false && count < 5){
					    if (p1_hand[count] == false){
						returned = true;
						p1_hand_object.setHandSlot(count, display_card.getCardToDisplay());
						p1_hand_object.getHandSlot(count).setPlayer(1);
						p1_hand_object.getHandSlot(count).setX(22 + 157*count);
						p1_hand_object.getHandSlot(count).setY(720);
						clearPileCard(display_card, p1_discard_clicked, p2_discard_clicked, p1_magic_clicked, p2_magic_clicked);
						p1_hand[count] = true;
					    }
					    count++;
					}
				    }
				}
				else if (i == 4){
				    if(player_of_last == -1){
					p2_discard.addToDiscard(display_card.getCardToDisplay());
					display_card.getCardToDisplay().setPlayer(-1);
					clearPileCard(display_card, p1_discard_clicked, p2_discard_clicked, p1_magic_clicked, p2_magic_clicked);
					draw_pile_menu = false;
				    }
				    else{
					p1_discard.addToDiscard(display_card.getCardToDisplay());
					display_card.getCardToDisplay().setPlayer(1);
					clearPileCard(display_card, p1_discard_clicked, p2_discard_clicked, p1_magic_clicked, p2_magic_clicked);
					draw_pile_menu = false;
				    }
				}
				else if(i == 5){
				    if(player_of_last == -1){
					p2_magic.addToMagic(display_card.getCardToDisplay());
					display_card.getCardToDisplay().setPlayer(-1);
					clearPileCard(display_card, p1_discard_clicked, p2_discard_clicked, p1_magic_clicked, p2_magic_clicked);
					draw_pile_menu = false;
				    }
				    else{
					p1_magic.addToMagic(display_card.getCardToDisplay());
					display_card.getCardToDisplay().setPlayer(1);
					clearPileCard(display_card, p1_discard_clicked, p2_discard_clicked, p1_magic_clicked, p2_magic_clicked);
					draw_pile_menu = false;
				    }
			    
				}
				else if(i == 6){
				    int count = 0;
				    boolean returned = false;
				    if (player_of_last == -1){
					while(returned == false && count < 5){
					    if (p2_hand[count] == false){
						returned = true;
						p2_hand_object.setHandSlot(count, display_card.getCardToDisplay());
						display_card.getCardToDisplay().setPlayer(-1);
						p2_hand_object.getHandSlot(count).setX(22 + 157*count);
						p2_hand_object.getHandSlot(count).setY(30);
						clearPileCard(display_card, p1_discard_clicked, p2_discard_clicked, p1_magic_clicked, p2_magic_clicked);
						p2_hand[count] = true;
					    }
					    count++;
					}
				    }
				    else{
					while(returned == false && count < 5){
					    if (p1_hand[count] == false){
						returned = true;
						p1_hand_object.setHandSlot(count, display_card.getCardToDisplay());
						p1_hand_object.getHandSlot(count).setPlayer(1);
						p1_hand_object.getHandSlot(count).setX(22 + 157*count);
						p1_hand_object.getHandSlot(count).setY(720);
						clearPileCard(display_card, p1_discard_clicked, p2_discard_clicked, p1_magic_clicked, p2_magic_clicked);
						p1_hand[count] = true;
					    }
					    count++;
					}
				    }
				}
				else if (i == 7){
				    game_mat.ressurect(display_card.getCardToDisplay());
				    clearPileCard(display_card, p1_discard_clicked, p2_discard_clicked, p1_magic_clicked, p2_magic_clicked);
				    // if (p1_magic_clicked){
				    //      game_mat.ressurect(p1_magic.getCardToDisplay());
				    // }
				}
			    } 
			}
		    }
		}
	    
		// if either arrow is clicked then the arrow count
		// is appropriately adjusted so the player can see
		// all of the cards in the piles
		if (my >= 336 && my <= 388)
		{
		    if (mx >= 199 && mx <= 267)
		    {
			if (p1_magic_clicked){
			    p1_magic.subtractToArrowCount ();
			}
			else if (p1_discard_clicked){
			    p1_discard.subtractToArrowCount ();
			}
			else if (p2_magic_clicked){
			    p2_magic.subtractToArrowCount ();
			}
			else if (p2_discard_clicked){
			    p2_discard.subtractToArrowCount ();
			}
		    }

		    else if (mx >= 1078 && mx <= 1144)
		    {
			if (p1_magic_clicked){
			    p1_magic.addToArrowCount ();
			}
			else if (p1_discard_clicked){
			    p1_discard.addToArrowCount ();
			}
			else if (p2_magic_clicked){
			    p2_magic.addToArrowCount ();
			}
			else if (p2_discard_clicked){
			    p2_discard.addToArrowCount ();
			}
		    }
		}
		// Check if a card was clicked in one of the piles
		// so it can be displayed

		if (my >= 335 && my <= 388)
		{
		    for (int i = 0 ; i < 5 ; i++)
		    {
			if (mx >= 279 + 160 * i && mx <= 424 + 160 * i)
			{
			    draw_pile_menu = true;
			    try{
				if (p1_magic_clicked){
				    display_card.setCardToDisplay (p1_magic.getMagicPile () [i + p1_magic.getArrowCount () * 5]);
				}
				if (p1_discard_clicked){
				    display_card.setCardToDisplay (p1_discard.getDiscardPile () [i + p1_discard.getArrowCount () * 5]);
				}
				if (p2_magic_clicked){
				    display_card.setCardToDisplay (p2_magic.getMagicPile () [i + p2_magic.getArrowCount () * 5]);
				}
				if (p2_discard_clicked){
				    display_card.setCardToDisplay (p2_discard.getDiscardPile () [i + p2_discard.getArrowCount () * 5]);
				}
			    } catch(NullPointerException npe){}
			}
		    }
		}

		// check if the return button was clicked in either
		// the discard or the magic screen

		if (mx >= 456 && mx <= 974 && my <= 180 && my >= 101)
		{
		    draw_pile_menu = false;
		    p1_discard_clicked = false;
		    p1_magic_clicked = false;
		    p2_discard_clicked = false;
		    p2_magic_clicked = false;
		    main_game = true;
		}
	    }


	}


	repaint ();
    }


    public Deck setUpMatFallenPhoenix (Mat m)
    {
	FallenPhoenixDeck p1_deck = new FallenPhoenixDeck ();
	Cultist cultist = new Cultist ();
	cultist.setX (147 * 1 + 15);
	cultist.setY (547 - 53 * 3);
	cultist.setName ("Cultist");
	cultist.setPlayer(1);
	Warrior warrior = new Warrior ();
	warrior.setX (147 * 3 + 15);
	warrior.setY (547 - 53 * 2);
	warrior.setName ("Warrior");
	warrior.setPlayer(1);
	Harbinger harbinger = new Harbinger ();
	harbinger.setX (147 * 1 + 15);
	harbinger.setY (547 - 53 * 2);
	harbinger.setName ("Harbinger");
	harbinger.setPlayer(1);
	Wall wall = new Wall ();
	wall.setX (147 * 2 + 15);
	wall.setY (547 - 53 * 2);
	wall.setName ("Wall");
	wall.setPlayer(1);
	game_mat.setCreature (cultist, 1, 3);
	game_mat.setCreature (warrior, 3, 2);
	game_mat.setCreature (harbinger, 1, 2);
	game_mat.setCard (wall, 2, 2);
	return p1_deck;
    }


    public Deck setUpMatFallenPhoenixAI (Mat m)
    {
	FallenPhoenixDeck p2_deck = new FallenPhoenixDeck ();
	Cultist cultist = new Cultist ();
	cultist.setX (147 * 4 + 15);
	cultist.setY (547 - 53 * 4);
	cultist.setName ("Cultist");
	cultist.setPlayer(-1);
	Warrior warrior = new Warrior ();
	warrior.setX (147 * 2 + 15);
	warrior.setY (547 - 53 * 5);
	warrior.setName ("Warrior");
	warrior.setPlayer(-1);
	Harbinger harbinger = new Harbinger ();
	harbinger.setX (147 * 4 + 15);
	harbinger.setY (547 - 53 * 5);
	harbinger.setName ("Harbinger");
	harbinger.setPlayer(-1);
	Wall wall = new Wall ();
	wall.setX (147 * 3 + 15);
	wall.setY (547 - 53 * 5);
	wall.setName ("Wall");
	wall.setPlayer(-1);
	game_mat.setCreature (cultist, 4, 4);
	game_mat.setCreature (warrior, 2, 5);
	game_mat.setCreature (harbinger, 4, 5);
	game_mat.setCard (wall, 3, 5);
	return p2_deck;
    }
    
    public void clearPileCard(DisplayCard display_card, boolean p1_discard_clicked, boolean p2_discard_clicked, boolean p1_magic_clicked, boolean p2_magic_clicked){
	 if (p1_discard_clicked){
	     p1_discard.clearCard(display_card.getCardToDisplay());
	 }
	 else if (p2_discard_clicked){
	     p2_discard.clearCard(display_card.getCardToDisplay());
	 }
	 else if (p1_magic_clicked){
	     p1_magic.clearCard(display_card.getCardToDisplay());
	 }
	 else if (p2_magic_clicked){
	     p2_magic.clearCard(display_card.getCardToDisplay());
	 }
    }
}
