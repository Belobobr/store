package com.mixailsednev.storeproject.view.product.list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mixailsednev.storeproject.Injection;
import com.mixailsednev.storeproject.R;
import com.mixailsednev.storeproject.model.product.Product;
import com.mixailsednev.storeproject.view.common.BaseFragment;
import com.mixailsednev.storeproject.view.product.list.ProductListContract.ProductListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListFragment extends BaseFragment<ProductListPresenter> implements ProductListView,
        ProductRecyclerViewAdapter.ProductRemovedListener {

    public interface ProductSelectedListener {
        void productSelected(@NonNull String productId);
    }

    public static ProductListFragment newInstance() {
        return new ProductListFragment();
    }

    @BindView(R.id.product_list)
    protected RecyclerView recyclerView;
    @BindView(R.id.progress)
    protected View progressLayout;

    private ProductRecyclerViewAdapter productRecyclerViewAdapter;
    private ProductSelectedListener productSelectedListener;

    @Override
    public ProductListPresenter createPresenter() {
        return new ProductListPresenter(this, Injection.provideProductRepository());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_list, container, false);
        ButterKnife.bind(this, rootView);

        productRecyclerViewAdapter = new ProductRecyclerViewAdapter(new ArrayList<>(), productSelectedListener, this);
        recyclerView.setAdapter(productRecyclerViewAdapter);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ProductSelectedListener) {
            productSelectedListener = (ProductSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ProductSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        productSelectedListener = null;
    }

    @Override
    public void setProducts(@NonNull List<Product> products) {
        productRecyclerViewAdapter.setProducts(products);
    }

    @Override
    public void onNewViewStateInstance() {
    }

    @Override
    public void productRemoved(@NonNull Product product) {
        getPresenter().removeProduct(product);
    }
}
