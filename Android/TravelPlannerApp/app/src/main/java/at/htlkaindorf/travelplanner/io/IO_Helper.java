package at.htlkaindorf.travelplanner.io;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import at.htlkaindorf.travelplanner.bl.Trip;

public class IO_Helper {
    private static Context context;

    public static void init(Context ctx){
        IO_Helper.context = ctx;
    }

    public static Map<String, List<Trip>> loadTrips() throws IOException {

        List<Trip> trips = new BufferedReader(new InputStreamReader(context.getAssets().open("travel_data.csv")))
                .lines()
                .skip(4)
                .map(Trip::new)
                .collect(Collectors.toList());

        Map<String, List<Trip>> result = new TreeMap<>();

        trips.forEach(t -> {
            if(result.containsKey(t.getCountry())){
                result.get(t.getCountry()).add(t);
            }else{
                List<Trip> list = new ArrayList<>();
                list.add(t);
                result.put(t.getCountry(), list);
            }
        });

        return result;
    }
}
