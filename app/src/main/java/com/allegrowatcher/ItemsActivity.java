package com.allegrowatcher;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.allegrowatcher.adapters.ItemAdapter;
import com.allegrowatcher.model.Summary;

import org.parceler.Parcels;

/**
 * Created by daba on 2016-08-18.
 */

public class ItemsActivity extends Activity {

    public static final String ITEMS_KEY = "items_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        Summary summary = Parcels.unwrap(getIntent().getParcelableExtra(ITEMS_KEY));
        ItemAdapter adapter = new ItemAdapter(this, summary.newIitems);
        ((ListView)findViewById(R.id.list_view)).setAdapter(adapter);
    }
}
