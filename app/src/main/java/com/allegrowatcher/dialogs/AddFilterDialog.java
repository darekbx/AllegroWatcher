package com.allegrowatcher.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.allegrowatcher.R;
import com.allegrowatcher.model.Category;
import com.allegrowatcher.model.Filter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by daba on 2016-08-19.
 */

public class AddFilterDialog extends Dialog {

    public interface Listener {
        void newFilter(Filter filter);
    }

    private Listener listener;

    @BindView(R.id.dialog_keyword)
    TextView keyword;

    @BindView(R.id.dialog_category_id)
    TextView categoryId;

    @BindView(R.id.dialog_category_name)
    TextView categoryName;

    @BindView(R.id.dialog_price_min)
    TextView priceMin;

    @BindView(R.id.dialog_price_max)
    TextView priceMax;

    public AddFilterDialog(Context context, Listener listener) {
        super(context);

        this.listener = listener;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_filter);
        setCanceledOnTouchOutside(true);

        ButterKnife.bind(this);
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

    public Filter createFilter() {
        if (hasCategory() && !hasKeyword()) {
            return new Filter(new Category(getCategoryId(), getCategoryName()), getPriceMin(), getPriceMax());
        } else if (hasCategory()) {
            return new Filter(getKeyword(), new Category(getCategoryId(), getCategoryName()));
        } else {
            return new Filter(getKeyword());
        }
    }

    private boolean hasKeyword() {
        return !TextUtils.isEmpty(getKeyword());
    }

    private boolean hasCategory() {
        return getCategoryId() != null && !TextUtils.isEmpty(getCategoryName());
    }

    private String getKeyword() {
        return keyword.getText().toString();
    }

    private Integer getCategoryId() {
        try {
            return Integer.parseInt(categoryId.getText().toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String getCategoryName() {
        return categoryName.getText().toString();
    }

    private Integer getPriceMin() {
        try {
            return Integer.parseInt(priceMin.getText().toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Integer getPriceMax() {
        try {
            return Integer.parseInt(priceMax.getText().toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}