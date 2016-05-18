package com.mixailsednev.storeproject;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity
        implements ProductListFragment.ProductSelectedListener, Toolbar.OnMenuItemClickListener
{
    private boolean mTwoPane;

    protected DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.details_container) != null) {
            mTwoPane = true;
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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        mainToolbar.inflateMenu(R.menu.main_menu);
        mainToolbar.setTitle(getString(R.string.products));
        mainToolbar.setNavigationIcon(R.drawable.ic_menu);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mainToolbar,
                R.string.open_navigation_drawer, R.string.close_navigation_drawer) {
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        Toolbar detailsToolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        detailsToolbar.inflateMenu(R.menu.details_menu);
        detailsToolbar.setOnMenuItemClickListener(this);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public void onConfigurationChanged(final Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void productSelected(@NonNull String productId) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(ProductDetailFragment.ARG_ITEM_ID, productId);
            ProductDetailFragment fragment = new ProductDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.details_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, ProductDetailActivity.class);
            intent.putExtra(ProductDetailFragment.ARG_ITEM_ID, productId);

            this.startActivity(intent);
        }
    }
}
