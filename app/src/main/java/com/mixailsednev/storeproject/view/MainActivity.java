package com.mixailsednev.storeproject.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.mixailsednev.storeproject.R;

public class MainActivity extends AppCompatActivity
        implements ProductListFragment.ProductSelectedListener, Toolbar.OnMenuItemClickListener {
    private boolean twoPane;
    //TODO move to model view? / presenter
    @NonNull
    private String selectedProductId;

    protected DrawerLayout drawerlayout;
    private ActionBarDrawerToggle drawerToggle;
    @Nullable
    private Toolbar detailsToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.details_container) != null) {
            twoPane = true;
        }

        if (savedInstanceState == null) {
            ProductListFragment fragment = ProductListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_container, fragment)
                    .commit();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (twoPane) {
                    newProduct();
                } else {
                    Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
                    MainActivity.this.startActivity(intent);
                }
            }
        });

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mainToolbar.inflateMenu(R.menu.main_menu);
        mainToolbar.setTitle(getString(R.string.products));
        mainToolbar.setNavigationIcon(R.drawable.ic_menu);

        drawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerlayout, mainToolbar,
                R.string.open_navigation_drawer, R.string.close_navigation_drawer) {
        };

        drawerlayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        detailsToolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        if (detailsToolbar != null) {
            detailsToolbar.setOnMenuItemClickListener(this);
        }
        updateDetailsMenu();
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
    public void onConfigurationChanged(final Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void productSelected(@NonNull String productId) {
        this.selectedProductId = productId;

        if (twoPane) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.details_container, ProductDetailFragment.newInstance(productId))
                    .commit();
        } else {
            Intent intent = new Intent(this, ProductDetailActivity.class);
            intent.putExtra(ProductDetailFragment.ARG_PRODUCT_ID, productId);

            this.startActivity(intent);
        }
    }

    private void newProduct() {
        editProduct(null);
    }

    private void editProduct(@Nullable String productId) {
        ProductEditFragment fragment = ProductEditFragment.newInstance(productId);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.details_container, fragment, ProductEditFragment.TAG)
                .addToBackStack(null)
                .commit();

        updateDetailsMenu();
    }

    private void editProductComplete() {
        getSupportFragmentManager().popBackStack();

        updateDetailsMenu();
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
