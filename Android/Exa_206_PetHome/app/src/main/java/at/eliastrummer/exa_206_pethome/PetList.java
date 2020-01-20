package at.eliastrummer.exa_206_pethome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import at.eliastrummer.exa_206_pethome.beans.Cat;
import at.eliastrummer.exa_206_pethome.beans.Dog;
import at.eliastrummer.exa_206_pethome.beans.Pet;
import at.eliastrummer.exa_206_pethome.bl.PetAdapter;

public class PetList extends AppCompatActivity {

    private PetAdapter adapter;
    private RecyclerView rvPetList;
    private ArrayList<Pet> pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);
        this.rvPetList = findViewById(R.id.rvPetList);
        pets = (ArrayList<Pet>) this.getIntent().getSerializableExtra("pets");

        if(pets.get(0) instanceof Dog){
            pets.sort((d1, d2) -> {
                if(((Dog)d1).getSize() == (((Dog)d2).getSize())){
                    return ((Dog)d1).getDateOfBirth().compareTo(((Dog)d2).getDateOfBirth());
                }
                return ((Dog)d1).getSize().compareTo(((Dog)d2).getSize());
            });
        }else{
            pets.sort(Comparator.comparing(Pet::getDateOfBirth));
        }

        this.adapter = new PetAdapter(pets);

        rvPetList.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        rvPetList.setAdapter(adapter);

        ((TextView)findViewById(R.id.tvListType)).setText(getIntent().getStringExtra("type"));
    }
}