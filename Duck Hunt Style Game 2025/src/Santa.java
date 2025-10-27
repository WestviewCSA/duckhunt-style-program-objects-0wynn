import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

// The Duck class represents a picture of a duck that can be drawn on the screen.
public class Santa {
    // Instance variables (data that belongs to each Duck object)
    private Image img;               // Stores the picture of the duck
    private AffineTransform tx;      // Used to move (translate) and resize (scale) the image

    // Variables to control the size (scale) of the duck image
    private double scaleX;           
    private double scaleY;           

    // Variables to control the location (x and y position) of the duck
    private double x;                
    private double y;        
    
    //variables for speed
    private int vx = 3;
    private int vy;
    
    //debugging variable
    public boolean debugging = true;

    // Constructor: runs when you make a new Duck object
    public Santa() {
        img = getImage("/imgs/The_Santa (1).gif"); // Load the image file
        
        tx = AffineTransform.getTranslateInstance(0, 0); // Start with image at (0,0)
        
        // Default values
        scaleX = .3;
        scaleY = .3;
        x = 350;
        y = 720;

        init(x, y); // Set up the starting location and size
    }
    
    //2nd constructor to initialize location and scale!
    public Santa(int x, int y, int scaleX, int scaleY) {
    	this();
    	this.x 		= x;
    	this.y 		= y;
    	this.scaleX = scaleX;
    	this.scaleY = scaleY;
    	init(x,y);
    }
    
    //2nd constructor to initialize location and scale!
    public Santa(int x, int y, int scaleX, int scaleY, int vx, int vy) {
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
    	x += vx;
    	y += vy;
    	
    	if(x>=1900) {
    		vx *= -1;
    	}
    	
    	if (x<= 0) {
    		vx *= -1;
    	}
    	
    	if (y <= 0) vy*= -1;
    	
    	//if object is falling from sky
    	
    	if (vx== 0 && vy > 10) {
    		vy = - (int)(Math.random()*8+3);
    		vx = (int) (Math.random() *8+3);
    		
    		
    		if (Math.random()<0.5) {
    			
    		}
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
        g.drawRect((int) x , (int) y, 312, 313);
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
            URL imageURL = Santa.class.getResource(path);
            tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempImage;
    }

    // NEW: Method to set scale
    public void setScale(double sx, double sy) {
        scaleX = vx;
        scaleY = vy;
        init(x, y);  // Keep current location
    }

    // NEW: Method to set location
    public void setLocation(double newX, double newY) {
        x = newX;
        y = newY;
        init(x, y);  // Keep current scale
    }
    
    //collision and collision logic
    public boolean checkCollision(int mX, int mY) {
    	Rectangle mouse = new Rectangle(mX, mY, 50,50);
    	
    	Rectangle thisObject = new Rectangle ((int) x,(int) y,50,50);
    	
    	//use built in method for rectangle to chekc if they intersect
    	if(mouse.intersects(thisObject)) {
    		
    		//logic if colliding
    		
    		vx = 0;
    		vy = 9;
    		
    		return true;
    	}else  {
    		
    		//logic not colliding
    		return false;
    	}
    }
    
    
    
    
}
