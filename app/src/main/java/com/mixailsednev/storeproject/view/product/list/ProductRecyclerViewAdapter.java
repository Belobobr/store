package com.mixailsednev.storeproject.view.product.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mixailsednev.storeproject.R;
import com.mixailsednev.storeproject.model.product.Product;

import java.util.List;

public class ProductRecyclerViewAdapter
        extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {

    private final List<Product> products;
    private final ProductListFragment.ProductSelectedListener productSelectedListener;

    public ProductRecyclerViewAdapter(List<Product> products,
                                      ProductListFragment.ProductSelectedListener productSelectedListener) {
        this.products = products;
        this.productSelectedListener = productSelectedListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.product = products.get(position);
        holder.nameTextView.setText(products.get(position).getName());
        holder.costTextView.setText(products.get(position).getCost());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productSelectedListener.productSelected(holder.product.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView nameTextView;
        public final TextView costTextView;
        public Product product;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            nameTextView = (TextView) view.findViewById(R.id.name);
            costTextView = (TextView) view.findViewById(R.id.cost);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + costTextView.getText() + "'";
        }
    }
}