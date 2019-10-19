package at.EliasTrummer.HelloWorld;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private Button btSubmit;
    private TextView txtMain;

    private boolean isEnterMode = true;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get instances of views
        etName = findViewById(R.id.etName);
        btSubmit = findViewById(R.id.btSubmit);
        txtMain = findViewById(R.id.txtMain);
        //create Event Handler
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Clicked ;)");
                if(isEnterMode){
                    isEnterMode = false;
                    etName.setVisibility(View.GONE);
                    btSubmit.setText(R.string.finish);
                    txtMain.setText(String.format("Hello %s - nice to meet you", etName.getText()));
                }else{
                    isEnterMode = true;
                    txtMain.setText(R.string.enterName);
                    etName.setText("");
                    etName.setVisibility(View.VISIBLE);
                    btSubmit.setText(R.string.submit);
                }
            }
        });
    }
}
