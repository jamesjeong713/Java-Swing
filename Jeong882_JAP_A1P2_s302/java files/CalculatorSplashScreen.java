package calculator;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 * File name: Calculator.java 
 * Author: Seongyeop Jeong, 040885882 
 * Course: CST8221 _ JAP, Lab Section: 302 
 * Assignment: 1 
 * Date: October 16th, 2018 
 * Professor: Daniel Cormier 
 * Purpose: This class is main application to launch the view controller of the calculator 
 * Class list: Controller
 */
public class CalculatorSplashScreen extends JWindow {
  /** serialVersioUID = {@value} */
  private static final long serialVersionUID = 1L;
  private final int timeSplash;
  
  /**
   * This constructor is to show the splash how long it will run
   * @param timeSplash      set the time of splash how long it will run
   * 
   */
  public CalculatorSplashScreen(int timeSplash) {
    this.timeSplash = timeSplash;
  }

  /**
   * displaying a splash screen before launching the program
   * 
   */
  public void showSplashWindow() {
    // declare the JPanel to assign the window 
    JPanel mainPanel = new JPanel(new BorderLayout());
    // set the window size
    int width = 500;
    int height = 400;
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screen.width - width) / 2;
    int y = (screen.height - height) / 2;
    // set the location and the size of the window
    setBounds(x, y, width, height);
    // to create the splash screen with labels 
    JLabel label = new JLabel(new ImageIcon(getClass().getResource("splash.gif")));
    JLabel demo = new JLabel("Seongyeop Jeong's Application (040885882)", JLabel.CENTER);
    // set the font style 
    demo.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
    // set the color of the text
    demo.setForeground(Color.white);
    // create custom RGB color
    mainPanel.setBackground(new Color(38, 38, 38));
    mainPanel.setBorder(BorderFactory.createLineBorder(Color.gray, 10));
    // add the labels to the main panel
    mainPanel.add(label, BorderLayout.CENTER);
    mainPanel.add(demo, BorderLayout.SOUTH);
    setContentPane(mainPanel); // set the content pane for this application's window
    setVisible(true); // to set up the visibility of the splash window
    // While the application is loading, throw the exceptions if the application is interrupted   
    try {
      Thread.sleep(timeSplash);
    } catch (InterruptedException e) {
      // thrown the thread when the application is interrupted
    }
    dispose(); // release the all of the resources of the window
  }
}
