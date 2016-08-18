package com.allegrowatcher.adapters;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.allegrowatcher.R;
import com.allegrowatcher.databinding.AdapterItemBinding;
import com.allegrowatcher.model.Item;

import java.util.List;

/**
 * Created by daba on 2016-08-18.
 */

public class ItemAdapter extends ArrayAdapter<Item> {

    private static final int LAYOUT_RESOURCE_ID = R.layout.adapter_item;
    private LayoutInflater inflater;

    public ItemAdapter(Context context, List<Item> objects) {
        super(context, LAYOUT_RESOURCE_ID, objects);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdapterItemBinding binding = (convertView == null)
                ? (AdapterItemBinding) DataBindingUtil.inflate(inflater, LAYOUT_RESOURCE_ID, parent, false)
                : (AdapterItemBinding)DataBindingUtil.getBinding(convertView);
        binding.setItem(getItem(position));
        return binding.getRoot();
    }

    @BindingAdapter("bind:imageUrl")
    public static void imageUrl(final ImageView view, String url) {

    }
}
