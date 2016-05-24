package com.mixailsednev.storeproject.view.product.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mixailsednev.storeproject.R;
import com.mixailsednev.storeproject.model.product.Product;

import java.util.List;

public class ProductRecyclerViewAdapter extends RecyclerView.Adapter<ProductRecyclerViewAdapter.ViewHolder> {

    public interface ProductRemovedListener {
        void productRemoved(@NonNull Product product);
    }

    private List<Product> products;
    private ProductListFragment.ProductSelectedListener productSelectedListener;
    private ProductRemovedListener productRemovedListener;

    public ProductRecyclerViewAdapter(List<Product> products,
                                      ProductListFragment.ProductSelectedListener productSelectedListener,
                                      ProductRemovedListener productRemovedListener) {
        this.products = products;
        this.productSelectedListener = productSelectedListener;
        this.productRemovedListener = productRemovedListener;
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

        holder.view.setOnClickListener((view) -> {
            productSelectedListener.productSelected(holder.product.getId());
        });
        holder.popupMenuImageView.setOnClickListener((v) -> {
            final PopupMenu popupMenu = new PopupMenu(v.getContext(), v, Gravity.RIGHT);
            final Menu menu = popupMenu.getMenu();

            popupMenu.getMenuInflater().inflate(R.menu.menu_item_product, menu);
            popupMenu.setOnMenuItemClickListener((item) -> {
                productRemovedListener.productRemoved(holder.product);
                return true;
            });

            popupMenu.show();
        });

    }

    public void setProducts(@NonNull List<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView nameTextView;
        public final ImageView popupMenuImageView;
        public Product product;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            nameTextView = (TextView) view.findViewById(R.id.product_name);
            popupMenuImageView = (ImageView) view.findViewById(R.id.product_popup_menu);
        }

        @Override
        public String toString() {
            return super.toString() + " '";
        }
    }
}