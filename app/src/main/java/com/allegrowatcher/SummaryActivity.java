package com.allegrowatcher;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.allegrowatcher.adapters.FilterAdapter;
import com.allegrowatcher.db.DataManager;
import com.allegrowatcher.dialogs.AddFilterDialog;
import com.allegrowatcher.model.Filter;
import com.allegrowatcher.model.Item;
import com.allegrowatcher.model.Summary;

import org.parceler.Parcels;

public class SummaryActivity
        extends AppCompatActivity
        implements AddFilterDialog.Listener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {

    private DataManager dataManager;
    private FilterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddDialog();
            }
        });

        dataManager = new DataManager(this);
        adapter = new FilterAdapter(this, dataManager.getFilters());

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
    }

    public void showAddDialog() {
        new AddFilterDialog(this, this).show();
    }

    @Override
    public void newFilter(Filter filter) {
        dataManager.addFilter(filter);
        adapter.add(filter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (view.getTag(R.string.tag_summary) != null) {
            Summary summary = (Summary) view.getTag(R.string.tag_summary);
            for (Item item : summary.newIitems) {
                dataManager.addAllegroId(item.id);
            }
            openItemList(summary);
        }
    }

    public void openItemList(Summary summary) {
        Intent intent = new Intent(this, ItemsActivity.class);
        intent.putExtra(ItemsActivity.ITEMS_KEY, Parcels.wrap(summary));
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (view.getTag(R.string.tag_filter) != null) {
            final Filter filter = (Filter) view.getTag(R.string.tag_filter);
            if (filter.storageId != null && filter.storageId > 0) {
                new AlertDialog.Builder(this)
                        .setTitle(R.string.delete_title)
                        .setNegativeButton(R.string.delete_no, null)
                        .setPositiveButton(R.string.delete_yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dataManager.deleteFilter(filter.storageId);
                                refreshList();
                            }
                        })
                        .show();
            }
        }
        return true;
    }

    private void refreshList() {
        adapter.clear();
        adapter.addAll(dataManager.getFilters());
        adapter.notifyDataSetChanged();
    }
}