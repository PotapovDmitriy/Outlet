package com.example.outlet.ui.gallery;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.outlet.R;
import com.example.outlet.adapters.NewYorkerAdapter;
import com.example.outlet.models.Product;

import java.util.ArrayList;

public class GalleryFragment extends Fragment implements View.OnClickListener {

    private GalleryViewModel galleryViewModel;
    private RecyclerView recyclerView;
    private NewYorkerAdapter adapter;
    private Button btnMen, btnWomen;
    private ArrayList<Product> productList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        btnMen = root.findViewById(R.id.button2);
        btnWomen = root.findViewById(R.id.button);
        btnWomen.setBackgroundColor(Color.GRAY);
        btnWomen.setClickable(false);
        btnWomen.setOnClickListener(this);
        btnMen.setOnClickListener(this);
        recyclerView = root.findViewById(R.id.recycleIdNewYorker);
        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        productList = galleryViewModel.getProductList("https://api.newyorker.de/csp/products/public/query?limit=250&offset=0&filters[country]=ru&filters[gender]=FEMALE&filters[brand]=&filters[color]=&filters[web_category]=&filters[likes]=&filters[collections]=&filters[editorials]=&filters[sale]=true");
        adapter = new NewYorkerAdapter(productList);

        recyclerView.setAdapter(adapter);
        return root;
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button: {
                productList = galleryViewModel.getProductList("https://api.newyorker.de/csp/products/public/query?limit=250&offset=0&filters[country]=ru&filters[gender]=FEMALE&filters[brand]=&filters[color]=&filters[web_category]=&filters[likes]=&filters[collections]=&filters[editorials]=&filters[sale]=true");
                btnWomen.setClickable(false);
                btnWomen.setBackgroundColor(Color.GRAY);
                btnMen.setBackgroundColor(Color.GREEN);
                btnMen.setClickable(true);
                adapter = new NewYorkerAdapter(productList);
                recyclerView.setAdapter(adapter);

                break;
            }
            case R.id.button2: {
                productList = galleryViewModel.getProductList("https://api.newyorker.de/csp/products/public/query?limit=250&offset=0&filters[country]=ru&filters[gender]=MALE&filters[brand]=&filters[color]=&filters[web_category]=&filters[likes]=&filters[collections]=&filters[editorials]=&filters[sale]=true");
                btnWomen.setClickable(true);
                btnWomen.setBackgroundColor(Color.GREEN);
                btnMen.setBackgroundColor(Color.GRAY);
                btnMen.setClickable(false);
                adapter = new NewYorkerAdapter(productList);
                recyclerView.setAdapter(adapter);
                break;
            }
        }
    }
}
