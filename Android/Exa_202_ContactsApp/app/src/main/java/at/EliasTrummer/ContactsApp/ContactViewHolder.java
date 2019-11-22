package at.EliasTrummer.ContactsApp;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    private LinearLayout llContactContainer;
    private ImageView ivContactPicture;
    private TextView tvContactName;

    public ContactViewHolder(@NonNull View itemView, LinearLayout llContactContainer, ImageView ivContactPicture, TextView tvContactName) {
        super(itemView);
        this.llContactContainer = llContactContainer;
        this.ivContactPicture = ivContactPicture;
        this.tvContactName = tvContactName;
    }

    public LinearLayout getLlContactContainer() {
        return llContactContainer;
    }

    public void setLlContactContainer(LinearLayout llContactContainer) {
        this.llContactContainer = llContactContainer;
    }

    public ImageView getIvContactPicture() {
        return ivContactPicture;
    }

    public void setIvContactPicture(ImageView ivContactPicture) {
        this.ivContactPicture = ivContactPicture;
    }

    public TextView getTvContactName() {
        return tvContactName;
    }

    public void setTvContactName(TextView tvContactName) {
        this.tvContactName = tvContactName;
    }
}
