package com.example.outlet.ui.viewModels;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.outlet.models.Product;
import com.example.outlet.services.JsonRequestService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class UniverseViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UniverseViewModel() {
    }

    public LiveData<String> getText() {
        return mText;
    }


    public ArrayList<Product> getProductList(String shopID, String sex) {
        AsyncRequest asyncRequest = new AsyncRequest();
        ArrayList<Product> productsList = new ArrayList<>();
        String url = "http://78.29.35.227:3000/items/query?shops%5B0%5D=" + shopID+"&sex[0]="+sex;
        try {

            JSONArray productsJsonArray = asyncRequest.execute(url)
                    .get()
                    .getJSONArray("items");
            for (int i = 0; i < productsJsonArray.length(); i++) {
                JSONObject jsProduct = productsJsonArray.getJSONObject(i);
                Product product = new Product(jsProduct.getString("name"),
                        jsProduct.getString("shop_name"),
                        jsProduct.getInt("sex"),
                        jsProduct.getString("url"),
                        jsProduct.getString("url_image"),
                        jsProduct.getInt("price_sale"),
                        jsProduct.getInt("price_origin"),
                        jsProduct.getString("category_name"));
                productsList.add(product);
            }

        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        return productsList;
    }


    private class AsyncRequest extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... strings) {
            JsonRequestService jsonRequestService = new JsonRequestService();
            return jsonRequestService.readJsonFromUrl(strings[0]);
        }
    }
}