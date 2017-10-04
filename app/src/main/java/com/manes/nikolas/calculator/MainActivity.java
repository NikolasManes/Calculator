package com.manes.nikolas.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    // The number the user enters will build as he continues to press number buttons
    // So first of all a StringBuilder
    private StringBuilder mainScreenStringBuilder = new StringBuilder();
    // We will show the expression on screen until the user press "="
    private StringBuilder expressionStringBuilder = new StringBuilder();
    // The numbers for the operation
    private String numOnScreen = "";        // The number shown on screen as a String
    private String numInMemory = "";             // The number kept in memory
    // The operator stored in memory
    private String operatorInMemory = "";
    // The last button pressed
    private String lastButtonPressed = "";
    // What appears on the screen
    private TextView screenNumberText;      // Main Screen
    private TextView expressionText;        // Expression Screen
    // The number buttons
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button decimalPointButton;
    // To avoid writing more than one decimal points, initialize the following boolean
    private boolean decimalPointWasPressed = false;
    // The operation buttons
    private Button additionButton;          //  +
    private Button subtractionButton;       //  -
    private Button multiplicationButton;    //  x
    private Button divisionButton;          //  /
    private Button squareRootButton;        //  sqrt
    private Button perCentButton;           //  %
    private Button reciprocalButton;        //  1/x
    private Button signChangeButton;        //  +/-
    // The actions buttons
    private Button executionButton;         //  =
    private Button clearEntryButton;        //  CE          clears last entry
    private Button clearButton;             //  C           clears everything we enter
    private Button backspaceButton;         //  backspace   remove the last digit we enter
    // The Number Format used at screen
    java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("#########.#########");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize text views and buttons
        screenNumberText = (TextView) findViewById(R.id.calculator_screen);
        expressionText = (TextView) findViewById(R.id.expression_text);
        button0 = (Button) findViewById(R.id.button_0);
        button1 = (Button) findViewById(R.id.button_1);
        button2 = (Button) findViewById(R.id.button_2);
        button3 = (Button) findViewById(R.id.button_3);
        button4 = (Button) findViewById(R.id.button_4);
        button5 = (Button) findViewById(R.id.button_5);
        button6 = (Button) findViewById(R.id.button_6);
        button7 = (Button) findViewById(R.id.button_7);
        button8 = (Button) findViewById(R.id.button_8);
        button9 = (Button) findViewById(R.id.button_9);
        decimalPointButton = (Button) findViewById(R.id.decimal_point_button);
        additionButton = (Button) findViewById(R.id.addition_button);
        subtractionButton = (Button) findViewById(R.id.subtraction_button);
        multiplicationButton = (Button) findViewById(R.id.multiplication_button);
        divisionButton = (Button) findViewById(R.id.division_button);
        squareRootButton = (Button) findViewById(R.id.square_root_button);
        perCentButton = (Button) findViewById(R.id.per_cent_button);
        reciprocalButton = (Button) findViewById(R.id.reciprocal_button);
        signChangeButton = (Button) findViewById(R.id.sign_change_button);
        executionButton = (Button) findViewById(R.id.execution_button);
        clearEntryButton = (Button) findViewById(R.id.clear_entry_button);
        clearButton = (Button) findViewById(R.id.clear_button);
        backspaceButton = (Button) findViewById(R.id.backspace_button);

        // Attach buttons to click listener
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        decimalPointButton.setOnClickListener(this);
        additionButton.setOnClickListener(this);
        subtractionButton.setOnClickListener(this);
        multiplicationButton.setOnClickListener(this);
        divisionButton.setOnClickListener(this);
        squareRootButton.setOnClickListener(this);
        perCentButton.setOnClickListener(this);
        reciprocalButton.setOnClickListener(this);
        signChangeButton.setOnClickListener(this);
        executionButton.setOnClickListener(this);
        clearEntryButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
        backspaceButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_0:
                writeOnScreen(button0);
                lastButtonPressed = "number";
                break;
            case R.id.button_1:
                writeOnScreen(button1);
                lastButtonPressed = "number";
                break;
            case R.id.button_2:
                writeOnScreen(button2);
                lastButtonPressed = "number";
                break;
            case R.id.button_3:
                writeOnScreen(button3);
                lastButtonPressed = "number";
                break;
            case R.id.button_4:
                writeOnScreen(button4);
                lastButtonPressed = "number";
                break;
            case R.id.button_5:
                writeOnScreen(button5);
                lastButtonPressed = "number";
                break;
            case R.id.button_6:
                writeOnScreen(button6);
                lastButtonPressed = "number";
                break;
            case R.id.button_7:
                writeOnScreen(button7);
                lastButtonPressed = "number";
                break;
            case R.id.button_8:
                writeOnScreen(button8);
                lastButtonPressed = "number";
                break;
            case R.id.button_9:
                writeOnScreen(button9);
                lastButtonPressed = "number";
                break;
            case R.id.decimal_point_button:
                // Check if the button was pressed before
                if(decimalPointWasPressed){
                    // No need to add another decimal point
                    return;     // ...so return early
                }
                else {
                    // Check if it was the first button of the string pressed
                    if (numOnScreen == ""){
                        // Then add a "0" at the start
                        writeOnScreen(button0);
                        writeOnScreen(decimalPointButton);
                    }
                    else {
                        writeOnScreen(decimalPointButton);
                    }
                    lastButtonPressed = "number";
                    // Set the boolean to true so we avoid adding another decimal point
                    decimalPointWasPressed = true;
                }
                break;
            case R.id.addition_button:
                // Show the expression on screen
                writeExpression(additionButton);
                // Execute the operation stored in memory
                execute(numOnScreen, numInMemory, operatorInMemory);
                // Set the next operation to addition
                operatorInMemory = "+";
                lastButtonPressed = additionButton.getText().toString();
                break;
            case R.id.subtraction_button:
                // Show the expression on screen
                writeExpression(subtractionButton);
                // Execute the operation stored in memory
                execute(numOnScreen, numInMemory, operatorInMemory);
                // Set the next operation to subtraction
                operatorInMemory = "-";
                lastButtonPressed = subtractionButton.getText().toString();
                break;
            case R.id.multiplication_button:
                // Show the expression on screen
                writeExpression(multiplicationButton);
                // Execute the operation stored in memory
                execute(numOnScreen, numInMemory, operatorInMemory);
                // Set the next operation to multiplication
                operatorInMemory = "x";
                lastButtonPressed = multiplicationButton.getText().toString();
                break;
            case R.id.division_button:
                // Show the expression on screen
                writeExpression(divisionButton);
                // Execute the operation stored in memory
                execute(numOnScreen, numInMemory, operatorInMemory);
                // Set the next operation to division
                operatorInMemory = "/";
                lastButtonPressed = divisionButton.getText().toString();
                break;
            case R.id.square_root_button:
                // Check if the number on screen is an empty String
                if (numOnScreen == ""){
                    // If so return early since there is no number to perform any operation
                    return;
                }
                try {
                    // If any operation stored in memory... Execute
                    execute(numOnScreen, numInMemory, operatorInMemory);
                    // Do the maths
                    double result = Math.sqrt(Double.valueOf(numOnScreen));
                    // Convert result to String and store it to numOnScreen value
                    numOnScreen = String.valueOf(result);
                    // Show it on screen
                    screenNumberText.setText(decimalFormat.format(result));
                    // Clear memory and initialize again the mainScreenStringBuilder
                    // and set the boolean decimalPointWasPressed to false
                    mainScreenStringBuilder = new StringBuilder();
                    numInMemory = numOnScreen;
                    operatorInMemory = "";
                    decimalPointWasPressed = false;
                    break;
                } catch (NumberFormatException e) {
                    // The same action as clear but show a message on expression screen
                    // Initialize the mainScreenStringBuilder and the expressionStringBuilder
                    // so we erase everything is written
                    mainScreenStringBuilder = new StringBuilder();
                    expressionStringBuilder = new StringBuilder();
                    // Set number on screen, number in memory and operator to empty String
                    numOnScreen = "";
                    numInMemory = "";
                    operatorInMemory = "";
                    // Show a message on expression screen
                    expressionText.setText("Please refer to limits and infinity...");
                    // Clear main screen
                    screenNumberText.setText(numOnScreen);
                    decimalPointWasPressed = false;
                    // !!! Need to break again
                    break;
                }
            case R.id.per_cent_button:
                try {
                    double perCent, memNo, screenNo;
                    memNo = Double.valueOf(numInMemory);
                    screenNo = Double.valueOf(numOnScreen);
                    perCent = (memNo*screenNo)/100;
                    // Convert result to String
                    numOnScreen = String.valueOf(perCent);
                    screenNumberText.setText(decimalFormat.format(perCent));
                    // Clear memory and initialize again the mainScreenStringBuilder
                    // and set the boolean decimalPointWasPressed to false
                    mainScreenStringBuilder = new StringBuilder();
                    numInMemory = numOnScreen;
                    operatorInMemory = "";
                    decimalPointWasPressed = false;
                    break;
                } catch (NumberFormatException e) {
                    // The same action as clear but show a message on expression screen
                    // Initialize the mainScreenStringBuilder and the expressionStringBuilder
                    // so we erase everything is written
                    mainScreenStringBuilder = new StringBuilder();
                    expressionStringBuilder = new StringBuilder();
                    // Set number on screen, number in memory and operator to empty String
                    numOnScreen = "";
                    numInMemory = "";
                    operatorInMemory = "";
                    // Show a message on expression screen
                    expressionText.setText("Please refer to limits and infinity...");
                    // Clear main screen
                    screenNumberText.setText(numOnScreen);
                    decimalPointWasPressed = false;
                    // !!! Need to break again
                    break;
                }
            case R.id.reciprocal_button:
                // If any operation stored in memory... Execute
                execute(numOnScreen, numInMemory, operatorInMemory);
                // Set last button pressed to "number" to complete the operation
                lastButtonPressed = "number";
                /** Execute the operation...
                 *  In that case we set the operation to division
                 *  and the number in memory to 1
                 *  since the reciprocal of x is 1/x
                **/
                operatorInMemory = "/";
                execute(numOnScreen, "1", operatorInMemory);
                break;
            case R.id.sign_change_button:
                // If any operation stored in memory... Execute
                execute(numOnScreen, numInMemory, operatorInMemory);
                // Set last button pressed to "number" to complete the operation
                lastButtonPressed = "number";
                /** Execute the operation...
                 *  In that case we set the operation to multiplication
                 *  and the number in memory to -1
                 *  since when we multiply any number with -1 it change its sign
                 **/
                operatorInMemory = "x";
                execute(numOnScreen, "-1", operatorInMemory);
                break;
            case R.id.execution_button:
                // Check if the operator is not an empty String
                if (operatorInMemory != "") {
                    // Show the expression on screen
                    writeExpression(executionButton);
                    execute(numOnScreen, numInMemory, operatorInMemory);
                    expressionStringBuilder = new StringBuilder();
                    expressionStringBuilder.append(numOnScreen);
                }
                lastButtonPressed = "=";
                break;
            case R.id.clear_entry_button:
                clearEntry();
                break;
            case R.id.clear_button:
                clear();
                break;
            case R.id.backspace_button:
                backspace();
                break;
        }
    }
    // Write on screen method
    // Used when the numerical buttons are pressed
    private void writeOnScreen(Button button){
        // If the last button pressed is "=" it means the expression has ended and a new one started
        if (lastButtonPressed == "="){
            // ...so initialize again the expressionStringBuilder
            expressionStringBuilder = new StringBuilder();
            expressionText.setText("");
        }
        // Check the length of the number so it doesn't goes off screen
        if (mainScreenStringBuilder.length() < 11){
            // Add the number of the button pressed at the end of the mainScreenStringBuilder
            mainScreenStringBuilder.append(button.getText());
            // Convert it to string
            numOnScreen = mainScreenStringBuilder.toString();
            // Show it on screen
            screenNumberText.setText(numOnScreen);
        }
        else{
            return;
        }
    }
    // Write on expression screen method
    // Used when the operator buttons are pressed
    private void writeExpression(Button button){
        /** Check if user press an operator button without having type any number on screen
         *  Or if user press the same operator button as before
         *  In both cases nothing needs to be written on expression screen
         **/
        if ((lastButtonPressed != "number" && expressionStringBuilder.length() == 0) || lastButtonPressed == button.getText().toString()){
            return;
        }
        // Check if last button was number and user press an operator button
        // Write it on screen
        if (lastButtonPressed == "number"){
            // Add the operator and the number on the screen at the end of the expressionStringBuilder
            expressionStringBuilder.append(numOnScreen).append(button.getText());
            // Convert it to String and show it on screen
            expressionText.setText(expressionStringBuilder.toString());
        }
        // Check if user pressed an other operator button
        // Last operator in the expression needs to be changed
        if (lastButtonPressed != "number" && lastButtonPressed != button.getText().toString()) {
            // Delete last character
            expressionStringBuilder.deleteCharAt(expressionStringBuilder.length() -1);
            // Append with the last operator
            expressionStringBuilder.append(button.getText().toString());
            // Convert it to String and show it on screen
            expressionText.setText(expressionStringBuilder.toString());
        }
    }
    // Execution Method
    // Used almost for every operation
    private void execute(String screenNo, String memoryNo, String operator){
        // Check the last button pressed
        if (lastButtonPressed != "number"){
            // Return early
            return;
        }
        // Check if the number on screen is an empty String
        if (screenNo == ""){
            // If so return early since there is no number to perform any operation
            return;
        }
        // Check if the number in memory is an empty String
        /** This happens when "+", "-", "x" or "/" is pressed
         * ...also happens when "=" is pressed
         * In that case the num on screen stored in memory
         * and if the user press an operation button can continue with an operation
         * or if he press a number button start to write a new number on screen
         **/
        if (memoryNo == "" || operatorInMemory == ""){
            // Store the number on screen and the operator pressed in memory
            numInMemory = screenNo;
            // Create a new StringBuilder so user will enter the next number for the operation
            mainScreenStringBuilder = new StringBuilder();
            // Set boolean decimalPointWasPressed to false
            decimalPointWasPressed = false;
        }
        // If both numbers are not empty Strings proceed with the operation
        else {
            try {
                // Convert strings to doubles
                double numberA = Double.valueOf(memoryNo);
                double numberB = Double.valueOf(screenNo);
                // Initialize the result of the operation
                double result = 0;
                // Use a switch statement to choose the right operation
                switch (operator){
                    // Addition
                    case "+":
                        result = numberA + numberB;
                        break;
                    // Subtraction
                    case "-":
                        result = numberA - numberB;
                        break;
                    // Multiplication
                    case "x":
                        result = numberA * numberB;
                        break;
                    // Division
                    case "/":
                        result = numberA / numberB;
                        break;
                }
                // Convert result to String
                numOnScreen = String.valueOf(result);
                // Show it on screen
                screenNumberText.setText(decimalFormat.format(result));
                // Clear memory and initialize again the mainScreenStringBuilder
                mainScreenStringBuilder = new StringBuilder();
                numInMemory = numOnScreen;
                operatorInMemory = "";
                // Set boolean decimalPointWasPressed to false
                decimalPointWasPressed = false;
            } catch (NumberFormatException e) {
                // The same action as clear but show a message on expression screen
                // Initialize the mainScreenStringBuilder and the expressionStringBuilder
                // so we erase everything is written
                mainScreenStringBuilder = new StringBuilder();
                expressionStringBuilder = new StringBuilder();
                // Set number on screen, number in memory and operator to empty String
                numOnScreen = "";
                numInMemory = "";
                operatorInMemory = "";
                // Show a message on expression screen
                expressionText.setText("Please refer to limits and infinity...");
                // Clear main screen
                screenNumberText.setText(numOnScreen);
                decimalPointWasPressed = false;
            }
        }
    }
    // Clear Last Entry Method
    private void clearEntry(){
        // Initialize the mainScreenStringBuilder so we erase everything is written
        mainScreenStringBuilder = new StringBuilder();
        // Set number on screen to empty String
        numOnScreen = "";
        // Clear the screen
        screenNumberText.setText("");
        decimalPointWasPressed = false;
    }
    // Clear Method
    private void clear(){
        // Initialize the mainScreenStringBuilder and the expressionStringBuilder
        // so we erase everything is written
        mainScreenStringBuilder = new StringBuilder();
        expressionStringBuilder = new StringBuilder();
        // Set number on screen, number in memory and operator to empty String
        numOnScreen = "";
        numInMemory = "";
        operatorInMemory = "";
        // Clear the screen
        expressionText.setText(expressionStringBuilder.toString());
        screenNumberText.setText(numOnScreen);
        decimalPointWasPressed = false;
    }
    // Backspace Method
    private void backspace(){
        // Check if there is anything written on screen
        if (mainScreenStringBuilder.length() > 0){
            // Check if the last character was '.'
            if (mainScreenStringBuilder.charAt(mainScreenStringBuilder.length() - 1) == '.') {
                // Set decimalPointWasPressed to false because it will be deleted
                decimalPointWasPressed = false;
            }
            // Delete the last character
            mainScreenStringBuilder.deleteCharAt(mainScreenStringBuilder.length() - 1);
            // Convert it to String
            numOnScreen = mainScreenStringBuilder.toString();
            // Show it on screen
            screenNumberText.setText(numOnScreen);
        }
    }
}