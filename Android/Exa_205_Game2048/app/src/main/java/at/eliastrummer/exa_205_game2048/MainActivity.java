package at.eliastrummer.exa_205_game2048;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TextView;

import at.eliastrummer.exa_205_game2048.utils.Direction;
import at.eliastrummer.exa_205_game2048.utils.OnSwipeTouchListener;

public class MainActivity extends AppCompatActivity {

    private ImageButton btRestart;
    private TextView tvPoints;
    private TableLayout tlContainer;

    private Button[] buttons = new Button[15];

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btRestart = findViewById(R.id.ibtRestart);
        tvPoints = findViewById(R.id.tvPoints);
        tlContainer = findViewById(R.id.tlContainer);

        tlContainer.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this){
            @Override
            public void onSwipe(Direction direction) {
                super.onSwipe(direction);

            }
        });

        initButtons();
    }

    private void initButtons(){
        for(int i = 0; i < buttons.length; i++){
            buttons[i] = findViewById(getResources().getIdentifier("btNum" + (i + 1), "id", getPackageName()));
        }
    }

    private boolean onSwipe(View view, MotionEvent evt){
        return true;
    }
}
