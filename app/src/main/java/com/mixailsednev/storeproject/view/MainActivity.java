package com.mixailsednev.storeproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.mixailsednev.storeproject.R;
import com.mixailsednev.storeproject.view.product.details.ProductDetailFragment;
import com.mixailsednev.storeproject.view.product.edit.ProductEditFragment;
import com.mixailsednev.storeproject.view.product.list.ProductListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements ProductListFragment.ProductSelectedListener, Toolbar.OnMenuItemClickListener {

    private boolean twoPane;
    //TODO move to model view? / presenter
    @Nullable
    private Long selectedProductId;

    @Nullable
    @BindView(R.id.detail_toolbar)
    protected Toolbar detailsToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (findViewById(R.id.details_container) != null) {
            twoPane = true;
        }

        if (savedInstanceState == null) {
            ProductListFragment fragment = ProductListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_container, fragment)
                    .commit();
        }

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mainToolbar.inflateMenu(R.menu.main_menu);
        mainToolbar.setTitle(getString(R.string.products));

        if (detailsToolbar != null) {
            detailsToolbar.setOnMenuItemClickListener(this);
        }
        updateDetailsMenu();
    }

    @OnClick(R.id.fab)
    public void onCreateProduct(View view) {
        if (twoPane) {
            newProduct();
        } else {
            Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
            MainActivity.this.startActivity(intent);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                editProduct(selectedProductId);
                return true;
            case R.id.complete:
                editProductComplete();
                return true;
        }
        return false;
    }

    @Override
    public void productSelected(@NonNull Long productId) {
        this.selectedProductId = productId;

        if (twoPane) {
            showProduct(productId);
        } else {
            Intent intent = new Intent(this, ProductDetailActivity.class);
            intent.putExtra(ProductDetailFragment.ARG_PRODUCT_ID, productId);

            this.startActivity(intent);
        }
    }

    private void showProduct(@NonNull Long productId) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.details_container, ProductDetailFragment.newInstance(productId))
                .commit();
        updateDetailsMenu();
    }

    private void newProduct() {
        editProduct(null);
    }

    private void editProduct(@Nullable Long productId) {
        ProductEditFragment fragment = ProductEditFragment.newInstance(productId);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.details_container, fragment, ProductEditFragment.TAG)
                .addToBackStack(null).commit();
        updateDetailsMenu();
    }

    private void editProductComplete() {
        if (getProductEditFragment() != null) {
            getProductEditFragment().editProductComplete();
        }
        getSupportFragmentManager().popBackStack();
        updateDetailsMenu();
    }

    private void updateDetailsMenu() {
        getSupportFragmentManager().executePendingTransactions();
        if (detailsToolbar != null) {
            detailsToolbar.getMenu().clear();

            if (inEditMode()) {
                detailsToolbar.inflateMenu(R.menu.edit_menu_menu);
            } else {
                if (selectedProductId != null) {
                    detailsToolbar.inflateMenu(R.menu.details_menu);
                }
            }
        }
    }

    private boolean inEditMode() {
        return getSupportFragmentManager().findFragmentByTag(ProductEditFragment.TAG) != null;
    }

    @Nullable
    private ProductEditFragment getProductEditFragment() {
        return (ProductEditFragment) getSupportFragmentManager().findFragmentByTag(ProductEditFragment.TAG);
    }
}
