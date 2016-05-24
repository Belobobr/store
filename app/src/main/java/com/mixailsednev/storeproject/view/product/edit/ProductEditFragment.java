package com.mixailsednev.storeproject.view.product.edit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mixailsednev.storeproject.Injection;
import com.mixailsednev.storeproject.R;
import com.mixailsednev.storeproject.model.product.Product;
import com.mixailsednev.storeproject.model.product.ProductBuilder;
import com.mixailsednev.storeproject.view.common.BaseFragment;
import com.mixailsednev.storeproject.view.product.details.ProductDetailFragment;
import com.mixailsednev.storeproject.view.product.edit.ProductEditContract.ProductEditView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductEditFragment extends BaseFragment<ProductEditPresenter> implements ProductEditView {

    public static final String TAG = ProductEditFragment.class.getSimpleName();
    public static final String ARG_PRODUCT_ID = "product_id";

    public static ProductEditFragment newInstance(@Nullable Long productId) {
        ProductEditFragment fragment = new ProductEditFragment();
        Bundle arguments = new Bundle();
        if (productId != null) {
            arguments.putLong(ProductDetailFragment.ARG_PRODUCT_ID, productId);
        }
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    private Long productId;

    @BindView(R.id.name)
    protected EditText nameEditText;
    @BindView(R.id.cost)
    protected EditText costEditText;
    @BindView(R.id.description)
    protected EditText descriptionEditText;

    @Override
    public ProductEditPresenter createPresenter() {
        return new ProductEditPresenter(productId, this,
                Injection.provideProductStore(),
                Injection.provideUpdateProductAction(getContext()),
                Injection.provideCreateProductAction(getContext()));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_PRODUCT_ID)) {
            productId = getArguments().getLong(ARG_PRODUCT_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_edit, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void setProduct(@NonNull Product product) {
        nameEditText.setText(product.getName());
        costEditText.setText(product.getCost());
        descriptionEditText.setText(product.getDescription());
    }

    public void editProductComplete() {
        getPresenter().saveProduct(getProduct());
    }

    @NonNull
    private Product getProduct() {
        return new ProductBuilder()
                .setId(productId)
                .setName(nameEditText.getText().toString())
                .setCost(costEditText.getText().toString())
                .setDescription(descriptionEditText.getText().toString())
                .createProduct();
    }
}
