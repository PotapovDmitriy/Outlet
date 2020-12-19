package com.example.outlet.ui.gallery;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.outlet.R;
import com.example.outlet.adapters.OutletAdapter;
import com.example.outlet.models.Product;

import java.util.ArrayList;

public class NewYorkerFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {

    private NewYorkerViewModel galleryViewModel;
    private RecyclerView recyclerView;
    private OutletAdapter adapter;
    private Button btnMen, btnWomen;
    private ArrayList<Product> productList;
    private TextView tvGender;
    private boolean flag;
    private ProgressBar progressBar;
    private ImageView logo;

    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(NewYorkerViewModel.class);
        View root = inflater.inflate(R.layout.fragment, container, false);
        btnMen = root.findViewById(R.id.btnMale);
        btnWomen = root.findViewById(R.id.btnFemale);
        logo = root.findViewById(R.id.search_bar_hint_icon);
        btnWomen.setOnClickListener(this);
        btnMen.setOnClickListener(this);
        btnWomen.setOnTouchListener(this);
        btnMen.setOnTouchListener(this);
        int btnWidth = root.getWidth()/2;
        btnMen.setWidth(btnWidth);
        btnWomen.setWidth(btnWidth);
        tvGender = root.findViewById(R.id.tvGender);
        flag = true;
        progressBar = root.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        recyclerView = root.findViewById(R.id.recycle);
        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        logo.setImageResource(R.drawable.ic_nyer_white);
        return root;
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFemale: {
                System.out.println("Click ClickClickClick");
                productList = galleryViewModel.getProductList("https://api.newyorker.de/csp/products/public/query?limit=250&offset=0&filters[country]=ru&filters[gender]=FEMALE&filters[brand]=&filters[color]=&filters[web_category]=&filters[likes]=&filters[collections]=&filters[editorials]=&filters[sale]=true");
                btnWomen.setClickable(false);
                btnWomen.setBackgroundColor(Color.WHITE);
                btnWomen.setTextColor(Color.LTGRAY);
                btnMen.setBackgroundColor(Color.parseColor("#333333"));
                btnMen.setTextColor(Color.WHITE);
                btnMen.setClickable(true);
                adapter = new OutletAdapter(productList);
                recyclerView.setAdapter(adapter);

                break;
            }
            case R.id.btnMale: {
                productList = galleryViewModel.getProductList("https://api.newyorker.de/csp/products/public/query?limit=250&offset=0&filters[country]=ru&filters[gender]=MALE&filters[brand]=&filters[color]=&filters[web_category]=&filters[likes]=&filters[collections]=&filters[editorials]=&filters[sale]=true");
                btnWomen.setClickable(true);
                btnWomen.setBackgroundColor(Color.parseColor("#333333"));
                btnWomen.setTextColor(Color.WHITE);
                btnMen.setBackgroundColor(Color.WHITE);
                btnMen.setTextColor(Color.LTGRAY);
                btnMen.setClickable(false);
                adapter = new OutletAdapter(productList);
                recyclerView.setAdapter(adapter);
                break;
            }
        }
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.isClickable()) {
            if (flag) {
                tvGender.setVisibility(View.INVISIBLE);
                flag = false;
            }
            recyclerView.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }
        return false;
    }
}
