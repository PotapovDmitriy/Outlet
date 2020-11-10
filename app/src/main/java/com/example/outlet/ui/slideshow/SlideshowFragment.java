package com.example.outlet.ui.slideshow;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.outlet.R;
import com.example.outlet.adapters.AdidasAdapter;
import com.example.outlet.adapters.NewYorkerAdapter;
import com.example.outlet.models.Product;

import java.util.ArrayList;

public class SlideshowFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {

    private SlideshowViewModel slideshowViewModel;
    private RecyclerView recyclerView;
    private AdidasAdapter adapter;
    private Button btnMen, btnWomen;
    private TextView tvGender;
    private boolean flag;
    private ArrayList<Product> productList;
    private ProgressBar progressBar;

    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        btnMen = root.findViewById(R.id.button2);
        btnWomen = root.findViewById(R.id.button);
        btnWomen.setOnClickListener(this);
        btnWomen.setOnTouchListener(this);
        btnMen.setOnTouchListener(this);
        btnMen.setOnClickListener(this);
        tvGender = root.findViewById(R.id.tvGender);
        flag = true;
        progressBar = root.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        recyclerView = root.findViewById(R.id.recycleAdidas);
        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        return root;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button: {
                productList = slideshowViewModel.getProductList("https://www.adidas.ru/api/plp/content-engine?query=zhenshchiny-outlet");
                btnWomen.setClickable(false);
                btnWomen.setBackgroundColor(Color.GRAY);
                btnMen.setBackgroundColor(Color.GREEN);
                btnMen.setClickable(true);
                adapter = new AdidasAdapter(productList);
                recyclerView.setAdapter(adapter);
                break;
            }
            case R.id.button2: {
                productList = slideshowViewModel.getProductList("https://www.adidas.ru/api/plp/content-engine?query=muzhchiny-outlet");
                btnWomen.setClickable(true);
                btnWomen.setBackgroundColor(Color.GREEN);
                btnMen.setBackgroundColor(Color.GRAY);
                btnMen.setClickable(false);
                adapter = new AdidasAdapter(productList);
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