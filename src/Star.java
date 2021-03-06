import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

/**
 * Class that represents a star. Contains good/bad label.
 * 
 * @author Julia Chen
 * @version 9 June 4 2018
 * Time spent: 1 hour
 */

public class Star{
  /**Count of missed stars*/
  public static int count;
  /**x,y coordinates and width and height of star*/
  public int x, y, w, h; 
  /**The image of the star*/
  private BufferedImage image;
  /*The level the star is in*/
  private Level lvl;
  /**Array of the physical health words*/
  private String[] physicalW;
  /**Array of the mental health words*/
  private String[] mentalW;
  /**Array of the social health words*/
  private String[] socialW;
  /**The word on the star*/
  public String label;
  /**If the label is a good or bad word*/
  private boolean type;
  /**Speed of star (increment)*/
  private int speed;
  
  /**
   * Constructor
   * @param  x  start pos x of player
   * @param  y  start pos y of player
   * @param lvl is the level the star is in
   * @param pW is the array of physical words
   * @param mW is the array of mental words
   * @param sW is the array of social words
   */
  public Star(int x, int y, Level lvl, String[]pW, String[]mW, String[]sW){
    count = 0;
    this.x = x;
    this.y = y;
    this.lvl = lvl;
    physicalW = pW;
    mentalW = mW;
    socialW = sW;    
    speed = 4;
  }
  
  /**
   * Update method to update star position
   */
  public void update(){
    y += speed;    
    // Falling too low
    if(y > 630){//replace 0 with the height of star
      if (type)
        count++;
      reset();  
    }
  }
  
  /**
   * Resets the player size and position for a new level
   */
  public void reset(){
    if (count>0)
      lvl.updateStar(count);
    x=(int)(30+Math.random()*941);
    y = -100;
    setLabel(Driver.level-2);
  }
  
  /**
   * Draws all player graphics
   * @param g Graphics reference
   * <b>Local variables: </b>
   * <p>
   * <b> width </b> The width of the label in pixels.
   */
  public void draw(Graphics g){
    g.drawImage(image,x,y,null);
    g.setColor(Color.white);
    g.setFont(new Font("Arial", 1, 24));
    int width = g.getFontMetrics().stringWidth(label);
    g.drawString(label,x-width/2+30,y);
  }
  
  /**
   * Method to generate the hitbox for the player
   * @return Rectangle instance with x,y,w,h
   */
  public Rectangle getBounds(){
    return new Rectangle(x,y,w,h);
  }
  
  /**
   * Method to set the star colour
   * @param col is the level
   */
  public void setColour(int col){
    try{
      if (col== 1){
        image = ImageIO.read(new File("assets/starBlue.png"));
      }
      else if (col== 2){
        image = ImageIO.read(new File("assets/starGreen.png"));
      }
      else if (col==3){
        image = ImageIO.read(new File("assets/starOrange.png"));
      }
      w = image.getWidth();
      h = image.getHeight();
    }
    catch(IOException e){
     System.err.println("Could not find star image!");}
  }
  
  /**
   * Method to generate the labels of the stars
   * @param col is the level
   * <b>Local variables: </b>
   * <p>
   * <b> ind </b> The radom index to generate a random label.
   */
  public void setLabel(int col){
    int ind = (int) (40*Math.random());
    if (col == 1)
      label = physicalW[ind];
    else if (col == 2)
      label = socialW[ind];
    else 
      label = mentalW[ind];    
    if (label.charAt(0)=='!')
      type = true;
    else
      type = false;
    label = label.substring(1);
  }
  
  /**
   * Increments speed variable
   */
  public void addSpeed(){
    speed++;
  }
  
  /**Accessor for star speed.
    * @return the speed
    */
  public int getSpeed(){
   return speed; 
  }
  
  /**
   * Reset star speed.
   */
  public void resetSpeed() {
   speed = 4;
  }
  /**
   * Accessor for type
   * @return the boolean type (good/bad).
   */
  public boolean getType(){
    return type; 
  }
}