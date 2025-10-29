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
	
	
	

    private Image img;   
    
    private Image normal; //normal look
    private Image dead;
    
    // Stores the picture of the duck
    private AffineTransform tx;      // Used to move (translate) and resize (scale) the image

    // Variables to control the size (scale) of the duck image
    private double scaleX;           
    private double scaleY;           

    // Variables to control the location (x and y position) of the duck
   public double x;                
    private double y;        
    
    private boolean hit = false;
    //debugging variable
    
    
    public boolean debugging = true;
    
    private int imgWidth = 128;
    private int imgHeight = 128; // size of image
    
    
    //variables for speed
    private int vx = 20;
    private int vy = 20;

    
    
    
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
    	if (!hit) {     // if it is not clicked, continue moving around
    	
    		x += vx;
        	y+= vy;
        	
        if( x<= 0) vx *=-1;
        if (x>= 2000) vx *= -1;
        if ( y<= 0) vy *= -1;
        if (y >= 900) vy *= -1;
        
        if ( vx == 0 && vy >10) {
        	vy = -(int) (Math.random()*8+3) ;    	
        	vx = (int) (Math.random()* 8 + 3);
        	if ( Math.random() < 0.5) vx *= -1;
        }
        
        
    	}else {
    		y += vy;
    		if ( y>= 900) {
    			y = 900;
    			vy = 0;
    			img = dead;
    			
    		}
    	}
    	
    

    	
    	
    	
    }
    
    
    public void hit() {
    	hit = true;
    	vx = 0;
    	vy = 10;
    }
    
    
    
   public boolean isClicked ( int mouseX , int mouseY) {
	   double scaledWidth = imgWidth * scaleX * 1.2;
	   double scaledHeight = imgHeight * scaleY * 1.2;
	   	return mouseX >= x && mouseX <= x + scaledWidth 
	   			&& mouseY >= y && mouseY <=y + scaledHeight;
   }
    
    
    //called when duck lands after hit
    public void reset() {
    	
    	
    	hit = false;
    	img = normal;
    	
    	
    	vx = 20;
    	
    	if(Math.random() < 0.5) vx = -vx;
    	vy = 20;
    	
    	x = Math.random() * (1920 - 200) + 100; // random x area in the screen
    	y = 700; //start y below top
    	
    	init( x  , y);
    }
    
    
    // Draws the duck on the screen
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;   // Graphics2D lets us draw images
        g2.drawImage(img, tx, null);      // Actually draw the duck image
        update();
        init(x,y);
        
       
        	double scaledWidth = imgWidth * scaleX;
       double scaledHeight = imgHeight * scaleY;
        	g.setColor(Color.green);
        	g.drawRect( (int) x, (int) y, (int) scaledWidth, (int) scaledHeight);
     
        
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
}
    
       
      // Rectangle mouse = new Rectangle ( mX, mY, 50, 50);
       
        // represent the gift as a rectangle
       
    //   Rectangle thisObject =  new Rectangle ( (int) x, (int) y, 50, 50);
	  
       
       // use built in method for rectangle to check if they intersect
      
    
    	   
    	   //santa shows up where the duck is going to fall ( in the x)
    	  
    	   
    	   
    	   
