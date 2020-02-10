package at.eliastrummer.exa_207_minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FinishedGame extends AppCompatActivity {

    private TextView tvUsedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_game);

        tvUsedTime = findViewById(R.id.tvUsedTime);
        int timeInSec = getIntent().getIntExtra("time", 0);
        int min = timeInSec / 60;
        int sec = timeInSec % 60;
        tvUsedTime.setText(String.format("%02d", min) + ":" + String.format("%02d", sec));
    }

    public void onRestart(View view) {
        MainActivity.start();
        finish();
    }
}
