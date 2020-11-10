package com.example.outlet.ui.slideshow;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.outlet.models.Product;
import com.example.outlet.services.JsonRequestService;
import com.example.outlet.ui.gallery.GalleryViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SlideshowViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SlideshowViewModel() {
    }

    public LiveData<String> getText() {
        return mText;
    }


    public ArrayList<Product> getProductList() {
        AsyncRequest asyncRequest = new AsyncRequest();
        ArrayList<Product> productsList = new ArrayList<>();
        try {
            JSONObject jsonObject = asyncRequest.execute("all")
                    .get()
                    .getJSONObject("raw")
                    .getJSONObject("itemList");
            JSONArray productsJsonArray = jsonObject.getJSONArray("items");
            for (int i = 0; i < productsJsonArray.length(); i++) {
                AsyncProductRequest asyncProduct = new AsyncProductRequest();
                JSONObject jsProduct = productsJsonArray.getJSONObject(i);
                String id = jsProduct.getString("productId");
                String globalId = "",
                        productId = "";
                boolean isSale = true;
                String image = jsProduct.getJSONObject("image").getString("src");
                String maintenanceGroup = jsProduct.getString("category");
                String description = jsProduct.getString("displayName");
                jsProduct = asyncProduct.execute("one",id).get();
                String originalPrice = jsProduct.getInt("price") + "₽";
                String currentPrice = jsProduct.getInt("salePrice") + "₽";
                Product product = new Product(globalId, id, productId, maintenanceGroup, isSale, image, currentPrice, originalPrice, description);
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
            if(strings[0].equals("all")) {
                System.out.println("Start of async for all product");
                return jsonRequestService.readJsonFromUrl("https://www.adidas.ru/api/plp/content-engine?query=muzhchiny-outlet");
            }
            return jsonRequestService.readJsonFromUrl("https://www.adidas.ru/api/search/product/" + strings[1]);
        }
    }
    private class AsyncProductRequest extends AsyncTask<String, Void, JSONObject>{
        @Override
        protected JSONObject doInBackground(String... strings) {
            JsonRequestService jsonRequestService = new JsonRequestService();
            if(strings[0].equals("all")) {
                System.out.println("Start of async for all product");
                return jsonRequestService.readJsonFromUrl("https://www.adidas.ru/api/plp/content-engine?query=muzhchiny-outlet");
            }
            return jsonRequestService.readJsonFromUrl("https://www.adidas.ru/api/search/product/" + strings[1]);
        }
    }
}