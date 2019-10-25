package at.EliasTrummer.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
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

    public void onChoose(Category category){
        MainActivity.setCategory(category);
        finish();
    }

    private void initCategories(){
        for(Category category : Category.values()){
            Button bt = new Button(this);
            bt.setText(category.toString());
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            params.bottomMargin = 40;
            bt.setLayoutParams(params);
            bt.setBackground(getDrawable(R.drawable.rounded_blue_dark));
            bt.setBackgroundTintList(ColorStateList.valueOf(getColor(R.color.blue)));
            bt.setTextColor(getColor(R.color.white));
            bt.setTextSize(16);
            bt.setPadding(0, 100, 0, 100);

            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onChoose(Category.valueOf(((Button)v).getText().toString()));
                }
            });

            container.addView(bt);
        }
    }
}
