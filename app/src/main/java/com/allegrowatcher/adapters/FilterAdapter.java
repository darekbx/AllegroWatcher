package com.allegrowatcher.adapters;

import android.content.Context;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.allegrowatcher.R;
import com.allegrowatcher.controllers.AllegroController;
import com.allegrowatcher.databinding.AdapterFilterItemBinding;
import com.allegrowatcher.model.Filter;
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

    public FilterAdapter(Context context, List<Filter> objects) {
        super(context, LAYOUT_RESOURCE_ID, objects);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdapterFilterItemBinding binding = (convertView == null)
                ? (AdapterFilterItemBinding) DataBindingUtil.inflate(inflater, LAYOUT_RESOURCE_ID, parent, false)
                : (AdapterFilterItemBinding) DataBindingUtil.getBinding(convertView);
        binding.setFilter(getItem(position));
        binding.setHandlers(this);
        return binding.getRoot();
    }

    @BindingAdapter("bind:filter")
    public static void loadSummary(final TextView view, final Filter filter) {
        AllegroController
                .getInstance()
                .loadSummary(view.getContext(), filter)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Summary>() {
                    @Override
                    public void call(Summary summary) {
                        invalidateText(view, summary);
                        ((FrameLayout) view.getParent()).setTag(R.string.tag_summary, summary);
                        ((FrameLayout) view.getParent()).setTag(R.string.tag_filter, filter);
                    }
                });
    }

    public static void invalidateText(TextView view, Summary summary) {
        view.setTypeface(summary.newItemsCount > 0 ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
        view.setTextColor(summary.newItemsCount > 0 ? Color.RED : Color.BLACK);
        view.setText(summary.newItemsCount + "/" + summary.itemsCount);
    }
}