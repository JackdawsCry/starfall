import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

/**
 * Class for level select menu.
 * @author Caroline Ma
 * @version 1 June 6 2018
 * Time spent: 2 Hours
 */

public class LvlSelect extends MouseAdapter{
 /**The background picture*/
 private BufferedImage i;

 /**
  * The class constructor to load the image.
  */
 public LvlSelect(){
  try{
   i = ImageIO.read(new File("assets/LevelSelect.png"));
  }
  catch(IOException e){
   System.err.println("Could not find level select image!");
  }
 }

 /**
  * Draws all graphics per frame
  * @param g is the Graphics object
  */
 public void draw(Graphics g){
  g.drawImage (i, 0, 0, null);
  g.fillRect(0,0,Driver.WIDTH,Driver.HEIGHT);
  g.dispose();
 }

 /**
  * Determines what happens when the mouse is clicked.
  * @param e is the MouseEvent when mouse is clicked.
  */
 public void mouseClicked(MouseEvent e){
  if (e.getX() >= 35 && e.getY() >= 293 && e.getX() <= 340 && e.getY() <= 373){ //physical button
   Window.d.removeMouseListener (this);
   Driver.clicked = false;
   Driver.level = 3;
  }
  else if (e.getX() >= 115 && e.getY() >= 385 && e.getX() <= 262 && e.getY() <= 436) {//physical learn
   Window.d.removeMouseListener (this);
   Driver.clicked = false;
   Driver.level = 10;
  }
  else if (e.getX() >= 359 && e.getY() >= 293 && e.getX() <= 667 && e.getY() <= 373){ //social button
   Window.d.removeMouseListener (this);
   Driver.clicked = false;
   Driver.level = 4;   
  }
  else if (e.getX() >= 442 && e.getY() >= 386 && e.getX() <= 586 && e.getY() <= 435){ //social learn
   Window.d.removeMouseListener (this);
   Driver.clicked = false;
   Driver.level = 11;   
  }
  else if (e.getX() >= 686 && e.getY() >= 293 && e.getX() <= 993 && e.getY() <= 373){ //mental button
   Window.d.removeMouseListener (this);
   Driver.clicked = false;
   Driver.level = 5;
  }
  else if (e.getX() >= 769 && e.getY() >= 383 && e.getX() <= 912 && e.getY() <= 435){ //mental learn
   Window.d.removeMouseListener (this);
   Driver.clicked = false;
   Driver.level = 12;
  }
  else if (e.getX() >= 400 && e.getY() >= 462 && e.getX() <= 640 && e.getY() <= 513){
   Window.d.removeMouseListener (this);
   Driver.clicked = false;//coords of MainMenu button
   Driver.level = 1;
  }
 }
}
