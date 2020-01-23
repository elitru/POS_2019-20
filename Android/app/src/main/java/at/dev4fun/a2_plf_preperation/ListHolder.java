package at.dev4fun.a2_plf_preperation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import at.dev4fun.a2_plf_preperation.beans.Thing;
import at.dev4fun.a2_plf_preperation.list.ThingAdapter;

public class ListHolder extends AppCompatActivity {

    private RecyclerView rvList;
    private List<Thing> things;
    private ThingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_holder);

        this.rvList = findViewById(R.id.rvList);
        this.things = getIntent().getParcelableArrayListExtra("things");
        this.adapter =  new ThingAdapter(this.things);

        rvList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvList.setAdapter(adapter);

        adapter.filter("this");

        Intent i = getIntent();
        i.putExtra("test", "Hello World");
        setResult(10, i);
        finish();
    }
}
