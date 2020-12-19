package com.example.outlet.ui.gallery;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.outlet.ui.viewModels.UniverseViewModel;

import java.util.ArrayList;

public class NewYorkerFragment extends Fragment implements View.OnClickListener, View.OnTouchListener, TextView.OnEditorActionListener {

    private UniverseViewModel newYorkerViewModel;
    private RecyclerView recyclerView;
    private OutletAdapter adapter;
    private Button btnMen, btnWomen;
    private ArrayList<Product> productList;
    private TextView tvGender;
    private boolean flag;
    private ProgressBar progressBar;
    private ImageView logo;
    private EditText etSearch;

    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newYorkerViewModel =
                new ViewModelProvider(this).get(UniverseViewModel.class);
        View root = inflater.inflate(R.layout.fragment, container, false);
        btnMen = root.findViewById(R.id.btnMale);
        btnWomen = root.findViewById(R.id.btnFemale);
        logo = root.findViewById(R.id.search_bar_hint_icon);
        etSearch = root.findViewById(R.id.search_bar_edit_text);
        etSearch.setOnEditorActionListener(this);
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
                productList = newYorkerViewModel.getProductList("1","2");
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
                productList = newYorkerViewModel.getProductList("1","1");
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

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            String text = String.valueOf(etSearch.getText());
            ArrayList<Product> newProductList = new ArrayList<>();
            for (Product item : productList ){
                if (item.getName().contains(text)){
                    newProductList.add(item);
                }
            }
            if (newProductList.isEmpty()){
                tvGender.setVisibility(View.VISIBLE);
                tvGender.setText("Таких товаров нет");
            }
            else {
                tvGender.setVisibility(View.INVISIBLE);
            }
            adapter = new OutletAdapter(newProductList);
            recyclerView.setAdapter(adapter);
            return true;
        }
        return false;
    }
}
