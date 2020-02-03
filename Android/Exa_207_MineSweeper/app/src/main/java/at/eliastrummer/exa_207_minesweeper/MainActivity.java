package at.eliastrummer.exa_207_minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import at.eliastrummer.exa_207_minesweeper.game.GameHandler;
import at.eliastrummer.exa_207_minesweeper.game.Node;
import at.eliastrummer.exa_207_minesweeper.game.Point;

public class MainActivity extends AppCompatActivity {

    private GridLayout glField;
    private Button[][] btnField = new Button[9][9];
    private ImageButton btFlag;
    private ImageButton btHit;

    private TextView tvTime;
    private TextView tvMines;

    private GameHandler gameHandler;
    private boolean isRunning = false;

    //1 = Flag
    //0 = Hit
    private int useMode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.glField = findViewById(R.id.glField);
        this.tvMines = findViewById(R.id.tvMines);
        this.tvTime = findViewById(R.id.tvMines);
        this.btFlag = findViewById(R.id.btMark);
        this.btHit = findViewById(R.id.btTry);
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

                final int xCoordinate = x;
                final int yCoordinate = y;

                bt.setOnClickListener(v -> {
                    onClickField(v, xCoordinate, yCoordinate);
                });
            }
        }
    }

    private void onClickField(View v, int x, int y){
        if(!isRunning){
            startGame(x, y);
            return;
        }

        if(useMode == 0){
            //make hit
        }else{
            //make mark
            if(gameHandler.changeNodeMarkedState(x, y)){
                //TODO: game won
            }else{
                renderView();
            }
        }
    }

    private void startGame(int x, int y){
        useMode = 0;
        gameHandler = new GameHandler(9, 9, 10, new Point(x, y));
        gameHandler.print();
        renderView();
        isRunning = true;
    }

    private void renderView(){
        for(int y = 0; y < gameHandler.getField().length; y++){
            for(int x = 0; x < gameHandler.getField()[y].length; x++){
                Button btn = btnField[y][x];

                if(gameHandler.getNode(x, y).isMarked() && !gameHandler.getNode(x, y).isHit()){
                    btn.setBackground(getDrawable(R.drawable.flag));
                    btn.setText("");
                    continue;
                }

                if(gameHandler.getNode(x, y).isHit() && !gameHandler.getNode(x, y).isBomb()){
                    btn.setText(gameHandler.getNeighbourCount(x, y) + "");
                    btn.setBackground(null);
                    btn.setBackgroundColor(getColor(R.color.yellow));
                }
            }
        }
    }

    public void onUseModeFlag(View view) {
        btHit.setAlpha(0.5f);
        btFlag.setAlpha(1f);
        useMode = 1;
    }

    public void onUseModeHit(View view) {
        btHit.setAlpha(1f);
        btFlag.setAlpha(0.5f);
        useMode = 0;
    }
}
