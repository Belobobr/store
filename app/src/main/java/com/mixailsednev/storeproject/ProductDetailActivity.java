package com.mixailsednev.storeproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class ProductDetailActivity extends AppCompatActivity
    implements Toolbar.OnMenuItemClickListener
{

    private Toolbar detailsToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.details_container, ProductDetailFragment.newInstance(getProductId()))
                    .commit();
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
                    updateDetailsMenu(R.menu.details_menu);
                } else {
                    NavUtils.navigateUpTo(ProductDetailActivity.this, new Intent(ProductDetailActivity.this, MainActivity.class));
                }
            }
        });
        updateDetailsMenu(inEditMode() ? R.menu.edit_menu_menu : R.menu.details_menu);
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

    @NonNull
    private String getProductId() {
        return getIntent().getExtras().getString(ProductDetailFragment.ARG_PRODUCT_ID, "");
    }

    private void editProduct() {
        ProductEditFragment fragment = ProductEditFragment.newInstance(getProductId());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.details_container, fragment, ProductEditFragment.TAG)
                .addToBackStack(ProductEditFragment.TAG)
                .commit();

        updateDetailsMenu(R.menu.edit_menu_menu);
    }

    private void editProductComplete() {
        getSupportFragmentManager().popBackStack();

        updateDetailsMenu(R.menu.details_menu);
    }

    private void updateDetailsMenu(@MenuRes int menu) {
        if (detailsToolbar != null) {
            detailsToolbar.getMenu().clear();
            detailsToolbar.inflateMenu(menu);
        }
    }

    private boolean inEditMode() {
        return getSupportFragmentManager().findFragmentByTag(ProductEditFragment.TAG) != null;
    }

}
