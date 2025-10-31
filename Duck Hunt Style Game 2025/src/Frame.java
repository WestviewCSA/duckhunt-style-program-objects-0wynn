import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	//frame size
	private int screenWidth = 1920, screenHeight = 1080;
	private String title = "Duck Hunt";
	
	
	/**
	 * Declare and instantiate (create) your objects here
	 */
	private Santa mySanta = new Santa ();
	
	private MyCursor Cursor = new MyCursor();
	
	private Background myBackground = new Background();
	
	private ChristmasBush myBush = new ChristmasBush();
	
	private Cloud myCloud = new Cloud();
	
	
	private gift myGift = new gift();
	
    private HitSign myHitSign = new HitSign();	
    
    private ShotSign myRound = new ShotSign();
    
    private ScoreSign myScore = new ScoreSign();
    

    
    // ALL DUCKS FOR HIT SIGN
    // calling from giftHit.java
    private giftHit Santa1 = new giftHit();
    private giftHit Santa2 = new giftHit();
    private giftHit Santa3 = new giftHit();
    private giftHit Santa4 = new giftHit();
    private giftHit Santa5 = new giftHit();
    private giftHit Santa6 = new giftHit();
    private giftHit Santa7 = new giftHit();
    
    //SHOTTS
    
    private Snowball hit1 = new Snowball();
    private Snowball hit2 = new Snowball();
    private Snowball hit3 = new Snowball();
    private Snowball hit4 = new Snowball();
    private Snowball hit5 = new Snowball();
    private Snowball hit6 = new Snowball();
    private Snowball hit7 = new Snowball();



    
    private final int hitsignx = 170;
    private final int hitsigny = 870;
    private final int duckSpace = 100;
    
    
  private int delayReset = 0;
    
    private int totalScore = 0; // needs to increase when it 
   
    
    private int missFlash = 0; //if user misses, the flash indicates it 
    
    private int missX = 0; // when you click and miss, this is the x cord when u miss
    private int missY = 0; // when u miss, this gets the y cord
	
    public  int hitCounter = 0;
    
    
    private int shotsLeft = 7; // how many shots are left
	
	
	public void paint(Graphics pen) {
		
		//this line of code is to force redraw the entire frame
		super.paintComponent(pen);
		
		//call paint for the object
		//for objects, you call methods on them using the dot operator
		//methods use always involve parenthesis
		Graphics2D g2 = (Graphics2D) pen;
		
		myBackground.paint(g2);
		

		
		Cursor.paint(g2);
		
		myBush.paint(g2);
		
		myCloud.paint(g2);
		
		
		myGift.paint(g2);
		
		myHitSign.paint(g2);
		
		myRound.paint(g2);
		
		myScore.paint(g2);
		
		mySanta.paint(g2);
		
		if ( hitCounter> 0) Santa1.paint(g2);
		if (hitCounter > 1) Santa2.paint(g2);
		if (hitCounter > 2) Santa3.paint(g2);
		if (hitCounter > 3) Santa4.paint(g2);
		if (hitCounter > 4) Santa5.paint(g2);
		if (hitCounter > 5) Santa6.paint(g2);
		if (hitCounter > 6) Santa7.paint(g2);
		
if( hitCounter == 7 || shotsLeft == 0 && totalScore >= 55) {  //end text
	Font gameOver = new Font ("Segoe UI" , Font.BOLD, 100);
	pen.setFont(gameOver);
	pen.setColor(Color.BLUE);
	pen.drawString("YOU WON!" , 220 , 100);
	
}

if (shotsLeft == 0) {  // end text
	if(totalScore < 55) {
	Font gameOver = new Font ("Segoe UI" , Font.BOLD, 100);
	pen.setFont(gameOver);
	pen.setColor(Color.BLUE);
	pen.drawString("YOU LOST!" , 500 , 100);
	

}
}
int i = 0;

while ( i < shotsLeft) {
	if (i == 0) hit1.paint(g2);
    else if (i == 1) hit2.paint(g2);
    else if (i == 2) hit3.paint(g2);
    else if (i == 3) hit4.paint(g2);
    else if (i == 4) hit5.paint(g2);
    else if (i == 5) hit6.paint(g2);
    else if (i == 6) hit7.paint(g2);
    i++;
}




	// SCORE
		 
		Font score = new Font ("Segoe UI" , Font.PLAIN, 60);
		pen.setFont(score);
		pen.setColor(Color.RED);
		pen.drawString("" + totalScore , 220 , 100);
		
		if ( missFlash  > 0) {
			
			g2.setColor(Color.RED);
			g2.setFont(score);
			g2.drawString("MISS!" , missX - 60 , missY - 10);
		}
		
		}
	
	
	@Override
	public void mouseClicked(MouseEvent mouse) {
	    // Runs when the mouse is clicked (pressed and released quickly).
	    // Example: You could use this to open a menu or select an object.
		
			
	}
	

	@Override
	public void mouseEntered(MouseEvent mouse) {
	    // Runs when the mouse enters the area of a component (like a button).
	    // Example: You could highlight the button when the mouse hovers over it.
		
		System.out.println(mouse.getX()-25  + mouse.getY()-50);
	}

	@Override
	public void mouseExited(MouseEvent mouse) {
	    // Runs when the mouse leaves the area of a component.
	    // Example: You could remove the highlight when the mouse moves away.
	}
	
	private void consumeShot() {
		if (shotsLeft <= 0) 
			return;
		
		
		int snowBallHide = shotsLeft - 1; // number that tells which snowball should disappear
		
		if ( snowBallHide == 6) hit7.setLocation(-500, -500);
		else if ( snowBallHide == 5) hit6.setLocation(-500, -500);
		else if ( snowBallHide == 4) hit5.setLocation(-500, -500);
		else if ( snowBallHide == 3) hit4.setLocation(-500, -500);
		else if ( snowBallHide == 2) hit3.setLocation(-500, -500);
		else if ( snowBallHide == 1) hit2.setLocation(-500, -500);
		else if ( snowBallHide == 0) hit1.setLocation(-500, -500);

		shotsLeft --;
		
		
		
		if (shotsLeft == 0) {
			delayReset = 1;
		}
		
		
		
	
	}

	@Override
	public void mousePressed(MouseEvent mouse) {
	    // Runs when a mouse button is pressed down.
	    // Example: You could start dragging an object here.
		int x = mouse.getX();
		int y = mouse.getY();
		
		if (shotsLeft >0) {
			consumeShot();
		}
		
		
		
		
		System.out.println ("mouse click at : x :" + x + "and y :" + y);
		if ( myGift.isClicked( x , y)) {
			
			// gift hit sound
			Music hitSound = new Music("hitsound.wav", false);
			hitSound.play();
			
			myGift.hit();
			totalScore += 10;
			
			
			
			
			
			if (hitCounter <= 6) {
			    int hit_x = hitsignx + 150 + hitCounter * duckSpace;
			    int hit_y = hitsigny + 20;

			    if (hitCounter == 0) Santa1.setLocation(hit_x, hit_y);
			    else if (hitCounter == 1) Santa2.setLocation(hit_x, hit_y);
			    else if (hitCounter == 2) Santa3.setLocation(hit_x, hit_y);
			    else if (hitCounter == 3) Santa4.setLocation(hit_x, hit_y);
			    else if (hitCounter == 4) Santa5.setLocation(hit_x, hit_y);
			    else if (hitCounter == 5) Santa6.setLocation(hit_x, hit_y);
			    else if (hitCounter == 6) Santa7.setLocation(hit_x, hit_y);

			    
			    hitCounter++;
			}
			
		
			
			mySanta.setFrame(this);
			mySanta.showRetrieve(myGift.x);
			mySanta.setTargetGift(myGift);
			
		} else {
			
			// gift miss shot sound
			Music hitSound = new Music("misshit.wav", false);
			hitSound.play();
			
			
			totalScore -= 5;
			 
			if ( totalScore < 0 ) totalScore = 0;
			
			missX = x;
			missY = y;
			missFlash = 15;
		}
		
		
		
		}
	
			
		
	
	

	@Override
	public void mouseReleased(MouseEvent mouse) {
	    // Runs when a mouse button is released.
	    // Example: You could stop dragging the object or drop it in place.
	}



	/*
	 * This method runs automatically when a key is pressed down
	 */
	public void keyPressed(KeyEvent key) {
		
		System.out.println("from keyPressed method:"+key.getKeyCode());
		
	}

	/*
	 * This method runs when a keyboard key is released from a pressed state
	 * aka when you stopped pressing it
	 */
	public void keyReleased(KeyEvent key) {
		
	}

	/**
	 * Runs when a keyboard key is pressed then released
	 */
	public void keyTyped(KeyEvent key) {
		
		
	}
	
	
	/**
	 * The Timer animation calls this method below which calls for a repaint of the JFrame.
	 * Allows for our animation since any changes to states/variables will be reflected
	 * on the screen if those variables are being used for any drawing on the screen.
	 */
	
	public void doGamereset() {
		
		totalScore = 0;
		hitCounter = 0;
		delayReset = 0;
		
		Santa1.setLocation(-200, -200);
	    Santa2.setLocation(-200, -200);
	    Santa3.setLocation(-200, -200);
	    Santa4.setLocation(-200, -200);
	    Santa5.setLocation(-200, -200);
	    Santa6.setLocation(-200, -200);
	    Santa7.setLocation(-200, -200);
		

	    
	    
	    shotsLeft = 7;
	    int snowBallX = 1450; // where the snowball pops in x area
	    int snowBallY = 53; // in y area
	    
	    int ballSpacing = 60;
	    int i = 0;
	    while ( i < shotsLeft) {
	    	int x = snowBallX + i * ballSpacing;
	    	if ( i == 0) hit1.setLocation(x, snowBallY);
	    	else if (i ==1) hit2.setLocation(x, snowBallY);
	    	else if (i ==2) hit3.setLocation(x, snowBallY);
	    	else if (i ==3) hit4.setLocation(x, snowBallY);
	    	else if (i ==4) hit5.setLocation(x, snowBallY);
	    	else if (i ==5) hit6.setLocation(x, snowBallY);
	    	else if (i ==6) hit7.setLocation(x, snowBallY);
	    	
	    
	    	
	    	i++;
	    	
	    }
	    
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 if ( missFlash > 0) {
			 missFlash --;
		 }
		 
		 
		 if (delayReset > 0) {
			 delayReset++;
			 if ( delayReset > 120) {
				 doGamereset();
				 delayReset = 0;
			 }

		 }
		repaint();
		
	}
	
	/*
	 * Main method to create a Frame (the GUI that you see)
	 */
	public static void main(String[] arg) {
		new Frame();
	}
	
	
	
	public Frame() {
		JFrame f = new JFrame(title);
		f.setSize(new Dimension(screenWidth, screenHeight));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		
		f.setContentPane(this);
		
		//panel size
		this.setPreferredSize(new Dimension (screenWidth , screenHeight));
		
		//null layout
		f.setLayout(null);
		Music mySound = new Music("fortniteaudio_compressed.wav", true);
		
		mySound.play();
		
		this.addMouseListener(this);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocusInWindow();
		
		Timer t = new Timer(16, this);
		t.start();
		
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("christmasCursor.png");
		Cursor a = toolkit.createCustomCursor(image , new Point(this.getX(), this.getY()), "");
		this.setCursor (a);
		
		mySanta.setFrame(this);
		  
		Santa1.setLocation(-200, -200);
	    Santa2.setLocation(-200, -200);
	    Santa3.setLocation(-200, -200);
	    Santa4.setLocation(-200, -200);
	    Santa5.setLocation(-200, -200);
	    Santa6.setLocation(-200, -200);
	    Santa7.setLocation(-200, -200);
		
	    
	    int snowBallX = 1450; // where the snowball pops in x area
	    int snowBallY = 53; // in y area
	    
	    int ballSpacing = 60; // space between each snowball
	    int i = 0;
	    
	    while (i < 7) {
	    	int x = snowBallX + i * ballSpacing;
	    	if ( i == 0) hit1.setLocation(x, snowBallY);
	    	else if (i ==1) hit2.setLocation(x, snowBallY);
	    	else if (i ==2) hit3.setLocation(x, snowBallY);
	    	else if (i ==3) hit4.setLocation(x, snowBallY);
	    	else if (i ==4) hit5.setLocation(x, snowBallY);
	    	else if (i ==5) hit6.setLocation(x, snowBallY);
	    	else if (i ==6) hit7.setLocation(x, snowBallY);
	    	
	    	
	    	i++;
	    	
	    }
	    
	  
		
		
		
	}

}
