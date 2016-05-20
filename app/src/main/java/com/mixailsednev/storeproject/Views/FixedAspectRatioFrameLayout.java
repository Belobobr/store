package com.mixailsednev.storeproject.Views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.mixailsednev.storeproject.R;

public class FixedAspectRatioFrameLayout extends FrameLayout {
    private int aspectRationWidth;
    private int aspectRationHeight;

    public FixedAspectRatioFrameLayout(Context context) {
        super(context);
    }

    public FixedAspectRatioFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }

    public FixedAspectRatioFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FixedAspectRatioFrameLayout);

        aspectRationWidth = a.getInt(R.styleable.FixedAspectRatioFrameLayout_aspectRatioWidth, 4);
        aspectRationHeight = a.getInt(R.styleable.FixedAspectRatioFrameLayout_aspectRatioHeight, 3);

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int originalWidth = MeasureSpec.getSize(widthMeasureSpec);

        int calculatedHeight = originalWidth * aspectRationHeight / aspectRationWidth;

        int finalWidth, finalHeight;
        finalWidth = originalWidth;
        finalHeight = calculatedHeight;

        super.onMeasure(
                MeasureSpec.makeMeasureSpec(finalWidth, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(finalHeight, MeasureSpec.EXACTLY));
    }
}