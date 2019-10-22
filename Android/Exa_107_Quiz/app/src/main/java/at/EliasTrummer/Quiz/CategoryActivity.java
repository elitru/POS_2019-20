package at.EliasTrummer.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.style.LineHeightSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import at.EliasTrummer.Quiz.BusinessLayer.Category;
import at.EliasTrummer.Quiz.BusinessLayer.QuestionPool;

public class CategoryActivity extends AppCompatActivity {

    private QuestionPool questionPool;
    private LinearLayout container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        container = findViewById(R.id.container);

        questionPool = new QuestionPool(getApplicationContext());

        initCategories();
    }

    public void onChoose(View v){
        finish();
    }

    private void initCategories(){
        for(Category category : Category.values()){
            Button bt = new Button(this);
            bt.setText(category.toString());
            bt.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT));
            bt.setBackgroundColor(getColor(R.color.blue));
            bt.setTextColor(getColor(R.color.white));

            container.addView(bt);
        }
    }
}
