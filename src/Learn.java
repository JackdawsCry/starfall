import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

/**
 * Class to display learn pages.
 * @author Caroline Ma
 * @version 8 May 24 2018
 * Time spent: 2 hours
 */

public class Learn extends MouseAdapter{
 /**The background images for the different topics*/
 private BufferedImage p1, s1, m1, p2, s2, m2;

 /**
  * Class constructor to load images
  */
 public Learn(){
  try{
   p1 = ImageIO.read(new File("assets/LearnP1.png"));
   s1 = ImageIO.read(new File("assets/LearnS1.png"));
   m1 = ImageIO.read(new File("assets/LearnM1.png"));
   p2 = ImageIO.read(new File("assets/LearnP2.png"));
   s2 = ImageIO.read(new File("assets/LearnS2.png"));
   m2 = ImageIO.read(new File("assets/LearnM2.png"));
  }
  catch(IOException e){
   System.err.println("Could not find learn images!");
  }
 }

 /**
  * Draws learn pages.
  * @param g is the Graphics object
  * @param level is a number corresponding with a page.
  */
 public void draw(Graphics g, int level){
  if (level == 1){
   g.drawImage (p1, 0, 0, null);
  }
  else if (level == 2){
   g.drawImage (s1, 0, 0, null);
  }
  else if (level == 3){
   g.drawImage (m1, 0, 0, null);
  }
  else if (level == 4){
   g.drawImage (p2, 0, 0, null);
  }
  else if (level == 5){
   g.drawImage (s2, 0, 0, null);
  }
  else if (level == 6){
   g.drawImage (m2, 0, 0, null);
  }
 }

 /**
  * Determines what happens when the mouse is clicked.
  * @param e is the MouseEvent when mouse is clicked.
  */
 public void mouseClicked(MouseEvent e){
  if (e.getX() >= 730 && e.getY() >= 516 && e.getX() <= 968 && e.getY() <= 565) {
   Window.d.removeMouseListener (this);
   Driver.clicked = false;
   Driver.level = 2;
  }
  else if (e.getX() >= 57 && e.getY() >= 506 && e.getX() <= 292 && e.getY() <= 556&&Driver.level >= 10 && Driver.level <=12) {
   Window.d.removeMouseListener (this);
   Driver.clicked = false;
   Driver.level += 3;
  }
 }
}