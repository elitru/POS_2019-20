package at.EliasTrummer.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import at.EliasTrummer.Quiz.BusinessLayer.Category;
import at.EliasTrummer.Quiz.BusinessLayer.IOHandler;
import at.EliasTrummer.Quiz.BusinessLayer.Question;
import at.EliasTrummer.Quiz.BusinessLayer.QuestionPool;

public class MainActivity extends AppCompatActivity {

    private static TextView tvCategory;
    private static TextView tvQuestion;
    private static TextView[] tvAnswered;
    private static Button btNext;
    private static Button[] btAnswers;

    private static QuestionPool questionPool;
    private static List<Question> questions;
    private static int current = 0;
    private static Category currentCategory;
    private static List<Category> usedCategories = new ArrayList<>();

    private Intent chooseCategory;

    private static MainActivity main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = this;

        showCategoryChooser();

        tvAnswered = new TextView[]{
            findViewById(R.id.tvAnweredQuestion1),
            findViewById(R.id.tvAnweredQuestion2),
            findViewById(R.id.tvAnweredQuestion3),
            findViewById(R.id.tvAnweredQuestion4),
            findViewById(R.id.tvAnweredQuestion5)
        };

        btNext = findViewById(R.id.btNext);
        btAnswers = new Button[]{
            findViewById(R.id.btAnswer1),
            findViewById(R.id.btAnswer2),
            findViewById(R.id.btAnswer3),
            findViewById(R.id.btAnswer4)
        };

        for(int i = 0; i < btAnswers.length; i++){
            final int answerIndex = i + 1;

            btAnswers[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onAnswerClick(v, answerIndex);
                }
            });
        }

        tvCategory = findViewById(R.id.tvCategory);
        tvQuestion = findViewById(R.id.tvQuestion);

        questionPool = new QuestionPool(getApplicationContext());
    }

    private void showCategoryChooser(){
        chooseCategory = new Intent(this, CategoryActivity.class);
        startActivity(chooseCategory);
    }

    public void onDisplayQuestion(View view){

        if((current + 1) == questions.size()){
            //start again from scratch
            for(int i = 0; i < tvAnswered.length; i++){
                tvAnswered[i].setBackground(getDrawable(R.drawable.rounded_slightly_transparent));
            }
            btNext.setEnabled(false);

            showCategoryChooser();

            return;
        }

        current++;

        loadQuestion();
        enableAnswers();
        btNext.setEnabled(false);
    }

    public static void onAnswerClick(View view, int answerIndex){
        if(answerIndex == questions.get(current).getCorrectAnswer()){
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(main.getColor(R.color.green)));
            tvAnswered[current].setBackground(main.getDrawable(R.drawable.rounded_green));
        }else{
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(main.getColor(R.color.red)));
            btAnswers[questions.get(current).getCorrectAnswer() - 1].setBackgroundTintList(ColorStateList.valueOf(main.getColor(R.color.green)));
            tvAnswered[current].setBackground(main.getDrawable(R.drawable.rounded_red));
        }

        disableAnswers();
        btNext.setEnabled(true);
    }

    private static void enableAnswers(){
        for(int i = 0; i < btAnswers.length; i++){
            btAnswers[i].setEnabled(true);
        }
    }

    private static void disableAnswers(){
        for(int i = 0; i < btAnswers.length; i++){
            btAnswers[i].setEnabled(false);
        }
    }

    private static void loadQuestion(){
        tvQuestion.setText(questions.get(current).getQuestion());

        for(int i = 0; i < btAnswers.length; i++){
            btAnswers[i].setText(questions.get(current).getAnswers().get(i));
            btAnswers[i].setBackgroundTintList(ColorStateList.valueOf(main.getColor(R.color.dark)));
        }
    }

    public static void setCategory(Category category){
        currentCategory = category;

        enableAnswers();
        tvCategory.setText(category.toString());
        questions = questionPool.getQuestionsByCategory(category);
        current = 0;
        loadQuestion();
    }
}
