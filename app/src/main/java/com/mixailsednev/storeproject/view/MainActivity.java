package com.mixailsednev.storeproject.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mixailsednev.storeproject.R;
import com.mixailsednev.storeproject.view.product.details.ProductDetailFragment;
import com.mixailsednev.storeproject.view.product.edit.ProductEditFragment;
import com.mixailsednev.storeproject.view.product.list.ProductListFragment;
import com.mixailsednev.storeproject.view.utils.GlideUtil;
import com.mixailsednev.storeproject.view.welcome.WelcomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements ProductListFragment.ProductSelectedListener, Toolbar.OnMenuItemClickListener {

    private boolean twoPane;
    //TODO move to model view? / presenter
    @NonNull
    private String selectedProductId;
    private ActionBarDrawerToggle drawerToggle;

    @BindView(R.id.navigation_view)
    protected NavigationView navigationView;

    @Nullable
    @BindView(R.id.detail_toolbar)
    protected Toolbar detailsToolbar;

    protected ImageView userPhotoImageView;

    protected TextView userNameTextView;

    protected TextView userEmailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        userPhotoImageView = ButterKnife.findById(navigationView.getHeaderView(0), R.id.user_photo);
        userNameTextView = ButterKnife.findById(navigationView.getHeaderView(0), R.id.user_name);
        userEmailTextView = ButterKnife.findById(navigationView.getHeaderView(0), R.id.user_email);
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        DrawerLayout drawerlayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (findViewById(R.id.details_container) != null) {
            twoPane = true;
        }

        if (savedInstanceState == null) {
            ProductListFragment fragment = ProductListFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_container, fragment)
                    .commit();
        }

        mainToolbar.inflateMenu(R.menu.main_menu);
        mainToolbar.setTitle(getString(R.string.products));
        mainToolbar.setNavigationIcon(R.drawable.ic_menu);

        drawerToggle = new ActionBarDrawerToggle(this, drawerlayout, mainToolbar,
                R.string.open_navigation_drawer, R.string.close_navigation_drawer) {
        };
        drawerlayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        if (detailsToolbar != null) {
            detailsToolbar.setOnMenuItemClickListener(this);
        }
        updateDetailsMenu();
        updateUserInfo();

        navigationView.setNavigationItemSelectedListener((menuItem) -> {
            switch (menuItem.getItemId()) {
                case R.id.sign_out:
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(this, WelcomeActivity.class));
                    return true;
                default:
                    menuItem.setChecked(true);
                    drawerlayout.closeDrawers();
                    return true;
            }

        });
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
        if (getProductEditFragment() != null) {
            getProductEditFragment().editProductComplete();
        }

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

    private void updateUserInfo() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            userNameTextView.setText(name);
            userEmailTextView.setText(email);

            if (photoUrl != null) {
                GlideUtil.loadProfileIcon(photoUrl.toString(), userPhotoImageView);
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
