package at.dev4fun.a2_plf_preperation.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import at.dev4fun.a2_plf_preperation.R;
import at.dev4fun.a2_plf_preperation.beans.ThatThing;
import at.dev4fun.a2_plf_preperation.beans.Thing;
import at.dev4fun.a2_plf_preperation.beans.ThisThing;

public class ThingAdapter extends RecyclerView.Adapter<ThingsHolder> {

    private List<Thing> allThings = new ArrayList<>();
    private List<Thing> filteredThings = new ArrayList<>();

    public ThingAdapter(List<Thing> things){
        this.allThings.addAll(things);
        this.filteredThings.addAll(things);
    }

    @NonNull
    @Override
    public ThingsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.thing_item, parent, false);

        TextView tvID = view.findViewById(R.id.tvID);
        TextView tvData = view.findViewById(R.id.tvData);

        return new ThingsHolder(view, tvID, tvData);
    }

    @Override
    public void onBindViewHolder(@NonNull ThingsHolder holder, int position) {
        Thing thing = filteredThings.get(position);

        holder.getTvID().setText("ID: " + thing.getId());

        if(thing instanceof ThatThing){
            holder.getTvData().setText(((ThatThing)thing).getDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        }else{
            holder.getTvData().setText(((ThisThing)thing).getUri().toString());
        }
    }

    @Override
    public int getItemCount() {
        return filteredThings.size();
    }

    public void filter(String type){
        filteredThings.clear();
        filteredThings.addAll(allThings);

        if(type.equalsIgnoreCase("that")){
            filteredThings.removeIf(t -> t instanceof ThisThing);
        }else{
            filteredThings.removeIf(t -> t instanceof ThatThing);
        }

        notifyDataSetChanged();
    }
}
