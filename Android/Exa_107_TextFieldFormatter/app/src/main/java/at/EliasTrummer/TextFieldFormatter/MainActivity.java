package at.EliasTrummer.TextFieldFormatter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity {

    private EditText etInput;
    private TextView tvSize;
    private CheckBox cbBold;
    private CheckBox cbItalic;
    private SeekBar sbSize;
    private RadioGroup rgFont;
    private RadioButton rbUbuntu;
    private RadioButton rbGrandHotel;
    private RadioButton rbPermanentMarker;
    private Typeface currentFont;
    private int currentStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.etInput = findViewById(R.id.etInput);
        this.tvSize = findViewById(R.id.tvSize);
        this.cbBold = findViewById(R.id.cbBold);
        this.cbItalic = findViewById(R.id.cbItalic);
        this.sbSize = findViewById(R.id.sbSize);
        this.rgFont = findViewById(R.id.rgFont);
        this.rbUbuntu = findViewById(R.id.rbUbuntu);
        this.rbGrandHotel = findViewById(R.id.rbGrandHotel);
        this.rbPermanentMarker = findViewById(R.id.rbPermanentMarker);

        currentFont = ResourcesCompat.getFont(getApplicationContext(), R.font.ubuntu);
        currentStyle = Typeface.NORMAL;

        tvSize.setText(String.format(getString(R.string.size), 12));

        cbBold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChangeFontStyle(v);
            }
        });

        cbItalic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChangeFontStyle(v);
            }
        });

        sbSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                onChangeFontSize(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        rgFont.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                onChangeFont();
            }
        });
    }

    private void onChangeFontStyle(View view){
        int style;

        if(cbBold.isChecked()){
            style = Typeface.BOLD;
        }else if(cbItalic.isChecked()){
            style = Typeface.BOLD;
        }else if(cbBold.isChecked() && cbItalic.isChecked()){
            style = Typeface.BOLD_ITALIC;
        }else{
            style = Typeface.NORMAL;
        }

        currentStyle = style;
        etInput.setTypeface(currentFont, style);
    }

    private void onChangeFontSize(int progress){
        etInput.setTextSize(progress);
        tvSize.setText(String.format(getString(R.string.size), progress));
    }

    private void onChangeFont(){
        Typeface font = etInput.getTypeface();
        font = ResourcesCompat.getFont(getApplicationContext(), R.font.ubuntu);

        if(rbUbuntu.isChecked()){
            font = ResourcesCompat.getFont(getApplicationContext(), R.font.ubuntu);
        }else if(rbGrandHotel.isChecked()){
            font = ResourcesCompat.getFont(getApplicationContext(), R.font.grand_hotel_regular);
        }else{
            font = ResourcesCompat.getFont(getApplicationContext(), R.font.permanent_marker);
        }

        currentFont = font;

        etInput.setTypeface(font, currentStyle);
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
