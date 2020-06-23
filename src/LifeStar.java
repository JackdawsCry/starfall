 import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;

/**
 * This class represents the stars that keep track of your score.
 * @author Julia Chen
 * @version 9 June 6 2018
 * Time Spent: 30 mins
 */

public class LifeStar{
  /**The image of the star*/
  private BufferedImage image;
  /**The x,y coordinates of the star*/
  private int x,y;
  
  /**
   * Class constructor to load the images.
   * @param x is the x coordinate
   * @param y is the y coordinate
   */
  public LifeStar(int x, int y){
    this.x = x;
    this.y = y;
    try{
      image = ImageIO.read(new File("assets/grayStar.png"));
    }
    catch(IOException e){
      System.err.println("Could not find lifestar image!");
    }
  }
  
  /**
   * Changes the colour of the star to red to signify a lost life
   */
  public void changeColour(){
    try{
      image = ImageIO.read(new File("assets/redStar.png"));
    }catch(IOException e){
      System.err.println("Could not find images!");
    }
  }
  
  /**
   * Resets the star to default gray.
   */
  public void reset(){
    try{
      image = ImageIO.read(new File("assets/grayStar.png"));
    }catch(IOException e){
      System.err.println("Could not find images!");
    }
  }
  
  /**
   * Draws star onto screen.
   * @param g is the graphics object.
   */
  public void draw(Graphics g){
    g.drawImage(image,x,y,null);
  }
}