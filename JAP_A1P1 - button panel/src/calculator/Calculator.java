package calculator;

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
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * This class is launch the main program
 * @author Seongyeop Jeong
 * @version 1.0
 * @see Calculator
 * @since 1.8.0_181
 */
public class Calculator {
	
	/**
	 * 
	 * @param 
	 * @return N/A
	 */
	public static void main(String[] args) {
		
		CalculatorSplashScreen calcSplashScreen = new CalculatorSplashScreen(5000);  
		CalculatorViewController controller = new CalculatorViewController();
		
		calcSplashScreen.showSplashWindow();
				
		/**
		 * 
		 * @param 
		 * @return N/A
		 */
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				JFrame frame = new JFrame();
				frame.setTitle("Calculator");
				frame.setMinimumSize(new Dimension(380, 520));  
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setContentPane(controller);
				frame.setLocationByPlatform(true); // sets the application location at launch
				frame.setVisible(true);
			}
		});		
	}
}
