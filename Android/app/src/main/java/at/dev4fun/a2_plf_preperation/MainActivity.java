package at.dev4fun.a2_plf_preperation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import at.dev4fun.a2_plf_preperation.beans.Thing;
import at.dev4fun.a2_plf_preperation.io.IOHandler;

public class MainActivity extends AppCompatActivity {

    private List<Thing> things;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IOHandler.setCtx(getApplicationContext());

        try {
            things = IOHandler.getThings();
            IOHandler.Test();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onShowList(View view) {
        Intent i = new Intent(getApplicationContext(), ListHolder.class);
        i.putParcelableArrayListExtra("things", (ArrayList<Thing>)this.things);
        startActivityForResult(i, 5);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 10){
            Toast.makeText(getApplicationContext(), data.getStringExtra("test"), Toast.LENGTH_LONG).show();
        }
    }
}
