

/**
 * File name: CalculatorViewController.java 
 * Author: Seongyeop Jeong, 040885882 
 * Course: CST8221 - JAP, Lab Section: 302 
 * Assignment: 1 
 * Date: October 16th, 2018 
 * Professor: Daniel Cormier 
 * Purpose: this class is to build the calculator GUI
 * Class list: CalculatorViewController, Controller
 */

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.Box;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is to assign text filed, label, and buttons on the panels. Also it is to control the
 * features with the action listener
 * @author Seongyeop Jeong
 * @version 1.0
 * @see CalculatorViewController
 * @since 1.8.0_181
 */
public class CalculatorViewController extends JPanel {
  /** serialVersioUID = {@value} */
  private static final long serialVersionUID = 896163862031126857L;
  // for the number buttons, and operators
  private static final String[] numbers =
      {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "\u00B1", "+"};
  private JTextField display1; // first display for the result of calculation
  private JTextField display2; // second display for the button action
  private JLabel error; // letter 'F'

  /**
   * This default constructor is to build the GUI of this application, calculator
   * @param none
   * @return none
   */
  public CalculatorViewController() {
    // The reference class for Controller to handle events
    Controller controller = new Controller();
    // set border of this class
    this.setLayout(new BorderLayout());
    this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    this.setBackground(Color.BLACK);
    // Main panels
    JPanel topPane = new JPanel(); // two kinds of displays
    JPanel modePanel = new JPanel(); // for mode (check box and radios)
    JPanel mainPanel = new JPanel(); // for number, operators, equals, and clear button
    // second line's panels from the top
    JPanel display = new JPanel(); // it is display panel to display for the text filed
    JPanel checkBoxPanel = new JPanel(); // it is panel for check box
    JPanel radios = new JPanel(); // panel for the radio
    // third line's main panels from the top
    JPanel keypad = new JPanel(); // to build the numbers on the keypad
    // top panel set layout
    topPane.setLayout(new BorderLayout()); // it is panel for error label, text field, backspace,
                                           // check box, and radio
    mainPanel.setLayout(new BorderLayout());
    // declare the reference of the buttons, check box, and radio button
    JButton backspace = new JButton(); // used for backspace button
    JCheckBox checkBox = new JCheckBox("Int"); // check box for Int
    JRadioButton singleJRadioButton = new JRadioButton(".0", false); // radio button for .0
    // radio button for .00, and it is to display as default at launch
    JRadioButton doubleJRadioButton = new JRadioButton(".00", true); 
    JRadioButton sciJRadioButton = new JRadioButton("Sci", false); // radio button for Sci
    // it is lightweight container to control the check box and radio buttons
    Box box = Box.createHorizontalBox();
    // it is to group check box and radio buttons
    ButtonGroup group = new ButtonGroup();
    // create the equal button1, 2 through the creatButton method, then set the border and size
    JButton equalButton1 = createButton("=", "=", Color.BLACK, Color.YELLOW, new Controller());
    equalButton1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Color.BLACK)); // to set up the border gap
    equalButton1.setPreferredSize(new Dimension(46, 55));
    JButton equalButton2 = createButton("=", "=", Color.BLACK, Color.YELLOW, new Controller());
    equalButton2.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.BLACK)); // to set up the border gap
    equalButton2.setPreferredSize(new Dimension(46, 55));
    // create the clear button1, 2 through the createButton method, then set the border and size
    JButton clearButton1 = createButton("C", "C", Color.BLACK, Color.RED, new Controller());
    clearButton1.setPreferredSize(new Dimension(0, 45));
    clearButton1.setBorder(BorderFactory.createEmptyBorder()); // it doesn't need to set the border gap
    clearButton1.setLayout(new GridLayout(1, 1, 1, 1));
    JButton clearButton2 = createButton("C", "C", Color.BLACK, Color.RED, new Controller());
    clearButton2.setPreferredSize(new Dimension(0, 45));
    clearButton2.setBorder(BorderFactory.createEmptyBorder()); // it doesn't need to set the border gap
    clearButton2.setLayout(new GridLayout(1, 1, 1, 1));
    // to set the error label
    error = new JLabel("F");
    // to set up the size of the label, option, color, alignment, font, and borders.
    error.setPreferredSize(new Dimension(46, 55));
    error.setOpaque(true);
    error.setBackground(Color.YELLOW);
    error.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 5, Color.BLACK));
    error.setHorizontalAlignment(JLabel.CENTER);
    error.setFont(new Font(error.getFont().getName(), error.getFont().getStyle(), 20));
    // use the grid layout to distribute display 1 and 2
    display.setLayout(new GridLayout(2, 0));
    display.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 1, Color.BLACK));
    // reference to JTextField for the first display
    display1 = new JTextField();
    display1.setPreferredSize(new Dimension(16, 30)); // set the size of this text field
    display1.setBackground(Color.WHITE); // background's color is white
    display1.setEditable(false); // user can not edit this display
    display1.setHorizontalAlignment(JTextField.RIGHT); // align to right side for text
    // set the location of the text
    display1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Color.WHITE)); 
    // reference to JTextField for the second display
    display2 = new JTextField();
    display2.setPreferredSize(new Dimension(16, 30));
    display2.setBackground(Color.WHITE);
    display2.setEditable(false);
    display2.setHorizontalAlignment(JTextField.RIGHT);
    display2.setText("0.0"); // display the text as default to 0.0
    display2.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Color.WHITE));
    // set the preference of the backspace button
    backspace.setPreferredSize(new Dimension(45, 55)); // set the size of this button
    backspace.setBackground(Color.YELLOW); // set the background's color
    backspace.setOpaque(true); // to set the transparent
    backspace.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 1, Color.BLACK)); // set the border
                                                                                   // and color
    backspace.setText("\u21da"); // set the backspace as unicode
    backspace.setFont(new Font(backspace.getFont().getName(), Font.BOLD, 25)); // set the properties
                                                                               // of the button
    backspace.setToolTipText("Backspace (Alt-B)"); // to show up the tool tip when the mouse is over
    backspace.setMnemonic('B'); // it is for mnemonic (shortcut)
    backspace.setActionCommand("\u21da"); // define a custom short action command for the button
    backspace.addActionListener(controller); // set action listener for button
    // add the text filed into display panel
    display.add(display1);
    display.add(display2);
    // add to top panel which is first one from the top
    topPane.add(error, BorderLayout.WEST); // add error label into west side of the top panel
    topPane.add(display, BorderLayout.CENTER); // add the display panel on the center to top panel
    topPane.add(backspace, BorderLayout.EAST); // add the backspace button on the east side
    topPane.add(modePanel, BorderLayout.SOUTH); // add the mode panel on the bottom of the top panel
    // set layout and background's color, and border to mode panel
    modePanel.setLayout(new BorderLayout());
    modePanel.setBackground(Color.BLACK);
    modePanel.setBorder(BorderFactory.createEmptyBorder(10, 1, 10, 1));
    // set layout, border, color of the background, size, default selection, and add checkbox panel
    // to action listener
    checkBox.setLayout(new FlowLayout());
    checkBox.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 5, Color.BLACK));
    checkBox.setBackground(Color.GREEN);
    checkBox.setSize(40, 0);
    checkBox.setSelected(false); // do not select the check box at the first time (default: false)
    checkBox.addActionListener(controller);
    // set the properties for 3 radios, then add them to the action listener
    singleJRadioButton.setBackground(Color.YELLOW);
    singleJRadioButton.addActionListener(controller);
    doubleJRadioButton.setBackground(Color.YELLOW);
    doubleJRadioButton.addActionListener(controller);
    sciJRadioButton.setBackground(Color.YELLOW);
    sciJRadioButton.addActionListener(controller);
    // set the grid layout for radios, then add 3 radios to panel of radios
    radios.setLayout(new GridLayout(1, 3, 5, 5)); // 3 columns for 3 radios, with 5 gaps for height
                                                  // and vertical
    radios.add(singleJRadioButton);
    radios.add(doubleJRadioButton);
    radios.add(sciJRadioButton);
    // set the layout, color then add to check box panel by locating to west side
    checkBoxPanel.setLayout(new BorderLayout());
    checkBoxPanel.setBackground(Color.BLACK);
    checkBoxPanel.add(checkBox, BorderLayout.WEST);
    // add three radio buttons to the box container
    box.add(singleJRadioButton);
    box.add(doubleJRadioButton);
    box.add(sciJRadioButton);
    // then group them all
    group.add(checkBox);
    group.add(singleJRadioButton);
    group.add(doubleJRadioButton);
    group.add(sciJRadioButton);
    // then add panels (checkBoxPanel, box) to mode panel
    modePanel.add(checkBoxPanel, BorderLayout.WEST);
    modePanel.add(box, BorderLayout.EAST);
    // set the layout and border for the keypad
    keypad.setLayout(new GridLayout(4, 2, 2, 2));
    keypad.setBorder(BorderFactory.createEmptyBorder(2, 4, 2, 4));
    keypad.setBackground(Color.WHITE);
    // this loop is to display the keypad on the keypad panel,
    // then add keypad panel to the main panel
    for (int i = 0; i < numbers.length; i++) {
      // if the element in array is equal to ".", assign to dotButton by calling createButton method
      if (numbers[i].equals(".")) {
        // then add the dotButton to keypad panel
        keypad.add(createButton(".", ".", Color.BLACK, Color.BLUE, new Controller()));
      }
      // if there is the letter "+-" in the array, create button. then add it to key pad panel
      else if (numbers[i].equals("\u00B1")) {
        // add the create +- button to the keypad
        keypad.add(createButton("\u00B1", "\u00B1", Color.BLACK, Color.PINK, new Controller()));
      } 
      // if the element is +, -, *, /, add them to keypad panel 
      else if (numbers[i].equals("+") || numbers[i].equals("-") || numbers[i].equals("*")
          || numbers[i].equals("/")) {
        keypad.add(createButton(numbers[i], numbers[i], Color.BLACK, Color.CYAN, new Controller()));
      } 
      // if the element is numbers, add them to keypad panel
      else {
        keypad.add(createButton(numbers[i], numbers[i], Color.BLACK, Color.BLUE, new Controller()));
      }
    }
    // to add buttons to equal button panel
    add(equalButton1, BorderLayout.WEST);
    add(equalButton2, BorderLayout.EAST);
    // to add buttons to clear Button panel
    mainPanel.add(clearButton1, BorderLayout.SOUTH);
    mainPanel.add(clearButton2, BorderLayout.NORTH);
    // add the panel top panel and main panel
    add(topPane, BorderLayout.NORTH);
    add(mainPanel, BorderLayout.CENTER);
    // add the keypad panel on the main panel
    mainPanel.add(keypad, BorderLayout.CENTER);
  }

  /**
   * This method is to create button by using JButton function, then it is including the 4
   * parameters which are strings, colors, and action listener
   * @param String text - it is text to show the letters on the label and button
   * @param String ac - to display texts which is connected to the user input with the button
   * @param Color fg - foreground color
   * @param Color gf - background color
   * @param ActionListener handler - this is the reference of the event handler class
   * @return JButton button - return the created button to calling function
   */
  private JButton createButton(String text, String ac, Color fg, Color bg, ActionListener handler) {
    // create the button object with text and color which is the letters and background
    JButton button = new JButton(text);
    button.setForeground(fg);
    button.setBackground(bg);
    // to avoid the run-time error, set the action command for the button when the string is not
    // null
    if (ac != null)
      button.setActionCommand(ac);
    // set the font size of the button to 20
    button.setFont(new Font(button.getFont().getName(), button.getFont().getStyle(), 20));
    // registers the handler
    button.addActionListener(handler);
    // return the reference of the button
    return button;
  }

  /**
   * This class is a private inner class of CalculatorViewController to connect to the display of
   * the buttons with user input
   * @author Seongyeop Jeong
   * @version 1.0
   * @see Controller
   * @since 1.8.0_181
   */
  private class Controller implements ActionListener {

    /**
     * This method is to create responding action with user by implmenting the action listener
     * interface. When the action event is occurred by user, this object's action listener will be
     * invoked.
     * @param ActionEvent event
     * @return none
     */
    @Override
    public void actionPerformed(ActionEvent event) {
      // set the text on the second display with user input
      display2.setText(event.getActionCommand());
    }
  }

}
