package at.EliasTrummer.NumberPuzzleGame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity {

    private Button[] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons = new Button[]{
            findViewById(R.id.bt_1),
            findViewById(R.id.bt_2),
            findViewById(R.id.bt_3),
            findViewById(R.id.bt_4),
            findViewById(R.id.bt_5),
            findViewById(R.id.bt_6),
            findViewById(R.id.bt_7),
            findViewById(R.id.bt_8),
            findViewById(R.id.bt_9),
            findViewById(R.id.bt_10),
            findViewById(R.id.bt_11),
            findViewById(R.id.bt_12),
            findViewById(R.id.bt_13),
            findViewById(R.id.bt_14),
            findViewById(R.id.bt_15),
            findViewById(R.id.bt_16),
        };

        initButtons();
    }

    private void initButtons(){
        Random rnd = new Random();

        for(int i = 0; i < buttons.length; i++){
            int randomNum = rnd.nextInt(15) + 1;

            buttons[i].setText("test");

            buttons[i].setOnTouchListener(new OnSwipeTouchListener(getApplication()){

            });
        }
    }
}
