package at.EliasTrummer.ZodiacSign;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ZodiacHolder extends RecyclerView.ViewHolder {

    private LinearLayout zodiacItem;
    private ImageView ivZodiac;
    private TextView tvZodiacName, tvZodiacDescription;

    public ZodiacHolder(@NonNull View itemView, LinearLayout zodiacItem, ImageView ivZodiac, TextView tvZodiacName, TextView tvZodiacDescription) {
        super(itemView);
        this.zodiacItem = zodiacItem;
        this.ivZodiac = ivZodiac;
        this.tvZodiacName = tvZodiacName;
        this.tvZodiacDescription = tvZodiacDescription;

        zodiacItem.setOnClickListener(this::onItemClick);
    }

    private void onItemClick(View view){
        String url = MainActivity.main.getString(R.string.wikipedia_url, tvZodiacName.getText().toString());
        Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(url));
        MainActivity.main.startActivity(viewIntent);
    }

    public ImageView getIvZodiac() {
        return ivZodiac;
    }

    public void setIvZodiac(ImageView ivZodiac) {
        this.ivZodiac = ivZodiac;
    }

    public TextView getTvZodiacName() {
        return tvZodiacName;
    }

    public void setTvZodiacName(TextView tvZodiacName) {
        this.tvZodiacName = tvZodiacName;
    }

    public TextView getTvZodiacDescription() {
        return tvZodiacDescription;
    }

    public void setTvZodiacDescription(TextView tvZodiacDescription) {
        this.tvZodiacDescription = tvZodiacDescription;
    }
}
