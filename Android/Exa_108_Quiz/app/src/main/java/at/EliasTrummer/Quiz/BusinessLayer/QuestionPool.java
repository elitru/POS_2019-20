package at.EliasTrummer.Quiz.BusinessLayer;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionPool {
    private Map<Category, List<Question>> questionPool = new HashMap<>();

    public QuestionPool(Context context){
        questionPool = IOHandler.getQuestions(context);
    }

    public List<Question> getQuestionsByCategory(Category category){
        return questionPool.get(category);
    }
}
