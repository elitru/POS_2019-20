package at.eliastrummer.exa_206_pethome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import at.eliastrummer.exa_206_pethome.beans.Cat;
import at.eliastrummer.exa_206_pethome.beans.Dog;
import at.eliastrummer.exa_206_pethome.beans.Pet;
import at.eliastrummer.exa_206_pethome.bl.IO_Helper;

public class MainActivity extends AppCompatActivity {

    private List<Pet> allPets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IO_Helper.setContext(this.getApplicationContext());
        this.allPets = IO_Helper.loadPets();
    }


    public void onShowDogs(View view) {
        Intent intent = new Intent(this.getApplicationContext(), PetList.class);
        intent.putExtra("pets", getDogs());
        intent.putExtra("type", "Dog List");
        this.getApplicationContext().startActivity(intent);
    }

    public void onShowCats(View view) {
        Intent intent = new Intent(this.getApplicationContext(), PetList.class);
        intent.putExtra("pets", getCats());
        intent.putExtra("type", "Cat List");
        this.getApplicationContext().startActivity(intent);
    }

    private ArrayList<Dog> getDogs() {
        final ArrayList<Dog> dogs = new ArrayList<Dog>();

        allPets.forEach(pet -> {
            if(pet instanceof Dog){
                dogs.add(((Dog)pet));
            }
        });

        return dogs;
    }

    private ArrayList<Cat> getCats() {
        final ArrayList<Cat> cats = new ArrayList<Cat>();

        allPets.forEach(pet -> {
            if(pet instanceof Cat){
                cats.add(((Cat)pet));
            }
        });

        return cats;
    }
}