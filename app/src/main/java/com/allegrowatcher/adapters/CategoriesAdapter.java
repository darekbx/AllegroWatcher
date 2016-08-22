package com.allegrowatcher.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import com.allegrowatcher.Category;
import com.allegrowatcher.R;
import com.allegrowatcher.databinding.AdapterCategoryBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daba on 2016-08-22.
 */

public class CategoriesAdapter extends ArrayAdapter<Category> {

    private static final int LAYOUT_RESOURCE_ID = R.layout.adapter_category;
    private LayoutInflater inflater;
    private List<Category> mItems, mSuggestions, mTempItems;

    public CategoriesAdapter(Context context, List<Category> objects) {
        super(context, LAYOUT_RESOURCE_ID, objects);
        inflater = LayoutInflater.from(context);

        mItems = objects;
        mTempItems = new ArrayList<>(objects);
        mSuggestions = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AdapterCategoryBinding binding = (convertView == null)
                ? (AdapterCategoryBinding) DataBindingUtil.inflate(inflater, LAYOUT_RESOURCE_ID, parent, false)
                : (AdapterCategoryBinding)DataBindingUtil.getBinding(convertView);
        binding.setCategory(mItems.get(position));
        return binding.getRoot();
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }

    Filter nameFilter = new Filter() {

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((Category) resultValue).getCategory_name();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                mSuggestions.clear();
                for (Category entry : mTempItems) {
                    if (entry.getCategory_name().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        mSuggestions.add(entry);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mSuggestions;
                filterResults.count = mSuggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<Category> filterList = (ArrayList<Category>) results.values;
            if (results != null && results.count > 0) {
                clear();
                addAll(filterList);
                notifyDataSetChanged();
            }
        }
    };
}