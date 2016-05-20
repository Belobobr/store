package com.mixailsednev.storeproject.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mixailsednev.storeproject.R;

public class ProductParamView extends LinearLayout {

    private ImageView iconImageView;
    private TextView paramTextView;
    private TextView paramValueTextView;

    public ProductParamView(Context context) {
        super(context);
    }

    public ProductParamView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }

    public ProductParamView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ProductParamView);

        Drawable icon = a.getDrawable(R.styleable.ProductParamView_product_icon);
        String param = a.getString(R.styleable.ProductParamView_product_param);

        a.recycle();

        View view = LayoutInflater.from(context).inflate(R.layout.item_product_details, this, true);

        iconImageView = ((ImageView)view.findViewById(R.id.icon));
        paramTextView = ((TextView)view.findViewById(R.id.param));
        paramValueTextView = ((TextView)view.findViewById(R.id.param_value));

        iconImageView.setImageDrawable(icon);
        paramTextView.setText(param);
    }

    public void setParamValue(@Nullable String paramValue) {
        paramValueTextView.setText(paramValue);
    }


}
