package at.EliasTrummer.ContactsApp.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import at.EliasTrummer.ContactsApp.bl.Contact;

public class IOHandler {

    private static Context context;

    public static void init(Context context){
        IOHandler.context = context;
    }

    public static List<Contact> getContacts(){

        List<Contact> contacts = null;

        try {
            InputStream inputStream = context.getAssets().open("contact_data.csv");

            contacts = new BufferedReader(new InputStreamReader(inputStream))
                                        .lines()
                                        .skip(1)
                                        .map(Contact::new)
                                        .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contacts;
    }

}
