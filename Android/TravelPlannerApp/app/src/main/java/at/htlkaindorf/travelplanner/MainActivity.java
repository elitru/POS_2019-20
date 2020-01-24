package at.htlkaindorf.travelplanner;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;

import at.htlkaindorf.travelplanner.bl.TripAdapter;
import at.htlkaindorf.travelplanner.io.IO_Helper;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvTrips;
    private TripAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IO_Helper.init(this.getApplicationContext());
        this.rvTrips = findViewById(R.id.rvTrips);

        try {
            adapter = new TripAdapter(this.getApplicationContext());
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Es ist beim Lesen der Trips Datei ein Fehler aufgetreten!", Toast.LENGTH_LONG).show();
        }

        this.rvTrips.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        this.rvTrips.setAdapter(adapter);
    }
}
