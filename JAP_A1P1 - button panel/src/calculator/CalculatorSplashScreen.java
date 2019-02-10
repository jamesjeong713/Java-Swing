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
 * Course: CST8221 - JAP, Lab Section: 302
 * Assignment: 1
 * Date: October 16th, 2018
 * Professor: Daniel Cormier
 * Purpose: This class is main application to launch the view controller of the calculator
 * Class list: Controller
 */
public class CalculatorSplashScreen extends JWindow{
	/**   serialVersioUID = {@value}   */
  private static final long serialVersionUID = 1L;
  private final int duration;

	public CalculatorSplashScreen(int duration) {
		this.duration = duration;
	}
	
	public void showSplashWindow() {
		/** declare the JPanel to assign the window */
		JPanel mainContent = new JPanel(new BorderLayout()); 
		
		/** set the window size */
		int width = 500;
		int height = 400;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width - width) / 2;
		int y = (screen.height - height) / 2;
		
		/** set the location and the size of the window */
		setBounds(x, y, width, height); 
		
		/** To create the splash screen */
		JLabel label = new JLabel(new ImageIcon(getClass().getResource("splash.gif")));
		JLabel demo = new JLabel("Seongyeop Jeong's Application (040885882)", JLabel.CENTER);
		demo.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
		demo.setForeground(Color.white);
		mainContent.add(label, BorderLayout.CENTER);
		mainContent.add(demo, BorderLayout.SOUTH);
		/** create custom RGB color */
		mainContent.setBackground(new Color(38, 38, 38));
		mainContent.setBorder(BorderFactory.createLineBorder(Color.gray, 10));
		
	    setContentPane(mainContent);	
		setVisible(true);  

		try {
			Thread.sleep(duration);
		} catch(InterruptedException e) {
			/** destroy the window and release all resources */
		}
		dispose();
	}
}
