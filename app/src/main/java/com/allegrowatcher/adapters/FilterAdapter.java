package com.allegrowatcher.adapters;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.allegrowatcher.R;
import com.allegrowatcher.controllers.AllegroController;
import com.allegrowatcher.databinding.AdapterFilterItemBinding;
import com.allegrowatcher.db.DataManager;
import com.allegrowatcher.model.Filter;
import com.allegrowatcher.model.Item;
import com.allegrowatcher.model.Summary;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by daba on 2016-08-17.
 */

public class FilterAdapter extends ArrayAdapter<Filter> {

    private static final int LAYOUT_RESOURCE_ID = R.layout.adapter_filter_item;
    private LayoutInflater inflater;
    private DataManager dataManager;

    public FilterAdapter(Context context, List<Filter> objects) {
        super(context, LAYOUT_RESOURCE_ID, objects);
        inflater = LayoutInflater.from(context);
        dataManager = new DataManager();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdapterFilterItemBinding binding = (convertView == null)
                ? (AdapterFilterItemBinding)DataBindingUtil.inflate(inflater, LAYOUT_RESOURCE_ID, parent, false)
                : (AdapterFilterItemBinding)DataBindingUtil.getBinding(convertView);
        binding.setFilter(getItem(position));
        binding.setHandlers(this);
        return binding.getRoot();
    }

    @BindingAdapter("bind:filter")
    public static void loadSummary(final TextView view, Filter filter) {
        AllegroController
                .getInstance()
                .loadSummary(view.getContext(), filter)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Summary>() {
                    @Override
                    public void call(Summary summary) {
                        invalidateText(view, summary);
                        ((LinearLayout) view.getParent()).setTag(summary.newIitems);
                    }
                });
    }

    public static void invalidateText(TextView view, Summary summary) {
        view.setTypeface(summary.newItemsCount > 0 ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
        view.setText(summary.newItemsCount + "/" + summary.itemsCount);
    }

    public void onItemClick(View view) {
        if (view.getTag() != null) {
            List<Item> items = (List<Item>) view.getTag();
            for (Item item : items) {
                dataManager.addAllegroId(view.getContext(), item.id);
            }

            // TODO: show list with new items
            Toast.makeText(view.getContext(), "Marked " + items.size() + " items", Toast.LENGTH_SHORT).show();
        }
    }
}