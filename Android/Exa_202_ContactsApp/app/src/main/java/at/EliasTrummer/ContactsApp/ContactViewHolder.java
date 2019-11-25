package at.EliasTrummer.ContactsApp;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import at.EliasTrummer.ContactsApp.bl.Contact;
import at.EliasTrummer.ContactsApp.utils.Utils;

public class ContactViewHolder extends RecyclerView.ViewHolder {

    private LinearLayout llContactContainer;
    private ImageView ivContactPicture;
    private TextView tvContactName;
    private Contact contact;

    public ContactViewHolder(@NonNull View itemView, LinearLayout llContactContainer, ImageView ivContactPicture, TextView tvContactName) {
        super(itemView);
        this.llContactContainer = llContactContainer;
        this.ivContactPicture = ivContactPicture;
        this.tvContactName = tvContactName;

        llContactContainer.setOnClickListener(this::onClick);
    }

    private void onClick(View view){
        Utils.currentContact = this.contact;
        Intent intent = new Intent(MainActivity.main, EditContact.class);
        MainActivity.main.startActivity(intent);
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

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }
}
