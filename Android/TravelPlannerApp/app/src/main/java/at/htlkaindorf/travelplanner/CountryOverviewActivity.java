package at.htlkaindorf.travelplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import at.htlkaindorf.travelplanner.bl.Trip;

public class CountryOverviewActivity extends AppCompatActivity {

    private List<Trip> trips;

    private TextView tvCountry;
    private TextView tvOverview;
    private SearchView svCity;

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_overview);

        trips = (List<Trip>) getIntent().getSerializableExtra("trips");

        tvCountry = findViewById(R.id.tvCountry);
        tvOverview = findViewById(R.id.tvOverview);
        svCity = findViewById(R.id.svCity);

        tvCountry.setText(trips.get(0).getCountry());
        showTrips("");

        svCity.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                showTrips(newText);
                return false;
            }
        });
    }

    private void showTrips(String cityFilter){
        Map<LocalDate, String> tripData = new TreeMap<LocalDate, String>();

        for (Trip trip : trips) {
            if(!trip.getCity().toUpperCase().contains(cityFilter.toUpperCase())){
                continue;
            }

            String output = "";
            output += trip.getCity() + ": ";
            output += dtf.format(trip.getStartDate()) + " - ";
            output += dtf.format(trip.getStartDate().plusDays(trip.getDuration()));

            tripData.put(trip.getStartDate(), output);
        }

        tvOverview.setText("");

        tripData.values().forEach(e -> {
            tvOverview.append(e + "\n");
        });
    }
}
