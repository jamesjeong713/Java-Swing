package calculator;

/**
 * File name: CalculatorViewController.java
 * Author: Seongyeop Jeong, 040885882
 * Course: CST8221 - JAP, Lab Section: 302
 * Assignment: 1
 * Date: October 16th, 2018
 * Professor: Daniel Cormier
 * Purpose: [brief description of the contents]
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
  private JButton dotButton;
  // text fields and label
  private JTextField display1;
  private JTextField display2;
  private JLabel error;
  
  public CalculatorViewController() {
    /** The reference class for Controller to handle events */
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
    JPanel display = new JPanel();
    JPanel checkBoxPanel = new JPanel();
    JPanel radios = new JPanel();
    // third line's main panels from the top
    JPanel keypad = new JPanel();
    JPanel equalButton1 = new JPanel();
    JPanel equalButton2 = new JPanel();
    JPanel clearButton1 = new JPanel();
    JPanel clearButton2 = new JPanel();
    // top panel set layout
    topPane.setLayout(new BorderLayout());
    
    JButton backspace = new JButton();
    JCheckBox checkBox = new JCheckBox("Int");
    JRadioButton singleJRadioButton = new JRadioButton(".0", false);
    JRadioButton doubleJRadioButton = new JRadioButton(".00", true);
    JRadioButton sciJRadioButton = new JRadioButton("Sci", false);
    
    Box box = Box.createHorizontalBox();
    ButtonGroup group = new ButtonGroup();

    equalButton1.setLayout(new GridLayout(1, 4, 1, 1));
    equalButton1.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 5, Color.BLACK));
    equalButton2.setLayout(new GridLayout(1, 4, 1, 1));
    equalButton2.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 0, Color.BLACK));

    clearButton1.setPreferredSize(new Dimension(0, 45));
    clearButton1.setBorder(BorderFactory.createEmptyBorder());
    clearButton1.setLayout(new GridLayout(1, 1, 1, 1));
    clearButton2.setPreferredSize(new Dimension(0, 45));
    clearButton2.setBorder(BorderFactory.createEmptyBorder());
    clearButton2.setLayout(new GridLayout(1, 1, 1, 1));

    error = new JLabel("F");
    error.setPreferredSize(new Dimension(46, 55));
    error.setOpaque(true);
    error.setBackground(Color.YELLOW);
    error.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 5, Color.BLACK));
    error.setHorizontalAlignment(JLabel.CENTER);
    error.setFont(new Font(error.getFont().getName(), error.getFont().getStyle(), 20));

    display.setLayout(new GridLayout(2, 0));
    display.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
    display1 = new JTextField();
    display1.setPreferredSize(new Dimension(16, 30));
    display1.setBackground(Color.WHITE);
    display1.setEditable(false);
    display1.setHorizontalAlignment(JTextField.RIGHT);
    display1.setBorder(BorderFactory.createEmptyBorder());

    display2 = new JTextField();
    display2.setPreferredSize(new Dimension(16, 30));
    display2.setBackground(Color.WHITE);
    display2.setEditable(false);
    display2.setHorizontalAlignment(JTextField.RIGHT);
    display2.setText("0.0");
    display2.setBorder(BorderFactory.createEmptyBorder());

    backspace.setPreferredSize(new Dimension(45, 55));
    backspace.setBackground(Color.YELLOW);
    backspace.setOpaque(true);
    backspace.setBorder(BorderFactory.createMatteBorder(0, 5, 0, 1, Color.BLACK));
    backspace.setText("\u21da");
    backspace.setFont(new Font(backspace.getFont().getName(), Font.BOLD, 25));
    backspace.setToolTipText("Backspace (Alt-B)");
    backspace.setMnemonic('B');
    backspace.setActionCommand("\u21B2");
    backspace.addActionListener(controller);

    display.add(display1);
    display.add(display2);

    topPane.add(error, BorderLayout.WEST);
    topPane.add(display, BorderLayout.CENTER);
    topPane.add(backspace, BorderLayout.EAST);
    topPane.add(modePanel, BorderLayout.SOUTH);

    modePanel.setLayout(new BorderLayout());
    modePanel.setBackground(Color.BLACK);
    modePanel.setBorder(BorderFactory.createEmptyBorder(5, 1, 5, 1));

    checkBox.setLayout(new FlowLayout());
    checkBox.setBorder(BorderFactory.createEmptyBorder(0, 1, 0, 1));
    checkBox.setBackground(Color.GREEN);
    checkBox.setSize(40, 0);
    checkBox.setSelected(false);
    checkBox.addActionListener(controller);

    singleJRadioButton.setBackground(Color.YELLOW);
    singleJRadioButton.addActionListener(controller);
    doubleJRadioButton.setBackground(Color.YELLOW);
    doubleJRadioButton.addActionListener(controller);
    sciJRadioButton.setBackground(Color.YELLOW);
    sciJRadioButton.addActionListener(controller);

    radios.setLayout(new GridLayout(1, 1, 5, 5));
    radios.add(singleJRadioButton);
    radios.add(doubleJRadioButton);
    radios.add(sciJRadioButton);

    checkBoxPanel.setLayout(new BorderLayout());
    checkBoxPanel.setBackground(Color.BLACK);
    checkBoxPanel.add(checkBox, BorderLayout.WEST);

    box.add(Box.createHorizontalStrut(20));
    box.add(singleJRadioButton);
    box.add(doubleJRadioButton);
    box.add(sciJRadioButton);
    group.add(checkBox);
    group.add(singleJRadioButton);
    group.add(doubleJRadioButton);
    group.add(sciJRadioButton);

    modePanel.add(checkBoxPanel, BorderLayout.WEST);
    modePanel.add(box, BorderLayout.EAST);

    mainPanel.setLayout(new BorderLayout());
    keypad.setLayout(new GridLayout(4, 2, 2, 2));
    keypad.setBorder(BorderFactory.createEmptyBorder(2, 3, 2, 3));
    keypad.setBackground(Color.WHITE);

    for (int i = 0; i < numbers.length; i++) {
      if (numbers[i].equals(".")) {
        dotButton = createButton(".", ".", Color.BLACK, Color.BLUE, new Controller());
        keypad.add(dotButton);
      } else if (numbers[i].equals("\u00B1")) {
        keypad.add(createButton("\u00B1", "\u00B1", Color.BLACK, Color.PINK, new Controller()));
      } else if (numbers[i].equals("+") || numbers[i].equals("-") || numbers[i].equals("*")
          || numbers[i].equals("/")) {
        keypad.add(createButton(numbers[i], numbers[i], Color.BLACK, Color.CYAN, new Controller()));
      } else {
        keypad.add(createButton(numbers[i], numbers[i], Color.BLACK, Color.BLUE, new Controller()));
      }
    }
    // to add buttons to equal button panel
    equalButton1.add(createButton("=", "=", Color.BLACK, Color.YELLOW, new Controller()));
    add(equalButton1, BorderLayout.WEST);
    equalButton2.add(createButton("=", "=", Color.BLACK, Color.YELLOW, new Controller()));
    add(equalButton2, BorderLayout.EAST);
    // to add buttons to clear Button panel
    clearButton1.add(createButton("C", "C", Color.BLACK, Color.RED, new Controller()));
    mainPanel.add(clearButton1, BorderLayout.SOUTH);
    clearButton2.add(createButton("C", "C", Color.BLACK, Color.RED, new Controller()));
    mainPanel.add(clearButton2, BorderLayout.NORTH);

    add(topPane, BorderLayout.NORTH);
    add(mainPanel, BorderLayout.CENTER);
    mainPanel.add(keypad, BorderLayout.CENTER);
  }

  /**
   * This method is to create button by using JButton function, then it is including the 4
   * parameters which are strings, colors, and action listener
   * 
   * @param String text 
   * @param String ac 
   * @param Color fg 
   * @param Color gf 
   * @param ActionListener handler
   * @return JButton
   */
  private JButton createButton(String text, String ac, Color fg, Color bg, ActionListener handler) {
    JButton buttons = new JButton(text);
    buttons.setForeground(fg);
    buttons.setBackground(bg);

    if (ac != null)
      buttons.setActionCommand(ac);

    buttons.setFont(new Font(buttons.getFont().getName(), buttons.getFont().getStyle(), 20));
    buttons.addActionListener(handler);

    return buttons;
  }

  /**
 * This class is to connect to the display of the buttons with user input
 * @author Seongyeop Jeong
 * @version 1.0
 * @see Controller
 * @since 1.8.0_181
   */
  private class Controller implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
      display2.setText(event.getActionCommand());
    }
  }

}
