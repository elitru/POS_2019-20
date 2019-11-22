package at.EliasTrummer.ContactsApp.bl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import at.EliasTrummer.ContactsApp.ContactViewHolder;
import at.EliasTrummer.ContactsApp.R;
import at.EliasTrummer.ContactsApp.utils.IOHandler;

public class ContactAdpater extends RecyclerView.Adapter<ContactViewHolder> {

    private List<Contact> allContacts = IOHandler.getContacts();
    private List<Contact> filteredContacts = new ArrayList<Contact>(){{
       addAll(allContacts);
    }};

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);

        LinearLayout llContactContainer = view.findViewById(R.id.llContactContainer);
        ImageView ivContactPicture = view.findViewById(R.id.ivContactPicture);
        TextView tvContactName = view.findViewById(R.id.tvContactName);

        return new ContactViewHolder(view, llContactContainer, ivContactPicture, tvContactName);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

        Contact contact = filteredContacts.get(position);

        holder.getTvContactName().setText(contact.getLastname() + ", " + contact.getFirstname());

        Picasso.get().load(contact.getPicture()).into(holder.getIvContactPicture());
    }

    @Override
    public int getItemCount() {
        return filteredContacts.size();
    }

    public void filter(String param){
        filteredContacts.clear();
        filteredContacts.addAll(allContacts);

        filteredContacts.removeIf(contact -> {
            if(contact.getFirstname().toUpperCase().contains(param.toUpperCase()) || contact.getLastname().toUpperCase().contains(param.toUpperCase())){
                return false;
            }else{
                return true;
            }
        });
        this.notifyDataSetChanged();
    }
}
