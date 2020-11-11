package com.example.outlet.ui.slideshow;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

public class AdidasFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {

    private AdidasViewModel slideshowViewModel;
    private RecyclerView recyclerView;
    private OutletAdapter adapter;
    private Button btnMen, btnWomen;
    private TextView tvGender;
    private boolean flag;
    private ArrayList<Product> productList;
    private ProgressBar progressBar;

    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(AdidasViewModel.class);
        View root = inflater.inflate(R.layout.fragment, container, false);
        btnMen = root.findViewById(R.id.btnMale);
        btnWomen = root.findViewById(R.id.btnFemale);
        btnWomen.setOnClickListener(this);
        btnWomen.setOnTouchListener(this);
        btnMen.setOnTouchListener(this);
        btnMen.setOnClickListener(this);
        tvGender = root.findViewById(R.id.tvGender);
        flag = true;
        progressBar = root.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        recyclerView = root.findViewById(R.id.recycle);
        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        return root;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFemale: {
                productList = slideshowViewModel.getProductList("https://www.adidas.ru/api/plp/content-engine?query=zhenshchiny-outlet");
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
                productList = slideshowViewModel.getProductList("https://www.adidas.ru/api/plp/content-engine?query=muzhchiny-outlet");
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