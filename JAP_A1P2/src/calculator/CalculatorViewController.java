package calculator;


/**
 * File name: CalculatorViewController.java 
 * Author: Seongyeop Jeong, 040885882 
 * Course: CST8221_JAP, Lab Section: 302 
 * Assignment: 1 
 * Date: November 6th, 2018 
 * Professor: Daniel Cormier 
 * Purpose: this class is to build the calculator GUI 
 * Class list: CalculatorViewController, Controller, KeyAction
 */

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.Box;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * This class is to assign text filed, label, and buttons on the panels. Also it is to control the
 * features with the action listener
 * 
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
  private JButton dotButton;
  private JButton backspace;
  private JRadioButton singleJRadioButton;
  private JRadioButton doubleJRadioButton;
  private JRadioButton sciJRadioButton;

  // this constant is for numbers
  private static final int keyEvents[] = new int[] {KeyEvent.VK_7, KeyEvent.VK_8, KeyEvent.VK_9,
      KeyEvent.VK_SLASH, KeyEvent.VK_4, KeyEvent.VK_5, KeyEvent.VK_6, KeyEvent.VK_MULTIPLY,
      KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_MINUS, KeyEvent.VK_0,
      KeyEvent.VK_PERIOD, KeyEvent.VK_PLUS, KeyEvent.VK_EQUALS};
  // this constant is for number of keypad
  private static final int keyEventsPad[] = new int[] {KeyEvent.VK_NUMPAD7, KeyEvent.VK_NUMPAD8, 
      KeyEvent.VK_NUMPAD9,
      KeyEvent.VK_DIVIDE, KeyEvent.VK_NUMPAD4, KeyEvent.VK_NUMPAD5, KeyEvent.VK_NUMPAD6, KeyEvent.VK_MULTIPLY,
      KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD2, KeyEvent.VK_NUMPAD3, KeyEvent.VK_MINUS, KeyEvent.VK_NUMPAD0,
      KeyEvent.VK_PERIOD, KeyEvent.VK_PLUS, KeyEvent.VK_EQUALS};
  /**
   * This default constructor is to build the GUI of this application, calculator
   */
  public CalculatorViewController() {
    // The reference class for Controller to handle events
    // CalculatorModel calcModel = new CalculatorModel();
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
    backspace = new JButton(); // used for backspace button
    JCheckBox checkBox = new JCheckBox("Int"); // check box for Int
    singleJRadioButton = new JRadioButton(".0", false); // radio button for .0
    // radio button for .00, and it is to display as default at launch
    doubleJRadioButton = new JRadioButton(".00", true);
    sciJRadioButton = new JRadioButton("Sci", false); // radio button for Sci
    // it is lightweight container to control the check box and radio buttons
    Box box = Box.createHorizontalBox();
    // it is to group check box and radio buttons
    ButtonGroup group = new ButtonGroup();
    // create the equal button1, 2 through the creatButton method, then set the border and size
    JButton equalButton1 = createButton("=", "=", Color.BLACK, Color.YELLOW, controller);
    equalButton1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Color.BLACK)); // to set up
                                                                                      // the border
                                                                                      // gap
    equalButton1.setPreferredSize(new Dimension(46, 55));
    equalButton1.setActionCommand("=");
    JButton equalButton2 = createButton("=", "=", Color.BLACK, Color.YELLOW, controller);
    equalButton2.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.BLACK)); // to set up
                                                                                      // the border
                                                                                      // gap
    equalButton2.setPreferredSize(new Dimension(46, 55));
    equalButton2.setActionCommand("=");
    getActionMap().put("=", new KeyActions(equalButton1));
    getActionMap().put("=", new KeyActions(equalButton2));
    getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "=");
    // create the clear button1, 2 through the createButton method, then set the border and size
    JButton clearButton1 = createButton("C", "C", Color.BLACK, Color.RED, controller);
    clearButton1.setPreferredSize(new Dimension(0, 45));
    clearButton1.setBorder(BorderFactory.createEmptyBorder()); // it doesn't need to set the border
                                                               // gap
    clearButton1.setLayout(new GridLayout(1, 1, 1, 1));
    clearButton1.setActionCommand("C");
    JButton clearButton2 = createButton("C", "C", Color.BLACK, Color.RED, controller);
    clearButton2.setPreferredSize(new Dimension(0, 45));
    clearButton2.setBorder(BorderFactory.createEmptyBorder()); // it doesn't need to set the border
                                                               // gap
    clearButton2.setLayout(new GridLayout(1, 1, 1, 1));
    clearButton2.setActionCommand("C");
    // keyboard input for clear buttons
    getActionMap().put("C", new KeyActions(clearButton2));
    getActionMap().put("C", new KeyActions(clearButton1));
    getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "C");
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
    // display1.setColumns(23);
    display1.setEditable(false); // user can not edit this display
    display1.setHorizontalAlignment(JTextField.RIGHT); // align to right side for text
    // set the location of the text
    display1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Color.WHITE));
    // reference to JTextField for the second display
    display2 = new JTextField();
    display2.setPreferredSize(new Dimension(16, 30));
    display2.setBackground(Color.WHITE);
    // display2.setColumns(23);
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
    // set the transparent for backspace button
    backspace.setContentAreaFilled(false); 
    backspace.setOpaque(true);
    // keyboard input
    getActionMap().put("\u21da", new KeyActions(backspace));
    getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "\u21B2");
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
    // keyboard input
    getActionMap().put("Int", new KeyActions(checkBox));
    getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_I, 0), "Int");
    // set the properties for 3 radios, then add them to the action listener
    singleJRadioButton.setBackground(Color.YELLOW);
    singleJRadioButton.addActionListener(controller);
    doubleJRadioButton.setBackground(Color.YELLOW);
    doubleJRadioButton.addActionListener(controller);
    sciJRadioButton.setBackground(Color.YELLOW);
    sciJRadioButton.addActionListener(controller);
    // keyboard input for single raido
    getActionMap().put(".0", new KeyActions(singleJRadioButton));
    getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_OPEN_BRACKET, 0), ".0");
    // keyboard input for double radio
    getActionMap().put(".00", new KeyActions(doubleJRadioButton));
    getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_CLOSE_BRACKET, 0), ".00");
    // keyboard input for sci
    this.getActionMap().put("Sci", new KeyActions(sciJRadioButton));
    this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SLASH, 0), "Sci");
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
        dotButton = createButton(".", ".", Color.BLACK, Color.BLUE, controller);
        keypad.add(dotButton);
      }
      // if there is the letter "+-" in the array, create button. then add it to key pad panel
      else if (numbers[i].equals("\u00B1")) {
        // add the create +- button to the keypad
        keypad.add(createButton("\u00B1", "\u00B1", Color.BLACK, Color.PINK, controller));
      }
      // if the element is +, -, *, /, add them to keypad panel
      else if (numbers[i].equals("+") || numbers[i].equals("-") || numbers[i].equals("*")
          || numbers[i].equals("/")) {
        keypad.add(createButton(numbers[i], numbers[i], Color.BLACK, Color.CYAN, controller));
      }
      // if the element is numbers, add them to keypad panel
      else {
        keypad.add(createButton(numbers[i], numbers[i], Color.BLACK, Color.BLUE, controller));
      }
      // keyboard input for jbuttons
      getActionMap().put(numbers[i], new KeyActions((JButton) keypad.getComponent(i)));
      getInputMap().put(KeyStroke.getKeyStroke(keyEvents[i], 0), numbers[i]); // numbers
      getInputMap().put(KeyStroke.getKeyStroke(keyEventsPad[i], 0), numbers[i]); // numbers on the keypad
      
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

  public void display(boolean enableDefault) {

    if (enableDefault) { // not check box
      error.setText("F");
      error.setBackground(Color.YELLOW);
      dotButton.setBackground(Color.BLUE);
    } else { // Integer check
      error.setText("I");
      error.setBackground(Color.GREEN);
      dotButton.setBackground(new Color(178, 156, 250));
    }
    dotButton.setEnabled(enableDefault);
  }

  /**
   * This method is to create button by using JButton function, then it is including the 4
   * parameters which are strings, colors, and action listener
   * 
   * @param text it is text to show the letters on the label and button
   * @param ac to display texts which is connected to the user input with the button
   * @param fg foreground color
   * @param bg background color
   * @param handler this is the reference of the event handler class
   * @return button return the created button to calling function
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
   * 
   * @author Seongyeop Jeong
   * @version 1.0
   * @see Controller
   * @since 1.8.0_181
   */
  private class Controller implements ActionListener {

    CalculatorModel calcModel = new CalculatorModel();
    boolean errorStates = false;
    boolean isInteger = false;
    boolean isFloat = false;
    boolean isFirst = true;
    boolean isBackspace = false;
    boolean isDivide = false;
    boolean isSecondZero = false;
    boolean isFirstZero = false;
    String tempVal = "";

    /**
     * This method is to create responding action with user by implmenting the action listener
     * interface. When the action event is occurred by user, this object's action listener will be
     * invoked.
     * @param event to get the event of action from the user so that it can be connected to application
     */
    @Override
    public void actionPerformed(ActionEvent event) {

      String text = event.getActionCommand();
      // this switch statement is to check user input for numbers, operations and so on
      switch (text) {
        // operators
        case "*":
        case "+":
        case "-":
        case "/":
          if (calcModel.getErrorState() == false) {
            // this is to check if user use divide so that program can give the errors when it divided by 0
            if (text == "/")
              isDivide = true;
            // to display the first operand
            if (calcModel.getState() == CalculatorModel.OPERAND_FIRST) {
              // when it is first input, 
              if (isFirst) {
                display2.setText(text);
                isFirst = false; // it is not first input
              } else {
                calcModel.setOperand1(display2.getText()); // to get text of userinput
                calcModel.setOperation(text); // to get operation 
                display1.setText(display2.getText() + text);
                isFirst = true; // if else statement work successfully, change isFirst to true.
                break;
              }
              // second operand which is after the operation
            } else if (calcModel.getState() == CalculatorModel.OPERAND_SECOND) {
              if (isFirst) {
                calcModel.setOperation(text);
                display1.setText(display2.getText() + text);
                break;
              }
              // when the user input '='
            } else if (calcModel.getState() == CalculatorModel.RESULT) {
              calcModel.setOperation(text);
              display1.setText(display2.getText() + text);
              display2.getText();
              isFirst = true;
            } else {
              calcModel.setErrorState(true);
              break;
            }
          }
          errorStates = calcModel.getErrorState(); // set the true of error state
          isBackspace = true;
          break;
        // equal
        case "=":
          if (!calcModel.getErrorState()) {
            // to check if it is divided by 0
            if (isDivide && isSecondZero) {
              calcModel.setErrorState(true);
              errorStates = calcModel.getErrorState();
              break;
            }
            if (calcModel.getState() == CalculatorModel.OPERAND_SECOND) {
              tempVal = display2.getText(); // it needs to do because of the situation when the user 
                                            // click the '=' continuously after the calculation 
              calcModel.setOperand2(tempVal); // set the tempVal to second operand to calculate for exact val
              String results = calcModel.getResult();

              if (results != null) {
                display1.setText("");
                display2.setText(results);
                // it is for prohibiting digits of the numbers up to 23.
                if (display2.getText().length() > 23) {
                  display2.setText("result is too long to display");
                  calcModel.clear();
                }
              } else {
                calcModel.setErrorState(true);
              }
              // If it is result of the getState,
              if (calcModel.getState() == CalculatorModel.RESULT) {
                display2.setText(results);
                break;
              } else {
                calcModel.setErrorState(true);
                break;
              }
            } else if (calcModel.getState() == CalculatorModel.RESULT) {
              calcModel.setOperand2(tempVal);
              String results = calcModel.getResult();

              if (results != null) {
                display1.setText("");
                display2.setText(results);
              }
            }
            if (calcModel.getErrorState())
              errorStates = true;
            isBackspace = false;
            break;
          }
        case ".":
          if (!calcModel.getErrorState()) {
            if (!isFloat) {
              if (isFirst) {
                display2.setText(".");
                isFirst = false;
              } else {
                display2.setText(display2.getText() + ("."));
              }
              isFloat = true;
            }
          }
          break;
        case "\u21da": // backspace
          if (!isBackspace)
            return;
          if (!calcModel.getErrorState()) {
            if (isFirst == false) {
              if (display2.getText().length() >= 1) {
                // it is to reduce a digit of the numbers whenever user click the backspace
                display2.setText(display2.getText().substring(0, display2.getText().length() - 1));
                // it is to clear the display1 when the length of the text is 1 or if there is letter '-'
                if (display2.getText().length() == 1 && display2.getText().contains("-")) {
                  defaultDisplay();
                  calcModel.clear();
                  isFirst = true;
                  isFloat = false;
                }
                // if the displa2's length is under 0, clear it.
              } else {
                defaultDisplay();
                calcModel.clear();
                isFirst = true;
                isFloat = false;
              }
            }
            if (calcModel.getErrorState()) {
              errorStates = true;
            }
          }
          break;
        case "\u00B1": // + and -
          if (!calcModel.getErrorState()) {
            // whenever user clicks the +- button, it has to replace new one. 
            if (display2.getText().startsWith("-")) {
              display2.setText(display2.getText().substring(1));
            } else {
              display2.setText("-".concat(display2.getText()));
            }
            if (calcModel.getErrorState()) {
              errorStates = true;
            }
          }
          isBackspace = true;
          break;
          // clear
        case "C":
          if (calcModel.getErrorState()) {
            // When user clicks clear, it has to initialize as Integer mode 
            // with green color if it is Integer mode.
            if (isInteger)
              display(false);
            // if it is not integer mode, show the default mode which is float or sci
            else
              display(true);
          }
          calcModel.clear();
          defaultDisplay();
          isFloat = false;
          errorStates = false;
          break;
          // float mode
        case ".0":
          isInteger = false;
          calcModel.setPrecision(CalculatorModel.F_PRECISION_0);
          calcModel.setOperationMode(CalculatorModel.FLOAT_MODE);

          if (!calcModel.getErrorState()) {
            display(true);
            calcModel.clear();
            defaultDisplay();
          }
          break;
          // float mode
        case ".00":
          isInteger = false;
          calcModel.setPrecision(CalculatorModel.F_PRECISION_00);
          calcModel.setOperationMode(CalculatorModel.FLOAT_MODE);

          if (!calcModel.getErrorState()) {
            display(true);
            calcModel.clear();
            defaultDisplay();
          }
          break;
          // scientific mode
        case "Sci":
          isInteger = false;
          calcModel.setPrecision(CalculatorModel.F_PRECISION_SCI);
          calcModel.setOperationMode(CalculatorModel.FLOAT_MODE);

          if (!calcModel.getErrorState()) {
            display(true);
            calcModel.clear();
            defaultDisplay();
          }
          break;
          // integer mode
        case "Int":
          if (isInteger) {
            doubleJRadioButton.doClick();
            break;
          }
          isInteger = true;
          calcModel.setOperationMode(CalculatorModel.INTEGER_MODE);

          if (!calcModel.getErrorState()) {
            display(false);
            calcModel.clear();
            defaultDisplay();
          }
          break;
        // numbers
        default:
          if (!calcModel.getErrorState()) {
            // each display doesn't show over the 23 digits 
            if (display2.getText().length() < 23 && display1.getText().length() < 23) {
              if (calcModel.getState() == CalculatorModel.OPERAND_FIRST) {
                if (isFirst) {
                  display2.setText(text);
                  isFirst = false;
                  /* it is to check not divide by 0 from 0 */
                  if (text == "0")
                    isFirstZero = true;
                } else {
                  display2.setText(display2.getText() + text);
                }
              } else if (calcModel.getState() == CalculatorModel.OPERAND_SECOND) {
                if (isFirst) {
                  display2.setText(text);
                  isFirst = false;
                  /* it is to check not divide by 0 */
                  if (text == "0")
                    isSecondZero = true;
                } else {
                  display2.setText(display2.getText() + text);
                }
              } else if (calcModel.getState() == CalculatorModel.RESULT) {
                display1.setText("");
                display1.setText(text);
              } else {
                calcModel.setErrorState(true);
              }
              // if the digits are over 23, it has to show the msg on the display2
            } else {
              display2.setText("result is too long to display");
              calcModel.clear();
            }
            if (calcModel.getErrorState())
              errorStates = true;
          }
          isBackspace = true;
          break;
      }
      // if there is error in calculator, it has to show the E letter and red color
      if (errorStates) {
        error.setText("E");
        error.setBackground(Color.RED);
        // when the user use the divide, program will judge if it is divided by 0 with 0 or, 
        // just divided by 0
        if (isDivide) {
          // if it is divided by 0, show msg
          if (isSecondZero && !isFirstZero) {
            display2.setText("Cannot divide by zero");
            isSecondZero = false;
            calcModel.setErrorState(true);
            // if it is divided by 0 with 0, show msg
          } else if (isFirstZero) {
            display2.setText("Result in undefined");
            isFirstZero = false;
            isSecondZero = false;
            calcModel.setErrorState(true);
          }
        }
        // when it is not specific condition, it show normal message for error
        if (!(isDivide || isSecondZero || isFirstZero)) {
          display2.setText(calcModel.getErrorMessage());
          calcModel.clearErrorMessage();
        }
        isDivide = false;
      }
    }
    // to return the default mode.
    public void defaultDisplay() {

      if (calcModel.integerMode()) {
        display1.setText(" ");
        display2.setText("0");
      } else {
        display1.setText(" ");
        display2.setText("0.0");
      }
      isFirst = true;
    }
  }

  /**
   * This class is to set up the keybord input with calculator 
   * 
   * @author Seongyeop Jeong
   * @version 1.0
   * @see javax.swing.AbstractAction
   * @since 1.8.0_181
   */
  private class KeyActions extends AbstractAction {
    /** serialVersioUID = {@value} */
    private static final long serialVersionUID = 1L;
    AbstractButton button;
    /** constructor to get the abstract button action 
     * @param button to get the action of the keyboard to use into calculator
     * */
    public KeyActions(AbstractButton button) {
      this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      button.doClick();
    }
  }
}
