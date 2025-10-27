import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

// The Duck class represents a picture of a duck that can be drawn on the screen.
public class gift {
    // Instance variables (data that belongs to each Duck object)
	
	
	
	private Dog dog = new Dog ("dog1.png");

    private Image img;   
    
    private Image normal; //normal look
    private Image dead;
    
    // Stores the picture of the duck
    private AffineTransform tx;      // Used to move (translate) and resize (scale) the image

    // Variables to control the size (scale) of the duck image
    private double scaleX;           
    private double scaleY;           

    // Variables to control the location (x and y position) of the duck
    private double x;                
    private double y;        
    
    private boolean exploded = true;
    //debugging variable
    
    
    public boolean debugging = true;
    
    
    //variables for speed
    private int vx = 4;
    private int vy = 4;

    // Constructor: runs when you make a new Duck object
    public gift() {
        normal = getImage("/imgs/Moving_gift.gif"); 
        // Load the image file
        dead = getImage("/imgs/The_ExplodingGift.gif");
        //ADDD DEAD IMAGE
       
        //img will point to current state object for image
        		
        img = normal;
        
        tx = AffineTransform.getTranslateInstance(0, 0); // Start with image at (0,0)
        
        // Default values
        scaleX = 1.3;
        scaleY = 1.3;
        x = 800;
        y = 720;

        init(x, y); // Set up the starting location and size
    }
    
    //2nd constructor to initialize location and scale!
    public gift(int x, int y, int scaleX, int scaleY) {
    	this();
    	this.x 		= x;
    	this.y 		= y;
    	this.scaleX = scaleX;
    	this.scaleY = scaleY;
    	init(x,y);
    }
    
    //2nd constructor to initialize location and scale!
    public gift(int x, int y, int scaleX, int scaleY, int vx, int vy) {
    	this();
    	this.x 		= x;
    	this.y 		= y;
    	this.scaleX = scaleX;
    	this.scaleY = scaleY;
    	this.vx 	= vx; 
    	this.vy 	= vy;
    	init(x,y);
    }
    
    public void setVelocityVariables(int vx, int vy) {
    	this.vx = vx;
    	this.vy = vy;
    }
    
    
    // Changes the picture to a new image file
    public void changePicture(String imageFileName) {
        img = getImage("/imgs/"+imageFileName);
        init(x, y); // keep same location when changing image
    }
    
    //update any variables for the object such as x, y, vx, vy
    public void update() {
    	//x pos, updates based on vx
    	
    	
    	x += vx;
    	y+= vy;
    	
    	int rightEdge = 2000-100;
    	int bottomEdge = 1000-100;
    	//right bounce
    	
    	if ( x <= 0) {
    		x = 0;
    		vx *= -1;
    	}else if ( x>= rightEdge) {
    		x = rightEdge;
    		vx *= -1;
    	}
    			
    	//respawning _> change to nornmal spirirt
    	img = normal;
    	
    	if(y<=0 ) {
    		
    		vy *= -1;
    	} else if ( y>= bottomEdge) {
    		y = bottomEdge;
    		vy *= -1;
    	}
    	
    	
    }
    
    
    
    
    
    
    
    // Draws the duck on the screen
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;   // Graphics2D lets us draw images
        g2.drawImage(img, tx, null);      // Actually draw the duck image
        update();
        init(x,y);
        
        if(debugging) {
        	g.setColor(Color.green);
        	g.drawRect( (int) x, (int) y, 140, 150);
     
        }
    }
    
    // Setup method: places the duck at (a, b) and scales it
    private void init(double a, double b) {
        tx.setToTranslation(a, b);        // Move the image to position (a, b)
        tx.scale(scaleX, scaleY);         // Resize the image using the scale variables
    }

    // Loads an image from the given file path
    private Image getImage(String path) {
        Image tempImage = null;
        try {
            URL imageURL = gift.class.getResource(path);
            tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempImage;
    }

    // NEW: Method to set scale
    public void setScale(double sx, double sy) {
        scaleX = sx;
        scaleY = sy;
        init(x, y);  // Keep current location
    }

    // NEW: Method to set location
    public void setLocation(double newX, double newY) {
        x = newX;
        y = newY;
        init(x, y);  // Keep current scale
        
        //represent the mouse as a rectangle
    }
    
       public boolean checkCollision ( int mX , int mY) {
       
       Rectangle mouse = new Rectangle ( mX, mY, 50, 50);
       
        // represent the gift as a rectangle
       
       Rectangle thisObject =  new Rectangle ( (int) x, (int) y, 50, 50);
	  
       
       // use built in method for rectangle to check if they intersect
       vx = 0; // turn off vx to fall from sky
	   vy = 5;
       if(mouse.intersects(thisObject)) {
    	    // all y - grav
    	   
    	   
    	   //change sprit to dead skin
    	   vx = 0;
    	   vy = 0;
    	   img = dead;
    
    	   
    	   //santa shows up where the duck is going to fall ( in the x)
    	   this.dog.x = (int) x;
    	   this.dog.y = 770;
    	   this.dog.vy = -3;
    	   return true;
       }else {
    	   
    	   
    	   return false;
       }
    }
       
       public double getX() { return x;}
       public double getY() { return y;}
}
