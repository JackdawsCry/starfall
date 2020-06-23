import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

/**
 * Class to display the end of the game.
 * @author Emma Lam
 * @version 1 June 4, 2018
 * Time spent: 30 minutes
 */

public class End{
  /**The background image*/
  private BufferedImage end;
  
  /**
   * Constructor that loads the image.
   */
  public End(){
    try{
      end = ImageIO.read(new File("assets/End.png"));;
    }
    catch(IOException e){
      System.err.println("Could not find end image!");
    }
  }
  
  /**
   * Draws end page.
   * @param g is the Graphics object
   */
  public void draw(Graphics g){
      g.drawImage (end, 0, 0, null);
  }    
}