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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.outlet.R;
import com.example.outlet.adapters.OutletAdapter;
import com.example.outlet.enums.CategoriesEnum;
import com.example.outlet.models.Product;
import com.example.outlet.ui.viewModels.UniverseViewModel;

import java.util.ArrayList;

public class NewYorkerFragment extends Fragment implements View.OnClickListener, View.OnTouchListener, TextView.OnEditorActionListener {


    private UniverseViewModel newYorkerViewModel;

    private RecyclerView recyclerView;
    private OutletAdapter adapter;
    private Button btnMen, btnWomen, btnFilter, btnClear;
    private ArrayList<Product> productList;
    private ArrayList<Product> filterProductList;
    private TextView tvGender;
    private boolean genderFlag;
    private ProgressBar progressBar;
    private ImageView logo;
    private EditText etSearch;
    private ImageView logo, filter;
    private LinearLayout filterField;
    private CheckBox cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10;
    private ArrayList<CheckBox> categories;
    private CategoriesEnum categoriesEnum = new CategoriesEnum();
    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newYorkerViewModel =
                new ViewModelProvider(this).get(UniverseViewModel.class);
        View root = inflater.inflate(R.layout.fragment, container, false);
        initCheckBoxes(root);
        initButtons(root);
        logo = root.findViewById(R.id.search_bar_hint_icon);
        etSearch = root.findViewById(R.id.search_bar_edit_text);
        etSearch.setOnEditorActionListener(this);
        logo.setImageResource(R.drawable.ic_nyer_white);
        filterField = root.findViewById(R.id.filterField);
        tvGender = root.findViewById(R.id.tvGender);
        progressBar = root.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView = root.findViewById(R.id.recycle);
        GridLayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        setClearActive();
        genderFlag = true;
        return root;
    }
    //Initials methods
    private void initCheckBoxes(View root){
        categories = new ArrayList<>();
        cb1 = root.findViewById(R.id.checkBox1);
        categories.add(cb1);
        cb1.setOnClickListener(this);
        cb2 = root.findViewById(R.id.checkBox2);
        categories.add(cb2);
        cb2.setOnClickListener(this);
        cb3 = root.findViewById(R.id.checkBox3);
        categories.add(cb3);
        cb3.setOnClickListener(this);
        cb4 = root.findViewById(R.id.checkBox4);
        categories.add(cb4);
        cb4.setOnClickListener(this);
        cb5 = root.findViewById(R.id.checkBox5);
        categories.add(cb5);
        cb5.setOnClickListener(this);
        cb6 = root.findViewById(R.id.checkBox6);
        categories.add(cb6);
        cb6.setOnClickListener(this);
        cb7 = root.findViewById(R.id.checkBox7);
        categories.add(cb7);
        cb7.setOnClickListener(this);
        cb8 = root.findViewById(R.id.checkBox8);
        categories.add(cb8);
        cb8.setOnClickListener(this);
        cb9 = root.findViewById(R.id.checkBox9);
        categories.add(cb9);
        cb9.setOnClickListener(this);
        cb10 = root.findViewById(R.id.checkBox10);
        categories.add(cb10);
        cb10.setOnClickListener(this);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initButtons(View root){
        btnMen = root.findViewById(R.id.btnMale);
        btnClear = root.findViewById(R.id.clear_button);
        btnWomen = root.findViewById(R.id.btnFemale);
        btnFilter = root.findViewById(R.id.submitFilter);
        filter = root.findViewById(R.id.filter);
        filter.setOnClickListener(this);
        btnWomen.setOnClickListener(this);
        btnMen.setOnClickListener(this);
        btnWomen.setOnTouchListener(this);
        btnFilter.setOnClickListener(this);
        btnMen.setOnTouchListener(this);
        btnClear.setOnClickListener(this);
        int btnWidth = root.getWidth()/2;
        btnMen.setWidth(btnWidth);
        btnWomen.setWidth(btnWidth);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnFemale: {
                productList = newYorkerViewModel.getProductList("1","2");
                for(int i = 0; i < categories.size(); i++){
                    categories.get(i).setChecked(false);
                }
                btnClear.setVisibility(View.INVISIBLE);
                btnWomen.setClickable(false);
                btnWomen.setBackgroundColor(Color.WHITE);
                btnWomen.setTextColor(Color.LTGRAY);
                btnMen.setBackgroundColor(Color.parseColor("#333333"));
                btnMen.setTextColor(Color.WHITE);
                btnMen.setClickable(true);
                adapter = new OutletAdapter(productList);
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);
                tvGender.setVisibility(View.INVISIBLE);
                filterField.setVisibility(View.GONE);
                break;
            }
            case R.id.btnMale: {
                for(int i = 0; i < categories.size(); i++){
                    categories.get(i).setChecked(false);
                }
                btnClear.setVisibility(View.INVISIBLE);
                productList = newYorkerViewModel.getProductList("1","1");
                btnWomen.setClickable(true);
                btnWomen.setBackgroundColor(Color.parseColor("#333333"));
                btnWomen.setTextColor(Color.WHITE);
                btnMen.setBackgroundColor(Color.WHITE);
                btnMen.setTextColor(Color.LTGRAY);
                btnMen.setClickable(false);
                adapter = new OutletAdapter(productList);
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);
                tvGender.setVisibility(View.INVISIBLE);
                filterField.setVisibility(View.GONE);
                break;
            }
            case R.id.filter:{
                if(filterField.getVisibility() == View.GONE){
                    filterField.setVisibility(View.VISIBLE);
                }
                else {
                    filterField.setVisibility(View.GONE);
                }
                break;
            }
            case R.id.checkBox1:
            case R.id.checkBox2:
            case R.id.checkBox3:
            case R.id.checkBox4:
            case R.id.checkBox5:
            case R.id.checkBox6:
            case R.id.checkBox7:
            case R.id.checkBox8:
            case R.id.checkBox9:
            case R.id.checkBox10: {
                setClearActive();
                break;
            }
            case R.id.clear_button:{
                for(int i = 0; i < categories.size(); i++){
                    categories.get(i).setChecked(false);
                }
                btnClear.setVisibility(View.INVISIBLE);
                filterField.setVisibility(View.GONE);
                adapter = new OutletAdapter(productList);
                recyclerView.setAdapter(adapter);
                break;
            }
            case R.id.submitFilter:{
                setFilterProductList();
                filterField.setVisibility(View.GONE);
                break;
            }
        }
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void setFilterProductList(){
        filterProductList = new ArrayList<>();
        ArrayList<String> filterCategories = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++){
            CheckBox cb = categories.get(i);
            if(cb.isChecked()){
                filterCategories.addAll(categoriesEnum.getCategories(cb.getText().toString()));
            }
        }
        if(filterCategories.size() == 0){
            adapter = new OutletAdapter(productList);
            recyclerView.setAdapter(adapter);
            return;
        }
        if(productList == null){
            for(int i = 0; i < categories.size(); i++){
                categories.get(i).setChecked(false);
            }
            btnClear.setVisibility(View.INVISIBLE);
            tvGender.setText("Сначала выберите для кого хотите подобрать одежду");
            return;
        }
        for (int i = 0; i< productList.size();i++){
            Product product = productList.get(i);
            if(filterCategories.contains(product.getCategory())){
                filterProductList.add(product);
            }
        }
        if(filterProductList.size() == 0){
            recyclerView.setVisibility(View.INVISIBLE);
            tvGender.setText("По вашему запросу ничего не найдено.Попробуйте изменить условия фильтра.");
            tvGender.setVisibility(View.VISIBLE);
            return;
        }
        adapter = new OutletAdapter(filterProductList);
        recyclerView.setAdapter(adapter);
        tvGender.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
        etSearch.setText(null);
        tvGender.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.isClickable()) {
            if (genderFlag) {
                tvGender.setVisibility(View.INVISIBLE);
                genderFlag = false;
            }
            recyclerView.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }
        return false;
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            if(productList == null){
                tvGender.setText("Необходимо выбрать пол!)");
                return false;
            }
            String text = String.valueOf(etSearch.getText()).trim();
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
    private void setClearActive(){
        for (int i = 0; i < categories.size(); i++){
            CheckBox cb = categories.get(i);
            if(cb.isChecked()){
                btnClear.setVisibility(View.VISIBLE);
                return;
            }
        }
        btnClear.setVisibility(View.INVISIBLE);

    }
}
