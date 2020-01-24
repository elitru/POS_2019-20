package at.htlkaindorf.travelplanner.bl;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import at.htlkaindorf.travelplanner.CountryOverviewActivity;

public class TripHolder extends RecyclerView.ViewHolder {
    private TextView tvCountry;
    private TextView tvNumberOfTrips;
    private LinearLayout llContainer;
    private Context context;
    private List<Trip> trips;

    public TripHolder(@NonNull View itemView, TextView tvCountry, TextView tvNumberOfTrips, LinearLayout llContainer, Context context) {
        super(itemView);
        this.tvCountry = tvCountry;
        this.tvNumberOfTrips = tvNumberOfTrips;
        this.llContainer = llContainer;
        this.context = context;

        llContainer.setOnClickListener(v -> {
            Intent intent = new Intent(context, CountryOverviewActivity.class);
            ArrayList<Trip> list = new ArrayList<>();
            list.addAll(trips);
            intent.putExtra("trips", list);
            context.startActivity(intent);
        });
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public TextView getTvCountry() {
        return tvCountry;
    }

    public void setTvCountry(TextView tvCountry) {
        this.tvCountry = tvCountry;
    }

    public TextView getTvNumberOfTrips() {
        return tvNumberOfTrips;
    }

    public void setTvNumberOfTrips(TextView tvNumberOfTrips) {
        this.tvNumberOfTrips = tvNumberOfTrips;
    }

    public LinearLayout getLlContainer() {
        return llContainer;
    }

    public void setLlContainer(LinearLayout llContainer) {
        this.llContainer = llContainer;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
