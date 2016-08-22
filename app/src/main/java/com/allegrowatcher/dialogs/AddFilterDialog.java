package com.allegrowatcher.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.allegrowatcher.R;
import com.allegrowatcher.adapters.CategoriesAdapter;
import com.allegrowatcher.controllers.AllegroController;
import com.allegrowatcher.model.Category;
import com.allegrowatcher.model.Filter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by daba on 2016-08-19.
 */

public class AddFilterDialog extends Dialog implements AdapterView.OnItemClickListener {

    public interface Listener {
        void newFilter(Filter filter);
    }

    private Listener listener;

    @BindView(R.id.dialog_keyword)
    TextView keywordText;

    @BindView(R.id.dialog_category)
    AutoCompleteTextView categoryAutoComplete;

    @BindView(R.id.dialog_price_min)
    TextView priceMinText;

    @BindView(R.id.dialog_price_max)
    TextView priceMaxText;

    public AddFilterDialog(Context context, Listener listener) {
        super(context);

        this.listener = listener;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_filter);
        setCanceledOnTouchOutside(true);

        ButterKnife.bind(this);

        initializeCategoriesAutoComplete();
    }

    private void initializeCategoriesAutoComplete() {
        categoryAutoComplete.setThreshold(1);
        categoryAutoComplete.setOnItemClickListener(this);
        loadCategories();
    }

    private void loadCategories() {
        new AllegroController()
                .loadCategoriesAsync(getContext())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<com.allegrowatcher.Category>>() {
                    @Override
                    public void call(List<com.allegrowatcher.Category> categories) {
                        setAutoCompleteData(categories);
                    }
                });
    }

    private void setAutoCompleteData(List<com.allegrowatcher.Category> categories) {
        CategoriesAdapter adapter = new CategoriesAdapter(getContext(), categories);
        categoryAutoComplete.setAdapter(adapter);
    }

    @OnClick(R.id.button_add)
    public void onAddClick(View view) {
        if (listener != null) {
            listener.newFilter(createFilter());
        }
        dismiss();
    }

    @OnClick(R.id.button_cancel)
    public void onCancelClick(View view) {
        dismiss();
    }

    @Override
    public void onDetachedFromWindow() {
        if (categoryAutoComplete != null) {
            categoryAutoComplete.setOnItemClickListener(null);
        }
    }

    public Filter createFilter() {
        if (hasCategory() && !hasKeyword()) {
            return new Filter(getCategory(), getPriceMin(), getPriceMax());
        } else if (hasCategory()) {
            return new Filter(getKeyword(), getCategory());
        } else {
            return new Filter(getKeyword());
        }
    }

    private boolean hasKeyword() {
        return !TextUtils.isEmpty(getKeyword());
    }

    private boolean hasCategory() {
        return getCategory() != null;
    }

    private String getKeyword() {
        return keywordText.getText().toString();
    }


    private Category getCategory() {
        if (categoryAutoComplete.getTag() != null) {
            com.allegrowatcher.Category category = (com.allegrowatcher.Category) categoryAutoComplete.getTag();
            return new Category(category.getCategory_id(), category.getCategory_name());
        } else {
            return null;
        }
    }

    private Integer getPriceMin() {
        try {
            return Integer.parseInt(priceMinText.getText().toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Integer getPriceMax() {
        try {
            return Integer.parseInt(priceMaxText.getText().toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        com.allegrowatcher.Category category = ((CategoriesAdapter) adapterView.getAdapter()).getItem(position);
        categoryAutoComplete.setTag(category);
    }
}