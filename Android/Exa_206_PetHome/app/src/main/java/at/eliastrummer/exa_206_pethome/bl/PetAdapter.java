package at.eliastrummer.exa_206_pethome.bl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import at.eliastrummer.exa_206_pethome.R;
import at.eliastrummer.exa_206_pethome.beans.Cat;
import at.eliastrummer.exa_206_pethome.beans.Dog;
import at.eliastrummer.exa_206_pethome.beans.Gender;
import at.eliastrummer.exa_206_pethome.beans.Pet;

public class PetAdapter extends RecyclerView.Adapter<PetHolder> {
    private List<Pet> pets = new ArrayList<>();

    public PetAdapter(List<Pet> pets){
        this.pets = pets;
    }

    @NonNull
    @Override
    public PetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_item, parent, false);

        ImageView ivImage = view.findViewById(R.id.ivImage);
        ImageView ivGender = view.findViewById(R.id.ivGender);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvBirthdate = view.findViewById(R.id.tvBirthdate);
        TextView tvData = view.findViewById(R.id.tvData);

        return new PetHolder(view, ivImage, ivGender, tvName, tvBirthdate, tvData);
    }

    @Override
    public void onBindViewHolder(@NonNull PetHolder holder, int position) {
        Pet pet = pets.get(position);
        holder.getTvName().setText(pet.getName());
        holder.getTvBirthdate().setText(pet.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        if(pet.getGender() == Gender.MALE){
            holder.getIvGender().setImageResource(R.drawable.ic_boy_2024649);
        }else{
            holder.getIvGender().setImageResource(R.drawable.ic_emblem_2024648);
        }

        if(pet instanceof Dog){
            Dog dog = (Dog) pet;
            holder.getTvData().setText("Size: " + dog.getSize().toString().toLowerCase());
            holder.getIvImage().setImageResource(R.drawable.ic_dog_1710298);
        }else{
            Cat cat = (Cat) pet;
            holder.getTvData().setText("Color: " + cat.getColor().toString().toLowerCase());
            Picasso.get().load(cat.getPictureUri()).into(holder.getIvImage());
        }
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }
}