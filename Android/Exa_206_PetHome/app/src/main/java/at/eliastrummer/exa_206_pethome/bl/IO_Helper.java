package at.eliastrummer.exa_206_pethome.bl;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import at.eliastrummer.exa_206_pethome.beans.Cat;
import at.eliastrummer.exa_206_pethome.beans.Dog;
import at.eliastrummer.exa_206_pethome.beans.Pet;

public class IO_Helper {

    private static Context ctx;

    public static void setContext(Context context){
        ctx = context;
    }

    public static List<Pet> loadPets(){
        List<Pet> pets = new ArrayList<>();

        try {
            InputStream inputStream = ctx.getAssets().open("pets.csv");

            new BufferedReader(new InputStreamReader(inputStream))
                    .lines().skip(1).forEach(line -> {
                        if(line.split(",")[0].equalsIgnoreCase("dog")){
                            pets.add(new Dog(line.split(",")));
                        }else{
                            pets.add(new Cat(line.split(",")));
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pets;
    }
}