package at.dev4fun.a2_plf_preperation.list;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ThingsHolder extends RecyclerView.ViewHolder {

    private TextView tvID;
    private TextView tvData;

    public ThingsHolder(@NonNull View itemView, TextView tvID, TextView tvData) {
        super(itemView);
        this.tvID = tvID;
        this.tvData = tvData;
    }

    public TextView getTvID() {
        return tvID;
    }

    public void setTvID(TextView tvID) {
        this.tvID = tvID;
    }

    public TextView getTvData() {
        return tvData;
    }

    public void setTvData(TextView tvData) {
        this.tvData = tvData;
    }
}
