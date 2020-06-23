import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.*;
import java.io.*;

/**
 * Driver class
 * @author Julia Chen
 * @version 8 June 6 2018
 * Time spent: 8 hours
 */

public class Driver extends Canvas implements Runnable{
  /**The window the game is built on*/
  private JFrame frame;
  /**The thread the driver is run with*/
  private Thread thread;
  /**The Menu object*/
  private Menu menu;
  /**The instance of levelselect*/
  private LvlSelect lvlSelect;  
  /**The instance of the level class*/
  private Level lvl;
  /**The instance of ScoreScreen class*/
  private static ScoreScreen s;
  /**The HighScores object*/
  private HighScores high;
  /**The blackhole that can be moved*/
  public static BlackHole b;
  /**The array of falling stars*/
  public static ArrayList<Star> stars;
  /**The instance of learn*/
  private Learn learn;
  /**The instructions object*/
  private Instructions instr;
  /**Instance of AskData class*/
  private AskData ask;
  /**the End class*/
  private End end;
  /**Number corresponding game menu or level.*/
  public static int level;
  /**Indicating the game is running*/
  private boolean running = false;
  /**Boolean indicating that you have already added your name*/
  public static boolean addedName;
  /*Boolean indicating a button has been clicked*/
  public static boolean clicked = false;
  /**Keeps score to compare with previous score. Used to control a segment to prevent it from infinite looping*/
  private int keepScore;
  /**Counts the number of frames reset until program ends**/
  private int count = 0;
  /**Name of player*/
  public static String name;
  
  /**
   * Class constructor that initializes all game components.
   * @param f is the JFrame painted onto
   * 
   * <b>Local variables: </b>
   * <p>
   * <b> physicalW </b> The array of physical words.
   * <p>
   * <b> mentalW </b> The array of mental words.
   * <p>
   * <b> socialW </b> The array of social words.
   * <p>
   * <b> in </b> The buffered reader.
   */
  public Driver(JFrame f){   
    frame = f;
    level = 1;
    b = new BlackHole(getWidth()/2, 500);
    stars = new ArrayList<Star>();
    lvlSelect = new LvlSelect(); 
    learn = new Learn();
    lvl = new Level();
    menu = new Menu();
    instr = new Instructions();
    high = new HighScores();
    ask = new AskData();
    s = new ScoreScreen(this);
    end = new End();
    addedName = false;
    name = "";
    this.addMouseListener(menu);//adding mouse listener for menu
    
    /**Array of the physical health words*/
    String[] physicalW=new String[50];
    /**Array of the mental health words*/
    String[] mentalW=new String[50];
    /**Array of the social health words*/
    String[] socialW=new String[50];
    BufferedReader in;
    
    try {
      in = new BufferedReader (new FileReader("assets/physical.txt"));
      for (int x=0;x<50;x++)
        physicalW[x]=in.readLine();
      in = new BufferedReader (new FileReader("assets/mental.txt"));
      for (int x=0;x<50;x++)
        mentalW[x]=in.readLine();
      in = new BufferedReader (new FileReader("assets/social.txt"));
      for (int x=0;x<50;x++)
        socialW[x]=in.readLine();
    }
    catch (IOException e){
      System.err.println("Couldn't read from files.");
    }
    stars.add(new Star(300,-500,lvl,physicalW,mentalW,socialW));//Adds game stars to array.
  }
  
  /**
   * Starts running the game and creates a thread.
   */
  public synchronized void start(){
    if(running == false){
      running = true;
      thread = new Thread(this);
      thread.start();
    }
  }
  
  /**
   * Runs the runnable.
   */
  public void run(){    
    while(running){
      this.update();
      this.draw();
      try{
        Thread.sleep(17);
      }
      catch (Exception e){}
      if (count >=150)
        running = false;
    }
    frame.dispose();
  }
  
  /**
   * Updates frames of stars, background, and blackhole.
   */
  private void update(){
    if(level >= 3 && level <=5){
      b.update();
      for(Star star : stars){
        star.setColour(level-2);
        star.update();
        if (b.getScore()!=keepScore && b.getScore()!=0 && b.getScore()%30==0 && star.getSpeed()<=12){
          keepScore = b.getScore();
          star.addSpeed();        
        }
        if (Star.count == 3){
          lvl.setNewGame();
          level = 7;
        }
      }
    }
  }
  
  /**
   * Draws graphics onto the frame
   * 
   * <b>Local variables: </b>
   * <p>
   * <b> bs </b> This BufferStrategy object.
   * <p>
   * <b> g </b> This Graphics object for the entire program.
   */
  private void draw(){
    BufferStrategy bs = this.getBufferStrategy();   
    if(bs == null){
      this.createBufferStrategy(3);
      return;
    } 
    Graphics g = bs.getDrawGraphics(); 
    if(level == 1 && !clicked){//go to main menu
      this.addMouseListener(menu);
      clicked = true;
      menu.draw(g);
    }
    else if(level == 2 && !clicked){//go to level select
      keepScore = 0;
      clicked = true;
      this.addMouseListener(lvlSelect);
      lvlSelect.draw(g);
    }
    else if(level == 3){//go to physical health game
      this.addKeyListener(b);//adding key listener for black hole
      lvl.run(g);
    }
    else if(level == 4){//go to social health game
      this.addKeyListener(b);//adding key listener for black hole
      lvl.run(g);
    }
    else if(level == 5){//go to mental health game
      this.addKeyListener(b);//adding key listener for black hole
      lvl.run(g);
    }
    else if (level ==6 && !clicked){//ask name
      this.addKeyListener(ask);
      clicked = true;
      ask.draw(g);
    }
    else if (level == 7 && !clicked) {//score screen
      this.addMouseListener(s);
      Star.count = 0;
      clicked = true;
      s.draw (g);
    }
    else if (level ==8 && !clicked) {//high score page 1
      this.addMouseListener(high);
      clicked = true;
      high.draw(g);
    }
    else if (level == 9 && !clicked) {//high score page 2
      this.addMouseListener(high);
      clicked = true;
      high.draw(g);
    }
    else if (level == 10 && !clicked) {
      this.addMouseListener(learn);//physical learn 1
      clicked = true;
      learn.draw(g,1);
    }
    else if (level == 11 && !clicked) {//social learn 1
      this.addMouseListener(learn);
      clicked = true;
      learn.draw(g,2);
    }
    else if (level == 12 && !clicked) {//mental learn 1
      this.addMouseListener(learn);
      clicked = true;
      learn.draw(g,3);
    }
    else if (level == 13 && !clicked) {
      this.addMouseListener(learn);//physical learn 2
      clicked = true;
      learn.draw(g,4);
    }
    else if (level == 14 && !clicked) {//social learn 2
      this.addMouseListener(learn);
      clicked = true;
      learn.draw(g,5);
    }
    else if (level == 15 && !clicked) {//mental learn 2
      this.addMouseListener(learn);
      clicked = true;
      learn.draw(g,6);
    }
    else if (level == 16) {//end class
      end.draw(g);
      count++;
    }
    else if (level == 17 && !clicked) {//instructions page 1
      this.addMouseListener(instr);
      clicked = true;
      instr.draw(g,1);
    }
    else if (level == 18 && !clicked) {//instructions page 2
      this.addMouseListener(instr);
      clicked = true;
      instr.draw(g,2);
    }
    g.dispose();
    bs.show();
  }
  
  /**
   * Set addedName to false
   */
  public void setAddedName(){
    addedName = true; 
  }
}
