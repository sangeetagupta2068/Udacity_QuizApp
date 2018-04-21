package com.sangeetagupta1998.quizapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/*
    *@description: This is a simple Linux Quiz Application designed to implement the different views
    * in android and understand the respective interaction.
    * @author:Sangeeta Gupta
    * @created on: 18th April 2018
    * @modified on:20th April 2018
    */

public class MainActivity extends AppCompatActivity {

    //Declaration of View Objects.
    Button[] button;
    RadioGroup radioGroup1, radioGroup2;
    CheckBox[] checkBox;

    //Declaration of question counter and score variable.
    int count, score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVariableValues();

    }

    private void setVariableValues() {

        /*
         * @description:This initializes the instance variables (counter,score and View variables)
         */

        //initialization of View variables
        button = new Button[4];
        button[0] = findViewById(R.id.buttonNext1);
        button[1] = findViewById(R.id.buttonNext2);
        button[2] = findViewById(R.id.buttonNext3);
        button[3] = findViewById(R.id.buttonNext4);

        radioGroup1 = findViewById(R.id.radio);
        radioGroup2 = findViewById(R.id.radioQ2);

        checkBox = new CheckBox[3];
        checkBox[0] = findViewById(R.id.checkbox1);
        checkBox[1] = findViewById(R.id.checkbox2);
        checkBox[2] = findViewById(R.id.checkbox3);

        //initialization of count and score
        count = 0;
        score = 0;

        /*function call to disable all the submit buttons except the one for the
         *first question.Done in order to maintain sequencing of answers.
         */
        disableButtonClick(button[1]);
        disableButtonClick(button[2]);
        disableButtonClick(button[3]);

    }

    private void disableButtonClick(Button button) {

        /*
         *@description:This disables the click attribute of a variable of type Button and changes the text
         *colour of the button to white to give a disabled effect
         */

        button.setClickable(false);
        button.setTextColor(Color.WHITE);

    }

    private void resumeButtonClick(Button button) {

        /*
         *@description:This enables the click behaviour of a variable of type Button and changes the text
         *colour of the button to black to give an enabled effect
         */

        button.setClickable(true);
        button.setTextColor(Color.BLACK);

    }

    private String answerValue() {

        /*
         *@description:This is used to retrieve and return the answer of a question in the quiz.Returns the string
         *if answer is found else returns null.
         *@return:String
         */

        //Declaration of local view variables
        RadioButton answerButton;
        EditText editText;

        //Declaration of local variables
        int selectedRadioButtonId = -1;
        String value = null;

        switch (count) {

            case 0: //to retrieve the answer for question 1
                selectedRadioButtonId = radioGroup1.getCheckedRadioButtonId();

                if (selectedRadioButtonId != -1) {

                    answerButton = findViewById(selectedRadioButtonId);
                    value = answerButton.getText().toString();

                }

                break;

            case 1: //to retrieve the answer for question 2
                selectedRadioButtonId = radioGroup2.getCheckedRadioButtonId();

                if (selectedRadioButtonId != -1) {

                    answerButton = findViewById(selectedRadioButtonId);
                    value = answerButton.getText().toString();

                }

                break;

            case 2: //to retrieve the answer for question 3
                value = "";

                if (!(checkBox[0].isChecked() && checkBox[1].isChecked() && checkBox[2].isChecked())) {

                    value = null;

                }

                if (checkBox[0].isChecked()) {

                    value = value + checkBox[0].getText().toString() + " ";

                }

                if (checkBox[1].isChecked()) {

                    value = value + checkBox[1].getText().toString() + " ";

                }

                if (checkBox[2].isChecked()) {
                    value = value + checkBox[2].getText().toString() + " ";

                }

                break;

            case 3: //to retrieve the answer for question 3
                editText = findViewById(R.id.editText);

                if (!(editText.getText().toString().isEmpty())) {

                    value = editText.getText().toString();

                }

                break;

            default:
                break;

        }

        return value;
    }

    public void updateScore(View view) {

        /*
         *@description:This is used to check if the given answer by user is correct ,accordingly update the score
         *and display appropriate toasts to the user.
         */

        String answer = null;
        answer = answerValue();
        int flag = 1;

        //to check if answer has been supplied by user
        if (answer != null) {

            //check for correctness of answer for question 1 to update score
            if (answer.equalsIgnoreCase(getResources().getString(R.string.option13))) {

                score = score + 5;

            } else {

                //check for correctness of answer for question 2 to update score
                if (answer.equalsIgnoreCase(getResources().getString(R.string.option22))) {

                    score = score + 5;

                } else {

                    //check for correctness of answer for question 4 to update score
                    if (answer.equalsIgnoreCase(getResources().getString(R.string.option41))) {

                        score = score + 5;
                        Toast.makeText(getApplicationContext(), "Correct Answer! Score: " + score, Toast.LENGTH_SHORT).show();
                        totalScore();
                        disableButtonClick(button[count]);
                        return;

                    } else {

                        //check for incorrectness of question 4
                        if (!(answer.equalsIgnoreCase(getResources().getString(R.string.option41))) && count == 3) {

                            Toast.makeText(getApplicationContext(), "Incorrect Answer! Score : " + score, Toast.LENGTH_SHORT).show();
                            totalScore();
                            disableButtonClick(button[count]);
                            return;
                        }

                        //check for correctness of answer for question 3 to update score
                        if (answer.contains("Ubuntu") && answer.contains("CentOS")) {

                            score = score + 10;

                            if (answer.contains("Windows")) {

                                score = score - 5;
                                flag = 2;
                                Toast.makeText(getApplicationContext(), "Partially Correct Answer! Score : " + score, Toast.LENGTH_SHORT).show();
                            }

                        } else {

                            if ((answer.contains("Ubuntu")) || answer.contains("CentOS")) {

                                score = score + 5;
                                flag = 2;
                                Toast.makeText(getApplicationContext(), "Partially Correct Answer! Score : " + score, Toast.LENGTH_SHORT).show();

                            }
                            //toast message when answer is incorrect
                            else {

                                flag = 0;
                                Toast.makeText(getApplicationContext(), "Incorrect Answer! Score : " + score, Toast.LENGTH_SHORT).show();

                            }

                        }

                    }

                }

            }

            //Toast message when answer is correct
            if (flag == 1) {

                Toast.makeText(getApplicationContext(), "Correct Answer! Score: " + score, Toast.LENGTH_SHORT).show();

            }

            //disable the current answered question's submit button
            disableButtonClick(button[count]);

            count = count + 1;

            //enable the next question's submit button
            resumeButtonClick(button[count]);

        }
        //if no answer is supplied, appropriate toast is displayed.
        else if (answer == null) {

            Toast.makeText(getApplicationContext(), "Select an option!", Toast.LENGTH_SHORT).show();
            resumeButtonClick(button[count]);

        }

    }

    private void totalScore() {

        /*
         *@description:This is used to toast the final score message for the quiz.
         */

        Toast.makeText(getApplicationContext(), "Your total score is:" + score + " !\nThank you!", Toast.LENGTH_LONG).show();

    }

}
