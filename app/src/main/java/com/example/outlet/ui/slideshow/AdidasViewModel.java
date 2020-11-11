package com.example.outlet.ui.slideshow;

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

public class AdidasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AdidasViewModel() {
    }

    public LiveData<String> getText() {
        return mText;
    }


    public ArrayList<Product> getProductList(String url) {
        AsyncRequest asyncRequest = new AsyncRequest();
        ArrayList<Product> productsList = new ArrayList<>();
        try {
            JSONObject jsonObject = asyncRequest.execute(url)
                    .get()
                    .getJSONObject("raw")
                    .getJSONObject("itemList");
            JSONArray productsJsonArray = jsonObject.getJSONArray("items");
            for (int i = 0; i < productsJsonArray.length(); i++) {
                AsyncProductRequest asyncProduct = new AsyncProductRequest();
                JSONObject jsProduct = productsJsonArray.getJSONObject(i);
                String id = jsProduct.getString("productId");
                String globalId = "",
                productId =" ";
                String link ="https://www.adidas.ru" + jsProduct.getString("link");;
                boolean isSale = true;
                String image = jsProduct.getJSONObject("image").getString("src");
                String maintenanceGroup = jsProduct.getString("category");
                String description = jsProduct.getString("displayName");
                jsProduct = asyncProduct.execute(id).get();
                String originalPrice = jsProduct.getInt("price") + "₽";
                String currentPrice = jsProduct.getInt("salePrice") + "₽";
                Product product = new Product(globalId, id, productId, maintenanceGroup, isSale, image, currentPrice, originalPrice, description, link);
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
    private class AsyncProductRequest extends AsyncTask<String, Void, JSONObject>{
        @Override
        protected JSONObject doInBackground(String... strings) {
            JsonRequestService jsonRequestService = new JsonRequestService();
            return jsonRequestService.readJsonFromUrl("https://www.adidas.ru/api/search/product/" + strings[0]);
        }
    }
}