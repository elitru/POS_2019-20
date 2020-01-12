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
import android.widget.Toast;

import at.eliastrummer.exa_205_game2048.game.GameManager;
import at.eliastrummer.exa_205_game2048.utils.ColorScheme;
import at.eliastrummer.exa_205_game2048.utils.Direction;
import at.eliastrummer.exa_205_game2048.utils.OnSwipeTouchListener;

public class MainActivity extends AppCompatActivity {

    private ImageButton btRestart;
    private TextView tvPoints;
    private TableLayout tlContainer;

    private Button[][] buttons = new Button[4][4];
    private GameManager gameManager = new GameManager();

    private boolean gameStatus = true;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btRestart = findViewById(R.id.ibtRestart);
        tvPoints = findViewById(R.id.tvPoints);
        tlContainer = findViewById(R.id.tlContainer);

        tvPoints.setText("Points\n0");

        tlContainer.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this){
            @Override
            public void onSwipe(Direction direction) {
                super.onSwipe(direction);

                if(!gameStatus){
                    return;
                }

                gameStatus = gameManager.makeMove(direction);
                setButtonContent();
                tvPoints.setText("Points\n" + gameManager.getPoints());

                if(!gameStatus){
                    Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_LONG).show();
                }
            }
        });

        initButtons();
    }

    private void initButtons(){
        for(int i = 0; i < 16; i++){
            buttons[i / 4][i % 4] = findViewById(getResources().getIdentifier("btNum" + (i + 1), "id", getPackageName()));
        }

        setButtonContent();
    }

    private void setButtonContent(){
        for(int y = 0; y < buttons.length; y++){
            for(int x = 0; x < buttons[y].length; x++){
                int value = gameManager.getValue(x, y);
                buttons[y][x].setText(value + "");
                buttons[y][x].setBackgroundColor(ColorScheme.valueOf("C" + value).getBackgroundColor());
                buttons[y][x].setTextColor(ColorScheme.valueOf("C" + value).getFontColor());
            }
        }
    }

    public void onReset(View view) {
        gameManager.reset();
        gameStatus = true;
        tvPoints.setText("Points\n0");
        setButtonContent();
    }
}
