package calculator;

/**
 * File name: CalculatorModel.java 
 * Author: Seongyeop Jeong, 040885882 
 * Course: CST8221_JAP, Lab Section: 302 
 * Assignment: 1 
 * Date: November 6th, 2018 
 * Professor: Daniel Cormier 
 * Purpose: this class is to build the model of the calculator 
 * Class list: CalculatorModel
 */

/**
 * This class is to set up the model of the calculator so that it is actually working for calculation
 * 
 * @author Seongyeop Jeong
 * @version 1.0
 * @see CalculatorModel
 * @since 1.8.0_181
 */
public class CalculatorModel {
  // constants to distinguish what it is easily
  static final int INTEGER_MODE = 0;
  static final int FLOAT_MODE = 1;
  static final int F_PRECISION_0 = 2;
  static final int F_PRECISION_00 = 3;
  static final int F_PRECISION_SCI = 4;
  static final int OPERAND_FIRST = 5;
  static final int OPERAND_SECOND = 6;
  static final int RESULT = 7;
  static final int ERROR = 8;
  // states, modes, msg, operation and operands
  private int state;
  private int precisionMode;
  private int operationalMode;
  private boolean errorState;
  private String errorMessage;
  private String operand1;
  private String operand2;
  private String operation;
  // initialize the default values
  public CalculatorModel() {

    state = OPERAND_FIRST;
    precisionMode = F_PRECISION_00;
    operationalMode = FLOAT_MODE;
    errorState = false;
    errorMessage = null;
    operand1 = null;
    operand2 = null;
    operation = null;
  }
  // if the operation is set it up, it has to change to the next operand
  public void setOperation(String operation) {
    this.operation = operation;
    state = OPERAND_SECOND;
  }
  
  public void setOperationMode(int mode) {
    this.operationalMode = mode;
  }

  public boolean integerMode() {
    return operationalMode == INTEGER_MODE;
  }

  public boolean floatMode() {
    return operationalMode == FLOAT_MODE;
  }

  // when the user clicks the first operand, it is still on the first operand
  public void setOperand1(String operand1) {
    this.operand1 = operand1;
    state = OPERAND_FIRST;
  }
  
  // when the user clicks the second operand, it goes to the next state
  public void setOperand2(String operand2) {
    this.operand2 = operand2;
    state = RESULT;
  }
  
  public void setPrecision(int precision) {
    this.precisionMode = precision;
  }

  public int getPrecision() {
    return precisionMode;
  }

  // to get a result from the calculations
  public String getResult() {
    String result = isMode();
    
    if (result != null)
      operand1 = result;
    return result;
  }

  // if there is error in the calculation, it does not have to work anymore.
  public void setErrorState(boolean errorState) {
    this.errorState = errorState;
    
    /* exceptions */
    if (operand1 == null || operation == null || operand2 == null)
      this.errorState = true;
    else if (Double.isInfinite(OPERAND_FIRST) || Double.isInfinite(OPERAND_SECOND))
      this.errorState = true;
    else if (Double.isNaN(OPERAND_FIRST) || Double.isNaN(OPERAND_SECOND))
      this.errorState = true;
  }

  public boolean getErrorState() {
    return errorState;
  }
  
  public int getState() {
    return state;
  }

  // before this class calculate, check if it is integer or float
  public String isMode() {
    if (operationalMode == INTEGER_MODE)
      return calculation(INTEGER_MODE);
    else
      return calculation(FLOAT_MODE);
  }

  // calculate (integer / float mode)
  public String calculation(int type) {
    int op1Int = 0;
    int op2Int = 0;
    int resultInt = 0;
    float op1Float = 0.0f;
    float op2Float = 0.0f;
    float resultCalc = 0.0f;
    // in any case, i set up the state as result. 
    state = RESULT;
    // separate into two modes so that it makes the calculations exactly
    if (type == INTEGER_MODE) {
      // to avoid runtime error, use the try catch with number format and arithmatic exception
      try {
        op1Int = Integer.parseInt(operand1);
        op2Int = Integer.parseInt(operand2);
        
        switch (operation) {
          case "*":
            resultInt = op1Int * op2Int;
            break;
          case "/":
            resultInt = op1Int / op2Int;
            break;
          case "+":
            resultInt = op1Int + op2Int;
            break;
          case "-":
            resultInt = op1Int - op2Int;
            break;
        }
        return String.format("%d", resultInt);
      } catch (NumberFormatException e) {
        errorState = true;
      } catch (ArithmeticException e) {
        errorState = true;
      }
     
    } else if (type == FLOAT_MODE) {
   // to avoid runtime error, use the try catch with number format and arithmatic exception
      try {
        op1Float = Float.parseFloat(operand1);
        op2Float = Float.parseFloat(operand2);
        
        switch (operation) {
          case "*":
            resultCalc = op1Float * op2Float;
            break;
          case "/":
            resultCalc = op1Float / op2Float;
            break;
          case "+":
            resultCalc = op1Float + op2Float;
            break;
          case "-":
            resultCalc = op1Float - op2Float;
            break;
        }
      } catch (NumberFormatException e) {
        errorState = true;
      } catch (ArithmeticException e) {
        errorState = true;
      }
      // each result has to be different followed by precisions
      if (precisionMode == F_PRECISION_0)
        return String.format("%.1f", resultCalc);

      if (precisionMode == F_PRECISION_00)
        return String.format("%.2f", resultCalc);

      if (precisionMode == F_PRECISION_SCI)
        return String.format("%E", resultCalc);
    }
    // exceptions 2kinds of, cannot divide by zero
    // cannot calculate two 0 with dividing
    return null;
  }

  public void clear() {
    state = OPERAND_FIRST;
    errorState = false;
    operation = "";
    operand1 = "";
    operand2 = "";
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void clearErrorMessage() {
    errorMessage = null;
  }
}
