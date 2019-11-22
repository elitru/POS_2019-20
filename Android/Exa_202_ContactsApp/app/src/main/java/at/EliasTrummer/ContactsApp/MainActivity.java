package at.EliasTrummer.ContactsApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import at.EliasTrummer.ContactsApp.bl.ContactAdpater;
import at.EliasTrummer.ContactsApp.utils.IOHandler;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private ContactAdpater adpater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IOHandler.init(getApplicationContext());

        adpater = new ContactAdpater();

        rvList = findViewById(R.id.rvList);
        rvList.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        rvList.setAdapter(adpater);

        ((SearchView)findViewById(R.id.etSearch)).setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adpater.filter(newText);
                return true;
            }
        });
    }
}
