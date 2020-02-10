package at.eliastrummer.exa_207_minesweeper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import at.eliastrummer.exa_207_minesweeper.game.GameHandler;
import at.eliastrummer.exa_207_minesweeper.game.Point;

public class MainActivity extends AppCompatActivity {

    private int length = 9;
    private int timeInSec = 0;
    private GridLayout glField;
    private Button[][] btnField = new Button[this.length][this.length];
    private ImageButton btFlag;
    private ImageButton btHit;

    private TextView tvTime;
    private TextView tvMines;

    private GameHandler gameHandler;
    private boolean isRunning = false;
    private Timer timer;

    private static MainActivity main;

    //1 = Flag
    //0 = Hit
    private int useMode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = this;

        this.glField = findViewById(R.id.glField);
        this.tvMines = findViewById(R.id.tvMines);
        this.tvTime = findViewById(R.id.tvTime);
        this.btFlag = findViewById(R.id.btMark);
        this.btHit = findViewById(R.id.btTry);

        this.tvMines.setText("0 / 10");

        start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 4){

        }
    }

    public static void start(){
        main.initGUI();
        main.timeInSec = 0;

        main.onUseModeHit(null);
        main.isRunning = false;
        main.tvTime.setText("00:00");

        if(main.timer != null){
            main.timer.cancel();
        }
    }

    private void initGUI(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        this.glField.removeAllViewsInLayout();

        for (int y = 0; y < this.length; y++) {
            for (int x = 0; x < this.length; x++) {
                Button bt = new Button(this);
                bt.setId(y*10 + x);
                glField.addView(bt);
                ViewGroup.LayoutParams params = bt.getLayoutParams();
                bt.setGravity(Gravity.CENTER);

                setInitialBackground(bt, x, y);

                params.width = width / this.length;
                params.height = width / this.length;
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
            main.timer = new Timer();
            main.timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    main.timeInSec++;
                    int min = main.timeInSec / 60;
                    int sec = main.timeInSec % 60;
                    main.tvTime.setText(String.format("%02d", min) + ":" + String.format("%02d", sec));
                }
            },1000, 1000);

            startGame(x, y);
            onUseModeHit(null);
            return;
        }

        if(useMode == 0){
            //make hit
            boolean result = gameHandler.makeHit(x, y);

            if(result){
                //bomb was hit -> game over
                btnField[y][x].setBackground(getDrawable(R.drawable.bomb));
                showEnd();
                return;
            }

            renderView();
        }else{
            //make mark
            if(gameHandler.changeNodeMarkedState(x, y)){
                Intent intent = new Intent(this, FinishedGame.class);
                intent.putExtra("time", timeInSec);
                startActivity(intent);
                return;
            }

            this.tvMines.setText(gameHandler.getAmountMarked() + " / 10");

            renderView();
        }
    }

    private void startGame(int x, int y){
        useMode = 0;
        gameHandler = new GameHandler(this.length, this.length, 10, new Point(x, y));
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
                }else if(gameHandler.getNode(x, y).isHit() && !gameHandler.getNode(x, y).isBomb()){
                    btn.setText(gameHandler.getNeighbourCount(x, y) + "");
                    setHitBackground(btn, x, y);
                }else{
                    setInitialBackground(btn, x, y);
                }
            }
        }
    }

    private void showEnd(){
        for(int y = 0; y < gameHandler.getField().length; y++){
            for(int x = 0; x < gameHandler.getField()[y].length; x++){
                Button btn = btnField[y][x];
                btn.setEnabled(false);
                setHitBackground(btn, x, y);
                btn.setText("");

                if(gameHandler.getNode(x, y).isBomb()){
                    btn.setBackground(getDrawable(R.drawable.bomb));
                }
            }
        }
    }

    private void setInitialBackground(Button bt, int x, int y){
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
    }

    private void setHitBackground(Button bt, int x, int y){
        if(y % 2 == 0){
            if(x % 2 == 0){
                bt.setBackgroundColor(getColor(R.color.hit_2));
            }else{
                bt.setBackgroundColor(getColor(R.color.hit_1));
            }
        }else{
            if(x % 2 == 0){
                bt.setBackgroundColor(getColor(R.color.hit_1));
            }else{
                bt.setBackgroundColor(getColor(R.color.hit_2));
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

    public void onRestartGame(View view) {
        start();
    }
}
