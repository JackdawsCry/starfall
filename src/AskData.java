import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

/**
 * This class controls the screen where the user enters their name. It
 * errortraps the key input, and properly stores the name after it is
 * entered.
 * 
 * @author Julia Chen
 * @version 9 June 6 2018
 * Time Spent: 6 hours
 */
public class AskData extends KeyAdapter{
  /**The image of the background*/
  private BufferedImage background;
  /**The string to be printed*/
  private String str;
  /**The graphics object*/
  private Graphics g;
  /**The font of the text*/
  private Font font;
  
  /**
   * Constructor to load images, assign font, and initialize variables.
   * 
   * <b>Local variables: </b>
   * <p>
   * <b> ge </b> The graphics environment.
   */
  public AskData(){
    try{
      background = ImageIO.read(new File("assets/OldAskName.png"));
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("assets/Audiowide-Regular.ttf")));
      font = new Font("Courier New", 1, 36);
    }
    catch (IOException|FontFormatException e){
     System.err.println("Could not find image or fonts");
    }
    str = "";
  }
  
  /**
   * Draws all graphics onto the canvas.
   * @param g is the graphics object
   */
  public void draw(Graphics g){
    g.drawImage(background, 0,0,null);
    g.setColor(Color.white);
    g.setFont(font);
    g.drawString("Please enter your name: ", 100,200);
    g.fillRect(200,300,620,50);
    g.setColor(Color.black);
    g.drawString (str, 205, 340);
    g.dispose();
  }
  
  /**
   * Default method for when key is pressed. COnverts key value to 
   * character to print onto the screen.
   * 
   * <b>Local variables: </b>
   * <p>
   * <b> key </b> The pressed key.
   */
  public void keyReleased(KeyEvent e){
    if (Driver.clicked){
      Driver.clicked = false;
      int key = e.getKeyCode();
      if (key == KeyEvent.VK_ENTER){
        Driver.name = str;
        Driver.addedName = true;
        Driver.level=2;
        Window.d.removeKeyListener (this);
      }
      else if (key == KeyEvent.VK_BACK_SPACE && str.length()>0){
        str=str.substring(0,str.length()-1);
      }
      else if (str.length()<=27 && key != KeyEvent.VK_BACK_SPACE && ((key >=65 && key <=90) || (key >=48 && key <=57) || key ==32)){
        str += (char)key;
      }
    }
  }
}

