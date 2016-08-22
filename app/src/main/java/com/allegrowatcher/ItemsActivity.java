package com.allegrowatcher;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.allegrowatcher.adapters.ItemAdapter;
import com.allegrowatcher.model.Item;
import com.allegrowatcher.model.Summary;

import org.parceler.Parcels;

/**
 * Created by daba on 2016-08-18.
 */

public class ItemsActivity extends Activity implements AdapterView.OnItemClickListener {

    public static final String ITEMS_KEY = "items_key";

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);

        Summary summary = Parcels.unwrap(getIntent().getParcelableExtra(ITEMS_KEY));
        ItemAdapter adapter = new ItemAdapter(this, summary.newIitems);

        listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (listView != null) {
            listView.setOnItemClickListener(null);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Item item = ((ItemAdapter) adapterView.getAdapter()).getItem(position);
        openAllegroItem(item.id);
    }

    private void openAllegroItem(long itemId) {
        String path = getString(R.string.allegro_path, "" + itemId);
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(path)));
    }
}
