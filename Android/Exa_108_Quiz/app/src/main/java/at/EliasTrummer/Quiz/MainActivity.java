package at.EliasTrummer.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    private TextView tvAnweredQuestion1;
    private TextView tvAnweredQuestion2;
    private TextView tvAnweredQuestion3;
    private TextView tvAnweredQuestion4;
    private TextView tvAnweredQuestion5;
    private Button btNext;
    private Button[] btAnswers;

    private List<Question> questions;
    private int current = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCategory = findViewById(R.id.tvCategory);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvAnweredQuestion1 = findViewById(R.id.tvAnweredQuestion1);
        tvAnweredQuestion2 = findViewById(R.id.tvAnweredQuestion2);
        tvAnweredQuestion3 = findViewById(R.id.tvAnweredQuestion3);
        tvAnweredQuestion4 = findViewById(R.id.tvAnweredQuestion4);
        tvAnweredQuestion5 = findViewById(R.id.tvAnweredQuestion5);
        btNext = findViewById(R.id.btNext);
        btAnswers = new Button[]{
            findViewById(R.id.btAnswer1),
            findViewById(R.id.btAnswer2),
            findViewById(R.id.btAnswer3),
            findViewById(R.id.btAnswer4)
        };

        QuestionPool questionPool = new QuestionPool(getApplicationContext());
        Category category = Category.values()[new Random().nextInt(Category.values().length)];
        questions = questionPool.getQuestionsByCategory(category);

        //load first question
        tvCategory.setText("Kategorie: " + category.toString());
        //tvQuestion.setText(questions.get(current).getQuestion());

        for(int i = 0; i < btAnswers.length; i++){
            //btAnswers[i].setText(questions.get(current).getAnswers().get(i));
        }
    }

    public void onDisplayQuestion(View view){

    }

    public void onAnswerClick(View view){

    }
}
