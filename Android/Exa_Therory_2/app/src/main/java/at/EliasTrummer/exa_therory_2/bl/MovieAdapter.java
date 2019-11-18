package at.EliasTrummer.exa_therory_2.bl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import at.EliasTrummer.exa_therory_2.MovieHolder;
import at.EliasTrummer.exa_therory_2.R;

public class MovieAdapter extends RecyclerView.Adapter<MovieHolder> {

    private List<Movie> movies = new ArrayList<Movie>(){{
        add(new Movie("Batman - The Dark Knight", YearMonth.of(2008, 8), 152));
        add(new Movie("Dumm und Dümmer", YearMonth.of(1994, 12), 113));
        add(new Movie("James Bond 007: Spectre", YearMonth.of(2015, 11), 160));
    }};

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);

        TextView tvName = view.findViewById(R.id.tvMovieName),
                 tvReleaseDate = view.findViewById(R.id.tvReleaseDate),
                tvDuration = view.findViewById(R.id.tvMovieDuration);

        return new MovieHolder(view, tvName, tvReleaseDate, tvDuration);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
        Movie movie = movies.get(position);

        holder.getTvName().setText(movie.getName());
        holder.getTvDuration().setText(movie.getDuration());
        holder.getTvReleaseDate().setText(movie.getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
