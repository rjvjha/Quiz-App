package com.example.rahul.quizapp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * This app implements a simple quiz on number of 10 questions.
 */
public class MainActivity extends AppCompatActivity {
    int score;
    Chronometer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timer = (Chronometer) findViewById(R.id.timer_text_view);
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();
    }

    /**
     * This method is called when Submit button is clicked.
     */

    public void submitScore(View view) {
        Log.v("MainActivity", "Initial Score is :" + score);
        RadioGroup question1 = (RadioGroup) findViewById(R.id.radio_group_answers_q1);
        RadioGroup question2 = (RadioGroup) findViewById(R.id.radio_group_answers_q2);
        RadioGroup question3 = (RadioGroup) findViewById(R.id.radio_group_answers_q3);
        RadioGroup question4 = (RadioGroup) findViewById(R.id.radio_group_answers_q4);
        EditText question5 = (EditText) findViewById(R.id.edit_text_ans);
        RadioGroup question6 = (RadioGroup) findViewById(R.id.radio_group_answers_q6);
        CheckBox question7a = (CheckBox) findViewById(R.id.checkBox_ans_q7a);
        CheckBox question7b = (CheckBox) findViewById(R.id.checkBox_ans_q7b);
        CheckBox question7c = (CheckBox) findViewById(R.id.checkBox_ans_q7c);
        CheckBox question7d = (CheckBox) findViewById(R.id.checkBox_ans_q7d);
        RadioGroup question8 = (RadioGroup) findViewById(R.id.radio_group_answers_q8);
        RadioGroup question9 = (RadioGroup) findViewById(R.id.radio_group_answers_q9);
        RadioGroup question10 = (RadioGroup) findViewById(R.id.radio_group_answers_q10);
        evaluateRadioButtonQuestions(question1, question2, question3, question4, question6, question8, question9, question10);
        evaluateEditTextQuestion(question5);
        evaluateCheckBoxQuestion(question7a, question7b, question7c, question7d);
        Log.v("MainActivity", "Final Score  is :" + score);
        String toastGradingMessage = gradingMessage();
        Toast.makeText(this, toastGradingMessage, Toast.LENGTH_SHORT).show();
        score = 0;
        timer.stop();
    }

    /**
     * This method is called when Retry button is clicked and resets the quiz app to initial state.
     */
    public void reset(View view) {
        RadioGroup question1 = (RadioGroup) findViewById(R.id.radio_group_answers_q1);
        RadioGroup question2 = (RadioGroup) findViewById(R.id.radio_group_answers_q2);
        RadioGroup question3 = (RadioGroup) findViewById(R.id.radio_group_answers_q3);
        RadioGroup question4 = (RadioGroup) findViewById(R.id.radio_group_answers_q4);
        EditText question5 = (EditText) findViewById(R.id.edit_text_ans);
        RadioGroup question6 = (RadioGroup) findViewById(R.id.radio_group_answers_q6);
        CheckBox question7a = (CheckBox) findViewById(R.id.checkBox_ans_q7a);
        CheckBox question7b = (CheckBox) findViewById(R.id.checkBox_ans_q7b);
        CheckBox question7c = (CheckBox) findViewById(R.id.checkBox_ans_q7c);
        CheckBox question7d = (CheckBox) findViewById(R.id.checkBox_ans_q7d);
        RadioGroup question8 = (RadioGroup) findViewById(R.id.radio_group_answers_q8);
        RadioGroup question9 = (RadioGroup) findViewById(R.id.radio_group_answers_q9);
        RadioGroup question10 = (RadioGroup) findViewById(R.id.radio_group_answers_q10);

        //Un-checks all chosen radioButtons in a RadioGroup
        question1.clearCheck();
        question2.clearCheck();
        question3.clearCheck();
        question4.clearCheck();
        question6.clearCheck();
        question8.clearCheck();
        question9.clearCheck();
        question10.clearCheck();

        //Un-checks EditText questions.
        question5.getText().clear();

        //Un-checks all CheckBoxes responses.
        if (question7a.isChecked()) question7a.toggle();
        if (question7b.isChecked()) question7b.toggle();
        if (question7c.isChecked()) question7c.toggle();
        if (question7d.isChecked()) question7d.toggle();

        //recreates this activity
        this.recreate();
    }

    /**
     * This method evaluates all RadioGroupButton answers
     *
     * @param questionNo takes variable number objects ref variables of class RadioGroup.
     */
    private void evaluateRadioButtonQuestions(RadioGroup... questionNo) {
        int i = 0;
        int answersId[] = {R.id.radio_ans_q1c, R.id.radio_ans_q2a, R.id.radio_ans_q3b, R.id.radio_ans_q4d,
                R.id.radio_ans_q6b, R.id.radio_ans_q8d, R.id.radio_ans_q9c, R.id.radio_ans_q10c};

        for (RadioGroup x : questionNo) {
            if (x.getCheckedRadioButtonId() == answersId[i]) {
                score = score + 1;
                RadioButton checkedButton = (RadioButton) findViewById(answersId[i]);
                checkedButton.setTextColor(Color.GREEN);
                i++;
            }
            // When no button in RadioGroup question is clicked.
            else if (x.getCheckedRadioButtonId() == -1) {
                i++;
            } else {
                RadioButton checkedButton1 = (RadioButton) findViewById(x.getCheckedRadioButtonId());
                checkedButton1.setTextColor(Color.RED);
                i++;
            }
        }

    }

    /**
     * This method evaluates all EditTextField answers and computes score.
     *
     * @param question takes object reference of class EditText.
     */
    private void evaluateEditTextQuestion(EditText question) {
        String questionAnswer = "overridden";
        String userInput = question.getText().toString();
        if (questionAnswer.equals(userInput)) {
            score = score + 1;
            question.setTextColor(Color.GREEN);
        } else {
            question.setTextColor(Color.RED);
        }

    }

    /**
     * This method evaluates all CheckBox answers and computes score.
     *
     * @param questionA,questionB... takes object reference of class CheckBox.
     */
    private void evaluateCheckBoxQuestion(CheckBox questionA, CheckBox questionB, CheckBox questionC, CheckBox questionD) {
        boolean hasA = questionA.isChecked();
        boolean hasB = questionB.isChecked();
        boolean hasC = questionC.isChecked();
        boolean hasD = questionD.isChecked();
        if (hasC & hasD) {
            score = score + 1;
            questionC.setTextColor(Color.GREEN);
            questionD.setTextColor(Color.GREEN);
        } else if (hasC) {
            questionC.setTextColor(Color.GREEN);
            questionD.setTextColor(Color.MAGENTA);
        } else if (hasD) {
            questionD.setTextColor(Color.GREEN);
            questionC.setTextColor(Color.MAGENTA);
        }
        if (hasA) {
            questionA.setTextColor(Color.RED);
        }
        if (hasB) {
            questionB.setTextColor(Color.RED);
        }

    }

    /**
     * This method generates a grading message according to score and +calculates final score.
     *
     * @return string message
     */
    private String gradingMessage() {
        String message = "You have got " + score + " correct responses\n" + "Final Score: " + score * 10 + "\nTime Taken:" + timer.getText().toString();
        if (score >= 8) {
            return "Excellent! " + message;
        } else if (score >= 5 & score < 8) {
            return "Nice! " + message;
        } else if (score < 5 & score >= 3) {
            return "Not Bad! " + message;
        }
        return "BC! " + message;
    }
}


