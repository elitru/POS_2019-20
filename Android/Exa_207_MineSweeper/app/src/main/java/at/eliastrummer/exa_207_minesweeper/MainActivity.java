package at.eliastrummer.exa_207_minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private GridLayout glField;
    private Button[][] btnField = new Button[9][9];

    private TextView tvTime;
    private TextView tvMines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.glField = findViewById(R.id.glField);
        this.tvMines = findViewById(R.id.tvMines);
        this.tvTime = findViewById(R.id.tvMines);
        initGUI();
    }

    private void initGUI(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Button bt = new Button(this);
                bt.setText(y + "," + x);
                bt.setId(y*10 + x);
                glField.addView(bt);
                ViewGroup.LayoutParams params = bt.getLayoutParams();
                bt.setGravity(Gravity.CENTER);

                if(y % 2 == 0){
                    if(x % 2 == 0){
                        bt.setBackgroundColor(getColor(R.color.green_very_light));
                    }else{
                        bt.setBackgroundColor(getColor(R.color.green_light));
                    }
                }else{
                    if(x % 2 == 0){
                        bt.setBackgroundColor(getColor(R.color.green_light));
                    }else{
                        bt.setBackgroundColor(getColor(R.color.green_very_light));
                    }
                }

                params.width = width / 9;
                params.height = width / 9;
                btnField[y][x] = bt;
            }
        }
    }
}
