package com.allegrowatcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.allegrowatcher.adapters.FilterAdapter;
import com.allegrowatcher.model.Filter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Filter> filters = new ArrayList<Filter>() {
        { add(new Filter("Widelce", 16693, 0, 260)); }
        { add(new Filter("Ramy", 16447, 0, 500)); }
        { add(new Filter("Trialowy", 16414)); }
        { add(new Filter("Trialowka", 16414)); }
        { add(new Filter("Trialowa", 16414)); }
        { add(new Filter("Trialu", 16414)); }
        { add(new Filter("Trial", 16414)); }
        { add(new Filter("Echo", 16420)); }
        { add(new Filter("Echo", 16416)); }
        { add(new Filter("Koxx", 16414)); }
        { add(new Filter("Monty", 16414)); }
        { add(new Filter("Trialtech", 16414)); }
        { add(new Filter("Zoo", 16414)); }
        { add(new Filter("TryAll", 16414)); }
        { add(new Filter("Because", 16414)); }
        { add(new Filter("DaBomb", 16414)); }
        { add(new Filter("Da Bomb", 16414)); }
        { add(new Filter("Etch A Sketch")); }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FilterAdapter adapter = new FilterAdapter(this, filters);
        ((ListView)findViewById(R.id.list_view)).setAdapter(adapter);

        /*
       TODO:
       0. adapter_item.xml set photo box size in px (medium photo size?)
       1. Glide for loading images
       2. Parceler to store items
       3. Activity with items list
         */
    }
}