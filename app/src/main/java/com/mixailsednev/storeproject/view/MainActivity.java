package com.mixailsednev.storeproject.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import com.mixailsednev.storeproject.view.company.CompanyActivity;
import com.mixailsednev.storeproject.view.company.CompanyFragment;
import com.mixailsednev.storeproject.view.company.list.CompaniesFragment;
import com.mixailsednev.storeproject.view.company.list.CompaniesRecyclerViewAdapter;
import com.mixailsednev.storeproject.view.messages.chat.ChatActivity;
import com.mixailsednev.storeproject.view.messages.chat.ChatFragment;
import com.mixailsednev.storeproject.view.messages.userChat.UserChatsFragment;
import com.mixailsednev.storeproject.view.product.details.ProductDetailFragment;
import com.mixailsednev.storeproject.view.product.edit.ProductEditFragment;
import com.mixailsednev.storeproject.view.product.list.ProductListFragment;
import com.mixailsednev.storeproject.view.utils.GlideUtil;
import com.mixailsednev.storeproject.view.welcome.WelcomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements
        ProductListFragment.ProductSelectedListener,
        Toolbar.OnMenuItemClickListener,
        UserChatsFragment.UserChatSelectedListener,
        NavigationView.OnNavigationItemSelectedListener,
        CompaniesRecyclerViewAdapter.CompanySelectedListener
{

    private boolean twoPane;
    //TODO move to model view? / presenter
    @NonNull
    private String selectedProductId;
    private ActionBarDrawerToggle drawerToggle;

    @BindView(R.id.drawer_layout)
    protected DrawerLayout drawerlayout;

    @BindView(R.id.navigation_view)
    protected NavigationView navigationView;

    @Nullable
    @BindView(R.id.detail_toolbar)
    protected Toolbar detailsToolbar;

    @BindView(R.id.main_toolbar)
    protected Toolbar mainToolbar;

    @BindView(R.id.app_bar)
    protected View appBar;

    @BindView(R.id.fab)
    protected FloatingActionButton floatingActionButton;

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

        if (findViewById(R.id.details_container) != null) {
            twoPane = true;
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_container, CompaniesFragment.newInstance())
                    .commit();
        }

        drawerToggle = new ActionBarDrawerToggle(this, drawerlayout, mainToolbar,
                R.string.open_navigation_drawer, R.string.close_navigation_drawer) {
        };
        drawerlayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        if (detailsToolbar != null) {
            detailsToolbar.setOnMenuItemClickListener(this);
        }

        navigationView.setNavigationItemSelectedListener(this);
        updateAppBar();
        updateUserInfo();
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
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.sign_out:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, WelcomeActivity.class));
                return true;
            case R.id.messages:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, UserChatsFragment.newInstance())
                        .commit();
                drawerlayout.closeDrawers();
                updateAppBar();
                floatingActionButton.show();
                return true;
            case R.id.companies:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, CompaniesFragment.newInstance())
                        .commit();
                drawerlayout.closeDrawers();
                updateAppBar();
                floatingActionButton.hide();
                return true;
            case R.id.barbershop:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_container, CompanyFragment.newInstance("FAHAH"), CompanyFragment.TAG)
                        .commit();
                drawerlayout.closeDrawers();
                updateAppBar();
                floatingActionButton.hide();
            default:
                menuItem.setChecked(true);
                drawerlayout.closeDrawers();
                floatingActionButton.hide();
                return true;
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

    @Override
    public void userChatSelected(@NonNull String userChatID) {
        if (twoPane) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.details_container, ProductDetailFragment.newInstance(userChatID))
                    .commit();
        } else {
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra(ChatFragment.ARG_CHAT_ID, userChatID);

            this.startActivity(intent);
        }
    }

    @Override
    public void companySelected(@NonNull String companyId) {
        if (twoPane) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.details_container, CompanyFragment.newInstance(companyId))
                    .commit();
        } else {
            Intent intent = new Intent(this, CompanyActivity.class);
            intent.putExtra(CompanyFragment.ARG_COMPANY_ID, companyId);

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

        updateDetailsToolbar();
    }

    private void editProductComplete() {
        if (getProductEditFragment() != null) {
            getProductEditFragment().editProductComplete();
        }

        getSupportFragmentManager().popBackStack();
        updateDetailsToolbar();
    }

    private void updateDetailsToolbar() {
        getSupportFragmentManager().executePendingTransactions();

        int menuRes = inEditMode() ? R.menu.edit_menu_menu : R.menu.details_menu;

        if (detailsToolbar != null) {
            detailsToolbar.getMenu().clear();
            detailsToolbar.inflateMenu(menuRes);
        }
    }

    private void updateAppBar() {
        getSupportFragmentManager().executePendingTransactions();

        mainToolbar.getMenu().clear();
        mainToolbar.inflateMenu(R.menu.main_menu);
        mainToolbar.setTitle(getString(R.string.products));
        mainToolbar.setNavigationIcon(R.drawable.ic_menu);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appBar.setElevation(hasTabs() ? 0 : 4);
        }

        updateDetailsToolbar();
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

    private boolean hasTabs() {
        return getSupportFragmentManager().findFragmentByTag(CompanyFragment.TAG) != null;
    }

    private boolean inEditMode() {
        return getSupportFragmentManager().findFragmentByTag(ProductEditFragment.TAG) != null;
    }

    @Nullable
    private ProductEditFragment getProductEditFragment() {
        return (ProductEditFragment) getSupportFragmentManager().findFragmentByTag(ProductEditFragment.TAG);
    }
}
