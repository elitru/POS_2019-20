package at.eliastrummer.exa_206_pethome.bl;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import at.eliastrummer.exa_206_pethome.R;

public class PetHolder extends RecyclerView.ViewHolder {

    private ImageView ivImage;
    private ImageView ivGender;
    private TextView tvName;
    private TextView tvBirthdate;
    private TextView tvData;

    public PetHolder(@NonNull View itemView, ImageView ivImage, ImageView ivGender, TextView tvName, TextView tvBirthdate, TextView tvData) {
        super(itemView);
        this.ivImage = ivImage;
        this.ivGender = ivGender;
        this.tvName = tvName;
        this.tvBirthdate = tvBirthdate;
        this.tvData = tvData;
    }

    public ImageView getIvImage() {
        return ivImage;
    }

    public void setIvImage(ImageView ivImage) {
        this.ivImage = ivImage;
    }

    public ImageView getIvGender() {
        return ivGender;
    }

    public void setIvGender(ImageView ivGender) {
        this.ivGender = ivGender;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public TextView getTvBirthdate() {
        return tvBirthdate;
    }

    public void setTvBirthdate(TextView tvBirthdate) {
        this.tvBirthdate = tvBirthdate;
    }

    public TextView getTvData() {
        return tvData;
    }

    public void setTvData(TextView tvData) {
        this.tvData = tvData;
    }
}
