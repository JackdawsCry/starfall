import javax.swing.*;
import java.util.*;
import java.awt.*;

/**
 * Window class to handle and print the driver and game components.
 * 
 * @author Julia Chen
 * @version 9 June 6 2018
 * Time spent: 30 mins
 */
public class Window{
  public static Driver d;
  /**
   * Constructor to create new window
   * 
   * @param  title  window title
   * <b>Local variables: </b>
   * <p>
   * <b> frame </b> The frame the built is built on.
   */
  public Window(String title){
    JFrame frame = new JFrame(title);
    d = new Driver(frame);
    d.setPreferredSize(new Dimension(1020,575));
    d.setMaximumSize(new Dimension(1020,575));
    d.setMinimumSize(new Dimension(1020,575));
    frame.add(d);
    frame.setResizable(false);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    d.start();
  }
  
}
