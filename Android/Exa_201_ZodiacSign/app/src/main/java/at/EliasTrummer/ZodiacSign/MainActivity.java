package at.EliasTrummer.ZodiacSign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import at.EliasTrummer.ZodiacSign.bl.ZodiacAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvZodiacs;

    public static MainActivity main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = this;

        rvZodiacs = findViewById(R.id.rcZodiacs);
        rvZodiacs.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        rvZodiacs.setAdapter(new ZodiacAdapter());
    }
}
