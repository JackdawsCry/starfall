import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;

/**
 * Class for main menu. Controls menu flow with buttons.
 * 
 * @author Julia Chen
 * @version 1 June 6 2018
 * Time Spent: 1 hour
 */
public class Menu extends MouseAdapter{
 /**The image of the background*/
 private BufferedImage mainMenu;

 /**
  * Constructor to load images.
  */
 public Menu(){
  try{
   mainMenu = ImageIO.read(new File("assets/MainMenu.png"));
  }
  catch (IOException e){
   System.err.println("Could not find main menu image!");
  }
 }

 /**
  * Draws all graphics per frame
  * @param g is the graphics object
  */
 public void draw(Graphics g){
  g.drawImage(mainMenu, 0,0,null);
  g.dispose();
 }

 /**
  * Determines what happens when mouse is clicked
  * @param e is the MouseEvent object when mouse is clicked
  */
 public void mouseClicked(MouseEvent e){
  if(e.getX() >= 388 && e.getY() >= 388 && e.getX() <= 633 && e.getY() <= 440){//go to level select   
   Window.d.removeMouseListener (this);
   Driver.clicked = false;
   if (Driver.addedName)
    Driver.level = 2;
   else
    Driver.level=6;
  }
  else if (e.getX() >= 60 && e.getY() >= 424 && e.getX() <= 280 && e.getY() <= 470){//go to scores
   Window.d.removeMouseListener (this);
   Driver.level = 8;
   Driver.clicked = false;
  }
  else if (e.getX() >= 740 && e.getY() >= 420 && e.getX() <= 960 && e.getY() <= 470){//go to the end
   Driver.level = 16;
   Driver.clicked = false;
  }
  else if (e.getX() >= 388 && e.getY() >= 470 && e.getX() <= 638 && e.getY() <= 526){//go to the instructions 
   Driver.level = 17;
   Driver.clicked = false;
  }
 }
}

