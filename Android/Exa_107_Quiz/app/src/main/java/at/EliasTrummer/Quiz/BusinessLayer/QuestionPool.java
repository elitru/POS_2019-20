package at.EliasTrummer.Quiz.BusinessLayer;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionPool {
    private Map<Category, List<Question>> questionPool = new HashMap<>();

    public QuestionPool(Context context){
        questionPool = IOHandler.getQuestions(context);
        //Toast.makeText(context, questionPool.get(Category.Sport).size() + "", Toast.LENGTH_LONG).show();
    }

    public List<Question> getQuestionsByCategory(Category category){
        return questionPool.get(category);
    }

    public int amountCategories(){
        return questionPool.size();
    }
}
