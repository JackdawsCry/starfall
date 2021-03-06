import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.*;
import java.awt.event.*;

/**
 * Class for score display at end of game. 
 * 
 * @author Caroline Ma
 * @version 9 June 6 2018
 * Time spent: 1 hour
 */
public class ScoreScreen extends MouseAdapter{
 /**The image of the background*/
 private BufferedImage endGame;
 /**Custom fonts used*/
 private Font font,font2;
 /**Driver of the game*/
 private Driver d;

 /**
  * Constructor to load the fonts and images.
  * 
  * @param d is the game driver
  * <b>Local variables: </b>
  * <p>
  * <b> ge </b> The graphics environment.
  */
 public ScoreScreen(Driver d){
  this.d = d;
  try{
   endGame = ImageIO.read(new File("assets/EndGame.png"));
   GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
   ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("assets/Megrim.ttf")));
   font = new Font("Megrim", Font.BOLD, 72);
   font2 = new Font("Megrim", Font.BOLD, 40);
  }
  catch (IOException|FontFormatException e){
   System.err.println("Could not find score screen image!");
  }
 }

 /**
  * Draws all graphics per frame
  * @param g is the graphics object
  * 
  * <b>Local variables: </b>
  * <p>
  * <b> score </b> The score to be printed.
  * <p>
  * <b> width </b> The width of the score string in pixels.
  */
 public void draw(Graphics g){
  String score = "" + Driver.b.getScore();
  g.setFont (font);
  int width = g.getFontMetrics().stringWidth(score);
  g.drawImage(endGame, 0,0,null);
  g.setColor (Color.white);
  g.drawString (score, 510-width/2, 320);
  g.setFont (font2);
  width = g.getFontMetrics().stringWidth(Driver.name);
  g.drawString (Driver.name, 520-(width/2), 490);
  g.dispose();
 }

 /**
  * Records the score in the high scores list if it is part of the top ten scores.
  * 
  * <b>Local variables: </b>
  * <p>
  * <b> name </b> The string array of names.
  * <p>
  * <b> score </b> The int array of scores.
  * <p>
  * <b> numScores </b> The number of scores in the file.
  * <p>
  * <b> count </b> The index counter when writing to the file.
  * <p>
  * <b> printed </b> Indicating the information has been printed to the file.
  * <p>
  * <b> in </b> The bufferedreader.
  * <p>
  * <b> out </b> The printwriter.
  */
 public void writeScore () {
  ArrayList<String>name = new ArrayList<String>();
  ArrayList<Integer>score = new ArrayList<Integer>();
  int numScores = 0;
  int count = 0;
  boolean printed = false;
  BufferedReader in;
  PrintWriter out;
  try {
   in = new BufferedReader(new FileReader("assets/HighScores.txt"));
   while (in.readLine() != null){
    numScores++;
   }
   numScores /= 2;
   in.close();
  }
  catch (IOException e) {
   System.err.println ("Couldn't read from file for scores.");
  }
  try {
   in = new BufferedReader(new FileReader("assets/HighScores.txt"));
   for (int i = 0; i < numScores; i++) {
    name.add(in.readLine());
    score.add(Integer.parseInt(in.readLine()));
   }
   in.close();
  }
  catch (IOException | NumberFormatException e) {
   System.err.println ("Couldn't read from file for scores.");
  }
  while (count < numScores && count <=10){
   if (Driver.b.getScore()> score.get(count) && !printed){
    name.add(count, Driver.name);
    score.add(count, Driver.b.getScore());
    printed = true;
    if (name.size() >10) {
     name.remove(name.size()-1);
     score.remove(score.size()-1);
    }
   }
   count++;
  }
  try{
   out = new PrintWriter(new FileWriter("assets/HighScores.txt"));
   for (int i = 0; i < name.size(); i++) {
    out.println(name.get(i));
    out.println(score.get(i));
   }
   out.close();
  }
  catch (IOException e){
   System.err.println("Couldn't write to file 1");
  }
  if (numScores<10 && !printed){//adds score to file if it is less than all other scores but score slots are not filled
   try{
    out = new PrintWriter(new FileWriter("assets/HighScores.txt", true));
    out.println(Driver.name);
    out.println(Driver.b.getScore());
    out.close();
   }
   catch (IOException e){
    System.err.println("Couldn't write to file (append)");
   }
  }
 }

 /**
  * Determines what happens when mouse is clicked
  * @param e is the MouseEvent object when mouse is clicked
  */
 public void mouseClicked(MouseEvent e){
  if (e.getX() >= 740 && e.getY() >= 506 && e.getX() <= 977 && e.getY() <= 554 && Driver.clicked){//go to main menu
   d.removeMouseListener(this);
   writeScore();
   Driver.b.resetScore();
   Driver.clicked = false;
   Driver.level = 1;
  }
 }
}

