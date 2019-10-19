package at.EliasTrummer.Quiz.BusinessLayer;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import at.EliasTrummer.Quiz.R;

public class IOHandler {
    public static Map<Category, List<Question>> getQuestions(Context ctx){

        Map<Category, List<Question>> result = new HashMap<>();

        try {
            InputStream is = ctx.getResources().openRawResource(R.raw.questions);

            BufferedReader input =  new BufferedReader(new InputStreamReader(is), 1024*8);
            try {
                String line = null;
                input.readLine();

                while ((line = input.readLine()) != null){
                    String[] data = line.split(";");
                    List<Question> questions = new ArrayList<>();

                    Question q = new Question(data[1], Arrays.asList(new String[]{
                            data[2], data[3], data[4], data[5]
                    }), Integer.parseInt(data[6]));

                    result.put(Category.valueOf(data[0]), questions);
                }
            }
            finally {
                input.close();
            }
        }
        catch (FileNotFoundException ex) {
            return null;
        }
        catch (IOException ex){
            return null;
        }

        return result;
    }
}
