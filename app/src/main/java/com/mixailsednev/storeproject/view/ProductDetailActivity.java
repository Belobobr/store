package com.mixailsednev.storeproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.mixailsednev.storeproject.R;
import com.mixailsednev.storeproject.view.product.details.ProductDetailFragment;
import com.mixailsednev.storeproject.view.product.edit.ProductEditFragment;

public class ProductDetailActivity extends AppCompatActivity
        implements Toolbar.OnMenuItemClickListener {

    private Toolbar detailsToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        if (savedInstanceState == null) {
            if (getProductId() == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.details_container, ProductEditFragment.newInstance(null), ProductEditFragment.TAG)
                        .commit();
            } else {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.details_container, ProductDetailFragment.newInstance(getProductId()))
                        .commit();
            }
        }

        detailsToolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        detailsToolbar.setTitle(getString(R.string.products));
        detailsToolbar.setNavigationIcon(R.drawable.back);
        detailsToolbar.setOnMenuItemClickListener(this);
        detailsToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                    getSupportFragmentManager().popBackStack();
                    updateDetailsMenu();
                } else {
                    NavUtils.navigateUpTo(ProductDetailActivity.this, new Intent(ProductDetailActivity.this, MainActivity.class));
                }
            }
        });

        updateDetailsMenu();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                editProduct();
                break;
            case R.id.complete:
                editProductComplete();
                break;
        }
        return false;
    }

    @Nullable
    private Long getProductId() {
        return getIntent().getExtras() == null ? null
                : getIntent().getExtras().getLong(ProductDetailFragment.ARG_PRODUCT_ID);
    }

    private void editProduct() {
        ProductEditFragment fragment = ProductEditFragment.newInstance(getProductId());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.details_container, fragment, ProductEditFragment.TAG)
                .addToBackStack(ProductEditFragment.TAG)
                .commit();

        updateDetailsMenu();
    }

    private void editProductComplete() {
        if (getProductId() == null) {
            NavUtils.navigateUpTo(ProductDetailActivity.this, new Intent(ProductDetailActivity.this, MainActivity.class));
        } else {
            getSupportFragmentManager().popBackStack();

            updateDetailsMenu();
        }
    }

    private void updateDetailsMenu() {
        getSupportFragmentManager().executePendingTransactions();
        int menuRes = inEditMode() ? R.menu.edit_menu_menu : R.menu.details_menu;

        if (detailsToolbar != null) {
            detailsToolbar.getMenu().clear();
            detailsToolbar.inflateMenu(menuRes);
        }
    }

    private boolean inEditMode() {
        return getSupportFragmentManager().findFragmentByTag(ProductEditFragment.TAG) != null;
    }

}
