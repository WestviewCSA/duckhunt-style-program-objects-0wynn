import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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

	
	public void paint(Graphics pen) {
		
		//this line of code is to force redraw the entire frame
		super.paintComponent(pen);
		
		//call paint for the object
		//for objects, you call methods on them using the dot operator
		//methods use always involve parenthesis
		
		
		myBackground.paint(pen);
		
		Cursor.paint(pen);
		
		myBush.paint(pen);
		
		myCloud.paint(pen);
		
		
		myGift.paint(pen);
		
		myHitSign.paint(pen);
		
		myRound.paint(pen);
		
		myScore.paint(pen);
		
		myDog.paint(pen);
		
	
		Font f = new Font ("Segoe UI" , Font.PLAIN, 38);
		pen.setFont(f);;
		pen.setColor(Color.yellow);
		pen.drawString("" + totalScore, 745, 50); // total score is a variavle at top
		pen.drawString("" + time, 310,510);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent mouse) {
	    // Runs when the mouse is clicked (pressed and released quickly).
	    // Example: You could use this to open a menu or select an object.
		int xVal = mouse.getX();
		int Yval = mouse.getY();
		
		System.out.println( "mouse clicked at : x :" + xVal + " and Yval y :" + Yval);
		
		if(myGift.isClicked(xVal, Yval)) {
			myGift.hit();
			//myDog.showRetrieve(myGift.x);
			//myDog.setLocation(xVal, 350);
			myDog.showRetrieve(myGift.x);
		}
		
		
		
		
		
		
		
		
		
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
		int mx = mouse.getX();
		int my = mouse.getY();
		
	
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
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
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
