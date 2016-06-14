package com.mixailsednev.storeproject.model.product;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mixailsednev.storeproject.model.firebase.FirebaseUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseProductRepository extends ProductRepository {

    public static String TAG = FirebaseProductRepository.class.getSimpleName();

    private static ProductRepository instance = new FirebaseProductRepository();

    public static ProductRepository getInstance() {
        return instance;
    }

    private ValueEventListener productEventListener;

    @NonNull
    private List<Product> products;

    public FirebaseProductRepository() {
        this.products = new ArrayList<>();
    }

    //TODO если кто то наблюдает за репозитроием, то ему необходимо наблюдать за состоянием сервера
    @Override
    public void addListener(DataChangeListener dataChangeListener) {
        super.addListener(dataChangeListener);
        if (getListenersCount() == 1) {
            productEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    final List<Product> products = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Product product = snapshot.getValue(Product.class);
                        product.setId(snapshot.getKey());
                        products.add(product);
                    }
                    FirebaseProductRepository.this.products = products;
                    notifyDataChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e(TAG, databaseError.getMessage());
                }
            };
            FirebaseUtils.getProductsRef().addValueEventListener(productEventListener);
        }

    }

    @Override
    public void removeListener(DataChangeListener dataDataChangeListener) {
        super.removeListener(dataDataChangeListener);
        if (getListenersCount() <= 0) {
            FirebaseUtils.getProductsRef().removeEventListener(productEventListener);
        }
    }

    @Override
    public void removeProduct(@NonNull Product product) {
        if (product.getId() != null) {
            FirebaseUtils.getProductsRef().child(product.getId()).removeValue((databaseError, databaseReference) -> {
                if (databaseError != null) {
                    Log.e(TAG, "Error remove product: " + databaseError.getMessage());
                }
            });
        }
    }

    @Override
    public void addProduct(@NonNull Product product) {
        FirebaseUtils.getProductsRef().push().setValue(product, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                Log.e(TAG, "Error add product: " + databaseError.getMessage());
            }
        });
    }

    @Override
    public void updateProduct(@NonNull Product product) {
        Map<String, Object> updatedUserData = new HashMap<>();
        updatedUserData.put(
                FirebaseUtils.getProductsPath() + product.getId(),
                new ObjectMapper().convertValue(product, Map.class)
        );
        FirebaseUtils.getBaseRef().updateChildren(updatedUserData, (databaseError, databaseReference) -> {
            if (databaseError != null) {
                Log.e(TAG, "Error add product: " + databaseError.getMessage());
            }
        });

    }

    @NonNull
    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Nullable
    @Override
    public Product getProduct(@NonNull String productId) {
        for (Product product : products) {
            if (productId.equals(product.getId())) {
                return product;
            }
        }
        return null;
    }
}
