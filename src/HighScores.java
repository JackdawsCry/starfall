import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;
import java.awt.event.*;

/**
 * Class for high scores page 1 screen. 
 * 
 * @author Caroline Ma
 * @version 9 June 6 2018
 * Time spent: 5 hours
 */

public class HighScores extends MouseAdapter{
 /**The images of the background*/
 private BufferedImage bg, bg2;
 /**Custom font used*/
 private Font font;
 /**Array of scores and names*/
 private String[][] arr;

 /**
  * Class Constructor
  * <b>Local variables: </b>
  * <p>
  * <b> ge </b> The graphics environment.
  */
 public HighScores(){
  try{
   bg = ImageIO.read(new File("assets/Highscores.png"));
   bg2 = ImageIO.read(new File("assets/Highscore2.png"));
   GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
   ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("assets/Megrim.ttf")));
   font = new Font("Megrim", Font.BOLD, 25);
  }
  catch (IOException|FontFormatException e){ 
   System.err.println("Could not find high score image");
  }
 }

 /**
  * Method to get the scores from the file
  * 
  * <b>Local variables: </b>
  * <p>
  * <b> numScores </b> The number of lines in the file.
  * <p>
  * <b> br </b> The buffered reader.
  */
 public void getScores (){
  int numScores = 0;
  BufferedReader br;
  try {
   br = new BufferedReader(new FileReader("assets/HighScores.txt"));
   while (br.readLine() != null && numScores < 20)
    numScores++;
   numScores /= 2;
   br.close();
   arr = new String[numScores][2];
   br = new BufferedReader(new FileReader("assets/HighScores.txt"));
   for (int i = 0; i < numScores; i++) {
    arr[i][0] = br.readLine();
    arr[i][1] = br.readLine();
   }
   br.close();
  }
  catch (IOException e) {
   System.err.println ("Couldn't read from files in highscores.");
  }
 }

 /**
  * Draws all graphics per frame
  * @param g is the graphics object
  * 
  * <b>Local variables: </b>
  * <p>
  * <b> width </b> The length of the string in pixels.
  * <p>
  * <b> start </b> The index of where to start reading in scores.
  * <p>
  * <b> stop </b> The index of where to stop reading in scores.
  * <p>
  * <b> length </b> The length of the score string.
  */
 public void draw(Graphics g){
  int width = 0, stop = 0, start = 0;
  g.setFont (font);
  g.setColor (Color.white);
  getScores();
  if (Driver.level==8){
   g.drawImage(bg, 0,0,null);
   start = 0;
   if (arr.length<=5){
    stop = arr.length;
   }
   else
    stop = 5;
  }
  else{
   g.drawImage(bg2, 0,0,null);
   start = 5;
   stop = arr.length;
  }
  for (int i = start; i < stop; i++) {
   if (i == 0 && Driver.level==8)
    g.setColor (Color.yellow);
   else if (i == 1&& Driver.level==8)
    g.setColor (Color.gray);
   else if (i == 2&& Driver.level==8)
    g.setColor (new Color (102, 68, 25));
   else
    g.setColor (Color.white);
   width = g.getFontMetrics().stringWidth(arr[i][1]);
   if (Driver.level==8){
    g.drawString (arr[i][0], 40, 250 + 50 * i);
    g.drawString (arr[i][1], 990-width, 250 + 50 * i);
   }
   else{
    g.drawString (arr[i][0], 40, 250 + 50 * (i-5));
    g.drawString (arr[i][1], 990-width, 250 + 50 * (i-5));
   }
   int length = arr[i][1].length();
   if (length > 3) {
    String temp = arr[i][1];
    int x = length % 3;
    arr[i][1] = temp.substring (0, x);
    temp = temp.substring (x);
    if (x != 0) 
     arr[i][1] += ",";
    for (int j = 0; j <= length - 3; j += 3){
     if (j == 0) 
      arr[i][1] += temp.substring (j, j + 3);
     else
      arr[i][1] += "," + temp.substring (j, j + 3);
    }
   }
  }
  g.dispose();
 }

 /**
  * Determines what happens when mouse is clicked.
  * @param e is the MouseEvent object when mouse is clicked
  */
 public void mouseClicked(MouseEvent e){
  if (e.getX() >= 64 && e.getY() >= 505 && e.getX() <= 302 && e.getY() <= 553 && Driver.level ==8){//go to next page
   Window.d.removeMouseListener(this);
   Driver.clicked = false;
   Driver.level = 9;
  }
  else if (e.getX() >= 64 && e.getY() >= 505 && e.getX() <= 302 && e.getY() <= 553&& Driver.level ==9){//go to previous page
   Window.d.removeMouseListener(this);
   Driver.clicked=false;
   Driver.level = 8;
  }
  else if (e.getX() >= 739 && e.getY() >= 504 && e.getX() <= 979 && e.getY() <= 554){//return to main menu
   Window.d.removeMouseListener(this);
   Driver.clicked = false;
   Driver.level = 1;
  }
  else if (e.getX() >= 450 && e.getY() >= 512 && e.getX() <= 606 && e.getY() <= 550) {//reset high scores
   try {
    Driver.clicked = false;
    PrintWriter out = new PrintWriter (new FileWriter("assets/HighScores.txt"));
    out.println();
    out.close();
   }
   catch (IOException f) {
    System.out.println ("File error");
   }
  }
 }
}