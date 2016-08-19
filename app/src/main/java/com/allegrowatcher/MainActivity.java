package com.allegrowatcher;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.allegrowatcher.adapters.FilterAdapter;
import com.allegrowatcher.model.Category;
import com.allegrowatcher.model.Filter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<Filter> filters = new ArrayList<Filter>() {
        { add(new Filter(new Category(16693, "Widelce sztywne"), 0, 260)); }
        { add(new Filter(new Category(16447, "Ramy"), 0, 500)); }
        { add(new Filter("Trialowy", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("Trialowka", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("Trialowa", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("Trialu", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("Trial", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("Echo", new Category(16420, "Rowery"))); }
        { add(new Filter("Echo", new Category(16416, "Części"))); }
        { add(new Filter("Koxx", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("Monty", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("Trialtech", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("Zoo", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("TryAll", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("Because", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("DaBomb", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("Da Bomb", new Category(16414, "Rowery i akcesoria"))); }
        { add(new Filter("Etch A Sketch")); }
        { add(new Filter("Learning Resources")); }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FilterAdapter adapter = new FilterAdapter(this, filters);
        ((ListView)findViewById(R.id.list_view)).setAdapter(adapter);
    }
}