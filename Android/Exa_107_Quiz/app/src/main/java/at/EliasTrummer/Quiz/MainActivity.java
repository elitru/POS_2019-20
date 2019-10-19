package at.EliasTrummer.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import at.EliasTrummer.Quiz.BusinessLayer.Category;
import at.EliasTrummer.Quiz.BusinessLayer.IOHandler;
import at.EliasTrummer.Quiz.BusinessLayer.Question;
import at.EliasTrummer.Quiz.BusinessLayer.QuestionPool;

public class MainActivity extends AppCompatActivity {

    private TextView tvCategory;
    private TextView tvQuestion;
    private TextView[] tvAnswered;
    private Button btNext;
    private Button[] btAnswers;

    private List<Question> questions;
    private int current = 0;
    private Category currentCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCategory = findViewById(R.id.tvCategory);
        tvQuestion = findViewById(R.id.tvQuestion);

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

        initQuestions();
    }

    public void onDisplayQuestion(View view){

        if((current + 1) == questions.size()){
            //start again from scratch
            for(int i = 0; i < tvAnswered.length; i++){
                tvAnswered[i].setBackground(getDrawable(R.drawable.rounded_slightly_transparent));
            }

            initQuestions();
            enableAnswers();
            btNext.setEnabled(false);
            btNext.setText("weiter");
            return;
        }

        current++;

        loadQuestion(current);
        enableAnswers();
        btNext.setEnabled(false);

        if((current + 1) == questions.size()){
            //next question is last one
            btNext.setText("Neustart");
        }
    }

    public void onAnswerClick(View view, int answerIndex){
        if(answerIndex == questions.get(current).getCorrectAnswer()){
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.green)));
            tvAnswered[current].setBackground(getDrawable(R.drawable.rounded_green));
        }else{
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.red)));
            btAnswers[questions.get(current).getCorrectAnswer() - 1].setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.green)));
            tvAnswered[current].setBackground(getDrawable(R.drawable.rounded_red));
        }

        disableAnswers();
        btNext.setEnabled(true);
    }

    private void enableAnswers(){
        for(int i = 0; i < btAnswers.length; i++){
            btAnswers[i].setEnabled(true);
        }
    }

    private void disableAnswers(){
        for(int i = 0; i < btAnswers.length; i++){
            btAnswers[i].setEnabled(false);
        }
    }

    private void initQuestions(){
        QuestionPool questionPool = new QuestionPool(getApplicationContext());

        Category category;

        do{
            category = Category.values()[new Random().nextInt(Category.values().length)];
        }while (currentCategory == category && currentCategory == null);

        currentCategory = category;

        questions = questionPool.getQuestionsByCategory(category);
        tvCategory.setText("Kategorie: " + category.toString());
        current = 0;

        //load first question
        loadQuestion(current);
    }

    private void loadQuestion(int id){
        tvQuestion.setText(questions.get(current).getQuestion());

        for(int i = 0; i < btAnswers.length; i++){
            btAnswers[i].setText(questions.get(current).getAnswers().get(i));
            btAnswers[i].setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.dark)));
        }
    }
}
