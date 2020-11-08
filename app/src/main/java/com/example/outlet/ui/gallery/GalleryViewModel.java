package com.example.outlet.ui.gallery;

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

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> mText;


    public GalleryViewModel() {
        ArrayList<Product> productList = getProductList();
        mText = new MutableLiveData<>();
        mText.setValue("Product list have " + productList.size() + " products ");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public ArrayList<Product> getProductList() {
        AsyncRequest asyncRequest = new AsyncRequest();
        ArrayList<Product> productsList = new ArrayList<>();
        try {
            JSONObject jsonObject = asyncRequest.execute().get();
            JSONArray productsJsonArray = jsonObject.getJSONArray("items");
            for (int i = 0; i < productsJsonArray.length(); i++) {
                JSONObject jsProduct = productsJsonArray.getJSONObject(i);
                String id = jsProduct.getString("id");
                String globalId = jsProduct.getString("global_item_id");
                String maintenanceGroup = jsProduct.getString("maintenance_group");
                JSONArray variants = jsProduct.getJSONArray("variants");
                for (int j = 0; j < variants.length(); j++) {
                    JSONObject jsVariant = variants.getJSONObject(j);
                    Boolean isSale = jsVariant.getBoolean("sale");
                    if (!isSale) {
                        continue;
                    }
                    String productId = jsVariant.getString("product_id");
                    int originalPrice = jsVariant.getInt("original_price");
                    int currentPrice = jsVariant.getInt("current_price");
                    String images = jsVariant.getJSONArray("images").getJSONObject(0).getString("key");
                    Product product = new Product(globalId, id, productId, maintenanceGroup, isSale, images, currentPrice, originalPrice);
                    productsList.add(product);
                    j = variants.length();
                }
            }

        } catch (ExecutionException | InterruptedException | JSONException e) {
            e.printStackTrace();
        }
        return productsList;
    }


    private class AsyncRequest extends AsyncTask<String, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(String... strings) {

            System.out.println("Start of async");
            JsonRequestService jsonRequestService = new JsonRequestService();

            return jsonRequestService.readJsonFromUrl("https://api.newyorker.de/csp/products/public/query?limit=250&offset=0&filters[country]=ru&filters[gender]=MALE&filters[brand]=&filters[color]=&filters[web_category]=&filters[likes]=&filters[collections]=&filters[editorials]=&filters[sale]=true");
        }
    }


}