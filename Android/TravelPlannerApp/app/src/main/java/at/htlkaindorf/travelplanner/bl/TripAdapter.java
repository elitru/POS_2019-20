package at.htlkaindorf.travelplanner.bl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import at.htlkaindorf.travelplanner.R;
import at.htlkaindorf.travelplanner.io.IO_Helper;

public class TripAdapter extends RecyclerView.Adapter<TripHolder> {

    private Map<String, List<Trip>> trips;
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private Context context;

    public TripAdapter(Context context) throws IOException {
        trips = IO_Helper.loadTrips();
        this.context = context;
    }

    @NonNull
    @Override
    public TripHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item, parent, false);

        TextView tvCountry = view.findViewById(R.id.tvCountry);
        TextView tvNumberOfTrips = view.findViewById(R.id.tvNumberOfTrips);
        LinearLayout lllContainer = view.findViewById(R.id.llContainer);

        return new TripHolder(view, tvCountry, tvNumberOfTrips, lllContainer, context);
    }

    @Override
    public void onBindViewHolder(@NonNull TripHolder holder, int position) {
        String country = (String) trips.keySet().toArray()[position];
        List<Trip> tripsToCountry = (List<Trip>) trips.values().toArray()[position];

        holder.setTrips(tripsToCountry);

        String output = "";
        int amountTrips = tripsToCountry.size();
        output = amountTrips == 1 ? amountTrips + " trip - " : amountTrips + " trips - ";

        LocalDate minDate = LocalDate.ofEpochDay(tripsToCountry.stream()
                            .mapToLong(t -> t.getStartDate().toEpochDay())
                            .min().getAsLong());

        LocalDate maxDate = LocalDate.ofEpochDay(tripsToCountry.stream()
                .mapToLong(t -> t.getStartDate().toEpochDay())
                .max().getAsLong());

        int days =  tripsToCountry.stream()
                .mapToInt(t -> t.getDuration())
                .sum();

        Trip lastTrip = getTripByDate(tripsToCountry, maxDate);

        String day = " " + (days == 1 ? "day" : "days");
        output += days + day + " (" + dtf.format(minDate) + " - " + dtf.format(maxDate.plusDays(lastTrip.getDuration())) + ")";

        holder.getTvCountry().setText(country + " (" + tripsToCountry.get(0).getCountryCode().toUpperCase() + ")");
        holder.getTvNumberOfTrips().setText(output);
    }

    @Override
    public int getItemCount() {
        return trips.size();
    }

    private Trip getTripByDate(List<Trip> tripsToCountry, LocalDate date){
        for(Trip t : tripsToCountry){
            if(t.getStartDate().equals(date)){
                return t;
            }
        }

        return null;
    }
}
