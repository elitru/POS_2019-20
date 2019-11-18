package at.EliasTrummer.exa_therory_2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieHolder extends RecyclerView.ViewHolder {

    private TextView tvName, tvReleaseDate, tvDuration;

    public MovieHolder(@NonNull View itemView, TextView tvName, TextView tvReleaseDate, TextView tvDuration) {
        super(itemView);
        this.tvName = tvName;
        this.tvReleaseDate = tvReleaseDate;
        this.tvDuration = tvDuration;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public TextView getTvReleaseDate() {
        return tvReleaseDate;
    }

    public void setTvReleaseDate(TextView tvReleaseDate) {
        this.tvReleaseDate = tvReleaseDate;
    }

    public TextView getTvDuration() {
        return tvDuration;
    }

    public void setTvDuration(TextView tvDuration) {
        this.tvDuration = tvDuration;
    }
}
