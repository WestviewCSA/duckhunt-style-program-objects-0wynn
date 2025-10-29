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
	private Dog myDog = new Dog ();
	
	private MyCursor Cursor = new MyCursor();
	
	private Background myBackground = new Background();
	
	private ChristmasBush myBush = new ChristmasBush();
	
	private Cloud myCloud = new Cloud();
	
	
	private gift myGift = new gift();
	
    private HitSign myHitSign = new HitSign();	
    
    private ShotSign myRound = new ShotSign();
    
    private ScoreSign myScore = new ScoreSign();

    
    private int totalScore = 0; // needs to increase when it 
    private int time = 30;
    
    private int missFlash = 0; //if user misses, the flash indicates it 
    
    private int missX = 0; // when you click and miss, this is the x cord when u miss
    private int missY = 0; // when u miss, this gets the y cord
	
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
		
		myDog.paint(g2);
		
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

	@Override
	public void mousePressed(MouseEvent mouse) {
	    // Runs when a mouse button is pressed down.
	    // Example: You could start dragging an object here.
		int x = mouse.getX();
		int y = mouse.getY();
		
		System.out.println ("mouse click at : x :" + x + "and y :" + y);
		if ( myGift.isClicked( x , y)) {
			myGift.hit();
			totalScore += 10;
			myDog.showRetrieve(myGift.x);
			myDog.setTargetGift(myGift);
			
		} else {
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
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 if ( missFlash > 0) {
			 missFlash --;
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
	}

}
