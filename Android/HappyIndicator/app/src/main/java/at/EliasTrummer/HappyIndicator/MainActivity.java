package at.EliasTrummer.HappyIndicator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import at.EliasTrummer.HappyIndicator.BL.HappinessModel;

public class MainActivity extends AppCompatActivity {

    private SeekBar sbValue;
    private Button btSend;
    private EditText etName;
    private TextView tvOutput;
    private TextView tvHappiness;
    private ImageView ivSmile;

    private HappinessModel happinessModel = new HappinessModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBarListener seekBarListener = new SeekBarListener();

        sbValue = findViewById(R.id.sbValue);
        btSend = findViewById(R.id.btSend);
        tvHappiness = findViewById(R.id.tvHappiness);
        tvOutput = findViewById(R.id.tvOutput);
        etName = findViewById(R.id.etName);
        ivSmile = findViewById(R.id.ivSmile);

        tvHappiness.setText(String.format(getString(R.string.happiness_value), 5));

        sbValue.setOnSeekBarChangeListener(seekBarListener);

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSend(v);
            }
        });

        tvOutput.setText(happinessModel.getTopThreeString());
    }

    private void onSend(View view){
        if(etName.getText().toString().trim().isEmpty()){
            Toast.makeText(getApplicationContext(), "Please enter name", Toast.LENGTH_LONG).show();
            return;
        }

        happinessModel.addHappinessValue(etName.getText().toString().trim(), sbValue.getProgress());
        tvOutput.setText(happinessModel.getTopThreeString());
    }

    class SeekBarListener implements SeekBar.OnSeekBarChangeListener{

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(progress >= 1 && progress <= 3){
                ivSmile.setImageResource(R.drawable.smiley1);
            }else if(progress >= 4 && progress <= 7){
                ivSmile.setImageResource(R.drawable.smiley2);
            }else{
                ivSmile.setImageResource(R.drawable.smiley3);
            }

            tvHappiness.setText(String.format(getString(R.string.happiness_value), progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
