package at.EliasTrummer.exa_therory_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import at.EliasTrummer.exa_therory_2.bl.MovieAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMovies = findViewById(R.id.rvMovies);
        rvMovies.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        rvMovies.setAdapter(new MovieAdapter());
    }
}
